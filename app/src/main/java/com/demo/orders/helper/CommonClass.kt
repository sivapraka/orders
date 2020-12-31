package com.demo.orders.helper

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.demo.orders.activity.SplashActivity
import com.demo.orders.db.AppDatabase
import com.demo.orders.db.DBHelper
import com.demo.orders.db.DatabaseList
import com.demo.orders.db.dao.LoginDao
import com.demo.orders.db.table.OrdersDataSourceTable
import com.demo.orders.model.InvoiceDataSource
import com.demo.orders.session.SessionManager
import com.demo.orders.util.BitmapUtility
import com.demo.orders.util.Constants
import java.io.File
import java.io.FileOutputStream
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by admin on 01-Feb-18.
 */
class CommonClass {
    private var context: Context
    private val TAG = "CommonClass"
    private var databaseList: DatabaseList
    private var session: SessionManager
    var sweetAlertDialog: SweetAlertDialog? = null
    var dbHelper: DBHelper
    var bitmapUtility: BitmapUtility
    var appDatabase: AppDatabase
    var logindao: LoginDao

    constructor(ctxt: Context) {
        context = ctxt
        databaseList = DatabaseList(ctxt)
        dbHelper = DBHelper(ctxt)
        bitmapUtility = BitmapUtility(ctxt)
        session = SessionManager(ctxt)
        appDatabase = AppDatabase.getDatabase(ctxt)
        logindao = appDatabase.loginDao()
    }


    @Throws(NullPointerException::class)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    /* Context is converted to Activity.*/
    fun convertActivity(context: Context): AppCompatActivity {
        return context as AppCompatActivity
    }

    @SuppressLint("MissingPermission")
    fun getDeviceId(context: Context): String? {
        val deviceId: String
        deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID)
        } else {
            val mTelephony: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (mTelephony.deviceId != null) {
                mTelephony.deviceId
            } else {
                Settings.Secure.getString(
                        context.contentResolver,
                        Settings.Secure.ANDROID_ID)
            }
        }
        return deviceId
    }

    //
    //        String qty1 = " %3$3s |%n";
    fun replace(input: String, oin: String, reg: String): String {
        return input.replace(oin, reg)
    }

    fun replace(input: String): String {
        return input.replace("/", "-")
    }

    fun getDouble(input: String): String {
        //    Log.e(TAG, "getDouble: input : $input")
        val f = java.lang.Float.valueOf(input.replace("[^-\\d.]+|\\.(?!\\d)".toRegex(), ""))
        //   Log.e(TAG, "getDouble: $f")
        return f.toString()
    }// java.util.Date, NOT java.sql.Date or java.sql.Timestamp!

    /* Store the Date Format*/
    val currentDate: String
        get() {
            val now = Date() // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
            return SimpleDateFormat("EEE, d MMM yyyy").format(now)
        }

    //Move the Activity
    fun activitySend(context: Context, target: Class<*>?) {
        val a = Intent(context, target)
        context.startActivity(a)
        convertActivity(context).finish()
    }

    fun intentextra(_context: Context, _targetClass: Class<*>, keyValue: String, value: String) {
        val activity = _context as Activity
        val intent = Intent(_context, _targetClass)
        intent.putExtra(keyValue, value)
        activity.startActivity(intent)
    }

    fun intentextra(_context: Context, _targetClass: Class<*>, data: Bundle) {
        val activity = _context as Activity
        val intent = Intent(_context, _targetClass)
        intent.putExtras(data)
        activity.startActivity(intent)
    }

    //Calculate  the Net Amount from ProductQty and Product Rate
    fun getNetAmount(qty: String, rate: String): String {
        val netAmount = qty.toDouble() * rate.toDouble()
        return netAmount.toString()
    }

    //add the Two Product
    fun productAdd(input1: String, input2: String): String {
        return (getDouble(input1).toDouble() + getDouble(input2).toDouble()).toString()
    }

    //multiply the Two Product
    fun productMutiply(input1: String, input2: String): String {
        return (getDouble(input1).toDouble() * getDouble(input2).toDouble()).toString()
    }

    //divid the Two Product
    fun productDivid(input1: String, input2: String): String {
        return (getDouble(input1).toDouble() / getDouble(input2).toDouble()).toString()
    }

    //Subtract the product
    fun productSubtract(input1: String, input2: String): String {
        return (getDouble(input1).toDouble() - getDouble(input2).toDouble()).toString()
    }


    //Calculate the Total Amount
    fun getTotal(list: List<ProductsDataSource>): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            value = if (dataSource.amountTotal != null) {
                removeNewLine(dataSource.amountTotal).toDouble()
            } else {
                0.0
            }
            newSum += value
        }
        //   Log.e(TAG, "getTotal: " + total);
        return newSum
    }

    //Calculate the Total balance
    fun getTotalbalance(list: List<InvoiceDataSource>): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            value = if (dataSource.balance != null) {
                removeNewLine(dataSource.balance).toDouble()
            } else {
                0.0
            }
            newSum += value
        }
        //   Log.e(TAG, "getTotal: " + total);
        return newSum
    }

    //Calculate the Total Amount
    fun getTotalPay(list: List<ProductsDataSource>): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            value = if (dataSource.productTotal != null) {
                removeNewLine(dataSource.productTotal).toDouble()
            } else {
                0.0
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }

    fun textSize(text: String, size: Int): SpannableString {
        val span1 = SpannableString(text)
        span1.setSpan(AbsoluteSizeSpan(size), 0, text.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        return span1
    }

    fun isRange(value: Int, min: Int, max: Int): Boolean {
        return min <= value && value <= max
    }

    fun isRange(value: Int, min: Int): Boolean {
        return min >= value
    }

    //Calculate the Total Tax Amount
    fun getTotalTax(list: List<ProductsDataSource>, name: String?, name2: String?): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            when (name) {
                "tax" -> if (dataSource.productTax != null) {
                    val productTax = removeNewLine(dataSource.productTax).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTax * productQty
                }
                "value" -> if (dataSource.productTaxValue != null) {
                    val productTaxValue = removeNewLine(dataSource.productTaxValue).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTaxValue * productQty
                }
                "percent" -> value = if (dataSource.productTaxPercent != null) {
                    removeNewLine(dataSource.productTaxPercent).toDouble()
                } else {
                    0.0
                }
                "gst" -> value = if (dataSource.productTaxPercent != null) {
                    removeNewLine(dataSource.productTaxPercent).toDouble()
                } else {
                    0.0
                }
                "tax amount" -> {
                    val productTax = removeNewLine(dataSource.productTaxValue).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTax * productQty
                }
                "amount" -> {
                    value = if (dataSource.amountTotal != null) {
                        removeNewLine(dataSource.amountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "netTotal" -> {
                    value = if (dataSource.netTotal != null && dataSource.productType == name2) {
                        removeNewLine(dataSource.netTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "amountTotal" -> {
                    value = if (dataSource.amountTotal != null && dataSource.productType == name2) {
                        removeNewLine(dataSource.amountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "totalDiscount" -> {
                    value = if (dataSource.discountTotal != null && dataSource.discountTotal.isNotEmpty() &&
                            dataSource.discountTotal != "0") {
                        removeNewLine(dataSource.discountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }


    //Calculate the Total Tax Amount
    fun getTotalTax(list: List<ProductsDataSource>, name: String?): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            when (name) {
                "tax" -> if (dataSource.productTax != null) {
                    val productTax = removeNewLine(dataSource.productTax).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTax * productQty
                }
                "value" -> if (dataSource.productTaxValue != null) {
                    val productTaxValue = removeNewLine(dataSource.productTaxValue).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTaxValue * productQty
                }
                "percent" -> value = if (dataSource.productTaxPercent != null) {
                    removeNewLine(dataSource.productTaxPercent).toDouble()
                } else {
                    0.0
                }
                "gst" -> value = if (dataSource.productTaxPercent != null) {
                    removeNewLine(dataSource.productTaxPercent).toDouble()
                } else {
                    0.0
                }
                "tax amount" -> {
                    val productTax = removeNewLine(dataSource.productTaxValue).toDouble()
                    val productQty = dataSource.productQty.toDouble()
                    value = productTax * productQty
                }
                "amount" -> {
                    value = if (dataSource.amountTotal != null) {
                        removeNewLine(dataSource.amountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "netTotal" -> {
                    value = if (dataSource.netTotal != null) {
                        removeNewLine(dataSource.netTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "amountTotal" -> {
                    value = if (dataSource.amountTotal != null) {
                        removeNewLine(dataSource.amountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
                "totalDiscount" -> {
                    value = if (dataSource.discountTotal != null && dataSource.discountTotal.isNotEmpty() &&
                            dataSource.discountTotal != "0") {
                        removeNewLine(dataSource.discountTotal).toDouble()
                    } else {
                        0.0
                    }
                }
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }

    fun addLinebreaks(input: String, maxLineLength: Int): String {
        val tok = StringTokenizer(input, " ")
        val output = StringBuilder(input.length)
        var lineLen = 0
        while (tok.hasMoreTokens()) {
            val word = tok.nextToken()
            if (lineLen + word.length > maxLineLength) {
                output.append("\n")
                lineLen = 0
            }
            output.append(word)
            lineLen += word.length
        }
        return output.toString()
    }

    fun breakLines(input: String, maxLineLength: Int): String {
        val tokens = input.split(SPLIT_REGEXP.toRegex()).toTypedArray()
        val output = StringBuilder(input.length)
        var lineLen = 0
        for (i in tokens.indices) {
            var word = tokens[i]
            if (lineLen + (SPACE_SEPARATOR + word).length > maxLineLength) {
                if (i > 0) {
                    output.append(NEWLINE)
                }
                lineLen = 0
            }
            if (i < tokens.size - 1 && lineLen + (word + SPACE_SEPARATOR).length + tokens[i + 1].length <=
                    maxLineLength) {
                word += SPACE_SEPARATOR
            }
            output.append(word)
            lineLen += word.length
        }
        return output.toString()
    }

    //Check the GPS is Enabled or Not
    fun isGPSEnabled(context: Context): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gps_enabled = false
        try {
            assert(lm != null)
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
            ex.message
        }
        return gps_enabled
    }

    fun turnGpsOn(context: Context) {
        val beforeEnable = Settings.Secure.getString(context.contentResolver,
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
        val newSet = String.format("%s,%s",
                beforeEnable,
                LocationManager.GPS_PROVIDER)
        try {
            Settings.Secure.putString(context.contentResolver,
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
                    newSet)
        } catch (e: Exception) {
        }
    }

    // Check  the Device Location  is Enabled
    fun isLocationEnabled(context: Context): Boolean {
        val permission = Manifest.permission.ACCESS_COARSE_LOCATION
        val res = context.checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    //Remove the New Lines and Comma Values from the Input (String)
    fun removeNewLine(text: String): String {
        return text.replace("\n", "").replace("\r", "").replace(" ", "").replace(",", "")
    }

    /* Random String Generator*/
    fun randomUserID(data: String?): String? {
        val chars = "0123456789".toCharArray()
        val sb = StringBuilder(5)
        val random = Random()
        for (i in 0..4) {
            val c = chars[random.nextInt(chars.size)]
            sb.append(c)
        }
        var result: String? = null
        when (data) {
            "customer" -> result = Constants.randomUserID + sb.toString()
            "product" -> result = Constants.randomPRODID + sb.toString()
            "company" -> result = Constants.randomCompanyID + sb.toString()
            "sales" -> result = Constants.randomSalesID + sb.toString()
            "orders" -> result = Constants.randomOrderID + sb.toString()
            "payment" -> result = Constants.randomPaymentID + sb.toString()
            "salesItem" -> result = Constants.randomSalesItemID + sb.toString()
            "ordersItem" -> result = Constants.randomOrdersItemID + sb.toString()
            "transaction" -> result = Constants.randomTransactionItemID + sb.toString()
        }
        Log.e(TAG, "result: $result")
        return result
    }

    //Show Logout Alert Dialog
    fun showLogout() {
        val activity = context as AppCompatActivity
        val alertDialog = AlertDialog.Builder(context)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_logout, null)
        alertDialog.setView(view)
        val dialog = alertDialog.create()
        dialog.show()
        /* set the  dialog transparent */
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window: Window = dialog.window
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        val buttonCancel = view.findViewById<View>(R.id.buttonCancel) as Button
        val buttonOk = view.findViewById<View>(R.id.buttonOk) as Button
        val imageViewUserImage = view.findViewById<View>(R.id.imageViewUserImage) as ImageView

        //Sales Man Information
        val userDataSources = databaseList.userList
        if (userDataSources.size > 0) {
            val userImage = userDataSources[0].userBitmapImage
            if (userImage != null) {
                imageViewUserImage.setImageBitmap(userImage)
            } else {
                imageViewUserImage.setImageResource(R.drawable.profile)
            }
        }
        buttonOk.setOnClickListener {
            logindao.delete()
            activity.startActivity(Intent(activity, SplashActivity::class.java))
            activity.finishAffinity()
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        buttonCancel.setOnClickListener { dialog.dismiss() }
    }


    fun multiply(value1: String, value2: String): String {
        // Log.e(TAG, "multiply: $value1")
        return (value1.toDouble() * value2.toDouble()).toString()
    }

    fun setStyle(name: String?, space: Int, style: StringAlignUtils.Alignment?): String {
        val util = StringAlignUtils(space, style)
        return util.format(name)
    }

    /* Divide of the Amount or Percentage*/
    fun getDivision(amount: Double, value1: Double): Double {

        return amount / value1
    }//        Log.e(TAG, "getDateTime: " + dateFormat.format(date));

    //get the Date and Time
    val dateTime: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a")
            val date = Date()
            //        Log.e(TAG, "getDateTime: " + dateFormat.format(date));
            return dateFormat.format(date)
        }

    //get the Date and Time
    fun getDate(inputFormat: String?): String {
        val dateFormat: DateFormat = SimpleDateFormat(inputFormat)
        val date = Date()
        //        Log.e(TAG, "getDateTime: " + dateFormat.format(date));
        return dateFormat.format(date)
    }

    //get the Date and Time
    fun getTime(inputFormat: String?): String {
        val dateFormat: DateFormat = SimpleDateFormat(inputFormat)
        val date = Date()
        // Log.e(TAG, "getTime: " + dateFormat.format(date))
        return dateFormat.format(date)
    }//        Log.e(TAG, "getDateTime: " + dateFormat.format(date));

    //Get the Date in Number format
    val dateNumber: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
            val date = Date()
            //        Log.e(TAG, "getDateTime: " + dateFormat.format(date));
            return dateFormat.format(date)
        }//        Log.e(TAG, "getDateTime: " + dateFormat.format(date));

    //Get the Date in Number format
    val dateNumberymd: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = Date()
            //        Log.e(TAG, "getDateTime: " + dateFormat.format(date));
            return dateFormat.format(date)
        }

    //Split the comma form the String
    fun commaSplit(number: Double): String {
        val formatter = DecimalFormat("##,##,###.##")
        // Log.e(TAG, "USER DATA: " + number);
        //Log.e(TAG, "commaSplit: " + data);
        return formatter.format(number)
    }

    //Split the comma form the String
    fun commaSplit(number: Float): String {
        val formatter = DecimalFormat("##,##,###.##")
        // Log.e(TAG, "USER DATA: " + number);
        //Log.e(TAG, "commaSplit: " + data);
        return formatter.format(number.toDouble())
    }

    fun convertInt(input: String): String {
        return input.toDouble().toString()
    }

    //Convert the String Value to Double Value
    fun convertDouble(input: String): String {
        //Convert the input to Double if any comma or new line is presented it will be removed
        // through removeNewLine Method
        val data = removeNewLine(input).toDouble()
        //Return the String with Comma Seperated..
        return commaSplit(data)
    }

    fun convertDate(input: String?, pattern: String?): Date? {
        var date: Date? = null
        try {
            date = SimpleDateFormat(pattern).parse(input)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    fun isBetweenDate(min: Date?, max: Date?, date: Date): Boolean {
        return !(date.before(min) || date.after(max))
    }

    //Remove extra digits from the String
    @SuppressLint("DefaultLocale")
    fun removeDigits(input: String): String? {
        var input = input
        var output: String? = null
        try {
            Log.e("comma error",input )
            input = getDouble(input)
            output = String.format("%.02f", input.toDouble().toFloat())
            //    Log.e(TAG, "removeDigits: double_digit :$output")
        } catch (e: IllegalFormatConversionException) {
            e.conversion
        }
        return output
    }


    fun roundOff(input: String): String? {
        val df = DecimalFormat("#.##")
        var output: String? = "0.00"
        try {
//            output = String.format("%.02f", input.toDouble().toFloat())
            df.setRoundingMode(RoundingMode.FLOOR)
                        output= df.format(input.toDouble())
            Log.e(TAG, "roundOff: double_digit :$output")
        } catch (e: IllegalFormatConversionException) {
            e.conversion
        }
        return output
    }

    //Convert String to byte
    fun aByte(input: String?): Byte {
        val integerObject = Integer.valueOf(input)
        val b = integerObject.toByte()
        println("byte:$b")
        return b
    }

    //Convert int to byte
    fun aByte(input: Int): Byte {
        val b = input.toByte()
        println("byte:$b")
        return b
    }

    //Remove extra digits from the String
    @SuppressLint("DefaultLocale")
    fun addZero(input: String): String {
        var output: String? = null
        val len = input.split("\\.".toRegex()).toTypedArray()[0]
        if (len.length > 1) {
            val sb = StringBuilder()
            for (toPrepend in 1 - input.length downTo 1) {
                sb.append('0')
            }
            sb.append(input)
            output = sb.toString()
        } else {
            val sb = StringBuilder()
            sb.append('0')
            sb.append(input)
            output = sb.toString()
            Log.e(TAG, "addZero: output  $output")
        }
        Log.e(TAG, "addZero: $output")
        return output
    }

    /* Hide the Specific View */
    fun hideKeyboard(activity: Activity, view: View) {
        try {
            // Then just use the following:
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            Log.e(TAG, "hideKeyboard: " + e.message)
        }
    }

    fun showKeyboard(activity: Activity, view: View?) {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.message
        }
    }

    //Get the Time From the DateFormat
    @SuppressLint("SimpleDateFormat")
    fun getTime(input: String?, sampleFormate: String?): String {
        var date: Date? = null
        try {
            date = SimpleDateFormat(sampleFormate).parse(input)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return SimpleDateFormat("hh:mm a").format(date)
    }

    //Get the Tax Value
    fun getTaxValue(price: Double, percentage: Double): Double {
        //  Log.e(TAG, "getTaxValue:  price $price")
        //   Log.e(TAG, "getTaxValue:  percentage $percentage")
        val taxAmount = price * (percentage / 100.0f)
        Log.e(TAG, "onCreate: taxAmount$taxAmount")
        return taxAmount
    }

    //Hide the KeyBoard
    fun hideKeyboard(activity: Activity) {
        try {
            // Check if no view has focus:
            val view = activity.currentFocus
            if (view != null) {
                val imm = (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.message
        }
    }

    //Load the Bitmap image
    fun loadImage(context: Context?, imageView: ImageView?, bitmap: Bitmap?) {
        if (bitmap != null) {
            Glide.with(context!!).asBitmap()
                    .load(bitmap)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView!!)
        }
    }

    fun loadImage(context: Context?, imageView: ImageView?, bitmap: ByteArray?) {
        val img = bitmapUtility.base64(bitmap)
        Glide.with(context!!).asBitmap()
                .load(Base64.decode(img, Base64.DEFAULT))
                .apply(RequestOptions.circleCropTransform())
                .into(imageView!!)
    }

    // load the url image
    fun loadImage(context: Context?, imageView: ImageView?, url: String?) {
        Glide.with(context!!)
                .load(url)
//                .apply(RequestOptions.circleCropTransform())
                .into(imageView!!)
    }

    // load the url image
    fun load(context: Context?, imageView: ImageView?, url: String?) {
        Glide.with(context!!).asBitmap()
                .load(url)
                .into(imageView!!)
    }

    fun hideKeyboard() {
        //Convert Context to Activity
        try {
            val activity = convertActivity(context)
            val inputMethodManager = (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.currentFocus).windowToken, 0)
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "hideKeyboard: " + e.localizedMessage)
        }
    }

    /**
     * validate your email address format. Ex-test@test.com
     */
    fun emailValidator(email: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun randomNumber(): String {
        val rand = Random()
        return (rand.nextInt(90000) + 10000).toString()
    }

    fun splitdoubleValue(test: String?): String? {
        val p = Pattern.compile("\\w+([\\d.]+)+%")
        val m = p.matcher(test)
        var match = test
        // iterating on findings
        while (m.find()) {
            // back-reference
            match = m.group(1)
            // trying to parse combination of digits and dots as double
            try {
                val d = java.lang.Double.valueOf(match)
                System.out.printf("Found double: %f%n", d)
            } // handling non-parseable combinations of digits and dots
            catch (nfe: NumberFormatException) {
                // TODO handle
                System.out.printf("Couldn't parse %s as double.%n", match)
            }
        }
        return match
    }

    fun EmailValid(emailAddress: String?): Boolean {
        val regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(emailAddress)
        return matcher.matches()
    }

    fun auth(): String {
        val credentials = "admin" + ":" + "1234"
        return ("Basic "
                + Base64.encodeToString(credentials.toByteArray(),
                Base64.NO_WRAP))
    }

    fun randomKey(count: Int): String {
        val text = ""
        val possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val builder = StringBuilder()
        for (i in 0 until count) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            val index = (possible.length
                    * Math.random()).toInt()

            // add Character one by one in end of sb
            builder.append(possible[index])
        }
        return builder.toString()
    }

    // get the paired blutooth device
    fun getPrinterByName(printerName: String?): BluetoothDevice? {
        val pairedDevices = BluetoothAdapter.getDefaultAdapter().bondedDevices
        for (device in pairedDevices) {
            if (device.name == null) continue
            if (device.name.contains(printerName!!)) {
                return device
            } else {
            }
        }
        return null
    }



    fun setSweetAlertDialog(type: Int, msg: String?) {
        SweetAlertDialog(context, type)
                .setTitleText(msg)
                .show()
        sweetAlertDialog = sweetAlertDialog
    }

    //location .
    fun displayLocationSettingsRequest() {
        val activity = context as Activity
        val googleApiClient = GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build()
        googleApiClient.connect()
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 10000 / 2.toLong()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result1: LocationSettingsResult ->
            val status = result1.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS -> Log.i(TAG, "All location settings are satisfied.")
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ")
                    try {
                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        status.startResolutionForResult(activity, Constants.REQUEST_CHECK_SETTINGS)
                    } catch (e: SendIntentException) {
                        Log.i(TAG, "PendingIntent unable to execute request.")
                    }
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.")
            }
        }
    }

    val hasMap: Map<String, String>
        get() {
            val auth = auth()
            val params: MutableMap<String, String> = HashMap()
            params[APIKey.KEY_CONTENT_TYPE] = "application/x-www-form-urlencoded"
            params[APIKey.KEY_AUTHORIZATION] = auth
            return params
        }

    //App Share
    fun appShare() {
        val packageName = context.packageName
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "POS")
            var sAux = "\nLet me recommend you this application\n\n"
            sAux = """
                ${sAux}https://play.google.com/store/apps/details?id=$packageName


                """.trimIndent()
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            context.startActivity(Intent.createChooser(i, "Choose one"))
        } catch (e: Exception) {
            //e.toString();
        }
    }

    //distance calculator
    fun distanceFrom_in_Km(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val df1 = DecimalFormat("#.0000")
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val tempDistance = 6371 * c
        Log.e("sds...", tempDistance.toString())
        return tempDistance
    }

    // Custom method to take screenshot
    fun TakeScreenShot(rootView: RelativeLayout): Bitmap {
        val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        rootView.draw(canvas)
        return bitmap
    }

    fun shareScreenShot(photoURI: Uri?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(Intent.EXTRA_STREAM, photoURI)
            intent.setPackage("com.whatsapp") //package name of the app
            intent.type = "image/png"
            context.startActivity(Intent.createChooser(intent, "Share image via"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    fun shareMail(file: File) {
//        Log.e(TAG, "onClick: " + file.path)
//        val filePath = file.path
//        val sendMail = SendMail(context, "malik@scoto.in", "invoice", "billing invoice image has attatched.", filePath)
//        sendMail.execute()
//    }

    fun shareImage(bitmap: Bitmap, billNo: String) {
        try {
            val separated = billNo.split("/".toRegex()).toTypedArray()
            val value = separated[2]
            val file = File(context.externalCacheDir, "$value.png")
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            val photoURI: Uri
            photoURI = if (Build.VERSION.SDK_INT > 19) {
                FileProvider.getUriForFile(context, "com.demo.orders.fileprovider", file)
            } else {
                Uri.fromFile(file)
            }
            showShare(photoURI, file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //Show Logout Alert Dialog
    fun showShare(photoURI: Uri?, file: File) {
        val activity = context as Activity
        val alertDialog = AlertDialog.Builder(context)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_share, null)
        alertDialog.setView(view)
        val dialog = alertDialog.create()
        dialog.show()
        //set the  dialog transparent
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val buttonCancel = view.findViewById<View>(R.id.buttonCancel) as Button
        val buttonOk = view.findViewById<View>(R.id.buttonOk) as Button
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val whatsapp = view.findViewById<RadioButton>(R.id.radioButton)
        val email = view.findViewById<RadioButton>(R.id.radioButton2)
        val shareCount = intArrayOf(1)
        whatsapp.isChecked = true
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val id = group.checkedRadioButtonId
            when (id) {
                R.id.radioButton -> shareCount[0] = 1
                R.id.radioButton2 -> shareCount[0] = 2
            }
        }
        buttonOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
            if (shareCount[0] == 1) {
                shareScreenShot(photoURI)
            } else if (shareCount[0] == 2) {
//                shareMail(file)
            }
        }
        buttonCancel.setOnClickListener { dialog.dismiss() }
    }

    companion object {
        private const val NEWLINE = '\n'
        private const val SPACE_SEPARATOR = " "

        //if text has \n, \r or \t symbols it's better to split by \s+
        private const val SPLIT_REGEXP = "\\s+"
    }

    fun intentFinish(_context: Context, _targetClass: Class<*>) {
        val activity = _context as Activity
        val intent = Intent(_context, _targetClass)
        activity.startActivity(intent)
        activity.finish()
    }

    fun sweetAlertDialog(title: String, content: String, alertDialog: Int) {
        val sw = SweetAlertDialog(context, alertDialog)
        sw.titleText = title
        sw.contentText = content
        sw.show()
        Handler().postDelayed({ sw.dismissWithAnimation() }, 2000)
    }

    fun intentextraFinish(_context: Context, _targetClass: Class<*>, data: Bundle) {
        val activity = _context as Activity
        val intent = Intent(_context, _targetClass)
        intent.putExtras(data)
        activity.startActivity(intent)
        activity.finish()
    }

    fun getdate(time: String, input: String, outTime: String): String? {

        var inputFormat = SimpleDateFormat(input)
        val outputFormat = SimpleDateFormat(outTime)
        var date: Date? = null
        var str: String? = null
        var finalDate: Date? = null
        try {
            date = inputFormat.parse(time)
            val calendar = Calendar.getInstance()
            calendar.time = date

            finalDate = Date(calendar.timeInMillis)
            str = outputFormat.format(finalDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            inputFormat = SimpleDateFormat(outTime)
            try {
                date = inputFormat.parse(time)
                val calendar = Calendar.getInstance()
                calendar.time = date
                finalDate = Date(calendar.timeInMillis)
                str = outputFormat.format(finalDate)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }

        return str
    }

    //Calculate the Total Tax Amount
    fun getTotalOrderDetails(list: List<ProductsDataSource>, name: String?): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            when (name) {
//                "value" -> if (dataSource.productTaxValue != null) {
//                    val productTaxValue = removeNewLine(dataSource.productTaxValue).toDouble()
//                    val productQty = dataSource.productQty.toDouble()
//                    value = productTaxValue * productQty
//                }
//                "percent" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
//                "gst" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
//                "tax amount" -> {
//                    val productTax = removeNewLine(dataSource.productTaxValue).toDouble()
//                    val productQty = dataSource.productQty.toDouble()
//                    value = productTax * productQty
//                }
                "schemeTotal" -> {
                    value = if (dataSource.scheme != null && dataSource.scheme!!.isNotBlank()
                            && dataSource.scheme != "null") {
                        removeNewLine(dataSource.discountTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "netTotal" -> {
                    value = if (dataSource.netTotal != null) {
                        removeNewLine(dataSource.netTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "amountTotal" -> {
                    value = if (dataSource.amountTotal != null) {
                        removeNewLine(dataSource.amountTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "totalDiscount" -> {
                    value = if (dataSource.discountTotal != null && dataSource.discountTotal!!.isNotEmpty() &&
                            dataSource.discountTotal != "0.00" && dataSource.scheme == "") {
                        removeNewLine(dataSource.discountTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }

    //Calculate the Total Tax Amount
    fun getTotalBillDetails(list: List<BillDetailsTable>, name: String?): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            when (name) {
                "tax" -> if (dataSource.totalTax != null) {
                    val productTax = removeNewLine(dataSource.totalTax!!).toDouble()
                    val productQty = dataSource.quantity!!.toDouble()
                    value = productTax * productQty
                }
//                "value" -> if (dataSource.productTaxValue != null) {
//                    val productTaxValue = removeNewLine(dataSource.productTaxValue).toDouble()
//                    val productQty = dataSource.productQty.toDouble()
//                    value = productTaxValue * productQty
//                }
//                "percent" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
//                "gst" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
//                "tax amount" -> {
//                    val productTax = removeNewLine(dataSource.productTaxValue).toDouble()
//                    val productQty = dataSource.productQty.toDouble()
//                    value = productTax * productQty
//                }
                "schemeTotal" -> {
                    value = if (dataSource.scheme != null && dataSource.scheme!!.isNotBlank()
                            && dataSource.scheme != "null") {
                        removeNewLine(dataSource.totalDiscount!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "netTotal" -> {
                    value = if (dataSource.subTotal != null) {
                        removeNewLine(dataSource.subTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "amountTotal" -> {
                    value = if (dataSource.totalPrice != null) {
                        removeNewLine(dataSource.totalPrice!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "totalDiscount" -> {
                    value = if (dataSource.totalDiscount != null && dataSource.totalDiscount!!.isNotEmpty() &&
                            dataSource.totalDiscount != "0.00" && dataSource.scheme == "") {
                        removeNewLine(dataSource.totalDiscount!!).toDouble()
                    } else {
                        0.0
                    }
                }
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }

    //oder
    fun getTotalBillDetailsOrder(list: List<OrdersDataSourceTable>, name: String?): Double {
        var newSum = 0.0
        for (dataSource in list) {
            var value = 0.0
            when (name) {
//                "tax" -> if (dataSource.totalTax != null) {
//                    val productTax = removeNewLine(dataSource.totalTax!!).toDouble()
//                    val productQty = dataSource.quantity!!.toDouble()
//                    value = productTax * productQty
//                }
//                "value" -> if (dataSource.productTaxValue != null) {
//                    val productTaxValue = removeNewLine(dataSource.productTaxValue).toDouble()
//                    val productQty = dataSource.productQty.toDouble()
//                    value = productTaxValue * productQty
//                }
//                "percent" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
//                "gst" -> value = if (dataSource.productTaxPercent != null) {
//                    removeNewLine(dataSource.productTaxPercent).toDouble()
//                } else {
//                    0.0
//                }
                "tax" -> {
                    val productTax = removeNewLine(dataSource.totalTax).toDouble()
                    value = productTax
                }
                "schemeTotal" -> {
                    value = if (dataSource.productType != null && dataSource.productType == "free") {
                        removeNewLine(dataSource.amount!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "netTotal" -> {
                    value = if (dataSource.amount != null) {
                        removeNewLine(dataSource.amount!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "amountTotal" -> {
                    value = if (dataSource.subTotal != null) {
                        removeNewLine(dataSource.subTotal!!).toDouble()
                    } else {
                        0.0
                    }
                }
                "totalDiscount" -> {
                    value = 0.0
                    /*if (dataSource.totalDiscount != null && dataSource.totalDiscount!!.isNotEmpty() &&
                            dataSource.totalDiscount != "0.00" && dataSource.scheme == "") {
                        removeNewLine(dataSource.totalDiscount!!).toDouble()
                    } else {
                        0.0
                    }*/
                }
            }
            newSum += value
        }
        val total = newSum
        Log.e(TAG, "getTotal: $total")
        return total
    }
}