package com.example.sharedpreferencesapp.view

import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferencesapp.R
import com.example.sharedpreferencesapp.adapter.ColorAdapter
import com.example.sharedpreferencesapp.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.color_item_view_layout.*

class MainActivity : AppCompatActivity(), ColorAdapter.colorAdapterDelegator {


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    override fun colorPicked(colorResource: String) {
        setUpColor(colorResource)
        spEditor.putString("my_app_color", colorResource)
        spEditor.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("color_app_101", 0)
        spEditor = sharedPreferences.edit()

        setUpColor(sharedPreferences.getString("my_app_color", "BLUE"))

        setupRV()
    }

    fun setupRV() {
        color_recyclerview.adapter = ColorAdapter(
            mutableListOf(
                Constants.COLOR_BLUE,
                Constants.COLOR_GREEN,
                Constants.COLOR_RED
            ), this
        )
        color_recyclerview.layoutManager = LinearLayoutManager(this)
        color_recyclerview.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    fun setUpColor(colorPicked: String?) {
        when (colorPicked) {
            Constants.COLOR_GREEN -> main_activity_layout.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.appGreen
                )
            )
            Constants.COLOR_RED -> main_activity_layout.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.appRed
                )
            )
            Constants.COLOR_BLUE -> main_activity_layout.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.appBlue
                )
            )
        }
    }
}