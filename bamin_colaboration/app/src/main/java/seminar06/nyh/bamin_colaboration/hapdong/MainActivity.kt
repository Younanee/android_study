package com.hyeong.hapdong


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import seminar06.nyh.bamin_colaboration.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

        when(v) {
            btn_main_home -> {
                replaceFragment(HomeTab())
            }
            btn_main_bookmark -> {
                replaceFragment(BookTab())
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeTab())

        btn_main_home.setOnClickListener(this)
        btn_main_bookmark.setOnClickListener(this)



    }
    //Fragment 붙이는 함수 (리턴타입이 없는 Unit= 생략해도됨)
    fun addFragment(fragment: Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame,fragment)
        transaction.commit()
    }
    //Fragment 교체
    fun replaceFragment(fragment: Fragment) : Unit {

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame,fragment)
        transaction.commit()
    }
}
