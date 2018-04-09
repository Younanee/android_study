package com.nyoun_000.android_semina2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mine.*

class MineTab : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_mine, container, false)
        addFragment(AllTab())
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_all_of_fragment_mine.setOnClickListener {
            replaceFragment(AllTab())
        }

        iv_align_of_fragment_mine.setOnClickListener {
            replaceFragment(AlignTab())
        }
        iv_save_of_fragment_mine.setOnClickListener {
            val intent = Intent(context, SaveActivity::class.java)
            intent.putExtra("save_image", R.drawable.my_save_off)
            startActivity(intent)
        }
    }

    fun addFragment(fragment: Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.mine_tab_frame, fragment)
        transaction.commit()
    }
    fun replaceFragment(fragment: Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.mine_tab_frame,fragment)
        transaction.commit()
    }
}