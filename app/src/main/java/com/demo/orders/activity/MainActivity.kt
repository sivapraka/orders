package com.demo.orders.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.gson.Gson
import com.demo.orders.R
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DBHelper
import com.demo.orders.db.DatabaseList
import com.demo.orders.db.TinyDB
import com.demo.orders.db.dao.CompanyDao
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.dao.MobileDao
import com.demo.orders.db.table.MobileMenuTable
import com.demo.orders.db.table.StatusResponse
import com.demo.orders.fragment.*
import com.demo.orders.fragment.FragmentDrawer.FragmentDrawerListener
import com.demo.orders.helper.*
import com.demo.orders.model.CustomersDataSource
import com.demo.orders.model.MobileModel
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import com.demo.orders.session.SessionManagerLogin
import com.demo.orders.util.Constants
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), FragmentDrawerListener {
    private lateinit var newToken: String
    private lateinit var imeiNo: String
    private lateinit var model: String
    var mToolbar: Toolbar? = null
    var commonClass: CommonClass? = null
    var databaseList: DatabaseList? = null
    var session: SessionManagerLogin? = null
    var animation: CustomAnimation? = null
    private var drawerFragment: FragmentDrawer? = null
    private var drawerLayout: DrawerLayout? = null
    var userImage: UserImage? = null
    var customDialog: CustomDialog? = null
    var dbHelper: DBHelper? = null
    private var tinyDB: TinyDB? = null
    internal lateinit var appDatabase: AppDatabase
    lateinit var invoiceDAO: InvoiceDAO
    lateinit var mobileMenuDao: MobileDao
    lateinit var loginDao: LoginDao
    lateinit var companyDao: CompanyDao
    var customersResultDataSource: CustomersDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        session = SessionManagerLogin(this)
        commonClass = CommonClass(this)
        databaseList = DatabaseList(this)
        animation = CustomAnimation(this)
        userImage = UserImage(this)
        customDialog = CustomDialog(this)
        dbHelper = DBHelper(this)
        tinyDB = TinyDB(this)
        appDatabase = AppDatabase.getDatabase(this)
        invoiceDAO = appDatabase.invoiceDAO()
        mobileMenuDao = appDatabase.mobileMenuDao()
        loginDao = appDatabase.loginDao()
        companyDao = appDatabase.companyDao()

        newToken = tinyDB!!.getString(Constants.fcmToken)
        model = tinyDB!!.getString(Constants.model)
        imeiNo = tinyDB!!.getString(Constants.imeiNo)
        val bundle = intent.extras
        if (bundle != null) {
            var fragment: Fragment? = null
            var title = ""
            val data = bundle.getString(APIKey.KEY_DATA)
            var screen = ""
            try {
                val datajson = JSONObject(data!!)
                screen = datajson.getString(Constants.screenName)
            } catch (e: Exception) {
                screen = ""
            }
            Log.e(TAG, "onCreate: screen name : " + screen)
            if (screen != null && screen.length > 0) {
                var fragment: Fragment? = null
                var title = getString(R.string.app_name)
                when (screen) {
                    Constants.menu_home -> {
                        fragment = RouteMenuFragment()
                        title = getString(R.string.title_home)
                    }

                    Constants.menu_orders -> {
                        fragment = OrderEnquiryListFragment()
                        title = "Order Query"
                    }

                    Constants.menu_translation -> {
                        val intent = Intent(this@MainActivity, LanguageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    Constants.menu_logout -> {
                        showLogout()
                    }
                }
                fragment?.let { setFragment(it, title, 0) }
            }
        }

        var loginlist = loginDao.allItems
        customersResultDataSource = CustomersDataSource(loginlist.customerCode, loginlist.customerName, loginlist.phoneNum, loginlist.createdOn,
                loginlist.mail, "", "", loginlist.street, loginlist.city,
                loginlist.region, loginlist.postalCode, "", null, "",
                loginlist.companyCode, loginlist.plantCode, loginlist.salesorgCode, loginlist.salesareaCode, loginlist.schemeDiscount, loginlist.priceCode, loginlist.materialCode,
                loginlist.loyalty, loginlist.lastModified)
        // display the first navigation drawer view on app launch
        val languageCode = tinyDB!!.getString(Constants.languagecode)
        var rowListItem: List<MobileMenuTable>
        if (languageCode != "") {
            rowListItem = mobileMenuDao.menu(Constants.menu, languageCode)
        } else {
            rowListItem = mobileMenuDao.menu(Constants.menu, Constants.languagecode)
        }
        if (rowListItem.isNotEmpty()) displayView(0, rowListItem)
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as FragmentDrawer?
        drawerFragment!!.setUp(R.id.fragment_navigation_drawer, (findViewById<View>(R.id.drawer_layout) as DrawerLayout), mToolbar!!)
        drawerFragment!!.setDrawerListener(this)
        drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout

        /* Hide the Navigation Drawer View */
        // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        /* Show the Navigation Drawer View*/
        drawerLayout!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        //Check  the Network is available or Not
        if (!commonClass!!.isNetworkAvailable(this)) {
            animation!!.customSnackBar(drawerLayout, Constants.errorNetwork)
        }
    }

    override fun onDrawerItemSelected(view: View, position: Int, rowlist: List<MobileMenuTable>) {
        displayView(position, rowlist)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        title = "Bills"
        // Result Code is -1 send from Payumoney activity
        Log.d(TAG, "request code $requestCode resultcode $resultCode")
      }

    /* Display the position of the Adapter*/
    private fun displayView(position: Int, rowlist: List<MobileMenuTable>) {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (rowlist[position].screenName) {
            Constants.menu_home -> {
                fragment = RouteMenuFragment()
                title = getString(R.string.title_home)
            }
            Constants.menu_orders -> {
                fragment = OrderEnquiryListFragment()
                title = "Order Query"
            }
            Constants.menu_translation -> {
                val intent = Intent(this@MainActivity, LanguageActivity::class.java)
                startActivity(intent)
            }
            Constants.menu_logout -> {
                showLogout()
            }
        }
        fragment?.let { setFragment(it, title, 0) }
    }

    //Show Logout Alert Dialog
    fun showLogout() {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_logout, null)
        alertDialog.setView(view)
        val dialog = alertDialog.create()
        dialog.show()
        /* set the  dialog transparent */
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window: Window = dialog.window
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        val buttonCancel = view.findViewById<View>(R.id.buttonCancel) as Button
        val buttonOk = view.findViewById<View>(R.id.buttonOk) as Button

        buttonOk.setOnClickListener {

            if (dialog.isShowing) {
                dialog.dismiss()
            }
            val mobileModel = MobileModel(companyDao.allItems.customerId!!, loginDao.allItems.customerCode!!, newToken, imeiNo, model)
            logout(mobileModel)
        }
        buttonCancel.setOnClickListener { dialog.dismiss() }
    }

    private fun logout(newVisitorRegModel: MobileModel) {
        val jso = Gson().toJson(newVisitorRegModel)
        Log.e(TAG, " json : " + jso.toString())
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.logout(newVisitorRegModel)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<StatusResponse> {
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
            }

            override fun onResponse(call: Call<StatusResponse>, response: retrofit2.Response<StatusResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        loginDao.delete()
                        commonClass!!.intentFinish(this@MainActivity, SplashActivity::class.java)
                    } else {
                        commonClass!!.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.ERROR_TYPE)
                    }
                }
            }
        })
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment?, title: String?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_body, fragment!!, title)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        // set the toolbar title
        supportActionBar!!.title = title
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, title: String, customerCount: Int) {
        val t = supportFragmentManager!!.beginTransaction()
        val bundle = Bundle()
        bundle.putString(Constants.title, title)
        bundle.putInt(Constants.count, customerCount)
        bundle.putSerializable(Constants.list, customersResultDataSource)
        fragment.arguments = bundle
        t.replace(R.id.container_body, fragment)
        t.addToBackStack(null)
        t.commit()
        // set the toolbar title
    }

    override fun onResume() {
        super.onResume()
        commonClass = CommonClass(this)
        databaseList = DatabaseList(this)
        animation = CustomAnimation(this)
        session = SessionManagerLogin(this)
        userImage = UserImage(this)
        customDialog = CustomDialog(this)
        dbHelper = DBHelper(this)
        //Check  the Network is available or Not
        if (!commonClass!!.isNetworkAvailable(this)) {
            if (drawerLayout == null) {
                drawerLayout = findViewById(R.id.drawer_layout)
            }
            animation!!.customSnackBar(drawerLayout, Constants.errorNetwork)
        }
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        //Checking for fragment count on backstack
        if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}