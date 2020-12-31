package com.demo.orders.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.demo.orders.R
import com.demo.orders.activity.SplashActivity
import com.demo.orders.db.TinyDB
import com.demo.orders.util.Constants

class LanguageActivity : AppCompatActivity(), OnItemSelectedListener {
    var spinner1: Spinner? = null
    lateinit var users: Array<String>
    var isBackPressed = true
    var conbtn: Button? = null
    var tinyDB: TinyDB? = null
    var str = ""
    private val TAG = "language"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        spinner1 = findViewById<View>(R.id.spinner1) as Spinner
        conbtn = findViewById<View>(R.id.conbtn) as Button
        tinyDB = TinyDB(this)
        users = arrayOf("English", "Tamil")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, users)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1!!.adapter = adapter
        spinner1!!.onItemSelectedListener = this
        conbtn!!.setOnClickListener { v: View? ->
            Log.e(TAG, "onClick: $str")
            var code = ""
            if (str == "English") {
                code = Constants.english
            }
            tinyDB!!.putString(Constants.languagecode, code)
            val intent = Intent(this@LanguageActivity, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View, position: Int, id: Long) {
        str = users[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {}
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val id = item.itemId
        return when (id) {
            R.id.action_home -> {
                onBackPressed()
                true
            }
            R.id.action_back -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}