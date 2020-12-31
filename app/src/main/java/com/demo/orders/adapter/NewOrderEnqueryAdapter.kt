package com.demo.orders.adapter

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.demo.orders.R
import com.demo.orders.helper.CommonClass
import com.demo.orders.util.BitmapUtility

class NewOrderEnqueryAdapter : RecyclerView.Adapter<NewOrderEnqueryAdapter.MyViewHolder> {
    private var newOrdersList: List<ProductsDataSource>
    private var context: Context
    var commonClass: CommonClass
    private var productQTY = 1
    private val TAG = "HomeAdapter"
    private var mListener: ListAdapterListener? = null
    var bitmapUtility: BitmapUtility? = null

    interface ListAdapterListener {
        fun onClickProducts(action: String?, products: List<ProductsDataSource?>?, position: Int) // create callback function
    }

    constructor(newOrdersList: List<ProductsDataSource>, ctxt: Context) { // add the interface to your adapter constructor
        context = ctxt
        commonClass = CommonClass(ctxt)
        this.newOrdersList = newOrdersList
    }

    constructor(newOrdersList: List<ProductsDataSource>, ctxt: Context, mListener: ListAdapterListener?) { // add the interface to your adapter constructor
        context = ctxt
        commonClass = CommonClass(ctxt)
        bitmapUtility = BitmapUtility(ctxt)
        this.newOrdersList = newOrdersList
        this.mListener = mListener // receive mListener from Fragment (or Activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_new_order_enquery_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataSource = newOrdersList[position]
        //Read the font from asset folder
        Log.e(TAG, "onBindViewHolder: getAmount : " + dataSource.amount)
        val font = Typeface.createFromAsset(context.assets, "font/fontawesome_webfont.ttf")
        holder.textViewProductName.text = dataSource.materialName
        holder.textViewProductPrice.text = commonClass.removeDigits(dataSource.amount)
        if (dataSource.schemeName != null) {
            holder.textViewItemID.visibility = View.VISIBLE
            holder.textViewItemID.text = dataSource.schemeName
        } else {
            holder.textViewItemID.visibility = View.GONE
        }
        holder.editTextQty.setText(dataSource.productQty)
        //Apply the Font
        //Apply the Font
        holder.textViewQtyIncrease.setText(R.string.plus)
        holder.textViewQtyReduce.setText(R.string.minus)
        holder.textViewQtyIncrease.typeface = font
        holder.textViewQtyReduce.typeface = font

        holder.editTextQty.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val text = s.toString()
                if (text.length > 0) {
                    var value = s.toString().toInt()
                    var availQty = 999

                    if (value > 1) {
                        if (value <= availQty) {
                            if (dataSource.netAmount != null) {
                                val price = commonClass.removeNewLine(dataSource.netAmount).toDouble()
                                val totalprice = value * price
                                val amountPrice = commonClass.removeNewLine(dataSource.amount).toDouble()
                                val amountTotal = value * amountPrice
                                dataSource.amountTotal = amountTotal.toString()
                                dataSource.productRate = amountTotal.toString()
                                dataSource.netTotal = totalprice.toString()
                                dataSource.productTotal = totalprice.toString()
                                dataSource.productQty = value.toString()
                                productQTY = if (value == 0) {
                                    1
                                } else {
                                    value
                                }
                                if (position != -1 || position.toString() != null) {
                                    try {
                                        mListener!!.onClickProducts("edit", newOrdersList, position)
                                    } catch (e: NullPointerException) {
                                        e.message
                                    }
                                }
                            }
                        } else {
                            value = availQty
                            if (dataSource.netAmount != null) {
                                val price = commonClass.removeNewLine(dataSource.netAmount).toDouble()
                                val totalprice = value * price
                                val amountPrice = commonClass.removeNewLine(dataSource.amount).toDouble()
                                val amountTotal = value * amountPrice
                                dataSource.amountTotal = amountTotal.toString()
                                dataSource.productRate = amountTotal.toString()
                                dataSource.netTotal = totalprice.toString()
                                dataSource.productTotal = totalprice.toString()
                                dataSource.productQty = value.toString()
                                productQTY = if (value == 0) {
                                    1
                                } else {
                                    value
                                }
                                holder.editTextQty.setText(value.toString())
                                if (position != -1 || position.toString() != null) {
                                    try {
                                        mListener!!.onClickProducts("edit", newOrdersList, position)
                                    } catch (e: NullPointerException) {
                                        e.message
                                    }
                                }
                            }
                            Log.e(TAG, "afterTextChanged: " + "ELSE  ENTERED QTY IS HIGHT")
                        }
                    }
                }
            }
        })
        productQTY = dataSource.productQty.toInt()
        holder.editTextQty.setText(productQTY.toString())
        holder.textViewQtyIncrease.setOnClickListener { v: View? ->
            try {
                val value = holder.editTextQty.text.toString().toInt()
                var availQty = 999
                if (value < availQty) {
                    if (dataSource.netAmount != null) {
                        val price = commonClass.removeNewLine(dataSource.netAmount).toDouble()
                        val increase = value + 1
                        val totalprice = increase * price
                        val amountPrice = commonClass.removeNewLine(dataSource.amount).toDouble()
                        val amountTotal = value * amountPrice
                        dataSource.amountTotal = amountTotal.toString()
                        Log.e(TAG, "total Price: Increase$totalprice")
                        dataSource.productRate = amountTotal.toString()
                        dataSource.netTotal = totalprice.toString()
                        dataSource.productTotal = totalprice.toString()
                        dataSource.productQty = increase.toString()
                        holder.editTextQty.setText(increase.toString())
                        try {
                            mListener!!.onClickProducts("increase", newOrdersList, position)
                        } catch (e: Exception) {
                            e.message
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "EXc")
            }
        }
        holder.textViewQtyReduce.setOnClickListener { v: View? ->
            val value = holder.editTextQty.text.toString().toInt()
            if (value > 1) {
                val increase = value - 1
                if (dataSource.amount != null) {
                    val price = commonClass.removeNewLine(dataSource.amount).toDouble()
                    val totalprice = increase * price
                    Log.e(TAG, "totalPrice Reduction: $totalprice")
                    val amountPrice = commonClass.removeNewLine(dataSource.amount).toDouble()
                    val amountTotal = increase * amountPrice
                    dataSource.amountTotal = amountTotal.toString()
                    dataSource.productRate = amountTotal.toString()
                    dataSource.netTotal = totalprice.toString()
                    dataSource.productTotal = totalprice.toString()
                    dataSource.productQty = increase.toString()
                    holder.editTextQty.setText(increase.toString())
                    try {
                        mListener!!.onClickProducts("decrease", newOrdersList, position)
                    } catch (e: Exception) {
                        e.message
                    }
                }
            }
        }
        holder.card_view.isLongClickable = true
        holder.card_view.setOnLongClickListener {
            if (position != -1 || position.toString() != null) {
                try {
                    mListener!!.onClickProducts("remove", newOrdersList, position)
                } catch (e: NullPointerException) {
                    e.message
                }
            }
            false
        }
        if (dataSource.productImage != null) {
            try {
                holder.imageViewProductImage.setImageResource(0)
                commonClass.loadImage(context, holder.imageViewProductImage, dataSource.productImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            holder.imageViewProductImage.setImageResource(R.drawable.product_holder)
        }
    }

    override fun getItemCount(): Int {
        return newOrdersList.size
    }

    inner class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var imageViewProductRemove: ImageView
        var imageViewProductImage: ImageView
        var editTextQty: EditText
        var textViewProductName: TextView
        var textViewProductPrice: TextView
        var textViewItemID: TextView
        var textViewQtyIncrease: TextView
        var textViewQtyReduce: TextView
        var card_view: CardView

        init {
            textViewProductName = view.findViewById<View>(R.id.textViewProductName) as TextView
            textViewProductPrice = view.findViewById<View>(R.id.textViewProductPrice) as TextView
            textViewItemID = view.findViewById<View>(R.id.textViewItemID) as TextView
            textViewQtyReduce = view.findViewById<View>(R.id.textViewQtyReduce) as TextView
            textViewQtyIncrease = view.findViewById<View>(R.id.textViewQtyIncrease) as TextView
            imageViewProductRemove = view.findViewById<View>(R.id.imageViewProductRemove) as ImageView
            imageViewProductImage = view.findViewById<View>(R.id.imageViewProductImage) as ImageView
            editTextQty = view.findViewById<View>(R.id.editTextQty) as EditText
            card_view = view.findViewById<View>(R.id.card_view) as CardView
            view.tag = view
        }
    }
}