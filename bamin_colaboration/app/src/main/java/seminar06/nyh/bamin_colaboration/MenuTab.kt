package seminar06.nyh.bamin_colaboration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu_tab.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.adapter.MenuTabRecyclerViewAdapter
import seminar06.nyh.bamin_colaboration.data.GetMenuResponse
import seminar06.nyh.bamin_colaboration.data.MenuData
import seminar06.nyh.bamin_colaboration.network.NetworkService

class MenuTab : Fragment() {

    lateinit var networkService : NetworkService
    lateinit var menuItems : ArrayList<MenuData>
    lateinit var recyclerViewAdapter : MenuTabRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        menuItems = ArrayList()
        networkService = ApplicationController.instance.networkService
        val getMenuResponse = networkService.getMenu(2)
        getMenuResponse.enqueue(object : Callback<GetMenuResponse>{
            override fun onFailure(call: Call<GetMenuResponse>?, t: Throwable?) {
                toast("Response failed!")
                Log.e("실패 로그", t.toString())
            }

            override fun onResponse(call: Call<GetMenuResponse>?, response: Response<GetMenuResponse>?) {
                if (response!!.isSuccessful){
                    menuItems = response.body().data
                    setView()
                    toast(response.message())
                }
            }
        })


        menu_tab_list_refresh_layout.setOnRefreshListener {
            setView()
        }

    }

    private fun setView(){
        menu_tab_list_refresh_layout.isRefreshing = true
        recyclerViewAdapter = MenuTabRecyclerViewAdapter(context!!,menuItems)
        menu_tab_item_list_rv.layoutManager = LinearLayoutManager(context)
        menu_tab_item_list_rv.adapter = recyclerViewAdapter
        menu_tab_list_refresh_layout.isRefreshing = false
    }


}