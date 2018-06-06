package seminar06.nyh.bamin_colaboration.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import seminar06.nyh.bamin_colaboration.InformationTab
import seminar06.nyh.bamin_colaboration.MenuTab
import seminar06.nyh.bamin_colaboration.ReviewTab

class RestaurantTabAdapter(var tabCount : Int, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0-> MenuTab()
            1-> InformationTab()
            2-> ReviewTab()
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}