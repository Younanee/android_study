package com.hyeong.hapdong


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import seminar06.nyh.bamin_colaboration.R

/**
 * Created by HYEON on 2018-05-24.
 */
class BookTab : Fragment() ,View.OnClickListener{


    lateinit var bookmarkitems : ArrayList<BookmarkData>
    lateinit var bookmarkAdapter : BookMarkAdapter
    override fun onClick(v: View?) {

        // val idx : Int = book_recycle.getChildAdapterPosition(v)
        // val profile : Int = bookmarkitems[idx].profile
        //val name : String = bookmarkitems[idx].name
        //val review : String = bookmarkitems[idx].review
        // val mene : String = bookmarkitems[idx].menu
        // var intent : Intent = Intent(applicationContext,BookTab::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_bookmark, container, false)

        bookmarkitems = ArrayList()
        bookmarkitems.add(BookmarkData(R.drawable.brown, "밥해주는 남자", "최근리뷰 0","123456789"))
        bookmarkitems.add(BookmarkData(R.drawable.selly, "밥해주는 남자", "최근리뷰 0","통오징어 떡볶이와 참치마요밥, 돼지고기 김치찌개"))
        bookmarkitems.add(BookmarkData(R.drawable.brown, "밥해주는 남자", "최근리뷰 0","통오징어 떡볶이와 참치마요밥, 돼지고기 김치찌개"))
        bookmarkitems.add(BookmarkData(R.drawable.brown, "밥해주는 남자", "최근리뷰 0","통오징어 떡볶이와 참치마요밥, 돼지고기 김치찌개"))
        bookmarkitems.add(BookmarkData(R.drawable.brown, "밥해주는 남자", "최근리뷰 0","통오징어 떡볶이와 참치마요밥, 돼지고기 김치찌개"))
        bookmarkitems.add(BookmarkData(R.drawable.brown, "밥해주는 남자", "최근리뷰 0","통오징어 떡볶이와 참치마요밥, 돼지고기 김치찌개"))

        bookmarkAdapter = BookMarkAdapter(bookmarkitems)
        Log.d("itemSize",bookmarkAdapter.itemCount.toString())
        bookmarkAdapter.setOnItemClickListener(this)

        v.book_recycle!!.adapter = bookmarkAdapter
        v.book_recycle!!.layoutManager = LinearLayoutManager(context)


        return v
    }
}