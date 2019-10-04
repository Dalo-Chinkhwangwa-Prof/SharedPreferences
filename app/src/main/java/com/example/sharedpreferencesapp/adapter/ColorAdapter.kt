package com.example.sharedpreferencesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferencesapp.R
import com.example.sharedpreferencesapp.util.Constants
import kotlinx.android.synthetic.main.color_item_view_layout.view.*

class ColorAdapter(private val colors: List<String>, private val delegator: colorAdapterDelegator) :
    RecyclerView.Adapter<ColorAdapter.CustomViewHolder>() {

    lateinit var appContext: Context

    interface colorAdapterDelegator{
        fun colorPicked(colorResource: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_item_view_layout, parent, false)
        appContext = parent.context.applicationContext
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.colorText.text = colors[position]
        when(colors[position])
        {
            Constants.COLOR_BLUE -> {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(appContext, R.color.appBlue))
                holder.itemView.setOnClickListener {
                    delegator.colorPicked(Constants.COLOR_BLUE)
                }
            }

            Constants.COLOR_RED -> {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(appContext, R.color.appRed))
                holder.itemView.setOnClickListener {
                    delegator.colorPicked(Constants.COLOR_RED)
                }
            }

            Constants.COLOR_GREEN -> {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(appContext, R.color.appGreen))
                holder.itemView.setOnClickListener {
                    delegator.colorPicked(Constants.COLOR_GREEN)
                }
            }
        }

    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorText: TextView = itemView.findViewById(R.id.color_textiew)
    }
}