package com.nyoun_000.android_semina2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            btn_main_home -> {
                clearSelected()
                btn_main_home.isSelected = true
                replaceFragment(HomeTab())
            }
            btn_main_mine -> {
                clearSelected()
                btn_main_mine.isSelected = true
                replaceFragment(MineTab())
            }
            btn_main_add -> {
                val intent = Intent(applicationContext, AddActivity::class.java)
                intent.putExtra("add_image",R.drawable.add_image)
                startActivity(intent)
            }

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeTab()) // addFragment(new HomeTab()) 자바코드 같은 의미
        //방법1
//        btn_main_home.setOnClickListener {
//            replaceFragment(HomeTab())
//        }
//        btn_main_mine.setOnClickListener {
//            replaceFragment(MineTab())
//        }

        //방법2
        btn_main_home.isSelected = true
        btn_main_home.setOnClickListener(this)
        btn_main_mine.setOnClickListener(this)
        btn_main_add.setOnClickListener(this)






    }

    fun addFragment(fragment: Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)

        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null) //백키 눌렀을때 순차대로
        transaction.commit()
    }

    fun clearSelected(){
        btn_main_home.isSelected = false
        btn_main_search.isSelected = false
        //btn_main_news.isSelected = false
        btn_main_mine.isSelected = false
    }
}
