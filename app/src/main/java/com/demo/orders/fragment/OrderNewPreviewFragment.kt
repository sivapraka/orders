package com.demo.orders.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.demo.orders.R
import com.demo.orders.activity.MainActivity
import com.demo.orders.db.*
import com.demo.orders.db.dao.CompanyMasterDao
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.table.OrdersDataSourceTable
import com.demo.orders.helper.*
import com.demo.orders.model.CompanyDataSource
import com.demo.orders.model.CustomersDataSource
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import com.wizesales.visitormanagent.db.table.OderNewResponse
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import java.util.*

//import static com.google.android.gms.plus.PlusOneDummyView.TAG;
class OrderNewPreviewFragment : Fragment(), View.OnClickListener {
    private lateinit var textCartItemCount: TextView
    var latitude: String? = null
    private var tinyDB: TinyDB? = null
    var stringUtils: StringUtils? = null
    var commonClass: CommonClass? = null
    var customDialog: CustomDialog? = null
    var dbHelper: DBHelper? = null
    var staticData: StaticData? = null
    var databaseList: DatabaseList? = null
    var bitmapUtility: BitmapUtility? = null
    lateinit var companyMasterDao: CompanyMasterDao
    var previewAdapter: OrderPreviewAdapter? = null
    var preview: List<SalesDataSource>? = null
    var companyDataSourceList: List<CompanyDataSource>? = null
    var recyclerViewPreview: RecyclerView? = null
    private val TAG = "SalesPreviewFragment"

    var customersResultDataSource: CustomersDataSource? = null
    var productList: List<OrdersDataSourceTable>? = null
    var salesManName: String? = null
    var textViewManualBillNo: EditText? = null
    var textViewCompanyName: TextView? = null
    var textViewCompanyAddress: TextView? = null
    var textViewCompanyGSTIN: TextView? = null
    var textViewUserID: TextView? = null
    var textViewUserName: TextView? = null
    var textViewTime: TextView? = null
    var textViewBillNo: TextView? = null
    var textViewCustomerName: TextView? = null
    var textViewCustomerAddress: TextView? = null
    var textViewCustomerGSTIN: TextView? = null
    var textViewSubTotalCount: TextView? = null
    var textViewSchemeCount: TextView? = null
    var textViewSGIST: TextView? = null
    var textViewCGST: TextView? = null
    var textViewIGST: TextView? = null
    var textViewTotalCount: TextView? = null
    var textViewPaymentMode: TextView? = null
    var textViewPaymentAmountCount: TextView? = null
    var textViewSgist: TextView? = null
    var textViewCgist: TextView? = null
    var textViewIgist: TextView? = null
    var textViewDiscountCount: TextView? = null
    var textViewTotal: TextView? = null
    var textViewDiscount: TextView? = null
    var textViewSubTotal: TextView? = null
    var textViewScheme: TextView? = null
    var relativeLayoutPaymentMode: RelativeLayout? = null
    var relativeLayoutCGSTPercentage: RelativeLayout? = null
    var relativeLayoutPayment: RelativeLayout? = null
    var relativeLayoutCGST: RelativeLayout? = null
    var relativeLayoutIGST: RelativeLayout? = null
    var relativeLayoutIGSTPercentage: RelativeLayout? = null
    var buttonSave: Button? = null

    private var splitAmount: String? = null
    private var amount = 0.0
    private var jsonResult: JSONResult? = null

    private var customerCount = 0
    var encoding = "US-ASCII"
    private var isBackPressed = false
    private lateinit var dialog: AlertDialog
    private var rootView: View? = null
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao
    lateinit var billsDao: BillsDao
    lateinit var billdata: OrdersDataSourceTable

    override fun onResume() {
        super.onResume()
        commonClass = CommonClass(activity!!)
        dbHelper = DBHelper(activity)
        customDialog = CustomDialog(activity)
        bitmapUtility = BitmapUtility(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        commonClass = CommonClass(activity!!)
        dbHelper = DBHelper(activity)
        customDialog = CustomDialog(activity)
        bitmapUtility = BitmapUtility(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        stringUtils = StringUtils(activity)
        tinyDB = TinyDB(activity)
        jsonResult = JSONResult(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_sales_preview, container, false)
        commonClass = CommonClass(activity!!)
        staticData = StaticData(activity)
        customDialog = CustomDialog(activity)
        databaseList = DatabaseList(activity)
        jsonResult = JSONResult(activity)

        appDatabase = AppDatabase.getDatabase(activity!!)
        logindao = appDatabase.loginDao()
        billsDao = appDatabase.billDao()
        companyMasterDao = appDatabase.companyMasterDao()
        /* Set the Title*/
        val activity = activity as MainActivity?
        var actionBar: ActionBar? = null
        if (activity != null) {
            actionBar = activity.supportActionBar
        }
        assert(actionBar != null)
        actionBar!!.title = "Order Preview"
        recyclerViewPreview = rootView!!.findViewById<View>(R.id.recyclerViewPreview) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getActivity())
        recyclerViewPreview!!.setHasFixedSize(true)
        recyclerViewPreview!!.layoutManager = mLayoutManager
        commonClass = CommonClass(getActivity()!!)
        dbHelper = DBHelper(getActivity())
        bitmapUtility = BitmapUtility(getActivity())

        /* Intitialization*/
        textViewCompanyName = rootView!!.findViewById<View>(R.id.textViewCompanyName) as TextView
        textViewCompanyAddress = rootView!!.findViewById<View>(R.id.textViewCompanyAddress) as TextView
        textViewCompanyGSTIN = rootView!!.findViewById<View>(R.id.textViewCompanyGSTIN) as TextView
        textViewUserID = rootView!!.findViewById<View>(R.id.textViewUserID) as TextView
        textViewUserName = rootView!!.findViewById<View>(R.id.textViewUserName) as TextView
        textViewBillNo = rootView!!.findViewById<View>(R.id.textViewBillNo) as TextView
        textViewManualBillNo = rootView!!.findViewById<View>(R.id.textViewManualBillNo) as EditText
        textViewCustomerName = rootView!!.findViewById<View>(R.id.textViewCustomerName) as TextView
        textViewCustomerAddress = rootView!!.findViewById<View>(R.id.textViewCustomerAddress) as TextView
        textViewCustomerGSTIN = rootView!!.findViewById<View>(R.id.textViewCustomerGSTIN) as TextView
        textViewSubTotalCount = rootView!!.findViewById<View>(R.id.textViewSubTotalCount) as TextView
        textViewSchemeCount = rootView!!.findViewById<View>(R.id.textViewSchemeCount) as TextView
        textViewDiscount = rootView!!.findViewById<View>(R.id.textViewDiscount) as TextView
        textViewSubTotal = rootView!!.findViewById<View>(R.id.textViewSubTotal) as TextView
        textViewScheme = rootView!!.findViewById<View>(R.id.textViewScheme) as TextView
        textViewTotal = rootView!!.findViewById<View>(R.id.textViewTotal) as TextView
        textViewSGIST = rootView!!.findViewById<View>(R.id.textViewSGIST) as TextView
        textViewCGST = rootView!!.findViewById<View>(R.id.textViewCGST) as TextView
        textViewIGST = rootView!!.findViewById<View>(R.id.textViewIGST) as TextView
        textViewDiscountCount = rootView!!.findViewById<View>(R.id.textViewDiscountCount) as TextView
        textViewTime = rootView!!.findViewById(R.id.textViewTime)
        textViewTotalCount = rootView!!.findViewById<View>(R.id.textViewTotalCount) as TextView
        textViewPaymentMode = rootView!!.findViewById<View>(R.id.textViewPaymentMode) as TextView
        textViewPaymentAmountCount = rootView!!.findViewById<View>(R.id.textViewPaymentAmountCount) as TextView
        textViewSgist = rootView!!.findViewById<View>(R.id.textViewSgist) as TextView
        textViewCgist = rootView!!.findViewById<View>(R.id.textViewCgist) as TextView
        textViewIgist = rootView!!.findViewById<View>(R.id.textViewIgist) as TextView
        buttonSave = rootView!!.findViewById(R.id.buttonSave)

        relativeLayoutCGSTPercentage = rootView!!.findViewById<View>(R.id.relativeLayoutCGSTPercentage) as RelativeLayout
        relativeLayoutIGSTPercentage = rootView!!.findViewById<View>(R.id.relativeLayoutIGSTPercentage) as RelativeLayout
        relativeLayoutPaymentMode = rootView!!.findViewById<View>(R.id.relativeLayoutPaymentMode) as RelativeLayout
        relativeLayoutPayment = rootView!!.findViewById<View>(R.id.relativeLayoutPayment) as RelativeLayout
        relativeLayoutCGST = rootView!!.findViewById<View>(R.id.relativeLayoutCGST) as RelativeLayout
        relativeLayoutIGST = rootView!!.findViewById<View>(R.id.relativeLayoutIGST) as RelativeLayout

        // Get the company List
        companyDataSourceList = databaseList!!.companyListDB
        textViewCompanyName!!.text = companyDataSourceList!!.get(0).companyName
        textViewCompanyAddress!!.text = companyDataSourceList!!.get(0).companyAddress
        textViewCompanyGSTIN!!.text = companyDataSourceList!!.get(0).companyGSTIN
        //Get Sales Details
        val bundle = arguments
        if (bundle != null) {
            val customerName = logindao.allItems.customerName
            val customerAddress = logindao.allItems.street + "," + logindao.allItems.city + "," + logindao.allItems.region
            val customerGSTIN = Constants.gstNo + logindao.allItems.tinCst
            billdata = bundle.getSerializable(Constants.list2) as OrdersDataSourceTable
            textViewBillNo!!.text = billdata.orderNo!!
            textViewCustomerName!!.text = customerName
            textViewCustomerAddress!!.text = customerAddress
            textViewCustomerGSTIN!!.text = customerGSTIN
            billList(billdata.orderNo!!, billdata.customerCode!!)
            customerCount = bundle.getInt(Constants.count)
            if (billdata.status == "Placed") {
                buttonSave!!.visibility = View.VISIBLE
                buttonSave!!.text = "CANCEL"
            } else {
                buttonSave!!.visibility = View.GONE
            }
            buttonSave!!.setOnClickListener {
                validation()
            }

        }
        handleBackPress(rootView!!)
        // Inflate the layout for this fragment
        return rootView!!
    }

    fun validation() {

        val dialog = AlertDialog.Builder(activity!!).setTitle("Confirmation")
                .setMessage("Are you sure want to cancel the order?")
                .setPositiveButton("Yes") { dialog, which ->
                    dialog.dismiss()
                    var isOnline = "0"
                    if (commonClass!!.isNetworkAvailable(context!!)) {
                        isOnline = "1"
                    }
                    if (isOnline == "1") {
                        dbHelper!!.updateFutureRequired(billdata.orderNo, DBHelper.COLUMN_STATUS, Constants.cancel, isOnline)
                        val cursorProductDetailsUpload = dbHelper!!.getFutureRequireOrderID(DBHelper.TABLE_FUTURE_REQUIRED, DBHelper.COLUMN_RANDOM_KEY,
                                billdata.orderNo, DBHelper.COLUMN_ORDER_DATE, commonClass!!.getDate(Constants.inputFormat1))
                        val jsonArray = commonClass!!.replace(jsonResult!!.getJSONArrayCursor(cursorProductDetailsUpload).toString(), "\\", "")
                        Log.e(TAG, "onClick: jsonArray : $jsonArray")
                        try {
                            getDeleteOrderUpload(context!!, Utils.KEY_BASEURL + Utils.KEY_DELETE_CUSTOMER_ORDER, JSONArray(jsonArray), billdata.orderNo)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }.show()
        //set the  dialog center
        val window = dialog.window
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }




    private fun billList(code: String, cusCode: String) {
        dialog = customDialog!!.loading(activity)
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.get_orders_by_id(code, cusCode)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<OderNewResponse> {
            override fun onFailure(call: Call<OderNewResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dialog.dismiss()
            }

            override fun onResponse(call: Call<OderNewResponse>, response: retrofit2.Response<OderNewResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    if (response.body() != null && response.body()!!.data != null) {
                        billsDao.deletebilldetails()
                        val login = response.body()!!.data as ArrayList<OrdersDataSourceTable>
                        productList = login

                        if (login != null && login.size > 0) {
                            calculation()
                            previewAdapter = OrderPreviewAdapter(login, activity!!)
                            recyclerViewPreview!!.adapter = previewAdapter
                        }
                        dialog.dismiss()
                    }
                    dialog.dismiss()
                }
            }
        })
    }

    fun calculation() {
        //Get  the Sales Items from the Database
        val netTotal = commonClass!!.commaSplit(commonClass!!.removeDigits(
                commonClass!!.getTotalBillDetailsOrder(productList!!, "netTotal").toString())!!.toDouble())
        val subtotal = commonClass!!.commaSplit(commonClass!!.removeDigits(
                commonClass!!.getTotalBillDetailsOrder(productList!!, "amountTotal").toString())!!.toDouble())
        textViewSubTotalCount!!.text = commonClass!!.removeDigits(subtotal)
        val schemetotal = commonClass!!.commaSplit(commonClass!!.removeDigits(
                commonClass!!.getTotalBillDetailsOrder(productList!!, "schemeTotal").toString())!!.toDouble())
        textViewSchemeCount!!.text = commonClass!!.removeDigits(schemetotal)
        val discounttotal = commonClass!!.commaSplit(commonClass!!.removeDigits(
                commonClass!!.getTotalBillDetailsOrder(productList!!, "totalDiscount").toString())!!.toDouble())

        var tax = commonClass!!.commaSplit(commonClass!!.removeDigits(
                commonClass!!.getTotalBillDetailsOrder(productList!!, "tax").toString())!!.toDouble())
        textViewDiscountCount!!.text = commonClass!!.removeDigits(discounttotal)
        val companyModel = companyMasterDao.allItems
        val customerModel = logindao.allItems
        //* State code is not same split the tax percentage  and append the tax percentage *//*
//        if (companyModel.country == customerModel.country && companyModel.state == customerModel.region) {
        relativeLayoutCGST!!.visibility = View.VISIBLE
        relativeLayoutCGSTPercentage!!.visibility = View.VISIBLE
        relativeLayoutIGST!!.visibility = View.GONE
        relativeLayoutIGSTPercentage!!.visibility = View.GONE
        val CGST = " CGST ( RS ) (+):"
        val SGST = " SGST ( RS ) (+):"
        textViewCgist!!.text = CGST
        textViewSgist!!.text = SGST
        tax=commonClass!!.getDouble(tax)
        amount = commonClass!!.getDivision(tax.toDouble(), 2.0)
        splitAmount = commonClass!!.commaSplit(amount)
        textViewCGST!!.text = splitAmount
        textViewSGIST!!.text = splitAmount
        Log.e(TAG, "SGST: " + "GST")
// caluculation total amount
        // caluculation total amount
        val displayTax = tax
        val displayDis = commonClass!!.removeDigits(discounttotal)
        Log.e(TAG, "onCreateView: displaySubTotalCount : " + subtotal + " displaySchemenetTotal : " +
                schemetotal + " displayTax : " + displayTax + " displayDis : " + displayDis)
        val addedSubTax = commonClass!!.productAdd(subtotal, displayTax)
        val addedDisScheme = commonClass!!.productAdd(schemetotal, displayDis!!)
        val sub = commonClass!!.productSubtract(addedSubTax, addedDisScheme)
        val totalRoundOff = Math.round(commonClass!!.getDouble(sub).toFloat()).toString()
        textViewTotalCount!!.text = totalRoundOff
//        }
        /*else {
            relativeLayoutCGST!!.visibility = View.GONE
            relativeLayoutCGSTPercentage!!.visibility = View.GONE
            relativeLayoutIGST!!.visibility = View.VISIBLE
            relativeLayoutIGSTPercentage!!.visibility = View.VISIBLE
            Log.e(TAG, "IGST: " + "IGST")
            val IGST = " IGST ( RS ) (+):"
            textViewIgist!!.text = IGST
            textViewIGST!!.text = billdata.tax
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_back, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val menuItem = menu.findItem(R.id.action_notification)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.hotlist_hot) as TextView
        val hotlist_bell = actionView.findViewById<ImageView>(R.id.hotlist_bell) as ImageView
        hotlist_bell.setOnClickListener {
            val fragment = FragmentNotification()
            val title = getString(R.string.title_notification)
            setFragment(fragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val id = item.itemId
        return when (id) {
            R.id.action_home -> {
                setFragment(RouteMenuFragment())
                true
            }
            R.id.action_notification -> {
                setFragment(FragmentNotification())
                true
            }
            R.id.action_back -> {
                Log.e(TAG, "onOptionsItemSelected: ")
                setFragment(OrderEnquiryListFragment(), 1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment) {
        val bundl = Bundle()
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, count: Int) {
        val bundl = Bundle()
        bundl.putBoolean("isBackPressed", true)
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    private fun handleBackPress(view: View?) {
        view!!.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent? ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!isBackPressed) {
                    setFragment(OrderEnquiryListFragment(), 1)
                    return@setOnKeyListener true
                } else if (isBackPressed) {
                    isBackPressed = false
                }
            }
            false
        }
    }

    companion object {
        private val TAG = OrderNewPreviewFragment::class.java.simpleName
    }

    override fun onClick(v: View?) {
    }

    private fun getDeleteOrderUpload(context: Context, URL: String, jsonArray: JSONArray, orderNo: String) {
        Log.e(TAG, "getDeleteOrderUpload: $URL")
        val dialog: AlertDialog = customDialog!!.loading(context)
        val postRequest: StringRequest = object : StringRequest(Method.POST, URL, Response.Listener { response: String ->
            Log.e(TAG, "getDeleteOrderUpload: $response")
            var jsonObject: JSONObject? = null
            try {
                jsonObject = JSONObject(response)
                val msg = jsonObject.getString(APIKey.KEY_MESSAGE)
                if (msg == "Success") {

                    val body = JSONObject(response)
                    setFragment(OrderEnquiryListFragment())
                    if (dialog.isShowing) dialog.dismiss()
                }
                dialog.dismiss()
            } catch (e: JSONException) {
                e.printStackTrace()
                dialog.dismiss()
            }
        }, Response.ErrorListener { error: VolleyError ->
            VolleyLog.e("Error : ", error.localizedMessage)
            dialog.dismiss()
        }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params[APIKey.KEY_DATA] = jsonArray.toString()
                return params
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass!!.auth()
                val params: MutableMap<String, String> = HashMap()
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

}