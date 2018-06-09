package com.user.sopt_delivery

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.user.sopt_delivery.get.GetMainResponseData
import seminar06.nyh.bamin_colaboration.R

class HomeAdapter (var categoryItems : ArrayList<GetMainResponseData>,var requestManager: RequestManager) : RecyclerView.Adapter<HomeViewHolder>() {

    lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.category.text = categoryItems[position].category
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return HomeViewHolder(mainView)
    }

    override fun getItemCount(): Int = categoryItems.size


}