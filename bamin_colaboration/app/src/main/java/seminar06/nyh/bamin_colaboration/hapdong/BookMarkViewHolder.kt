package com.hyeong.hapdong

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import seminar06.nyh.bamin_colaboration.R

/**
 * Created by HYEON on 2018-05-25.
 */
class BookMarkViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var bookmarkprofile : ImageView = itemView.findViewById(R.id.market_pic) as ImageView
    var bookmarkname : TextView = itemView.findViewById(R.id.market_name) as TextView
    var bookmarkreview : TextView = itemView.findViewById(R.id.market_review) as TextView
    var bookmarkmenu : TextView = itemView.findViewById(R.id.market_menu) as TextView
}