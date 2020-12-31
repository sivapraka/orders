package com.demo.orders.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.demo.orders.R
import com.demo.orders.activity.MainActivity
import com.demo.orders.adapter.PreviewAdapter
import com.demo.orders.db.*
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.table.ResponseNotificationCount
import com.demo.orders.helper.*
import com.demo.orders.model.CompanyDataSource
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import java.io.Serializable
import java.util.*

class OrderPreviewFragment : Fragment(), View.OnClickListener {
    private lateinit var textCartItemCount: TextView
    var commonClass: CommonClass? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var latitude: String? = null
    var longtiude: String? = null
    var customDialog: CustomDialog? = null
    var dbHelper: DBHelper? = null
    var stringUtils: StringUtils? = null
    var staticData: StaticData? = null
    var databaseList: DatabaseList? = null
    var bitmapUtility: BitmapUtility? = null
    var previewAdapter: PreviewAdapter? = null
    private var tinyDB: TinyDB? = null

    lateinit var companyDataSourceList: List<CompanyDataSource>
    var recyclerViewPreview: RecyclerView? = null
    private var displayDiscount: String? = null
    private var taxPrice: String? = null
    private var splitAmount: String? = null
    private var amount = 0.0
    private var displaySchemenetTotal: String? = null
    private var displaySubTotalCount: String? = null
    private val TAG = "OrderPreviewFragment"
    private var billNo: String? = null
    private var time: String? = null
    private var customerName: String? = null
    private var customerAddress: String? = null
    private var salesManName: String? = null
    private var salesManID: String? = null
    private var companyName: String? = null
    private lateinit var companyAddress: String
    private var companyGSTIN: String? = null
    private var customerGSTIN: String? = null
    var jsonResult: JSONResult? = null
    var productList = ArrayList<ProductsDataSource>()

    lateinit var dialog: AlertDialog
    lateinit var textViewCompanyName: TextView
    lateinit var textViewCompanyAddress: TextView
    lateinit var textViewCompanyGSTIN: TextView
    lateinit var textViewUserID: TextView
    lateinit var textViewBillNo: TextView
    lateinit var textViewCustomerName: TextView
    lateinit var textViewCustomerAddress: TextView
    lateinit var textViewCustomerGSTIN: TextView
    lateinit var textViewTime: TextView
    lateinit var buttonSave: Button
    private var customerCount = 0
    var isBackPressed = true
    var encoding = "US-ASCII"
    private var backcount = 0
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao
    private var total: String? = null
    private val paymentMode: String? = null
    private val paymentAmount: String? = null
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
    var textViewSubTotalCount: TextView? = null
    var relativeLayoutPaymentMode: RelativeLayout? = null
    var relativeLayoutCGSTPercentage: RelativeLayout? = null
    var relativeLayoutPayment: RelativeLayout? = null
    var relativeLayoutCGST: RelativeLayout? = null
    var relativeLayoutIGST: RelativeLayout? = null
    var relativeLayoutIGSTPercentage: RelativeLayout? = null
    var linearLayout: LinearLayout? = null


    override fun onResume() {
        super.onResume()
        commonClass = CommonClass(activity!!)
        customDialog = CustomDialog(activity)
        dbHelper = DBHelper(activity)
        bitmapUtility = BitmapUtility(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        commonClass = CommonClass(activity!!)
        customDialog = CustomDialog(activity)
        dbHelper = DBHelper(activity)
        bitmapUtility = BitmapUtility(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        stringUtils = StringUtils(activity)
        tinyDB = TinyDB(activity)
        jsonResult = JSONResult(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sales_preview, container, false)
        commonClass = CommonClass(activity!!)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        customDialog = CustomDialog(activity)
        stringUtils = StringUtils(activity)
        jsonResult = JSONResult(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)
        logindao = appDatabase.loginDao()
        /* Set the Title*/
        val activity = (activity as MainActivity?)!!
        val actionBar = activity.supportActionBar!!
        actionBar.title = "Order Preview"
        recyclerViewPreview = rootView.findViewById<View>(R.id.recyclerViewPreview) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getActivity())
        recyclerViewPreview!!.setHasFixedSize(true)
        recyclerViewPreview!!.layoutManager = mLayoutManager
        commonClass = CommonClass(getActivity()!!)
        dbHelper = DBHelper(getActivity())
        bitmapUtility = BitmapUtility(getActivity())
        /* Intitialization*/
        textViewCompanyName = rootView.findViewById<View>(R.id.textViewCompanyName) as TextView
        textViewCompanyAddress = rootView.findViewById<View>(R.id.textViewCompanyAddress) as TextView
        textViewCompanyGSTIN = rootView.findViewById<View>(R.id.textViewCompanyGSTIN) as TextView
        textViewUserID = rootView.findViewById<View>(R.id.textViewUserID) as TextView
        textViewBillNo = rootView.findViewById<View>(R.id.textViewBillNo) as TextView
        textViewCustomerName = rootView.findViewById<View>(R.id.textViewCustomerName) as TextView
        textViewCustomerAddress = rootView.findViewById<View>(R.id.textViewCustomerAddress) as TextView
        textViewCustomerGSTIN = rootView.findViewById<View>(R.id.textViewCustomerGSTIN) as TextView
        textViewTime = rootView.findViewById(R.id.textViewTime)
        linearLayout = rootView.findViewById(R.id.linearLayout)
        buttonSave = rootView.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener(this)
        textViewSubTotalCount = rootView!!.findViewById<View>(R.id.textViewSubTotalCount) as TextView
        textViewSchemeCount = rootView.findViewById<View>(R.id.textViewSchemeCount) as TextView
        textViewDiscount = rootView.findViewById<View>(R.id.textViewDiscount) as TextView
        textViewSubTotal = rootView.findViewById<View>(R.id.textViewSubTotal) as TextView
        textViewScheme = rootView.findViewById<View>(R.id.textViewScheme) as TextView
        textViewTotal = rootView.findViewById<View>(R.id.textViewTotal) as TextView
        textViewSGIST = rootView.findViewById<View>(R.id.textViewSGIST) as TextView
        textViewCGST = rootView.findViewById<View>(R.id.textViewCGST) as TextView
        textViewIGST = rootView.findViewById<View>(R.id.textViewIGST) as TextView
        textViewDiscountCount = rootView.findViewById<View>(R.id.textViewDiscountCount) as TextView
        textViewTime = rootView.findViewById(R.id.textViewTime)
        textViewTotalCount = rootView.findViewById<View>(R.id.textViewTotalCount) as TextView
        textViewPaymentMode = rootView.findViewById<View>(R.id.textViewPaymentMode) as TextView
        textViewPaymentAmountCount = rootView.findViewById<View>(R.id.textViewPaymentAmountCount) as TextView
        textViewSgist = rootView.findViewById<View>(R.id.textViewSgist) as TextView
        textViewCgist = rootView.findViewById<View>(R.id.textViewCgist) as TextView
        textViewIgist = rootView.findViewById<View>(R.id.textViewIgist) as TextView
        relativeLayoutCGSTPercentage = rootView.findViewById<View>(R.id.relativeLayoutCGSTPercentage) as RelativeLayout
        relativeLayoutIGSTPercentage = rootView.findViewById<View>(R.id.relativeLayoutIGSTPercentage) as RelativeLayout
        relativeLayoutPaymentMode = rootView.findViewById<View>(R.id.relativeLayoutPaymentMode) as RelativeLayout
        relativeLayoutPayment = rootView.findViewById<View>(R.id.relativeLayoutPayment) as RelativeLayout
        relativeLayoutCGST = rootView.findViewById<View>(R.id.relativeLayoutCGST) as RelativeLayout
        relativeLayoutIGST = rootView.findViewById<View>(R.id.relativeLayoutIGST) as RelativeLayout

        // Get the company List
        companyDataSourceList = databaseList!!.companyListDB
        if (companyDataSourceList.size > 0) {
            companyName = companyDataSourceList.get(0).companyName
            companyAddress = companyDataSourceList.get(0).companyAddress
            companyGSTIN = companyDataSourceList.get(0).companyGSTIN
            textViewCompanyName.text = companyName
            textViewCompanyAddress.text = companyAddress.replace("[", "").replace("]", "")
            textViewCompanyGSTIN.text = companyGSTIN
        }

        //Currect locaton://
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity()!!)
        if (ActivityCompat.checkSelfPermission(getActivity()!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity()!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        fusedLocationClient.lastLocation
                .addOnSuccessListener(getActivity()!!) { location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        // Logic to handle location object
                        Log.e(TAG, "onSuccess: $location")
                        latitude = location.latitude.toString()
                        longtiude = location.longitude.toString()
                    }
                }

        //Get Sales Details
        val bundle = arguments
        if (bundle != null) {
            customerCount = bundle.getInt(Constants.count)
            backcount = bundle.getInt(Constants.backcount)
            Log.e("TAG", "orderpreview customerCount=$customerCount")

            billNo = bundle.getString("billNo")
            productList = bundle.getSerializable("product_list") as ArrayList<ProductsDataSource>

            if (backcount == 0) {
                buttonSave.visibility = View.GONE

            } else {
                buttonSave.visibility = View.VISIBLE
            }

            assert(billNo != null)
            val name = commonClass!!.replace(billNo!!)

            if (productList != null && productList.isNotEmpty()) {
                customerName = logindao.allItems.customerName
                customerAddress = logindao.allItems.street + "," + logindao.allItems.city + "," + logindao.allItems.region
                customerGSTIN = Constants.gstNo + logindao.allItems.tinCst
                val currentDate = commonClass!!.getDate(Constants.inputFormat3)
                salesManID = dbHelper!!.getSpcName(DBHelper.TABLE_USER, DBHelper.COLUMN_SUPERVISOR_CODE)
                salesManName = dbHelper!!.getSpcName(DBHelper.TABLE_SUPERVISOR, DBHelper.COLUMN_SUPERVISOR_NAME, DBHelper.COLUMN_SUPERVISOR_CODE, salesManID)
                Log.e(TAG, "onCreateView: $currentDate")
                time = commonClass!!.getTime(Constants.inputFormat6)
                Log.e(TAG, "onCreateView: $time")
                val billDetails = Constants.billNo + billNo
                val GSTINNo = customerGSTIN
                textViewCustomerName.text = customerName
                textViewCustomerAddress.text = customerAddress
                textViewBillNo.text = billDetails
                textViewTime.text = time
                textViewCustomerGSTIN.text = GSTINNo
                textViewUserID.text = salesManID + " " + resources.getString(R.string.openbarget) + salesManName + resources.getString(R.string.closebarget)
                for (i in 0 until productList.size) {
                    Log.e(TAG, "onCreateView:  : " + i)
                    val productsDataSource = productList[i]
                    var discountFrom: Date? = null
                    var discountTill: Date? = null
                    var schemeFrom: Date? = null
                    var schemeTill: Date? = null
                    val price = commonClass!!.removeNewLine(
                            productsDataSource.productTotal).toDouble()
                    if (productsDataSource.discountFrom != null) {
                        discountFrom = commonClass!!.convertDate(productsDataSource.discountFrom,
                                Constants.inputFormat1)
                    }
                    if (productsDataSource.discountTill != null) {
                        discountTill = commonClass!!.convertDate(productsDataSource.discountTill,
                                Constants.inputFormat1)
                    }
                    if (productsDataSource.schemeFrom != null && productsDataSource.schemeFrom.isNotEmpty()) {
                        schemeFrom = commonClass!!.convertDate(productsDataSource.schemeFrom,
                                Constants.inputFormat1)
                    }
                    if (productsDataSource.schemeTill != null && productsDataSource.schemeTill.isNotEmpty()) {
                        schemeTill = commonClass!!.convertDate(productsDataSource.schemeTill,
                                Constants.inputFormat1)
                    }
                    val dt = commonClass!!.getDate(Constants.inputFormat1)
                    val currentDt = commonClass!!.convertDate(dt,
                            Constants.inputFormat1)
                    var isDiscountValid = false
                    var isDiscountQty = false
                    var isSchemeValid = false
                    var isSchemeQty = false
                    if (discountFrom != null && discountTill != null) {
                        isDiscountValid = commonClass!!.isBetweenDate(discountFrom, discountTill, currentDt!!)
                    }
                    if (schemeFrom != null && schemeTill != null) {
                        isSchemeValid = commonClass!!.isBetweenDate(schemeFrom, schemeTill, currentDt!!)
                    }
                    if (productsDataSource.discountToQty != null && productsDataSource.discountFromQty != null) {
                        isDiscountQty = commonClass!!.isRange(productsDataSource.productQty.toInt(),
                                productsDataSource.discountFromQty.toInt(), productsDataSource.discountToQty.toInt())
                    }
                    //scheme
                    if (productsDataSource.schemeQty != null && productsDataSource.schemeQty.isNotEmpty() && productsDataSource.productQty != null) {
                        isSchemeQty = commonClass!!.isRange(productsDataSource.productQty.toInt(),
                                productsDataSource.schemeQty.toInt())
                    }
                    if (isDiscountValid && isDiscountQty) {
                        if (productsDataSource.discountType != null &&
                                productsDataSource.discountType == Constants.rupees) {
                            val discount = commonClass!!.productMutiply(productsDataSource.discount, productsDataSource.productQty)
                            productsDataSource.discountAmount = discount
                            productsDataSource.discountTotal = discount
                            Log.e(TAG, "DISCOUNT TOTAL: $discount")
                        } else if (productsDataSource.discountType == Constants.percent) {
                            val taxAmount = commonClass!!.removeDigits(
                                    java.lang.String.valueOf(commonClass!!.getTaxValue(price, productsDataSource.discount.toDouble())))
                            val discount = commonClass!!.productMutiply(taxAmount!!, productsDataSource.productQty)
                            productsDataSource.discountAmount = discount
                            productsDataSource.discountTotal = discount
                        }
                    } else {
                        productsDataSource.discountTotal = "0"
                    }
                    //scheme
                    if (isSchemeValid && isSchemeQty) {
                        productsDataSource.productSchemeQty = productsDataSource.schemeFreeQty
                    } else {
                        productsDataSource.productSchemeQty = "0"
                    }
                    Log.e(TAG, "onCreateView:  :index " + i)
                    val scheme = productsDataSource.productQty
                    Log.e(TAG, "validation: getProductQty 2 : " + productsDataSource.productSchemeQty)
                    if (productsDataSource.productSchemeQty != null && productsDataSource.productSchemeQty.isNotEmpty() && productsDataSource.productSchemeQty != "0") {
                        val perschemeQty = commonClass!!.productDivid(productsDataSource.schemeQty, productsDataSource.schemeFreeQty)
                        if (scheme.toInt() >= perschemeQty.toDouble().toInt()) {
                            var productListCode: List<ProductsDataSource?>?
                            if (productsDataSource.schemeMaterial2 == "" || productsDataSource.schemeMaterial2 == "Empty") {
                                productListCode = databaseList!!.getProductsMaterialSchemeCodeList(currentDate, productsDataSource.schemeMaterial1,
                                        logindao.allItems.customerCode!!, logindao.allItems.salesorgCode,
                                        logindao.allItems.schemeDiscount, logindao.allItems.loyalty, productsDataSource.materialCode, logindao.allItems.priceCode)
                            } else {
                                productListCode = databaseList!!.getProductsMaterialSchemeCodeList(currentDate, productsDataSource.schemeMaterial2,
                                        logindao.allItems.customerCode!!, logindao.allItems.salesorgCode,
                                        logindao.allItems.schemeDiscount, logindao.allItems.loyalty, productsDataSource.materialCode, logindao.allItems.priceCode)
                            }
                            if (productListCode.size > 0) {
                                val dataSource = productListCode[0]!!
                                val schemeQty = commonClass!!.productDivid(scheme, perschemeQty).toDouble().toInt()
                                val price = commonClass!!.removeNewLine(dataSource.netAmount).toDouble()
                                val totalprice = schemeQty * price
                                val amountPrice = commonClass!!.removeNewLine(dataSource.amount).toDouble()
                                val amountTotal = schemeQty * amountPrice

                                val productsDataSourceNew = ProductsDataSource(dataSource.materialCode, dataSource.materialName,
                                        dataSource.amount, schemeQty.toString(), dataSource.productTotal,
                                        "0", dataSource.productTaxValue, dataSource.getproductUOM(),
                                        dataSource.currentDate, dataSource.priceTax, "0.0", "0.0",
                                        "0.0", "0.0", "0.0", "0.0",
                                        dataSource.customerStateCode, dataSource.companyStateCode, dataSource.productByteImage,
                                        dataSource.availableQty, dataSource.netTotal, dataSource.amountTotal, dataSource.totalTaxAmount, dataSource.productRate,
                                        dataSource.productTotal, dataSource.discountFromQty, dataSource.discountToQty,
                                        dataSource.discount, dataSource.discountType, dataSource.discountWot,
                                        dataSource.discountFrom, dataSource.discountTill, dataSource.dayCount,
                                        dataSource.replace_quantity, dataSource.soldQuantity, dataSource.scheme,
                                        dataSource.schemeCode, dataSource.schemeName, dataSource.schemeQty,
                                        dataSource.schemeFreeQty, dataSource.schemeFrom, dataSource.schemeTill,
                                        dataSource.schemeType, dataSource.schemeMaterial1, dataSource.schemeMaterial2, "0", "free", "Placed")
                                productsDataSourceNew.amountTotal = amountTotal.toString()
                                productsDataSourceNew.productRate = amountTotal.toString()
                                productsDataSourceNew.netTotal = totalprice.toString()
                                productsDataSourceNew.productTotal = totalprice.toString()
                                productsDataSourceNew.productQty = schemeQty.toString()
                                productsDataSourceNew.discountTotal = "0"
                                productsDataSourceNew.productTax = "0"
                                productsDataSourceNew.productTaxPercent = "0"
                                productsDataSourceNew.productTaxValue = "0"
                                productList.add(productsDataSourceNew)
                            }
                        }
                    }
                }

                val totalDiscount = commonClass!!.commaSplit(
                        commonClass!!.removeDigits(java.lang.String.valueOf(
                                commonClass!!.getTotalTax(productList, "totalDiscount")))!!.toDouble())
                Log.e(TAG, "totalDiscount: $totalDiscount")
                displayDiscount = totalDiscount

                taxPrice = commonClass!!.removeDigits(java.lang.String.valueOf(commonClass!!.getTotalTax(productList, "tax amount")))
                amount = commonClass!!.getDivision(taxPrice!!.toDouble(), 2.0)
                splitAmount = commonClass!!.commaSplit(amount)
                val customerStateCode = "TN"
                val salesManStateCode = "TN"
                /* State code is not same split the tax percentage  and append the tax percentage */
                if (customerStateCode == salesManStateCode) {
                    relativeLayoutCGST!!.visibility = View.VISIBLE
                    relativeLayoutCGSTPercentage!!.visibility = View.VISIBLE
                    relativeLayoutIGST!!.visibility = View.GONE
                    relativeLayoutIGSTPercentage!!.visibility = View.GONE
                    val CGST = " CGST ( RS ) (+):"
                    val SGST = " SGST ( RS ) (+):"
                    textViewCgist!!.text = CGST
                    textViewSgist!!.text = SGST
                    textViewCGST!!.text = splitAmount
                    textViewSGIST!!.text = splitAmount
                    Log.e(TAG, "SGST: " + "GST")
                } else if (customerStateCode != salesManStateCode) {
                    relativeLayoutCGST!!.visibility = View.GONE
                    relativeLayoutCGSTPercentage!!.visibility = View.GONE
                    relativeLayoutIGST!!.visibility = View.VISIBLE
                    relativeLayoutIGSTPercentage!!.visibility = View.VISIBLE
                    Log.e(TAG, "IGST: " + "IGST")
                    val IGST = " IGST ( RS ) (+):"
                    textViewIgist!!.text = IGST
                    textViewIGST!!.text = taxPrice
                }
                textViewScheme!!.text = resources.getString(R.string.scheme).toString() + " (-):"
                textViewDiscountCount!!.text = commonClass!!.removeDigits(totalDiscount)
                Log.e("TAG", "total vdiscount=" + commonClass!!.removeDigits(totalDiscount))
                val time = commonClass!!.getTime(Constants.inputFormat6)
                val date = commonClass!!.getTime(Constants.inputFormat3)
                textViewTime.text = date
                val billNo = "Bill: $billNo"
                val manualBillNo = dbHelper!!.getSpcName(DBHelper.TABLE_BILLING_DETAILS, DBHelper.COLUMN_MANUAL_BILLNO, DBHelper.COLUMN_BILL_NO, billNo)
                Log.e(TAG, "onCreateView: manualBillNo $manualBillNo")
                textViewUserID.text = salesManID + resources.getString(R.string.openbarget) + salesManName + resources.getString(R.string.closebarget)
                val subTotal = commonClass!!.productSubtract(
                        commonClass!!.removeDigits(java.lang.String.valueOf(
                                commonClass!!.getTotalTax(productList, "amountTotal")))!!,
                        totalDiscount)
                val netTotal = commonClass!!.commaSplit(commonClass!!.removeDigits(java.lang.String.valueOf(
                        commonClass!!.getTotalTax(productList, "amountTotal", "main")))!!.toDouble())

                val SchemeTotal = commonClass!!.commaSplit(commonClass!!.removeDigits(java.lang.String.valueOf(
                        commonClass!!.getTotalTax(productList, "amountTotal", "free")))!!.toDouble())

                displaySchemenetTotal = commonClass!!.removeDigits(SchemeTotal)
                displaySubTotalCount = commonClass!!.removeDigits(subTotal)
                textViewSchemeCount!!.text = commonClass!!.removeDigits(SchemeTotal)
                textViewSubTotalCount!!.text = commonClass!!.removeDigits(subTotal)

                //Get  the Sales Items from the Database

                if (productList != null && productList.size > 0) {
                    //Adding main contents of the document
                    previewAdapter = PreviewAdapter(productList, getActivity()!!)
                    recyclerViewPreview!!.adapter = previewAdapter
                } else {
                }
            } else {
                customDialog!!.CustomToast(getActivity() as AppCompatActivity?, "No Preview Data Found", "fail")
            }
        }
        // caluculation total amount
        val displayTax: String = taxPrice!!
        val displayDis = commonClass!!.removeDigits(displayDiscount!!)
        Log.e(TAG, "onCreateView: displaySubTotalCount : " + displaySubTotalCount + " displaySchemenetTotal : " +
                displaySchemenetTotal + " displayTax : " + displayTax + " displayDis : " + displayDis)
        val addedSubTax = commonClass!!.productAdd(displaySubTotalCount!!, displayTax)
        val addedDisScheme = commonClass!!.productAdd(displaySchemenetTotal!!, displayDis!!)
        val sub = commonClass!!.productSubtract(addedSubTax, addedDisScheme)
        val totalRoundOff = Math.round(commonClass!!.getDouble(sub).toFloat()).toString()
        textViewTotalCount!!.text = totalRoundOff
        Log.e(TAG, "onCreateView: display subTax : $addedSubTax disScheme : $addedDisScheme")

        //api get notification count
        getNotificationCount()

        handleBackPress(rootView)
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_back, menu)
        val menuItem = menu.findItem(R.id.action_notification)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.hotlist_hot) as TextView
        val hotlist_bell = actionView.findViewById<ImageView>(R.id.hotlist_bell) as ImageView
        hotlist_bell.setOnClickListener {
            val fragment = FragmentNotification()
            val title = getString(R.string.title_notification)
            setFragment(fragment, 0)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val id = item.itemId
        return when (id) {
            R.id.action_home -> {
                setFragment(RouteMenuFragment(), customerCount)
                true
            }
            R.id.action_notification -> {
                setFragment(FragmentNotification(), customerCount)
                true
            }
            R.id.action_back -> {
                if (backcount == 0) {
                    setFragment(OrderEnquiryListFragment(), backcount)
                } else {
                    setFragment(NewOrderEnquiryFragment(), backcount)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, count: Int) {

        val bundl = Bundle()
        bundl.putBoolean("isBackPressed", true)
        bundl.putString("billNo", billNo)
        bundl.putSerializable("product_list", productList as Serializable?)
        bundl.putInt(Constants.count, customerCount)
        bundl.putInt(Constants.backcount, count)
        Log.e("TAG", "Orderback customerCount=$count")
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    private fun handleBackPress(view: View) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, _, _ ->
            if (isBackPressed) {
                if (backcount == 0) {
                    setFragment(OrderEnquiryListFragment(), backcount)
                } else {
                    setFragment(NewOrderEnquiryFragment(), backcount)
                    Log.e("TAG", "new order backpreesed if in if called")
                }
            } else {
                isBackPressed = true
                //                        setFragment(new OrderEnquiryListFragment(),customerCount);
            }
            //                else if (isBackPressed){
//                    isBackPressed=false;
//                    Log.e("TAG","new order backpreesed elseif called");
//                }
            false
        }
    }

    override fun onClick(v: View) {
        val id = v.id
        Log.e(TAG, "onClick:  ID :$id")
        when (id) {
            R.id.buttonSave -> validation(productList)
        }
    }

    fun insertFutureRequire() {
        val cursor = dbHelper!!.getTableList(DBHelper.TABLE_FUTURE_REQUIRED, DBHelper.COLUMN_ORDER_NO, billNo)
        val jsonArray = jsonResult!!.getJSONArrayCursor(cursor)
        Log.e(TAG, "insertFutureRequire: " + jsonArray.toString())
        System.out.println(jsonArray.toString())
        if (jsonArray.length() > 0) {
            getFutureRequireUpload(activity, Utils.KEY_BASEURL + Utils.KEY_CUSTOMER_ORDER_ENQUIRY, jsonArray)
        }
    }

    private fun validation(productList: List<ProductsDataSource>?) {
        Log.e(TAG, "validation: "+Gson().toJson(productList) )
        if (commonClass!!.isNetworkAvailable(activity!!)) {
            var isOnline = "1"

            for (dataSource in productList!!) {
                var productTotal = "0"
                if (dataSource.productRate != null) {
                    productTotal = dataSource.productRate
                }
                val productTaxValue = commonClass!!.multiply(dataSource.productTaxValue, dataSource.productQty)
                val amount = commonClass!!.getDivision(productTaxValue.toDouble(), 2.0)
                val splitAmount = commonClass!!.commaSplit(amount)
                if (dataSource.discountTotal != null && dataSource.discountTotal.isNotEmpty() && !dataSource.discountTotal.equals("0")) {
                    productTotal = commonClass!!.productSubtract(dataSource.productRate, dataSource.discountTotal)
                }
                val total = commonClass!!.productAdd(productTotal, productTaxValue)
                Log.e(TAG, "validation: productSubTotal : $productTotal")

                if (dataSource.taxIGSTAmount != null) {
                    //inserting in product details.
                    dbHelper!!.insertProductDetails(billNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, dataSource.materialCategory,
                            dataSource.productQty, commonClass!!.roundOff(dataSource.amount), dataSource.discount,
                            dataSource.discountTotal, dataSource.productTaxPercent, commonClass!!.roundOff(productTaxValue), dataSource.schemeCode, dataSource.scheme,
                            dataSource.productRate, commonClass!!.roundOff(productTotal), commonClass!!.roundOff(productTotal), logindao.allItems.customerCode!!,
                            commonClass!!.getDate(Constants.inputFormat1),
                            dataSource.discount, dataSource.discountTotal, commonClass!!.roundOff(productTaxValue), commonClass!!.roundOff(productTotal), commonClass!!.roundOff(total),
                            billNo,
                            "", "", commonClass!!.roundOff(productTaxValue),
                            "", "",
                            dataSource.taxIGST, isOnline, Constants.tripID, dataSource.productType, dataSource.orderstatus, logindao.allItems.loyalty, logindao.allItems)
                } else {
                    //inserting in product details.
                    dbHelper!!.insertProductDetails(billNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, dataSource.materialCategory,
                            dataSource.productQty, commonClass!!.roundOff(dataSource.amount), dataSource.discount,
                            dataSource.discountTotal, dataSource.productTaxPercent, commonClass!!.roundOff(productTaxValue), dataSource.schemeCode, dataSource.scheme,
                            dataSource.productRate, commonClass!!.roundOff(productTotal), commonClass!!.roundOff(productTotal), logindao.allItems.customerCode!!,
                            commonClass!!.getDate(Constants.inputFormat1),
                            dataSource.discount, dataSource.discountTotal, commonClass!!.roundOff(productTaxValue), commonClass!!.roundOff(productTotal), commonClass!!.roundOff(total),
                            billNo,
                            commonClass!!.removeDigits(splitAmount), commonClass!!.removeDigits(splitAmount), "", dataSource.taxSGST, dataSource.taxCGST,
                            "", isOnline, Constants.tripID, dataSource.productType, dataSource.orderstatus, logindao.allItems.loyalty, logindao.allItems)
                }
            }
            insertFutureRequire()
        } else {
            var isOnline = "0"
            for (dataSource in productList!!) {
                var productTotal = "0"
                if (dataSource.productRate != null) {
                    productTotal = dataSource.productRate
                }
                val productTaxValue = commonClass!!.multiply(dataSource.productTaxValue, dataSource.productQty)
                val amount = commonClass!!.getDivision(productTaxValue.toDouble(), 2.0)
                val splitAmount = commonClass!!.commaSplit(amount)
                if (dataSource.discountTotal != null && dataSource.discountTotal.length > 0 && !dataSource.discountTotal.equals("0")) {
                    productTotal = commonClass!!.productSubtract(dataSource.productRate, dataSource.discountTotal)
                }
                val total = commonClass!!.productAdd(productTotal, productTaxValue)
                Log.e(TAG, "validation: productSubTotal :unit price ${dataSource.amount} roundoff ${commonClass!!.roundOff(dataSource.amount)}")
                Log.e(TAG, "validation: productSubTotal : $total and roundoff ${commonClass!!.roundOff(total)}")

                if (dataSource.taxIGSTAmount != null) {
                    //inserting in product details.
                    dbHelper!!.insertProductDetails(billNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, dataSource.materialCategory,
                            dataSource.productQty, commonClass!!.roundOff(dataSource.amount), dataSource.discount,
                            dataSource.discountTotal, dataSource.productTaxPercent, commonClass!!.roundOff(productTaxValue), dataSource.schemeCode, dataSource.scheme,
                            dataSource.productRate, commonClass!!.roundOff(productTotal), commonClass!!.roundOff(productTotal), logindao.allItems.customerCode!!,
                            commonClass!!.getDate(Constants.inputFormat1),
                            dataSource.discount, dataSource.discountTotal, commonClass!!.roundOff(productTaxValue), commonClass!!.roundOff(productTotal), commonClass!!.roundOff(total),
                            billNo,
                            "", "", commonClass!!.roundOff(productTaxValue),
                            "", "",
                            dataSource.taxIGST, isOnline, Constants.tripID, dataSource.productType, dataSource.orderstatus, logindao.allItems.loyalty, logindao.allItems)
                } else {
                    //inserting in product details.
                    dbHelper!!.insertProductDetails(billNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, dataSource.materialCategory,
                            dataSource.productQty, commonClass!!.roundOff(dataSource.amount), dataSource.discount,
                            dataSource.discountTotal, commonClass!!.roundOff(dataSource.productTaxPercent), commonClass!!.roundOff(productTaxValue), dataSource.schemeCode, dataSource.scheme,
                            dataSource.productRate, commonClass!!.roundOff(productTotal), commonClass!!.roundOff(productTotal), logindao.allItems.customerCode!!,
                            commonClass!!.getDate(Constants.inputFormat1),
                            dataSource.discount, dataSource.discountTotal, commonClass!!.roundOff(productTaxValue), commonClass!!.roundOff(productTotal), commonClass!!.roundOff(total),
                            billNo,
                            commonClass!!.roundOff(splitAmount), commonClass!!.roundOff(splitAmount), "", dataSource.taxSGST, dataSource.taxCGST,
                            "", isOnline, Constants.tripID, dataSource.productType, dataSource.orderstatus, logindao.allItems.loyalty, logindao.allItems)
                }
            }
            clearList()
            setFragment(OrderEnquiryListFragment(), 0)

        }
    }

    fun getFutureRequireUpload(context: Context?, URL: String, jsonArray: JSONArray) {
        Log.e(TAG, "getFutureRequireUpload: $URL")
        val dialog = customDialog!!.loading(activity)
        val postRequest: StringRequest = object : StringRequest(Method.POST, URL, Response.Listener { response: String ->
            try {
                Log.e(TAG, "getFutureRequireUpload: $response")
                val jsonObject = JSONObject(response)
                val jsonArray1 = jsonObject.getJSONArray(APIKey.KEY_DATA)
                for (i in 0 until jsonArray1.length()) {
                    val jsonObj = jsonArray1.getJSONObject(i)
                    val orderNo = jsonObj.getString(APIKey.KEY_ORDER_NO)
                    var randomKey = jsonObj.getString(APIKey.KEY_RANDOM_KEY)
                    randomKey = commonClass!!.replace(randomKey, "\\", "")
                    Log.e(TAG, "getFutureRequireUpload: randomKey : $randomKey")
                    dbHelper!!.updateFutureRequired(randomKey, DBHelper.COLUMN_ORDER_NO, orderNo, "1")
                }
                dialog.dismiss()
                clearList()
                setFragment(OrderEnquiryListFragment(), 0)
                //                }
            } catch (e: JSONException) {
                e.localizedMessage
                dbHelper!!.updateOnlineStatus(DBHelper.TABLE_FUTURE_REQUIRED, DBHelper.COLUMN_ORDER_DATE,
                        commonClass!!.getDate(Constants.inputFormat1), DBHelper.COLUMN_ORDER_NO, billNo, "0")
                dialog.dismiss()
                clearList()
                setFragment(OrderEnquiryListFragment(), 0)
            }
        }, Response.ErrorListener { error: VolleyError ->
            VolleyLog.e("Error : ", error.localizedMessage)
            dbHelper!!.updateOnlineStatus(DBHelper.TABLE_FUTURE_REQUIRED, DBHelper.COLUMN_ORDER_DATE,
                    commonClass!!.getDate(Constants.inputFormat1), DBHelper.COLUMN_ORDER_NO, billNo, "0")
            dialog.dismiss()
            clearList()
            setFragment(OrderEnquiryListFragment(), 0)
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

    private fun getNotificationCount() {
        val dialog = customDialog!!.loading(activity)
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.getNotificationcount(logindao.allItems.companyCode!!, logindao.allItems.customerCode!!)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<ResponseNotificationCount> {
            override fun onFailure(call: Call<ResponseNotificationCount>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                dialog.dismiss()
            }

            override fun onResponse(call: Call<ResponseNotificationCount>, response: retrofit2.Response<ResponseNotificationCount>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.isSuccessful) {
                    val message = response.body()!!.message
                    if (response.body()!!.status!!) {
                        val count = response.body()!!.data[0].totalCount
                        Log.e(TAG, "onResponse: count : " + count)
                        textCartItemCount.text = count

                    }
                }
                dialog.dismiss()
            }
        })
    }

    fun clearList() {
        // populate your List
        val complexObject = ListComplexObject()
        complexObject.products = null
        val complexPreferences = ComplexPreferences.getComplexPreferences(activity!!, "mypref", Context.MODE_PRIVATE)
        complexPreferences.putObject(Constants.list1, complexObject)
        complexPreferences.commit()
    }
}