package com.user.sopt_delivery

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import seminar06.nyh.bamin_colaboration.R

class HomeViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView){
    var category : TextView = itemView!!.findViewById(R.id.main_category_name) as TextView
}