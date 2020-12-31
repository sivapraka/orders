package com.demo.orders.adapter

import android.content.Context
import android.text.SpannableString
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.orders.R
import com.demo.orders.helper.CommonClass

class PreviewAdapter : RecyclerView.Adapter<PreviewAdapter.MyViewHolder> {
    var commonClass: CommonClass? = null
    private var listPreview: List<ProductsDataSource>
    private var context: Context
    private val TAG = "PreviewAdapter"
    private var mListener: ListAdapterListener? = null

    constructor(listPreview: List<ProductsDataSource>, ctxt: Context) { // add the interface to your adapter constructor
        context = ctxt
        commonClass = CommonClass(context)
        this.listPreview = listPreview
        Log.e(TAG, "PreviewAdapter: listPreview : " + listPreview.size)
    }

    constructor(listPreview: List<ProductsDataSource>, ctxt: Context, mListener: ListAdapterListener?) { // add the interface to your adapter constructor
        context = ctxt
        this.listPreview = listPreview
        this.mListener = mListener // receive mListener from Fragment (or Activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sales_preview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataSource = listPreview[position]
        // Total Net Value
        var d = "0"
        val productTax = dataSource.productTax.replace("\"", "")
        Log.e(TAG, "onBindViewHolder:productTax " + dataSource.productTax)
        d = dataSource.productTax.toDouble().toString()
        var discount = ""
        val span1 = commonClass!!.textSize(dataSource.materialName, 24)
        var span2: SpannableString? = null
        if (dataSource.discountTotal != null && dataSource.discountTotal.length > 0 && dataSource.discountTotal != "0") {
            discount = """
 Discount ( ${dataSource.discountTotal.toDouble()} )"""
            span2 = commonClass!!.textSize(discount, 18)
        }
        var scheme = dataSource.productQty
        if (dataSource.productSchemeQty.length > 0 && dataSource.productSchemeQty != "0") {
            val perschemeQty = commonClass!!.productDivid(dataSource.schemeQty, dataSource.schemeFreeQty)
            if (scheme.toInt() >= perschemeQty.toDouble().toInt()) {
                val schemeQty = commonClass!!.productDivid(scheme, perschemeQty).toDouble().toInt()
                Log.e(TAG, "onBindViewHolder: schemeQty $schemeQty")
            } else {
                scheme = scheme
            }
        }
        // let's put both spans together with a separator and all
        val finalText: CharSequence
        finalText = if (span2 != null) {
            TextUtils.concat(span1, " ", span2)
        } else {
            TextUtils.concat(span1)
        }
        holder.textViewProductName.text = finalText
        var amtTotal: String? = "0"
        if (dataSource.amount != null) {
            amtTotal = commonClass!!.removeDigits(dataSource.amount)
        }
        holder.textViewQty.text = scheme
        var net: String? = "0.00"
        if (dataSource.amountTotal != null && dataSource.amountTotal.length > 2) {
            net = commonClass!!.removeDigits(dataSource.amountTotal)
        }
        holder.textViewNet.text = net
        holder.textViewRate.text = amtTotal
        holder.textViewTax.text = d
    }

    override fun getItemCount(): Int {
        return listPreview.size
    }

    interface ListAdapterListener {
        // create an interface
        fun onClickViewButton(position: Int) // create callback function
        fun onClickPrintButton(position: Int) // create callback function
    }

    inner class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var textViewProductName: TextView
        var textViewQty: TextView
        var textViewRate: TextView
        var textViewTax: TextView
        var textViewNet: TextView

        init {
            textViewProductName = view.findViewById(R.id.textViewProductName)
            textViewQty = view.findViewById(R.id.textViewQty)
            textViewRate = view.findViewById(R.id.textViewRate)
            textViewTax = view.findViewById(R.id.textViewTax)
            textViewNet = view.findViewById(R.id.textViewNet)
            view.tag = view
        }
    }
}