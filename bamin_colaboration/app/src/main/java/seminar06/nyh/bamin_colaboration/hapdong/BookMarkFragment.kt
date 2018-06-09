package com.hyeong.hapdong

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import seminar06.nyh.bamin_colaboration.R

/**
 * Created by HYEON on 2018-05-25.
 */
class BookMarkFragment : Fragment() , View.OnClickListener {
    override fun onClick(p0: View?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_bookmark,container,false)

        return v
    }
}