package com.hyeong.hapdong

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import seminar06.nyh.bamin_colaboration.R

/**
 * Created by HYEON on 2018-05-25.
 */
class BookMarkAdapter(private var bookmarkItems : ArrayList<BookmarkData>) : RecyclerView.Adapter<BookMarkViewHolder>() {
    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        holder!!.bookmarkprofile.setImageResource(bookmarkItems.get(position).profile)
        holder!!.bookmarkname.text = bookmarkItems.get(position).name
        holder!!.bookmarkreview.text = bookmarkItems.get(position).review
        holder!!.bookmarkmenu.text = bookmarkItems.get(position).menu    }

    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListener(I : View.OnClickListener){
        onItemClick = I
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return BookMarkViewHolder(mainView)
    }

    override fun getItemCount(): Int = bookmarkItems.size


}