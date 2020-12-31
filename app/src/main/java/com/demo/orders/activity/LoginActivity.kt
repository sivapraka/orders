package com.demo.orders.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.pedant.SweetAlert.SweetAlertDialog
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
import com.demo.orders.helper.CustomAnimation
import com.demo.orders.helper.CustomDialog
import com.demo.orders.helper.UserImage
import com.demo.orders.model.Login
import com.demo.orders.model.MobileModel
import com.demo.orders.session.SessionManagerLogin
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imeiNo: String
    private lateinit var model: String
    private lateinit var newToken: String
    private var commonClass: CommonClass? = null
    private var customDialog: CustomDialog? = null
    private var dbHelper: DBHelper? = null
    var session: SessionManagerLogin? = null
    var userImage: UserImage? = null
    var animation: CustomAnimation? = null
    private var username: String? = null
    private var password: String? = null
    private var editTextPassword: EditText? = null
    private var imageViewUserImage: ImageView? = null
    private var textviewRegisterCompany: TextView? = null
    private var autoCompleteTextViewUserName: AutoCompleteTextView? = null
    private var loginButtonPost: TextView? = null
    var relativeLayoutLogin: RelativeLayout? = null
    private var tinyDB: TinyDB? = null
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao
    lateinit var companyMasterDao: CompanyMasterDao
    lateinit var companyDao: CompanyDao
    lateinit var mobileMenuDao: MobileDao
    lateinit var currencyDao: CurrencyDao
    lateinit var textviewOtp: TextView
    lateinit var textviewForgot: TextView

    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        /* Initialization*/
        commonClass = CommonClass(this)
        customDialog = CustomDialog(this)
        dbHelper = DBHelper(this)
        session = SessionManagerLogin(this)
        userImage = UserImage(this)
        animation = CustomAnimation(this)
        tinyDB = TinyDB(this)
        appDatabase = AppDatabase.getDatabase(this)
        logindao = appDatabase.loginDao()
        companyMasterDao = appDatabase.companyMasterDao()
        mobileMenuDao = appDatabase.mobileMenuDao()
        currencyDao = appDatabase.currencyDao()
        companyDao = appDatabase.companyDao()
        autoCompleteTextViewUserName = findViewById<View>(R.id.autoCompleteTextViewUserName) as AutoCompleteTextView
        editTextPassword = findViewById<View>(R.id.editTextPassword) as EditText
        textviewOtp = findViewById(R.id.textviewOtp)
        textviewForgot = findViewById(R.id.textviewForgot)
        loginButtonPost = findViewById(R.id.buttonLogin)
        relativeLayoutLogin = findViewById(R.id.relativeLayoutLogin)
        imageViewUserImage = findViewById(R.id.imageViewUserImage)
        textviewRegisterCompany = findViewById(R.id.textviewRegisterCompany)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            newToken = instanceIdResult.token
            Log.e("newToken", newToken)
            tinyDB!!.putString(Constants.fcmToken, newToken)
        }
        val bundle = intent.extras
        Log.e(TAG, "onCreate: $bundle")
        if (bundle != null) {
            val logo = bundle.getString(Constants.CompanyLogo)
            Log.e(TAG, "onCreate: " + Utils.adminUrl + logo)
            commonClass!!.loadImage(this, imageViewUserImage, Utils.adminUrl + logo)
        }
        animation!!.setAnimation(relativeLayoutLogin, 0)
        loginButtonPost!!.setOnClickListener(this)
        textviewRegisterCompany!!.setOnClickListener(this)
        textviewOtp.setOnClickListener(this)
        textviewForgot.setOnClickListener(this)
        model = Build.MODEL
        tinyDB!!.putString(Constants.model, model)
        Log.e(TAG, "run: $model = imei")
        if (ActivityCompat.checkSelfPermission(this@LoginActivity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@LoginActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE), SplashActivity.MY_PERMISSIONS_REQUEST_WRITE_FILES)
            return
        }
        imeiNo = commonClass!!.getDeviceId(this@LoginActivity).toString()
        tinyDB!!.putString(Constants.imeiNo, imeiNo)
        Log.e(TAG, "run: $model = imei = $imeiNo")

    }

    fun validation() {
        username = autoCompleteTextViewUserName!!.text.toString()
        password = editTextPassword!!.text.toString()
        if (username != null && username!!.isNotEmpty()) {
            if (password != null && password!!.isNotEmpty()) {
                dbHelper!!.deleteTable(DBHelper.TABLE_USER)
                val loginModel = Login(username!!, password!!)
                if (commonClass!!.isNetworkAvailable(this)) {
                    loginRequest(loginModel)
                } else {
                    commonClass!!.sweetAlertDialog(getString(R.string.network), getString(R.string.network_error), SweetAlertDialog.ERROR_TYPE)
                }
            } else {
                customDialog!!.CustomToast(this@LoginActivity, resources.getString(R.string.please_enter_mpin), Constants.fail)
            }
        } else {
            customDialog!!.CustomToast(this@LoginActivity, resources.getString(R.string.mobile_no), Constants.fail)
        }
    }


    fun validation_mpin(type: String) {
        username = autoCompleteTextViewUserName!!.text.toString()
        if (username != null && username!!.isNotEmpty()) {
            val loginModel = Login(username!!, "", type)
            loginOTP(loginModel, type)
        } else {
            customDialog!!.CustomToast(this@LoginActivity, resources.getString(R.string.mobile_no), Constants.fail)
        }
    }

    fun validation_new_reg(type: String) {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.dialog_reg_number, null)
        alertDialog.setView(view)
        val dialog = alertDialog.create()
        dialog.show()
        //set the  dialog transparent
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window: Window = dialog.window!!
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        val buttonCancel = view.findViewById<View>(R.id.buttonNo) as Button
        val editTexAmount = view.findViewById<View>(R.id.editTexAmount) as EditText
        val buttonOk = view.findViewById<View>(R.id.buttonYes) as Button

        buttonOk.setOnClickListener {
            username = editTexAmount.text.toString()
            if (username != null && username!!.isNotEmpty()) {
                val loginModel = Login(username!!, "", type)
                loginOTP(loginModel, type)
            } else {
                customDialog!!.CustomToast(this@LoginActivity, resources.getString(R.string.mobile_no), Constants.fail)
            }
            //                session.logoutUser();
        }
        buttonCancel.setOnClickListener { dialog.dismiss() }

    }

    private fun loginOTP(newVisitorRegModel: Login, type: String) {
        dialog = customDialog!!.loading(this)
        val jso = Gson().toJson(newVisitorRegModel)
        Log.e(TAG, " json : " + jso.toString())
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.sendOTP(newVisitorRegModel)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<StatusResponse> {
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dialog.dismiss()
            }

            override fun onResponse(call: Call<StatusResponse>, response: retrofit2.Response<StatusResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        dialog.dismiss()
                        val data = Bundle()
                        data.putString(Constants.customer, username)
                        data.putString(Constants.type, type)
                        commonClass!!.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.SUCCESS_TYPE)

                                } else {
                        dialog.dismiss()
                        if (type.equals("register")) {
                            commonClass!!.sweetAlertDialog(getString(R.string.register_msg), message!!, SweetAlertDialog.ERROR_TYPE)
                        } else {
                            commonClass!!.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.ERROR_TYPE)
                        }
                    }
                }
            }
        })
    }

    private fun loginRequest(newVisitorRegModel: Login) {
        dialog = customDialog!!.loading(this)
        val jso = Gson().toJson(newVisitorRegModel)
        Log.e(TAG, " json : " + jso.toString())
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.login(newVisitorRegModel)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<LoginResponseModel> {
            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dialog.dismiss()
            }

            override fun onResponse(call: Call<LoginResponseModel>, response: retrofit2.Response<LoginResponseModel>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        if (response.body() != null && response.body()!!.data != null) {
                            logindao.delete()
                            val login = response.body()!!.data as ArrayList<LoginTable>
                            val mobile = response.body()!!.mobile_menu as ArrayList<MobileMenuTable>
                            val currency = response.body()!!.currency as ArrayList<CurrencyTable>
                            val company = response.body()!!.company as ArrayList<CompanyMasterTable>
                            companyMasterDao.insert(company)
                            logindao.insert(login)
                            val mobileModel = MobileModel(companyDao.allItems.customerId!!, logindao.allItems.customerCode!!, newToken, imeiNo, model)
                            Log.e(TAG, "onResponse:model: " + Gson().toJson(mobileModel))
                            mobileMenuDao.delete()
                            mobileMenuDao.insert(mobile)
                            currencyDao.delete()
                            currencyDao.insert(currency)
//                            dialog.dismiss()
                            mobileMenu(mobileModel)
//                            commonClass!!.intentFinish(this@LoginActivity, MainActivity::class.java)
                        }
                    } else {
                        dialog.dismiss()
                        commonClass!!.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.ERROR_TYPE)
                    }
                }else{
                    dialog.dismiss()
                }
            }
        })
    }

    private fun mobileMenu(newVisitorRegModel: MobileModel) {
        val jso = Gson().toJson(newVisitorRegModel)
        Log.e(TAG, " json : " + jso.toString())
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.mobileModel(newVisitorRegModel)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<StatusResponse> {
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dialog.dismiss()
            }

            override fun onResponse(call: Call<StatusResponse>, response: retrofit2.Response<StatusResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        dialog.dismiss()
                        commonClass!!.intentFinish(this@LoginActivity, MainActivity::class.java)
                    } else {
                        dialog.dismiss()
                        commonClass!!.sweetAlertDialog(getString(R.string.login), message!!, SweetAlertDialog.ERROR_TYPE)
                    }
                }
            }
        })
    }


    public override fun onResume() {
        super.onResume()
        dbHelper = DBHelper(this)
        userImage = UserImage(this)
        commonClass = CommonClass(this)
        animation = CustomAnimation(this)
        customDialog = CustomDialog(this)
        isNetwork
    }

    public override fun onPause() {
        super.onPause()
    }

    /*Set error Color*/
    @Throws(NullPointerException::class)
    fun getColor(v: View): View {
        v.background.setColorFilter(resources.getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP)
        return v
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.buttonLogin -> validation()
            R.id.textviewOtp -> validation_mpin("forgot")
            R.id.textviewForgot -> validation_mpin("otp")
            R.id.textviewRegisterCompany -> validation_new_reg("register")
        }
    }//Check  the Network is available or Not

    //Check Network Available or Not
    val isNetwork: Boolean
        get() {
            val isNetwork: Boolean
            //Check  the Network is available or Not
            if (!commonClass!!.isNetworkAvailable(this)) {
                if (relativeLayoutLogin == null) {
                    relativeLayoutLogin = findViewById(R.id.relativeLayoutLogin)
                }
                animation!!.customSnackBar(relativeLayoutLogin, Constants.errorNetwork)
                isNetwork = false
            } else {
                isNetwork = true
            }
            return isNetwork
        }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
    }
}