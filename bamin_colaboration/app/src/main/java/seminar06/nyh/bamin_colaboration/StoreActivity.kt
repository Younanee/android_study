package seminar06.nyh.bamin_colaboration

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_store.*
import org.jetbrains.anko.toast
import seminar06.nyh.bamin_colaboration.adapter.RestaurantTabAdapter

class StoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        settingInitView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.restaurant_info_appbar_item, menu)
        val favoriteBtn = menu?.findItem(R.id.action_favorite)
        favoriteBtn!!.setOnMenuItemClickListener {
            toast("I am favorite button")
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun settingInitView(){
        setSupportActionBar(findViewById(R.id.restaurant_info_appbar_tb))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setTabLayout()
        restaurant_info_appbar_back_btn_iv.setOnClickListener {
            toast("I am back button")
        }
    }

    private fun setTabLayout(){
        restaurant_info_tab_layout.addTab(restaurant_info_tab_layout.newTab().setText("메뉴"),0)
        restaurant_info_tab_layout.addTab(restaurant_info_tab_layout.newTab().setText("정보"),1)
        restaurant_info_tab_layout.addTab(restaurant_info_tab_layout.newTab().setText("리뷰"),2)

        restaurant_info_view_pager.adapter = RestaurantTabAdapter(restaurant_info_tab_layout.tabCount, supportFragmentManager)
        restaurant_info_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(restaurant_info_tab_layout))
        restaurant_info_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                restaurant_info_view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                restaurant_info_view_pager.currentItem = tab!!.position
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                restaurant_info_view_pager.currentItem = tab!!.position
            }
        })

    }


}
