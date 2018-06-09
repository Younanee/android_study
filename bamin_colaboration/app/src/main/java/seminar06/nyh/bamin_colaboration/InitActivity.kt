package com.user.sopt_delivery


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_init.*
import seminar06.nyh.bamin_colaboration.R

class InitActivity : AppCompatActivity(), View.OnClickListener {
    var user_idx : Int = 0
    override fun onClick(v: View?){
        when(v){
            main_home_tab ->{
                clearSelected()
                main_home_tab.isSelected = true
                replaceFragment(HomeTab())
            }

            main_bookmark_tab ->{
                clearSelected()
                main_bookmark_tab.isSelected = true
                replaceFragment(BookmarkTab())
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)


        addFragment(HomeTab())

        main_home_tab.isSelected  = true
        main_home_tab.setOnClickListener(this)
        main_bookmark_tab.setOnClickListener(this)
        user_idx = intent.getIntExtra("user_idx",0)



    }

    fun addFragment(fragment: Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)
        transaction.commit()

    }

    fun replaceFragment(fragment: Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.commit()
    }

    fun clearSelected() {
        main_home_tab.isSelected = false
        main_bookmark_tab.isSelected = false

    }
}
