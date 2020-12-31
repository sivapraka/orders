package com.demo.orders.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.orders.R
import com.demo.orders.adapter.NavigationDrawerAdapter
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DatabaseList
import com.demo.orders.db.TinyDB
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.dao.MobileDao
import com.demo.orders.db.table.MobileMenuTable
import com.demo.orders.helper.CommonClass
import com.demo.orders.helper.CustomDialog
import com.demo.orders.helper.StaticData
import com.demo.orders.model.UserDataSource
import com.demo.orders.util.Constants


class FragmentDrawer : Fragment() {

    private lateinit var textCartItemCount: TextView
    private var recyclerView: RecyclerView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var adapter: NavigationDrawerAdapter? = null
    private var containerView: View? = null
    private var drawerListener: FragmentDrawerListener? = null
    private var staticData: StaticData? = null
    private lateinit var customDialog: CustomDialog
    private lateinit var tinyDB: TinyDB
    internal lateinit var relativeLayoutHeader: RelativeLayout
    internal lateinit var userImage: ImageView
    internal lateinit var close: ImageView
    internal lateinit var userName: TextView
    internal lateinit var commonClass: CommonClass
    internal lateinit var databaseList: DatabaseList
    internal lateinit var userList: List<UserDataSource>
    internal lateinit var appDatabase: AppDatabase
    lateinit var invoiceDAO: InvoiceDAO
    lateinit var logindao: LoginDao
    lateinit var mobileMenuDao: MobileDao
    private lateinit var dialog: AlertDialog
    val TAG = FragmentDrawer::class.java.simpleName

    fun setDrawerListener(listener: FragmentDrawerListener) {
        this.drawerListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseList = DatabaseList(activity)
        commonClass = CommonClass(activity!!)
        customDialog = CustomDialog(activity!!)
        setHasOptionsMenu(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_notification, menu)
        val menuItem = menu.findItem(R.id.action_notification)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.hotlist_hot) as TextView
        val hotlist_bell = actionView.findViewById<ImageView>(R.id.hotlist_bell) as ImageView
        hotlist_bell.setOnClickListener {
            val fragment = FragmentNotification()
            val title = getString(R.string.title_notification)
            setFragment(fragment)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

//
//    private fun getNotificationCount() {
//        dialog = customDialog!!.loading(activity)
//        val service = RetrofitClientInstance.createServices(ApiService::class.java, tinyDB!!.getString(Constants.baseURL))
//        val listCall = service.getNotificationcount(logindao.allItems.companyCode!!, logindao.allItems.customerCode!!)
//        Log.e(TAG, "listCall : " + listCall.request())
//        listCall.enqueue(object : Callback<ResponseNotificationCount> {
//            override fun onFailure(call: Call<ResponseNotificationCount>, t: Throwable) {
//                Log.e(TAG, "onFailure : " + t.localizedMessage)
//                dialog.dismiss()
//
//            }
//
//            override fun onResponse(call: Call<ResponseNotificationCount>, response: retrofit2.Response<ResponseNotificationCount>) {
//                Log.e(TAG, "onResponse : " + response)
//                if (response.isSuccessful) {
//                    val message = response.body()!!.message
//                    if (response.body()!!.status!!) {
//                        val count = response.body()!!.data[0].totalCount
//                        Log.e(TAG, "onResponse: count : "+count )
//                        textCartItemCount.setText(count)
//                        dialog.dismiss()
//                    } else {
//                        dialog.dismiss()
//                    }
//                }
//            }
//        })
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflating view layout

        val layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
        recyclerView = layout.findViewById<View>(R.id.drawerList) as RecyclerView
        close = layout.findViewById(R.id.close)
        staticData = StaticData(activity)
        databaseList = DatabaseList(activity)
        commonClass = CommonClass(activity!!)
        tinyDB = TinyDB(activity)
        appDatabase = AppDatabase.getDatabase(activity!!)
        invoiceDAO = appDatabase.invoiceDAO()
        logindao = appDatabase.loginDao()
        mobileMenuDao = appDatabase.mobileMenuDao()

        val languageCode = tinyDB.getString(Constants.languagecode)
        var rowListItem: List<MobileMenuTable>
        if (languageCode != "") {
            rowListItem = mobileMenuDao.menu(Constants.menu, languageCode)
        } else {
            rowListItem = mobileMenuDao.menu(Constants.menu, Constants.languagecode)
        }

        if (rowListItem != null && rowListItem.size > 0) {
            adapter = NavigationDrawerAdapter(activity!!, rowListItem)
            recyclerView!!.adapter = adapter
        }
        relativeLayoutHeader = layout.findViewById<View>(R.id.nav_header_container) as RelativeLayout
        userImage = layout.findViewById<View>(R.id.imageViewUserImage) as ImageView
        userName = layout.findViewById<View>(R.id.textViewUserName) as TextView
        // Checking is network on
        //        if (commonClass.isNetworkAvailable(getActivity())) {
        //            getNetWorkRequest(getActivity(),"","" );
        //        }else {
        //get User Data  from Database
//        userList = databaseList.userList
//        //        }
//        if(userList.size>0) {
//            val user = userList[0].userName
//            userName.text = user
//            val userBitmapImage = userList[0].userBitmapImage
//
//            if (userBitmapImage != null) {
//                userImage.setImageResource(0)
//                userImage.setImageBitmap(userBitmapImage)
//            } else {
//                userImage.setImageResource(R.drawable.profile)
//            }
//        }
        close.setOnClickListener { mDrawerLayout!!.closeDrawer(containerView!!) }
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(activity!!, recyclerView!!, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                drawerListener!!.onDrawerItemSelected(view, position, rowListItem)
                mDrawerLayout!!.closeDrawer(containerView!!)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))
//        getNotificationCount()
        return layout
    }

    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                //if Home Button pressed hide the soft input
//                commonClass.hideKeyboard()
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                //if Home Button pressed hide the soft input
                try {
                    commonClass.hideKeyboard()
                } catch (e: NullPointerException) {
                    Log.e(TAG, "NullPointerException : " + e.localizedMessage)
                }
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerToggle!!.isDrawerIndicatorEnabled = false
        mDrawerToggle!!.syncState()
        toolbar.setNavigationIcon(R.drawable.ic_menunew)
        mDrawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_menunew)
        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }

        toolbar.setNavigationOnClickListener {
            mDrawerLayout!!.openDrawer(Gravity.START)
            //if Home Button pressed hide the soft input
            commonClass.hideKeyboard()
//            activity!!.invalidateOptionsMenu()
        }
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

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

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(view: View, position: Int, rowlist: List<MobileMenuTable>)
    }

}
