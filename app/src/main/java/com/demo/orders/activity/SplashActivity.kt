package com.demo.orders.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.VolleyLog
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import com.demo.orders.R
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DBHelper
import com.demo.orders.db.TinyDB
import com.demo.orders.db.dao.*
import com.demo.orders.db.table.*
import com.demo.orders.db.table.response.LoginResponseModel
import com.demo.orders.helper.CommonClass
import com.demo.orders.helper.CustomDialog
import com.demo.orders.language.LanguageConfig
import com.demo.orders.model.Login
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import com.demo.orders.session.SessionManagerLogin
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class SplashActivity : AppCompatActivity() {
    private val TAG: String = SplashActivity::class.java.simpleName
    var commonClass: CommonClass? = null
    var session: SessionManagerLogin? = null
    var languageConfig: LanguageConfig? = null
    var tinyDB: TinyDB? = null
    var dbHelper: DBHelper? = null
    var dialog: AlertDialog? = null
    private var customDialog: CustomDialog? = null
    lateinit var alertDialog: AlertDialog
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao
    lateinit var companyMasterDao: CompanyMasterDao
    lateinit var mobileMenuDao: MobileDao
    lateinit var currencyDao: CurrencyDao
    lateinit var customerDao: CustomerDao
    lateinit var companyDao: CompanyDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        /* Initialization*/
        commonClass = CommonClass(this)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val newToken: String = instanceIdResult.token
            Log.e("newToken", newToken)
        }
        tinyDB = TinyDB(this)
        dbHelper = DBHelper(this)
        languageConfig = LanguageConfig(this)
        customDialog = CustomDialog(this)
        session = SessionManagerLogin(applicationContext)
        appDatabase = AppDatabase.getDatabase(this)
        logindao = appDatabase.loginDao()
        companyMasterDao = appDatabase.companyMasterDao()
        mobileMenuDao = appDatabase.mobileMenuDao()
        currencyDao = appDatabase.currencyDao()
        customerDao = appDatabase.customerDao()
        companyDao = appDatabase.companyDao()
        //Start the animation
        languageConfig!!.setLanguage(tinyDB!!.getString(Constants.languagecode))
        val baseURL = tinyDB!!.getString(Constants.baseURL)
        Utils.KEY_BASEURL = baseURL
        checkAppPermissions()
    }

    fun enableGps() {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && commonClass!!.isLocationEnabled(this@SplashActivity)) {
            commonClass!!.displayLocationSettingsRequest()
        } else {
            goNext()
        }
    }

    private fun checkAppPermissions() {
        if ((ContextCompat.checkSelfPermission(this,
                        Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.INTERNET) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_NETWORK_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_PHONE_STATE)) {
                enableGps()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE
                ),
                        MY_PERMISSIONS_REQUEST_WRITE_FILES)
            }
        } else {
            enableGps()
        }
    }

    private fun bindLogo() {
        // Start animating the image
        val splash = findViewById<View>(R.id.splash) as ImageView
        val animation1 = AlphaAnimation(0.2f, 1.0f)
        animation1.duration = 700
        val animation2 = AlphaAnimation(1.0f, 0.2f)
        animation2.duration = 700
        //animation1 AnimationListener
        animation1.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                // start animation2 when animation1 ends (continue)
                splash.startAnimation(animation2)
            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationStart(arg0: Animation) {}
        })

        //animation2 AnimationListener
        animation2.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                // start animation1 when animation2 ends (repeat)
                splash.startAnimation(animation1)
            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationStart(arg0: Animation) {}
        })
        splash.startAnimation(animation1)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FILES) {
            Log.e(VolleyLog.TAG, "onRequestPermissionsResult: " + grantResults.size)
            val count = 0
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableGps()
            }
        } else {
            val builder = AlertDialog.Builder(this@SplashActivity)
            builder.setMessage("App required some permission please enable it")
                    .setPositiveButton("Yes") { dialog, id -> // FIRE ZE MISSILES!
                        openPermissionScreen()
                    }
                    .setNegativeButton("Cancel") { dialog, id -> // User cancelled the dialog
                        dialog.dismiss()
                        finish()
                    }
            dialog = builder.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "Location has turned on ", Toast.LENGTH_SHORT).show()
            goNext()
        } else {
            // User did not enable Bluetooth or an error occurred
            Log.d(VolleyLog.TAG, "Location not enabled")
            enableGps()
        }
    }

    fun goNext() {
        bindLogo()
        if (!commonClass!!.isNetworkAvailable(this@SplashActivity)) {
            customDialog!!.CustomToast(this@SplashActivity, Constants.errorNetwork, Constants.fail)
        } else {
            val task: TimerTask = object : TimerTask() {
                override fun run() {
                    try {
                        val cursor = logindao.allItems
                        val companySource = companyDao.allItems
                        if (cursor.id != null) {                        /* Move to Main Activity if the user_not_used is NotLogin*/
                            if (ActivityCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(this@SplashActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_PHONE_STATE), MY_PERMISSIONS_REQUEST_WRITE_FILES)
                                return
                            }
                            validUser(logindao.allItems.id!!, companySource.customerId)
                        } else {

                           commonClass!!.activitySend(this@SplashActivity, LoginActivity::class.java)
                        }
                    } catch (e: NullPointerException) {
                        commonClass!!.activitySend(this@SplashActivity, LoginActivity::class.java)
                    }
                }
            }
            // Show splash screen for 3 seconds
            Timer().schedule(task, 2000)
        }
    }

    fun openPermissionScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", this@SplashActivity.packageName, null))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    // check valid user
    private fun validUser(id: String, companyid: String?) {

        Log.e(TAG, "listCall  id : " + id)
        Log.e(TAG, "listCall  companyid : " + companyid)
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.adminUrl)
        val listCall = service.validUser(id, companyid!!)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<VerifyUserRsponse> {
            override fun onFailure(call: Call<VerifyUserRsponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dbHelper!!.deleteTable(DBHelper.TABLE_USER)
                commonClass!!.activitySend(this@SplashActivity, LoginActivity::class.java)
            }

            override fun onResponse(call: Call<VerifyUserRsponse>, response: retrofit2.Response<VerifyUserRsponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful && response.body() != null) {
                    val status = response.body()!!.status
                    Handler().postDelayed({
                        if (status!!) {
                            mobileMenuDao.delete()
                            mobileMenuDao.insert(response.body()!!.data as ArrayList<MobileMenuTable>)
                            customerDao.delete()
                            customerDao.insert(response.body()!!.customer as ArrayList<Customer>)
                            customerDao.delete()
                            companyDao.insert(response.body()!!.company as ArrayList<Company>)
                            commonClass!!.activitySend(this@SplashActivity, MainActivity::class.java)
                        } else {
                            logindao.delete()
                            commonClass!!.intentFinish(this@SplashActivity, LoginActivity::class.java)
                        }
                    }, 1000)
                } else {
                    dbHelper!!.deleteTable(DBHelper.TABLE_USER)
                    commonClass!!.activitySend(this@SplashActivity, LoginActivity::class.java)
                }
            }

        })
    }
    companion object {
        const val MY_PERMISSIONS_REQUEST_WRITE_FILES = 102
    }
}