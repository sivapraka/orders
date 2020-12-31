package com.demo.orders.fragment

import android.app.job.JobScheduler
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.demo.orders.R
import com.demo.orders.activity.LanguageActivity
import com.demo.orders.activity.MainActivity
import com.demo.orders.activity.SplashActivity
import com.demo.orders.adapter.MenuAdapter
import com.demo.orders.db.*
import com.demo.orders.db.dao.CompanyDao
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.dao.MobileDao
import com.demo.orders.db.table.InvoiceModel
import com.demo.orders.db.table.MobileMenuTable
import com.demo.orders.db.table.StatusResponse
import com.demo.orders.helper.*
import com.demo.orders.language.LanguageConfig
import com.demo.orders.model.CustomersDataSource
import com.demo.orders.model.MobileModel
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import com.google.gson.Gson
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set

class RouteMenuFragment : Fragment(), MenuAdapter.ListAdapterListener {

    private lateinit var newToken: String
    private lateinit var imeiNo: String
    private lateinit var model: String
    private lateinit var textCartItemCount: TextView
    lateinit var viewAllTextView: TextView
    lateinit var survey_recyclerView: RecyclerView
    private var lLayout: GridLayoutManager? = null
    private val TAG = "HomeFragment"
    internal lateinit var commonClass: CommonClass
    internal lateinit var staticData: StaticData
    internal lateinit var bitmapUtility: BitmapUtility
    internal lateinit var dbHelper: DBHelper
    internal lateinit var databaseList: DatabaseList
    internal lateinit var tinyDB: TinyDB
    internal lateinit var languageConfig: LanguageConfig
    var isBackPressed = true
    var customersResultDataSource: CustomersDataSource? = null

    internal var customcount: Boolean = false
    internal var doubleBackToExitPressedOnce = false
    private var customDialog: CustomDialog? = null
    internal lateinit var userImage: UserImage
    internal lateinit var jsonResult: JSONResult
    internal lateinit var dialog1: AlertDialog
    private var supervisorCode: String? = null
    var list: ArrayList<InvoiceModel> = ArrayList()
    lateinit var dataSource: InvoiceModel
    internal lateinit var appDatabase: AppDatabase
    lateinit var messageRelativeLayout: RelativeLayout
    lateinit var swipeReferesh: SwipeRefreshLayout
    private var swipeRefreshCount = 0

    lateinit var loginDao: LoginDao
    private var jobScheduler: JobScheduler? = null
    lateinit var mobileMenuDao: MobileDao

    lateinit var rowListItem: List<MobileMenuTable>

    lateinit var sliderView: SliderView
    lateinit var companyDao: CompanyDao

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonClass = CommonClass(activity!!)
        staticData = StaticData(activity)
        languageConfig = LanguageConfig(activity)
        dbHelper = DBHelper(activity)
        databaseList = DatabaseList(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)

        companyDao = appDatabase.companyDao()


        tinyDB = TinyDB(activity)

        newToken = tinyDB.getString(Constants.fcmToken)
        model = tinyDB.getString(Constants.model)
        imeiNo = tinyDB.getString(Constants.imeiNo)

        setHasOptionsMenu(true)
        jobScheduler = activity!!.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        supervisorCode = dbHelper.getSpcName(DBHelper.TABLE_USER, DBHelper.COLUMN_SUPERVISOR_CODE)
        customcount = dbHelper.check_data("customer_accounts")
    }

    protected fun setFragment(fragment: Fragment, title: String) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container_body, fragment, title)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu, container, false)
        customDialog = CustomDialog(activity)
        dbHelper = DBHelper(activity)
        commonClass = CommonClass(activity!!)
        jsonResult = JSONResult(activity)
        tinyDB = TinyDB(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)
        loginDao = appDatabase.loginDao()
        mobileMenuDao = appDatabase.mobileMenuDao()
        bitmapUtility = BitmapUtility(activity)
        userImage = UserImage(activity)
        /* Set the Title*/
        val activity = activity as MainActivity?
        var actionBar: ActionBar? = null
        if (activity != null) {
            actionBar = activity.supportActionBar
        }
        if (actionBar != null) {
            actionBar.title = resources.getString(R.string.home)
        }
        staticData = StaticData(getActivity())
        val languageCode = tinyDB.getString(Constants.languagecode)
        Log.e("TAG", "languageCode : " + languageCode)

        if (languageCode != "") {
            rowListItem = mobileMenuDao.menu(Constants.menu, languageCode)
        } else {
            rowListItem = mobileMenuDao.menu(Constants.menu, Constants.languagecode)
        }
        lLayout = GridLayoutManager(getActivity(), 3)
        //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        val rView = rootView.findViewById<View>(R.id.recyclerViewMenu) as RecyclerView
        viewAllTextView = rootView.findViewById<View>(R.id.viewAllTextView) as TextView
        survey_recyclerView = rootView.findViewById<View>(R.id.survive_recyclerView) as RecyclerView
        messageRelativeLayout = rootView.findViewById(R.id.messageRelativeLayout)
        swipeReferesh = rootView.findViewById(R.id.swipeReferesh)

        survey_recyclerView.layoutManager = LinearLayoutManager(activity)
        survey_recyclerView.setHasFixedSize(true)

        sliderView = rootView.findViewById(R.id.imageSlider)

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 4 //set scroll delay in seconds :
        sliderView.startAutoCycle()

        rView.setHasFixedSize(true)
        rView.layoutManager = lLayout
        rowListItem = rowListItem.filter { mobileMenuTable -> mobileMenuTable.screenName != Constants.menu_home }
        val adapter = MenuAdapter(rowListItem, getActivity()!!, this)
        rView.adapter = adapter
        var loginlist = loginDao.allItems
        Constants.salesAreaCode = loginDao.allItems.salesareaCode
        customersResultDataSource = CustomersDataSource(loginlist.customerCode, loginlist.customerName, loginlist.phoneNum, loginlist.createdOn,
                loginlist.mail, "", "", loginlist.street, loginlist.city,
                loginlist.region, loginlist.postalCode, "", null, "",
                loginlist.companyCode, loginlist.plantCode, loginlist.salesorgCode, loginlist.salesareaCode, loginlist.schemeDiscount, loginlist.priceCode, loginlist.materialCode,
                loginlist.loyalty, loginlist.lastModified)
        if (Constants.isIsHomeLoading()) {
            getBillHeader(activity, Utils.KEY_BASEURL + Utils.KEY_BILL_HISTORY)
        }

        swipeReferesh.setOnRefreshListener {
            swipeReferesh.isRefreshing = true
            if (commonClass.isNetworkAvailable(activity!!)) {
                list.clear()
                swipeRefreshCount = 1
                getBillHeader(activity, Utils.KEY_BASEURL + Utils.KEY_BILL_HISTORY)
            }
        }
        handleBackPress(rootView)
        // onBackPressed(rootView);
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languageConfig.setLanguage(tinyDB.getString(Constants.languagecode))
    }

    override fun onClickButton(mkey: MobileMenuTable?, position: Int) {
        Log.e(TAG, "onAdapterItemClick: $position")
        var fragment: Fragment? = null
        var title: String? = null
        val cutomerCount = 0
        Log.e("TAG", " title : " + mkey!!.screenName)
        when (mkey.screenName) {
            Constants.menu_logout -> {
                showLogout()
                title = "Logout"
            }
            Constants.menu_orders -> {
                fragment = OrderEnquiryListFragment()
                title = "Orders"
            }

            Constants.menu_translation -> {
                val intent = Intent(activity, LanguageActivity::class.java)
                startActivity(intent)
            }
        }
        if (fragment != null) {
            setFragment(fragment, title!!, cutomerCount)
        }
    }


    //Show Logout Alert Dialog
    fun showLogout() {
        val alertDialog = AlertDialog.Builder(activity!!)
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
        val imageViewUserImage = view.findViewById<View>(R.id.imageViewUserImage) as ImageView
        //Sales Man Information
        val userDataSources = databaseList.userList
        if (userDataSources.size > 0) {
            val userImage = userDataSources[0].userBitmapImage
            if (userImage != null) {
                imageViewUserImage.setImageBitmap(userImage)
            } else {
                imageViewUserImage.setImageResource(R.drawable.profile)
            }
        }
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
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB.getString(Constants.baseURL))
        val listCall = service.logout(newVisitorRegModel)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<StatusResponse> {
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                //         dialog.dismiss()
            }

            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        // dialog.dismiss()
                        Constants.setIsHomeLoading(true)
                        loginDao.delete()
                        commonClass.intentFinish(activity!!, SplashActivity::class.java)
                    } else {
                        //   dialog.dismiss()
                        commonClass.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.ERROR_TYPE)
                    }
                }
            }
        })
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, title: String, customerCount: Int) {
        val t = this.fragmentManager!!.beginTransaction()
        val bundle = Bundle()
        bundle.putString(Constants.title, title)
        bundle.putInt(Constants.count, customerCount)
        bundle.putSerializable(Constants.list, customersResultDataSource)
        fragment.arguments = bundle
        t.replace(R.id.container_body, fragment)
        t.addToBackStack(null)
        t.commit()
        // set the toolbar title
        activity!!.title = title
    }

    private fun handleBackPress(view: View) {

        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!isBackPressed) {
                    if (doubleBackToExitPressedOnce) {
                        activity!!.finishAffinity()
                    }
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(activity, "Tap again to exit", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
                    isBackPressed = true
                } else if (isBackPressed) {
                    isBackPressed = false
                }
                true
            }
            false
        }
    }

    fun onBackPressed(view: View) {
        if (doubleBackToExitPressedOnce) {
            // getActivity().finish();
            // super.onBackPressed(view);
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(activity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        activity!!.finish()
    }

    fun getSchemeMaster(context: Context?, URL: String) {
        Log.e(TAG, "getSchemeMaster: $URL")

        val postRequest = object : StringRequest(Request.Method.GET, URL, { response ->
            try {

                val body = JSONArray(response)
                dbHelper.deleteTable("scheme_master")
                for (i in 0 until body.length()) {
                    val data = body.getJSONObject(i)
                    val scheme_code = data.getString(APIKey.KEY_SCHEME_CODE)
                    val scheme_name = data.getString(APIKey.KEY_SCHEME_NAME)
                    val scheme_type = data.getString(APIKey.KEY_SCHEME_TYPE)
                    val material_code1 = data.getString(APIKey.KEY_MATERIAL_CODE1)
                    val material_code2 = data.getString(APIKey.KEY_MATERIAL_CODE2)
                    val quantity = data.getString(APIKey.KEY_QUANTITY)
                    val free_quantity = data.getString(APIKey.KEY_FREE_QUANTITY)
                    val scheme = data.getString(APIKey.KEY_SCHEME)
                    val scheme_from = data.getString(APIKey.KEY_SCHEME_FROM)
                    val scheme_till = data.getString(APIKey.KEY_SCHEME_TILL)
                    val last_modified = data.getString(APIKey.KEY_LAST_MODIFIED)
                    //storing memberId
                    dbHelper.insertSchemeMaster(scheme_code, scheme_name, scheme_type, material_code1, material_code2, quantity, free_quantity, scheme,
                            scheme_from, scheme_till, last_modified)
                }
                getDiscountMaster(activity, Utils.KEY_BASEURL + Utils.KEY_DISCOUNT)
                //                }
            } catch (e: JSONException) {
                e.localizedMessage
                getDiscountMaster(activity, Utils.KEY_BASEURL + Utils.KEY_DISCOUNT)
            }


        }, { error ->
            VolleyLog.e("Error : ", error.localizedMessage)
            getDiscountMaster(activity, Utils.KEY_BASEURL + Utils.KEY_DISCOUNT)
        }) {
            override fun getParams(): Map<String, String> {
//                params.put(APIKey.phone, mobileNo);
                return HashMap()
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass.auth()
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                params["Authorization"] = auth
                return params
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        // add it to the RequestQueue
        val queue = Volley.newRequestQueue(context!!)
        queue.add(postRequest)

    }

    fun getDiscountMaster(context: Context?, URL: String) {
        Log.e(TAG, "getDiscountMaster: $URL")

        val postRequest = object : StringRequest(Request.Method.GET, URL, { response ->
            try {
                val body = JSONArray(response)
                dbHelper.deleteTable("discount_master")
                for (i in 0 until body.length()) {
                    val data = body.getJSONObject(i)
                    val discount_code = data.getString(APIKey.KEY_DISCOUNT_CODE)
                    val material_code = data.getString(APIKey.KEY_MATERIAL_CODE)
                    val from_quantity = data.getString(APIKey.KEY_FROM_QUANTITY)
                    val to_quantity = data.getString(APIKey.KEY_TO_QUANTITY)
                    val discount = data.getString(APIKey.KEY_DISCOUNT)
                    val discount_wot = data.getString(APIKey.KEY_DISCOUNT_WOT)
                    val discount_from = data.getString(APIKey.KEY_DISCOUNT_FROM)
                    val discount_till = data.getString(APIKey.KEY_DISCOUNT_TILL)
                    val discount_type = data.getString(APIKey.KEY_DISCOUNT_TYPE)
                    val last_modified = data.getString(APIKey.KEY_LAST_MODIFIED)
                    //storing memberId
                    dbHelper.insertDiscountMaster(discount_code, material_code, from_quantity, to_quantity, discount, discount_wot, discount_from, discount_till,
                            discount_type, last_modified)
                }

                //                }
            } catch (e: JSONException) {
                e.localizedMessage

            }


        }, { error ->
            VolleyLog.e("Error : ", error.localizedMessage)

        }) {
            override fun getParams(): Map<String, String> {
//                params.put(APIKey.phone, mobileNo);
                return HashMap()
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass.auth()
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                params["Authorization"] = auth
                return params
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        // add it to the RequestQueue
        val queue = Volley.newRequestQueue(context!!)
        queue.add(postRequest)

    }


    fun getBillHeader(context: Context?, URL: String) {
        Log.e(TAG, "getBillHeader: $URL")
        if (!swipeReferesh.isRefreshing) {
            dialog1 = customDialog!!.loading(context)
        }
        val postRequest = object : StringRequest(Method.GET, URL, { response ->
            try {

                val body = JSONArray(response)
                dbHelper.deleteTable("bill_header")
                for (i in 0 until body.length()) {
                    val data = body.getJSONObject(i)
                    val id = data.getString(APIKey.KEY_ID)
                    val header1 = data.getString(APIKey.KEY_HEADER1)
                    val header2 = data.getString(APIKey.KEY_HEADER2)
                    val header3 = data.getString(APIKey.KEY_HEADER3)
                    val last_modified = data.getString(APIKey.KEY_LAST_MODIFIED)
                    //storing memberId
                    dbHelper.insertBillHeader(id, header1, header2, header3, last_modified)
                    val currentDate = commonClass.getDate(Constants.inputFormat3)
                    // Insert Company Settings;
                    dbHelper.insertUpdateCompany(id, header1, "", "", header3, currentDate,
                            header2, "", "", "", "TN", "", "", null)
                }
                getSchemeMaster(activity, Utils.KEY_BASEURL + Utils.KEY_SCHEME)
            } catch (e: JSONException) {
                e.localizedMessage
                getSchemeMaster(activity, Utils.KEY_BASEURL + Utils.KEY_SCHEME)
            }
        }, { error ->
            VolleyLog.e("Error : ", error.localizedMessage)
            getSchemeMaster(activity, Utils.KEY_BASEURL + Utils.KEY_SCHEME)
        }) {
            override fun getParams(): Map<String, String> {
                return HashMap()
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass.auth()
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                params["Authorization"] = auth
                return params
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        // add it to the RequestQueue
        val queue = Volley.newRequestQueue(context!!)
        queue.add(postRequest)
    }


    fun getPrice(context: Context, URL: String) {
        Log.e(TAG, "getPrice: $URL")

        val postRequest = object : StringRequest(Method.GET, URL, { response ->
            try {
                val body = JSONArray(response)
                dbHelper.deleteTable("price_master")
                if (body.length() > 0) {
                }
                for (i in 0 until body.length()) {
                    //    textViewPriceCount.text = i.toString()
                    val data = body.getJSONObject(i)
                    val id = data.getString(APIKey.KEY_ID)
                    val company_code = data.getString(APIKey.KEY_COMPANY_CODE)
                    val plant_code = data.getString(APIKey.KEY_PLANT_CODE)
                    val salesorg_code = data.getString(APIKey.KEY_SALESORG_CODE)
                    val material_code = data.getString(APIKey.KEY_MATERIAL_CODE)
                    val price_list = data.getString(APIKey.KEY_PRICE_LIST)
                    val amount = data.getString(APIKey.KEY_AMOUNT)
                    val tax = data.getString(APIKey.KEY_TAX)
                    val net_price = data.getString(APIKey.KEY_NET_PRICE)
                    val valid_from = data.getString(APIKey.KEY_VALID_FROM)
                    val valid_to = data.getString(APIKey.KEY_VALID_TO)
                    val last_modified = data.getString(APIKey.KEY_LAST_MODIFIED)
                    val taxAmount = data.getString(APIKey.KEY_TAX_VALUE)
                    //storing memberId
                    dbHelper.insertPriceMaster(id, company_code, plant_code, salesorg_code,
                            material_code, price_list, amount, tax,
                            net_price, valid_from, valid_to, last_modified, taxAmount)
                }
                //                }
                if (swipeReferesh.isRefreshing) {
                    swipeReferesh.isRefreshing = false
                    swipeRefreshCount = 0
                } else {
                    Constants.setIsHomeLoading(false)
                    dialog1.dismiss()
                }
            } catch (e: JSONException) {
                e.localizedMessage
                if (swipeReferesh.isRefreshing) {
                    swipeReferesh.isRefreshing = false
                    swipeRefreshCount = 0
                } else {
                    Constants.setIsHomeLoading(false)
                    dialog1.dismiss()
                }
            }


        }, { error ->
            if (swipeReferesh.isRefreshing) {
                swipeReferesh.isRefreshing = false
                swipeRefreshCount = 0
            } else {
                Constants.setIsHomeLoading(false)
                dialog1.dismiss()
            }
            VolleyLog.e("Error : ", error.localizedMessage)
        }) {
            override fun getParams(): Map<String, String> {
//                params.put(APIKey.phone, mobileNo);
                return HashMap()
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass.auth()
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                params["Authorization"] = auth
                return params
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        // add it to the RequestQueue
        val queue = Volley.newRequestQueue(context)
        queue.add(postRequest)

    }


}// Required empty public constructor

