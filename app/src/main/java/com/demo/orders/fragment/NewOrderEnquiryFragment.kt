package com.demo.orders.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.demo.orders.R
import com.demo.orders.activity.MainActivity
import com.demo.orders.adapter.NewOrderEnqueryAdapter
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DBHelper
import com.demo.orders.db.DatabaseList
import com.demo.orders.db.TinyDB
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.table.ResponseNotificationCount
import com.demo.orders.helper.*
import com.demo.orders.model.CustomersDataSource
import com.demo.orders.model.SelectionDataSource
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import kotlin.collections.ArrayList

class NewOrderEnquiryFragment : Fragment(), View.OnClickListener, CustomerListener, NewOrderEnqueryAdapter.ListAdapterListener {
    private lateinit var textCartItemCount: TextView
    var latitude: String? = null
    var longtiude: String? = null
    var total: String? = null
    var taxAmount: String? = null
    var taxPrice: String? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    var commonClass: CommonClass? = null
    var customDialog: CustomDialog? = null
    var staticData: StaticData? = null
    var databaseList: DatabaseList? = null
    private var tinyDB: TinyDB? = null
    var dbHelper: DBHelper? = null
    var bitmapUtility: BitmapUtility? = null
    var autoCompleteTextViewCustomerName: AutoCompleteTextView? = null
    var autoCompleteTextViewProductName: AutoCompleteTextView? = null
    lateinit var buttonSave: Button
    var newOrderEnqueryAdapter: NewOrderEnqueryAdapter? = null
    private var companySateCode: String? = null
    private var customerStateCode: String? = null
    private var customerImage: ByteArray? = null
    private var salesManID: String? = null
    private var salesManName: String? = null
    var textViewTotalPayAmount: TextView? = null

    // RecycleView adapter object
    private var mAdapter: Adapter? = null

    lateinit var dialog: AlertDialog
    var productList: MutableList<ProductsDataSource>? = ArrayList()
    var OrdersList: List<ProductsDataSource>? = null
    var newOrdersList: List<ProductsDataSource>? = null
    var customerList: List<SelectionDataSource>? = null
    lateinit var recyclerViewNewOrders: RecyclerView
    var mLayoutManager: RecyclerView.LayoutManager? = null
    private val TAG = "NewOrderFragment"
    var isViewChallan = false
    private var customerID = ""

    // List of all dictionary words
    private lateinit var productsList: MutableList<ProductsDataSource>
    private lateinit var filteredList: MutableList<ProductsDataSource>
    private var productsDataSourceList: ArrayList<ProductsDataSource>? = null
    private var newProductsList: List<ProductsDataSource> = ArrayList()
    private lateinit var filteredListCustomer: List<CustomersDataSource>
    var mListener: CustomerListener? = null
    var isBackPressed = true
    private var customerCount = 1
    private val billNo: String? = null
    private var backCount = 0
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao

    override fun onResume() {
        super.onResume()
        commonClass = CommonClass(activity!!)
        customDialog = CustomDialog(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        dbHelper = DBHelper(activity)
        bitmapUtility = BitmapUtility(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        commonClass = CommonClass(activity!!)
        customDialog = CustomDialog(activity)
        dbHelper = DBHelper(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        bitmapUtility = BitmapUtility(activity)
        tinyDB = TinyDB(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_new_order_enquery_list, container, false)

        /* Initialization */commonClass = CommonClass(activity!!)
        dbHelper = DBHelper(activity)
        customDialog = CustomDialog(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        bitmapUtility = BitmapUtility(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)
        logindao = appDatabase.loginDao()
        productList = ArrayList()
        OrdersList = ArrayList()
        newOrdersList = ArrayList()
        /* Set the Title*/
        val activity = activity as MainActivity?
        var actionBar: ActionBar? = null
        if (activity != null) {
            actionBar = activity.supportActionBar
        }
        actionBar?.title = resources.getString(R.string.New_Order_Enquiry)
        /* Toolbar Button Click*/
        //   activity.textViewToolbarTitle.setText("Challan History");
        autoCompleteTextViewCustomerName = rootView.findViewById<View>(R.id.autoCompleteTextViewCustomerName) as AutoCompleteTextView
        autoCompleteTextViewProductName = rootView.findViewById<View>(R.id.autoCompleteTextViewProductName) as AutoCompleteTextView
        buttonSave = rootView.findViewById(R.id.buttonSave)
        autoCompleteTextViewCustomerName!!.threshold = 1
        autoCompleteTextViewProductName!!.setOnClickListener(this)
        autoCompleteTextViewCustomerName!!.setOnClickListener(this)
        buttonSave.setOnClickListener(this)
        recyclerViewNewOrders = rootView.findViewById(R.id.recyclerViewNewOrders)
        mLayoutManager = LinearLayoutManager(getActivity())
        recyclerViewNewOrders.setHasFixedSize(true)
        recyclerViewNewOrders.layoutManager = mLayoutManager
        //get the customer List from Database
        customerList = databaseList!!.customerList
        customerStateCode = "TN"
        companySateCode = "TN"
        textViewTotalPayAmount = rootView.findViewById<TextView>(R.id.textViewTotalPayAmount)
        //Company State Code
        val companyCode = dbHelper!!.getTableValue(DBHelper.TABLE_COMPANY, DBHelper.COLUMN_STATE_CODE)
        if (companyCode == null) {
            customDialog!!.CustomToast(getActivity(), Constants.errorCompany, 0)
        } else {
            companySateCode = companyCode
        }

        Log.e(TAG, "companySateCode: $companySateCode")

        salesManName = ""
        salesManID = ""

        val bundle = arguments
        if (bundle != null) {
            isViewChallan = true
            customerCount = bundle.getInt(Constants.count)
            backCount = bundle.getInt(Constants.backcount)
            isBackPressed = bundle.getBoolean("isBackPressed")
            //            if (backCount==1) {
//               billNo = bundle.getString("billNo");
            val complexPreferences = ComplexPreferences.getComplexPreferences(activity, "mypref", Context.MODE_PRIVATE)
            val complexObject = complexPreferences.getObject(Constants.list1, ListComplexObject::class.java)
            if (complexObject != null) {
                if (complexObject.products != null) {
                    productList = complexObject.products as ArrayList<ProductsDataSource>
                    if (productList!!.size > 0) {
                        setAdapter(productList)
                    }
                }
            }
            autoCompleteTextViewCustomerName!!.setText(logindao.allItems.customerName)
            customerID = logindao.allItems.customerCode!!
            autoCompleteTextViewCustomerName!!.isFocusable = false
            autoCompleteTextViewCustomerName!!.isEnabled = false
        } else {
            clearList()
        }
        //get notification count from api
        getNotificationCount()

        handleBackPress(rootView)
        // Inflate the layout for this fragment
        return rootView
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_back, menu)
        val menuItem = menu.findItem(R.id.action_notification)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.hotlist_hot) as TextView
        val hotlist_bell = actionView.findViewById<ImageView>(R.id.hotlist_bell) as ImageView

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val id = item.itemId
        return when (id) {
            R.id.action_home -> {
                clearList()
                setFragment(RouteMenuFragment())
                true
            }
            R.id.action_back -> {
                clearList()
                setFragmentOrder(OrderEnquiryListFragment())
                Log.e("TAG", "new order backpreesed if in else called")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setAdapter(list: List<ProductsDataSource>?) {
        if (list != null && list.size > 0) {
            Log.e(TAG, "setAdapter: $list")
            total = commonClass!!.removeDigits(commonClass!!.getTotal(list).toString())
            textViewTotalPayAmount!!.text = commonClass!!.commaSplit(total!!.toDouble())
            newOrderEnqueryAdapter = NewOrderEnqueryAdapter(list, activity!!, this)
            recyclerViewNewOrders.adapter = newOrderEnqueryAdapter
        }
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment) {
        val bundl = Bundle()
        bundl.putBoolean("isBackPressed", true)
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    // class for being re-used by several instances
    protected fun setFragmentOrder(fragment: Fragment) {
        val bundl = Bundle()
        bundl.putBoolean("isBackPressed", true)
        bundl.putInt(Constants.count, customerCount)
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, productList: ArrayList<ProductsDataSource>?, orderNo: String?,
                              count: Int, backCount: Int) {
        // populate your List
        val complexObject = ListComplexObject()
        complexObject.products = null
        complexObject.products = productList
        val complexPreferences = ComplexPreferences.getComplexPreferences(activity!!, "mypref", Context.MODE_PRIVATE)
        complexPreferences.putObject(Constants.list1, complexObject)
        complexPreferences.commit()

        val bundl = Bundle()
        bundl.putInt(Constants.count, count)
        bundl.putInt(Constants.backcount, backCount)
        bundl.putBoolean("isBackPressed", true)
        bundl.putSerializable("product_list", productList)
        bundl.putString("billNo", orderNo)
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    fun clearList() {
        // populate your List
        val complexObject = ListComplexObject()
        complexObject.products = null
        val complexPreferences = ComplexPreferences.getComplexPreferences(activity!!, "mypref", Context.MODE_PRIVATE)
        complexPreferences.putObject(Constants.list1, complexObject)
        complexPreferences.commit()
    }

    /* Backpress Clicked */
    private fun handleBackPress(view: View) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!isBackPressed) {
                    clearList()
                    setFragmentOrder(OrderEnquiryListFragment())
                    Log.e("TAG", "new order backpreesed if in else called")
                } else if (isBackPressed) {
                    isBackPressed = false
                    Log.e("TAG", "new order backpreesed elseif called")
                }
            }
            Log.e("TAG", "new order backpreesed if in main called")
            false
        }
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.autoCompleteTextViewProductName -> setProducts()
            R.id.buttonSave -> validation()

        }
    }

    private fun setProducts() {
        val customerName = autoCompleteTextViewCustomerName!!.text.toString()
        if (customerName.length > 0) {
            if (logindao.allItems != null) {
                val loyalty = logindao.allItems.loyalty
                val currentDate = commonClass!!.getDate(Constants.inputFormat1)
                val discount = logindao.allItems.schemeDiscount
                newProductsList = databaseList!!.getOrderProductsList(currentDate, customerID,
                        logindao.allItems.priceCode, logindao.allItems.salesorgCode,
                        logindao.allItems.schemeDiscount, logindao.allItems.loyalty)
                if (newProductsList.size > 0) {
                    showProductList()
                } else {
                    commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.no_product))
                }
            }
        } else {
            commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.choose_cus))
        }
    }

    //  orders validation
    fun validation() {
        if (commonClass == null) {
            commonClass = CommonClass(activity!!)
        }
        if (customDialog == null) {
            customDialog = CustomDialog(activity)
        }
        if (dbHelper == null) {
            dbHelper = DBHelper(activity)
        }
        val customerName = autoCompleteTextViewCustomerName!!.text.toString()
        var orderBillNo: String? = null
        var newBillNo: String? = null
        if (customerName.length > 0) {
            if (customerID.length > 0) {
                if (productList != null) {
                    if (productList!!.size > 0) {
                        orderBillNo = databaseList!!.getBillNoDB(Constants.orders)
                        orderBillNo = if (orderBillNo == null) {
                            Constants.orderBill + "/" + logindao.allItems.customerCode + "/" + commonClass!!.getDate(Constants.inputFormat5) + "/"
                        } else {
                            orderBillNo + "/" + logindao.allItems.customerCode + "/" + commonClass!!.getDate(Constants.inputFormat5) + "/"
                        }
                        val getLastBillNo = databaseList!!.getBillDB(Constants.orders, logindao.allItems.customerCode)
                        if (getLastBillNo != null) {
                            val seprate = getLastBillNo.split("/".toRegex()).toTypedArray()
                            val value = seprate[3].toInt()
                            val increaseBy = value + 1
                            newBillNo = if (increaseBy < 10) {
                                orderBillNo + "0000" + increaseBy
                            } else if (increaseBy < 100) {
                                orderBillNo + "000" + increaseBy
                            } else if (increaseBy < 1000) {
                                orderBillNo + "00" + increaseBy
                            } else if (increaseBy < 10000) {
                                orderBillNo + "0" + increaseBy
                            } else {
                                orderBillNo + increaseBy
                            }
                            println(newBillNo)
                            Log.e(TAG, "LAST BILL NO DB: $newBillNo")
                        } else {
                            newBillNo = orderBillNo + "000" + 1
                            println(newBillNo)
                            Log.e(TAG, "BILL NO : $newBillNo")
                        }
                        setFragment(OrderPreviewFragment(), productList as ArrayList<ProductsDataSource>?, newBillNo, customerCount, 1)
                    } else {
                        commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.add_product))
                    }
                }
            } else {
                commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.add_customer))
            }
        } else {
            commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.choose_cus))
        }
    }

    //Show the products List
    fun showProductList() {
        val alertDialog = AlertDialog.Builder(context!!)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.fragment_products_add_list, null)
        // View title = inflater.inflate(R.layout.custom_alert_title, null);
        //set Custom Title
        val textViewTitle = view.findViewById<View>(R.id.textViewTitle) as TextView
        textViewTitle.setText(R.string.choose_product)
        // alertDialog.setCustomTitle(title);
        alertDialog.setView(view)
        dialog = alertDialog.create()
        if (staticData == null) {
            staticData = StaticData(activity)
        }
        if (databaseList == null) {
            databaseList = DatabaseList(activity)
        }
        productsList = ArrayList()
        // newProductsList = databaseList.getProductDB();
        if (newProductsList.size > 0) {
//Duplicate List
            productsList.addAll(0, newProductsList)
            // Map Is Used for Remove the Duplicate data from the list based on Product ID
            val map: MutableMap<String, ProductsDataSource> = LinkedHashMap()
            for (ays in productsList) {
                //* This line make the  trick
                map[ays.materialCode] = ays
            }
            productsList.clear()
            productsList.addAll(map.values)
        }
        filteredList = ArrayList()
        productsDataSourceList = java.util.ArrayList()
        filteredList.addAll(productsList)
        productsDataSourceList!!.addAll(productsList)
        val rView = view.findViewById<View>(R.id.recyclerViewProductList) as RecyclerView
        val buttonoffenceListCancel = view.findViewById<View>(R.id.buttonoffenceListCancel) as Button
        val buttonoffenceListOk = view.findViewById<View>(R.id.buttonoffenceListOk) as Button
        val autoCompleteTextViewProductsName = view.findViewById<View>(R.id.autoCompleteTextViewProductsName) as AutoCompleteTextView
        //Grid Layout
        val lLayout = GridLayoutManager(activity, 2)
        //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rView.setHasFixedSize(true)
        rView.layoutManager = lLayout
        if (filteredList.size > 0) {
            mAdapter = Adapter(filteredList)
            rView.adapter = mAdapter
        }
        // search suggestions using the edittext widget
        autoCompleteTextViewProductsName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter!!.filter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
        dialog.show()
        buttonoffenceListOk.setOnClickListener {
            val list: MutableList<ProductsDataSource> = ArrayList()
            for (dataSource in productsDataSourceList!!) {
                if (dataSource.isChecked) {
                    if (companySateCode != null && customerStateCode != null) {
                        val netTotal = commonClass!!.multiply(dataSource.productQty, dataSource.netAmount)
                        val amoutTotal = commonClass!!.multiply(dataSource.productQty, dataSource.amount)
                        val totalTaxAmount = commonClass!!.multiply(dataSource.productQty, dataSource.productTaxValue)
                        val productRate = commonClass!!.productSubtract(dataSource.netAmount,
                                commonClass!!.multiply(dataSource.productTaxValue,
                                        dataSource.productQty))
                        if (customerStateCode == companySateCode) {
                            val Amount = commonClass!!.getDivision(commonClass!!.multiply(dataSource.priceTax, dataSource.netAmount).toDouble(), 100.0)
                            val Percent = commonClass!!.getDivision(dataSource.priceTax.toDouble(), 2.0)
                            val taxPercent = Percent.toString()
                            val taxAmount = java.lang.String.valueOf(commonClass!!.getDivision(Amount, 2.0))
                            list.add(ProductsDataSource(dataSource.materialCode, dataSource.materialName,
                                    dataSource.amount, dataSource.productQty, dataSource.productTotal,
                                    dataSource.priceTax, dataSource.productTaxValue,
                                    dataSource.getproductUOM(), dataSource.currentDate, dataSource.priceTax,
                                    dataSource.taxSGST, dataSource.taxSGSTAmount,
                                    dataSource.taxCGST, dataSource.taxCGSTAmount, null, null,
                                    customerStateCode, companySateCode, dataSource.productImage,
                                    dataSource.availableQty, netTotal, amoutTotal, totalTaxAmount, productRate,
                                    dataSource.netAmount, dataSource.discountFromQty,
                                    dataSource.discountToQty, dataSource.discount, dataSource.discountType,
                                    dataSource.discountWot, dataSource.discountFrom, dataSource.discountTill,
                                    dataSource.dayCount, dataSource.replace_quantity, dataSource.soldQuantity,
                                    dataSource.scheme, dataSource.schemeCode, dataSource.schemeName,
                                    dataSource.schemeQty, dataSource.schemeFreeQty, dataSource.schemeFrom,
                                    dataSource.schemeTill, dataSource.schemeType, dataSource.schemeMaterial1,
                                    dataSource.schemeMaterial2, "0", "main", "Placed"))
                        } else if (customerStateCode != companySateCode) {
                            list.add(ProductsDataSource(dataSource.materialCode, dataSource.materialName,
                                    dataSource.amount, dataSource.productQty, dataSource.productTotal,
                                    dataSource.priceTax, dataSource.productTaxValue, dataSource.getproductUOM(),
                                    dataSource.currentDate, dataSource.priceTax, null, null,
                                    null, null, dataSource.taxIGST, dataSource.taxIGSTAmount,
                                    customerStateCode, companySateCode, dataSource.productImage,
                                    dataSource.availableQty, netTotal, amoutTotal, totalTaxAmount, productRate,
                                    dataSource.netAmount, dataSource.discountFromQty, dataSource.discountToQty,
                                    dataSource.discount, dataSource.discountType, dataSource.discountWot,
                                    dataSource.discountFrom, dataSource.discountTill, dataSource.dayCount,
                                    dataSource.replace_quantity, dataSource.soldQuantity, dataSource.scheme,
                                    dataSource.schemeCode, dataSource.schemeName, dataSource.schemeQty,
                                    dataSource.schemeFreeQty, dataSource.schemeFrom, dataSource.schemeTill,
                                    dataSource.schemeType, dataSource.schemeMaterial1,
                                    dataSource.schemeMaterial2, "0", "main", "Placed"))
                        }

                    }
                }
            }
            if (list.size > 0) {
//Duplicate List
                productList!!.addAll(0, list)
                // Map Is Used for Remove the Duplicate data from the list based on Product ID
                val map: MutableMap<String, ProductsDataSource> = LinkedHashMap()
                for (ays in productList!!) {
                    //* This line make the  trick
                    map[ays.materialCode] = ays
                }
                productList!!.clear()
                productList!!.addAll(map.values)

                /* Set Adapter*/setAdapter(productList)
                /*  Move To First Position of the View */recyclerViewNewOrders.scrollToPosition(0)
            } else {
                customDialog!!.CustomToast(activity, resources.getString(R.string.No_Products_Available), "fail")
            }
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        buttonoffenceListCancel.setOnClickListener { dialog.dismiss() }
    }


    override fun onClickButton(position: Int, dataSource: CustomersDataSource, customerAccountDataSource: CustomersDataSource) {
        customerID = dataSource.customerID
        customerImage = dataSource.customerImageByte
        autoCompleteTextViewCustomerName!!.setText(dataSource.customerName)
    }


    inner class Adapter(item: List<ProductsDataSource>?) : RecyclerView.Adapter<Adapter.ViewHolder>(), Filterable {
        private var items: List<ProductsDataSource>? = ArrayList()
        private val mFilter: CustomFilter
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context
            val layoutForItem = R.layout.fragment_products_add_list_item
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(layoutForItem, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return if (items == null) {
                0
            } else items!!.size
        }

        override fun getFilter(): Filter {
            return mFilter
        }

        fun loadItems(tournaments: List<ProductsDataSource>?) {
            items = tournaments
            notifyDataSetChanged()
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            var textViewProductName: TextView
            var textViewPrice: TextView
            var checkBoxProduct: CheckBox
            var imageViewProduct: ImageView
            override fun toString(): String {
                return super.toString() + " '" + textViewProductName.text + "'"
            }

            fun bind(position: Int) {
                // check the state of the model
                checkBoxProduct.isChecked = items!![position].isChecked
                val dataSource = items!![position]
                textViewProductName.text = dataSource.materialName
                textViewPrice.text = resources.getString(R.string.Rs) + dataSource.amount
                if (dataSource.productImage != "") {
                    Glide.with(activity!!).asBitmap()
                            .load(dataSource.productImage)
                            .into(imageViewProduct)
                } else {
                    imageViewProduct.setImageResource(R.drawable.product_holder)
                    checkBoxProduct.tag
                }
            }

            override fun onClick(v: View) {
                val adapterPosition = adapterPosition
                if (!items!![adapterPosition].isChecked) {
                    for (newupdate in productsDataSourceList!!) {
                        if (newupdate.materialCode.equals(items!![adapterPosition].materialCode)) {
                            checkBoxProduct.isChecked = true
                            items!![adapterPosition].isChecked = true
                            newupdate.isChecked = true
                        }
                    }
                } else {
                    for (newupdate in productsDataSourceList!!) {
                        if (newupdate.materialCode.equals(items!![adapterPosition].materialCode)) {
                            checkBoxProduct.isChecked = false
                            items!![adapterPosition].isChecked = false
                            newupdate.isChecked = false
                        }
                    }
                }
            }

            init {
                imageViewProduct = view.findViewById(R.id.imageViewProduct)
                checkBoxProduct = view.findViewById(R.id.checkBoxProduct)
                textViewProductName = view.findViewById(R.id.textViewProductName)
                textViewPrice = view.findViewById(R.id.textViewPrice)
                checkBoxProduct.setOnClickListener(this)
                view.tag = view
            }
        }

        inner class CustomFilter(private val mAdapter: Adapter) : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                filteredList.clear()
                val results = FilterResults()
                if (constraint.length == 0) {
                    filteredList.addAll(productsList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                    for (mWords in productsList) {
                        if (mWords.materialName.toLowerCase().startsWith(filterPattern)) {
                            mWords.isChecked = mWords.isChecked
                            filteredList.add(mWords)
                        }
                    }
                }
                println("Count Number " + filteredList.size)
                results.values = filteredList
                results.count = filteredList.size
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                mAdapter.notifyDataSetChanged()
            }

        }

        init {
            items = item
            mFilter = CustomFilter(this@Adapter)
        }
    }


    override fun onClickProducts(action: String?, products: List<ProductsDataSource?>?, position: Int) {
        if (action == "remove") {
            if (newOrderEnqueryAdapter != null) {
                customDialog!!.customAlert(activity, products, position, newOrderEnqueryAdapter, resources.getString(R.string.string_alert_product))
            } else {
                customDialog!!.customAlert(activity, products, position)
            }
        } else {
            if (products!!.isNotEmpty()) {
                total = commonClass!!.commaSplit(commonClass!!.getTotalTax(products as List<ProductsDataSource>, "amount"))
                textViewTotalPayAmount!!.text = total

            } else {
                textViewTotalPayAmount!!.text = " "
            }
        }
    }
}