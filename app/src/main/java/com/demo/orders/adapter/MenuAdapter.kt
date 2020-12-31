package com.demo.orders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.orders.R
import com.demo.orders.db.table.MobileMenuTable
import com.demo.orders.helper.CommonClass
import com.demo.orders.helper.CustomAnimation
import com.demo.orders.util.Constants

/* Navigation Menu*/
class MenuAdapter(private val listMenu: List<MobileMenuTable>, private val context: Context, mListener: ListAdapterListener) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    var commonClass: CommonClass
    var animation: CustomAnimation
    private val TAG = "HomeMenuAdapter"
    private val mListener: ListAdapterListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_menu_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val dataSource = listMenu[position]
        holder.textViewTitle.text = dataSource.screenValue
        // Here you apply the animation when the view is bound
        animation.setAnimation(holder.itemView, position)
        if (dataSource.screenName == Constants.menu_customers) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_customer1)
        } else if (dataSource.screenName == Constants.menu_bills) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_bills)
        } else if (dataSource.screenName == Constants.menu_payment) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_payment)
        } else if (dataSource.screenName == Constants.menu_orders) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_order)
        } else if (dataSource.screenName == Constants.menu_Reports) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_reports)
        } else if (dataSource.screenName == Constants.menu_logout) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_logoutttt)
        } else if (dataSource.screenName == Constants.menu_translation) {
            holder.imageViewLogo.setImageResource(R.drawable.ic_lt)
        } else if (dataSource.screenName == Constants.menu_survey) {
            holder.imageViewLogo.setImageResource(R.drawable.invoice)
        } else if (dataSource.screenName == Constants.menu_feedback) {
            holder.imageViewLogo.setImageResource(R.drawable.invoice)
        }
        holder.relativeLayoutMenu.setOnClickListener {
            if (position != -1 || position.toString() != null) {
                Log.e(TAG, "POSITION: $position")
                // use callback function to Return the Position
                mListener.onClickButton(dataSource, position)
            }
        }
    }

    /**
     * Here is the key method to apply the animation
     */
    /*
    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position,int lastPosition) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }*/
    override fun getItemCount(): Int {
        return listMenu.size
    }

    interface ListAdapterListener {
        // create an interface
        fun onClickButton(mkey: MobileMenuTable?, position: Int) // create callback function
    }

    inner class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var textViewTitle: TextView
        var imageViewLogo: ImageView
        var relativeLayoutMenu: RelativeLayout

        init {
            textViewTitle = view.findViewById(R.id.textViewTitle)
            imageViewLogo = view.findViewById(R.id.imageViewLogo)
            relativeLayoutMenu = view.findViewById(R.id.relativeLayoutMenu)
            view.tag = view
        }
    }

    init { // add the interface to your adapter constructor
        commonClass = CommonClass(context)
        animation = CustomAnimation(context)
        this.mListener = mListener // receive mListener from Fragment (or Activity)
    }
}