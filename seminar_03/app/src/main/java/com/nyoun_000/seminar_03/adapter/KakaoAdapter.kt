package com.nyoun_000.seminar_03.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nyoun_000.seminar_03.R
import com.nyoun_000.seminar_03.data.KakaoData
import com.nyoun_000.seminar_03.viewHolder.KakaoViewHolder

class KakaoAdapter(private var kakaoItems : ArrayList<KakaoData>) : RecyclerView.Adapter<KakaoViewHolder>() {
    private lateinit var onItemClick: View.OnClickListener


    //메인에서 불러와서 매개변수로 this를 넘겨줌
    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.kakao_item, parent, false)
        //클릭 리스너 넣음
        mainView.setOnClickListener(onItemClick)
        return KakaoViewHolder(mainView)
    }

    override fun getItemCount(): Int = kakaoItems.size


    //요기서 어떻게 어떻게 코딩하면 사진 눌렀을때 리스너 달 수 있대요
    override fun onBindViewHolder(holder: KakaoViewHolder, position: Int) {
        holder.kakaoProfile.setImageResource(kakaoItems[position].profile)
        holder.kakaoName.text = kakaoItems[position].name
        holder.kakaoDate.text = kakaoItems[position].date
        holder.kakaoPreview.text = kakaoItems[position].preView
    }



}