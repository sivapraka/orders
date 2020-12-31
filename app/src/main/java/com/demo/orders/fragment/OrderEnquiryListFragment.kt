package com.demo.orders.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.demo.orders.R
import com.demo.orders.activity.MainActivity
import com.demo.orders.adapter.OrderEnquiryListAdapter
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DBHelper
import com.demo.orders.db.DatabaseList
import com.demo.orders.db.TinyDB
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.table.OrdersDataSourceTable
import com.demo.orders.db.table.ResponseNotificationCount
import com.demo.orders.db.table.ResponseOrdersDataSource
import com.demo.orders.db.table.StatusResponse
import com.demo.orders.helper.*
import com.demo.orders.retrofit.ApiService
import com.demo.orders.retrofit.RetrofitClientInstance
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import com.demo.orders.util.Utils
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

class OrderEnquiryListFragment : Fragment(), OrderEnquiryListAdapter.ListAdapterListener, View.OnClickListener {
    private lateinit var textCartItemCount: TextView
    private lateinit var dialog: AlertDialog
    private var dialogBottom: BottomSheetDialog? = null
    internal lateinit var userImage: UserImage
    var queryAdd: FloatingActionButton? = null
    var recyclerViewEnquiryList: RecyclerView? = null
    var commonClass: CommonClass? = null
    var customDialog: CustomDialog? = null
    var staticData: StaticData? = null
    var databaseList: DatabaseList? = null
    var customerCount = 0
    var list = ArrayList<OrdersDataSourceTable>()
    var dbHelper: DBHelper? = null
    lateinit var recyclerorder: RecyclerView
    var mLayoutManager: RecyclerView.LayoutManager? = null
    var textViewNoData: TextView? = null
    var bitmapUtility: BitmapUtility? = null
    private val TAG = "OrderListFragment"
    var isBackPressed = true
    private var tinyDB: TinyDB? = null
    lateinit var odersList: List<OrdersDataSourceTable>
    private var adapter: OrderEnquiryListAdapter? = null
    lateinit var appDatabase: AppDatabase
    lateinit var logindao: LoginDao
    lateinit var swipeReferesh: SwipeRefreshLayout
    internal var isLoading = false
    internal var pagecount = 0
    private var swipeRefreshCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val rootView = inflater.inflate(R.layout.fragment_enquery_list, container, false)
        recyclerorder = rootView.findViewById(R.id.recyclerViewEnquiryList)
        textViewNoData = rootView.findViewById(R.id.ordertextViewNoData)
        swipeReferesh = rootView.findViewById(R.id.swipeReferesh)
        recyclerorder.layoutManager = LinearLayoutManager(activity)
        recyclerorder.setHasFixedSize(true)
        dbHelper = DBHelper(activity)
        bitmapUtility = BitmapUtility(activity)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        userImage = UserImage(activity)
        customDialog = CustomDialog(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)
        logindao = appDatabase.loginDao()
        odersList = ArrayList()
        queryAdd = rootView.findViewById<View>(R.id.queryAdd) as FloatingActionButton
        setHasOptionsMenu(true)
        /* Set the Title*/
        val activity = activity as MainActivity?
        var actionBar: ActionBar? = null
        if (activity != null) {
            actionBar = activity.supportActionBar
        }
        actionBar?.title = resources.getString(R.string.Orderrrr)
        val bundle = arguments
        if (bundle != null) {
            isBackPressed = bundle.getBoolean("isBackPressed")
            customerCount = bundle.getInt(Constants.count)
            if (customerCount == 2) {
                setHasOptionsMenu(false)
            }
        }
        // LinearLayout for normal Listview
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getActivity())
        recyclerViewEnquiryList = rootView.findViewById<View>(R.id.recyclerViewEnquiryList) as RecyclerView
        //Grid Layout
        //  GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);
        recyclerViewEnquiryList!!.setHasFixedSize(true)
        recyclerViewEnquiryList!!.layoutManager = mLayoutManager
        queryAdd!!.setOnClickListener(this)
        networkcheck()
        swipeReferesh.setOnRefreshListener {
            swipeReferesh.isRefreshing = true
            if (commonClass!!.isNetworkAvailable(activity!!)) {
                list.clear()
                swipeRefreshCount = 1
                pagecount = 0
                networkcheck()
            }
        }
        initScrollListener()


        handleBackPress(rootView)
        // Inflate the layout for this fragment
        return rootView
    }

    fun networkcheck() {
        if (commonClass!!.isNetworkAvailable(activity!!)) {
            getOrders(logindao.allItems.customerCode!!)
        }
    }

    private fun initScrollListener() {
        recyclerViewEnquiryList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                } else {
                    isLoading = false
                }
            }
        })
    }

    private fun loadMore() {
        if (list.isNotEmpty() && list.size > 0) {
            val nextLimit = list.size + 1
            swipeRefreshCount = 0
            Log.e(TAG, "Pagecount : " + nextLimit)
            if (commonClass!!.isNetworkAvailable(activity!!)) {
                getOrdersList(logindao.allItems.customerCode!!, nextLimit)
                isLoading = false
            }
        }
    }


    private fun setAdapter(list: List<OrdersDataSourceTable>?, isCancel: Boolean) {
        if (list != null) {
            adapter = OrderEnquiryListAdapter(list, activity!!, this, isCancel)
            recyclerViewEnquiryList!!.adapter = adapter
        }
        if (list!!.size > 0) {
            recyclerViewEnquiryList!!.visibility = View.VISIBLE
            textViewNoData!!.visibility = View.GONE
        } else {
            recyclerViewEnquiryList!!.visibility = View.GONE
            textViewNoData!!.visibility = View.VISIBLE
        }
        updateBillDetails(list)
    }

    fun updateBillDetails(list: List<OrdersDataSourceTable>?) {
        //    textViewTotalBills!!.text = list!!.size.toString()
    }

    // If the List is canceled we need to updated the list
    override fun updatedList(salesDataSourceTableList: List<OrdersDataSourceTable>?) {
        updateBillDetails(salesDataSourceTableList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonClass = CommonClass(context)
        staticData = StaticData(activity)
        customDialog = CustomDialog(activity)
        databaseList = DatabaseList(activity)
    }

    /* View Click*/
    override fun onClick(v: View) {
        val id = v.id
        Log.e(TAG, "onClick:  ID :$id")
        when (id) {
            R.id.queryAdd -> {
                if (commonClass!!.isNetworkAvailable(activity!!)) {
                    checkCutOffTime()
                } else {
                    commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, resources.getString(R.string.network_error))
                }
            }
        }
    }

    fun clearList() {
        // populate your List
        val complexObject = ListComplexObject()
        complexObject.products = null
        val complexPreferences = ComplexPreferences.getComplexPreferences(activity!!, "mypref", Context.MODE_PRIVATE)
        complexPreferences.putObject(Constants.list1, complexObject)
        complexPreferences.commit()
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment) {
        val bundl = Bundle()
        bundl.putBoolean("viewChallan", true)
        bundl.putInt(Constants.count, customerCount)
        fragment.arguments = bundl
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, salesList: OrdersDataSourceTable) {
        val bundl = Bundle()
        bundl.putSerializable(Constants.list2, salesList as Serializable?)
        fragment.arguments = bundl
        //Make the Transition while moving to Next Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform)
            //If any transition found remove that
            exitTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.slide_left)
            fragment.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform)
            fragment.enterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.slide_left)
            val imageView = ImageView(activity)
            imageView.setBackgroundResource(R.drawable.logo)
            imageView.transitionName = "MyTransition"
            val t = this.fragmentManager!!.beginTransaction()
            t.addToBackStack(null)
            //This line make the tricks to Make the transition
            t.addSharedElement(imageView, "MyTransition")
            t.replace(R.id.container_body, fragment)
            t.commit()
        } else {
            setFragmentWithoutTransition(fragment)
        }
    }

    private fun handleBackPress(view: View) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!isBackPressed) {
                    if (customerCount == 2) {
                        Log.e("TAG", "new sales backpreesed if in if called")
                    } else {
                        setFragment(RouteMenuFragment())
                        Log.e("TAG", "new sales backpreesed if in else called")
                    }
                } else if (isBackPressed) {
                    isBackPressed = false
                }
            }
            true
        }
    }

    override fun onClickButton(position: Int, salesList: List<OrdersDataSourceTable>?, name: String?, action: String?) {
        Log.e(TAG, "POSITION: $position")
        Log.e(TAG, "action: $action")
        setFragment(OrderNewPreviewFragment(), salesList!![position]!!)
    }

    // class for being re-used by several instances
    protected fun setFragment(fragment: Fragment, productList: List<ProductsDataSource?>?, orderNo: String?,
                              count: Int, backCount: Int) {
        val bundl = Bundle()
        bundl.putBoolean("ispressed", true)
        bundl.putInt(Constants.count, count)
        bundl.putInt(Constants.backcount, backCount)
        bundl.putSerializable("product_list", productList as Serializable?)
        bundl.putString("billNo", orderNo)
        fragment.arguments = bundl
        //Make the Transition while moving to Next Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform)
            //If any transition found remove that
            exitTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.slide_left)
            fragment.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform)
            fragment.enterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.slide_left)
            val imageView = ImageView(activity)
            imageView.setBackgroundResource(R.drawable.logo)
            imageView.transitionName = "MyTransition"
            val t = this.fragmentManager!!.beginTransaction()
            t.addToBackStack(null)
            //This line make the tricks to Make the transition
            t.addSharedElement(imageView, "MyTransition")
            t.replace(R.id.container_body, fragment)
            t.commit()
        } else {
            setFragmentWithoutTransition(fragment)
        }
    }

    //Without transition move to Next Fragment
    private fun setFragmentWithoutTransition(fragment: Fragment) {
        val t = this.fragmentManager!!.beginTransaction()
        t.addToBackStack(null)
        t.replace(R.id.container_body, fragment)
        t.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_notification_home, menu)
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
                if (customerCount == 2) {

                } else {
                    setFragment(RouteMenuFragment())
                    Log.e("TAG", "new sales backpreesed if in else called")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getOrders(customerCode: String) {
//        dialog= customDialog!!.loading(activity)
        if (!swipeReferesh.isRefreshing) {
            dialog = customDialog!!.loading(activity)
        }
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.KEY_BASEURL)
        val listCall = service.getOrders(customerCode)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<ResponseOrdersDataSource> {
            override fun onResponse(call: Call<ResponseOrdersDataSource>, response: Response<ResponseOrdersDataSource>) {
                Log.e(TAG, "onResponse: notification : " + response)
                if (response.body() != null && response.body()!!.data != null) {
//                    Log.e(TAG, "onResponse: notification : " + response.body()!!.status)
                    var odersList = response.body()!!.data as ArrayList<OrdersDataSourceTable>
                    Log.e(TAG, "invoice list size : " + odersList.size)

                    if (odersList.size > 0) {
                        dbHelper!!.deleteTable(DBHelper.TABLE_FUTURE_REQUIRED)
                        for (dataSource in odersList) {
//
                            if (dataSource.igst != "") {
                                dbHelper!!.insertProductDetails(dataSource.orderNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, "",
                                        dataSource.quantity, dataSource.amount, dataSource.schemeDiscount,
                                        "", dataSource.taxPercent, commonClass!!.removeDigits(dataSource.totalTax), "", "",
                                        dataSource.unitPrice, dataSource.subTotal, dataSource.subTotal, logindao.allItems.customerCode!!,
                                        commonClass!!.getDate(Constants.inputFormat1),
                                        dataSource.schemeDiscount, "", dataSource.totalTax, dataSource.subTotal, dataSource.subTotal,
                                        dataSource.orderNo,
                                        dataSource.sgst!!, dataSource.sgst!!, dataSource.totalTax, "", "",
                                        dataSource.taxPercent, "1", Constants.tripID, dataSource.productType, dataSource.status, logindao.allItems.loyalty, logindao.allItems)
                            } else {
                                dbHelper!!.insertProductDetails(dataSource.orderNo, commonClass!!.getDate(Constants.inputFormat1), dataSource.materialCode, dataSource.materialName, "",
                                        dataSource.quantity, dataSource.amount, dataSource.schemeDiscount,
                                        "", dataSource.taxPercent, dataSource.totalTax, "", "",
                                        dataSource.unitPrice, dataSource.subTotal, dataSource.subTotal, logindao.allItems.customerCode!!,
                                        commonClass!!.getDate(Constants.inputFormat1),
                                        dataSource.schemeDiscount, "", dataSource.totalTax, dataSource.subTotal, dataSource.amount,
                                        dataSource.orderNo,
                                        dataSource.sgst, dataSource.sgst, "", dataSource.sgst, dataSource.sgst,
                                        "", "1", Constants.tripID, dataSource.productType, dataSource.status, logindao.allItems.loyalty, logindao.allItems)
                            }

                        }
                    }
                }
                getMaterial_master(activity!!, Utils.KEY_BASEURL + Utils.KEY_MATERIAL)
            }

            override fun onFailure(call: Call<ResponseOrdersDataSource>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.localizedMessage!!)
                getMaterial_master(activity!!, Utils.KEY_BASEURL + Utils.KEY_MATERIAL)
            }
        })
    }

    private fun getNotificationCount() {
        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
        val listCall = service.getNotificationcount(logindao.allItems.companyCode!!, logindao.allItems.customerCode!!)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<ResponseNotificationCount> {
            override fun onFailure(call: Call<ResponseNotificationCount>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                if (swipeReferesh.isRefreshing) {
                    swipeReferesh.isRefreshing = false
                    swipeRefreshCount = 0
                } else if (dialogBottom != null && dialogBottom!!.isShowing) {
                    customDialog!!.dismissLoading(dialogBottom)
                } else {
                    dialog.dismiss()
                }

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
                if (swipeReferesh.isRefreshing) {
                    swipeReferesh.isRefreshing = false
                    swipeRefreshCount = 0
                } else if (dialogBottom != null && dialogBottom!!.isShowing) {
                    customDialog!!.dismissLoading(dialogBottom)
                } else {
                    dialog.dismiss()
                }
            }
        })
    }


    fun getMaterial_master(context: Context, URL: String) {
        Log.e(TAG, "getMaterial_master: $URL")
        val postRequest = object : StringRequest(Request.Method.GET, URL, { response ->
            try {
                val body = JSONArray(response)
                dbHelper!!.deleteTable("material_master")
                for (i in 0 until body.length()) {
                    val data = body.getJSONObject(i)
                    val material_code = data.getString(APIKey.KEY_MATERIAL_CODE)
                    val material_name = data.getString(APIKey.KEY_MATERIAL_NAME)
                    val uom = data.getString(APIKey.KEY_UOM)
                    val taxdetails = data.getString(APIKey.KEY_TAXDETAILS)
                    val net_weight = data.getString(APIKey.KEY_NET_WEIGHT)
                    val gross_weight = data.getString(APIKey.KEY_GROSS_WEIGHT)
                    val company_code = data.getString(APIKey.KEY_COMPANY_CODE)
                    val plant_code = data.getString(APIKey.KEY_PLANT_CODE)
                    val salesorg_code = data.getString(APIKey.KEY_SALESORG_CODE)
                    val material_category = data.getString(APIKey.KEY_MATERIAL_CATGORY)
                    val last_modified = data.getString(APIKey.KEY_LAST_MODIFIED)
                    val material_image = data.getString(APIKey.KEY_MATERIAL_IMAGE)
                    val stateCode = "TN"

                    var img: String = ""
                    try {
                        if (material_image != null && material_image.length > 0 && material_image != "null" && material_image != "Empty") {
                            img = Utils.KEY_BASEURL + material_image
                        } else {
                            img = ""
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e(TAG, "login: " + e.localizedMessage)
                    }


                    //storing memberId
                    try {
                        dbHelper!!.insertMaterialMaster(material_code, material_name, uom, taxdetails,
                                company_code, plant_code, salesorg_code, material_category, net_weight, img)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                getOrdersList(logindao.allItems.customerCode!!, pagecount)
                //                }
            } catch (e: JSONException) {
                e.localizedMessage
                getOrdersList(logindao.allItems.customerCode!!, pagecount)

            }


        }, { error ->
            VolleyLog.e("Error : ", error.localizedMessage)
            getOrdersList(logindao.allItems.customerCode!!, pagecount)
        }) {
            override fun getParams(): Map<String, String> {
//                params.put(APIKey.phone, mobileNo);
                return HashMap()
            }

            override fun getHeaders(): Map<String, String> {
                val auth = commonClass!!.auth()
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

    private fun checkCutOffTime() {
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.KEY_BASEURL)
        val listCall = service.checkCutOff(logindao.allItems.companyCode!!, logindao.allItems.customerCode!!)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<StatusResponse> {
            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                Log.e(TAG, "onResponse: notification : " + response)
                if (response.isSuccessful && response.body() != null && response.body()!!.status!!) {
                    clearList()
                    setFragment(NewOrderEnquiryFragment())
                } else if (response.isSuccessful && response.body() != null && response.body()!!.message != null) {
                    val messageResponse = response.body()!!.message!!
                    commonClass!!.setSweetAlertDialog(SweetAlertDialog.ERROR_TYPE, messageResponse)
                }
            }
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.localizedMessage!!)

            }
        })
    }

    private fun getOrdersList(customerCode: String, pageCount: Int) {
        if (pageCount > 20) {
            dialogBottom = customDialog!!.loadingBottom(activity!!)
        }
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.KEY_BASEURL)
        val listCall = service.getOrdersList(customerCode, pageCount)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<ResponseOrdersDataSource> {
            override fun onResponse(call: Call<ResponseOrdersDataSource>, response: Response<ResponseOrdersDataSource>) {
                Log.e(TAG, "onResponse: notification : " + response)
                var odersList = ArrayList<OrdersDataSourceTable>()
                if (response.body() != null && response.body()!!.data != null) {
                    odersList = response.body()!!.data as ArrayList<OrdersDataSourceTable>

                }
                if (pageCount == 0) {
                    setAdapter(odersList, true)
                } else {
                    list.addAll(odersList)
                    adapter!!.notifyDataSetChanged()
                }
                getNotificationCount()
            }

            override fun onFailure(call: Call<ResponseOrdersDataSource>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.localizedMessage!!)
                getNotificationCount()
            }
        })
    }
}