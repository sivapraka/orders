package com.demo.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.orders.R
import com.demo.orders.db.table.MobileMenuTable
import com.demo.orders.helper.CustomAnimation
import com.demo.orders.util.Constants

class NavigationDrawerAdapter(private val context: Context, data: List<MobileMenuTable>) : RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>() {
    var selectedPosition: String?
    var data: List<MobileMenuTable>
    var animation: CustomAnimation
    private val inflater: LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.nav_drawer_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = data[position]
        holder.title.text = current.screenValue
        // set the animation from letf to Right
        animation.setAnimationLeft(holder.itemView, position)
        holder.itemBg.setOnClickListener {
            selectedPosition = current.screenName
            notifyDataSetChanged()
        }
        if (selectedPosition == current.screenName) {
            holder.itemBg.setBackgroundColor(context.resources.getColor(R.color.newColorPrimary))
            holder.title.setTextColor(context.resources.getColor(R.color.colorWhite))
            if (current.screenName == Constants.menu_home) {
                current.photo = R.drawable.ic_home_menu
            } else if (current.screenName == Constants.menu_customers) {
                current.photo = R.drawable.ic_customer_menu
            } else if (current.screenName == Constants.menu_bills) {
                current.photo = R.drawable.ic_sales_menu
            } else if (current.screenName == Constants.menu_payment) {
                current.photo = R.drawable.ic_payments_method
            } else if (current.screenName == Constants.menu_orders) {
                current.photo = R.drawable.ic_order_enquiry_menu
            } else if (current.screenName == Constants.menu_Reports) {
                current.photo = R.drawable.ic_reports_menu
            } else if (current.screenName == Constants.menu_translation) {
                current.photo = R.drawable.ic_white
            } else if (current.screenName == Constants.menu_logout) {
                current.photo = R.drawable.ic_logout_menu
            } else if (current.screenName == Constants.menu_survey) {
                current.photo = R.drawable.invoice_white
            } else if (current.screenName == Constants.menu_feedback) {
                current.photo = R.drawable.invoice_white
            }
        } else {
            holder.itemBg.setBackgroundColor(context.resources.getColor(R.color.transparent))
            holder.title.setTextColor(context.resources.getColor(R.color.colorBlack))
            if (current.screenName == Constants.menu_home) {
                current.photo = R.drawable.ic_home_v1
            } else if (current.screenName == Constants.menu_customers) {
                current.photo = R.drawable.ic_customer1
            } else if (current.screenName == Constants.menu_bills) {
                current.photo = R.drawable.ic_bills
            } else if (current.screenName == Constants.menu_payment) {
                current.photo = R.drawable.ic_payment
            } else if (current.screenName == Constants.menu_orders) {
                current.photo = R.drawable.ic_order
            } else if (current.screenName == Constants.menu_Reports) {
                current.photo = R.drawable.ic_reports
            } else if (current.screenName == Constants.menu_translation) {
                current.photo = R.drawable.ic_lt
            } else if (current.screenName == Constants.menu_logout) {
                current.photo = R.drawable.ic_logoutttt
            } else if (current.screenName == Constants.menu_survey) {
                current.photo = R.drawable.invoice
            } else if (current.screenName == Constants.menu_feedback) {
                current.photo = R.drawable.invoice
            }
        }
        holder.imageViewLogo.setImageResource(current.photo)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var imageViewLogo: ImageView
        var itemBg: RelativeLayout

        init {
            title = itemView.findViewById(R.id.title)
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo)
            itemBg = itemView.findViewById(R.id.itemBg)
        }
    }

    init {
        animation = CustomAnimation(context)
        inflater = LayoutInflater.from(context)
        selectedPosition = Constants.menu_home
        this.data = data
    }
}