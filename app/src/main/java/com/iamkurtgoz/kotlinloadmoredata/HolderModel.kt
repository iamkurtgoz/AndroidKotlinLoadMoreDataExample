package com.iamkurtgoz.kotlinloadmoredata

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HolderModel(view: View): RecyclerView.ViewHolder(view){

    val textTitle: TextView = view.findViewById(R.id.list_item_text_row_textTitle)
}