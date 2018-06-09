package com.user.sopt_delivery

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.user.sopt_delivery.get.GetMainResponse
import com.user.sopt_delivery.get.GetMainResponseData
import kotlinx.android.synthetic.main.activity_init.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.ApplicationController
import seminar06.nyh.bamin_colaboration.R
import seminar06.nyh.bamin_colaboration.network.NetworkService

class HomeTab : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {

        var idx : Int = main_home_category.getChildAdapterPosition(v)

        Toast.makeText(context,idx.toString(),Toast.LENGTH_SHORT).show()
        val intent : Intent = Intent(context, CategoryActivity::class.java)
        intent.putExtra("idx", idx)
        startActivity(intent)
    }

    lateinit var networkService: NetworkService
    lateinit var homeAdapter: HomeAdapter
    lateinit var categoryItems: ArrayList<GetMainResponseData>
    lateinit var requestManager: RequestManager // 이미지를 불러올 때 처리

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { // 함수 만들 때 fun이라는 키워드 사용
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)
        main_home_category.layoutManager = GridLayoutManager(context,2)

        val getMainResponse: Call<GetMainResponse> = networkService.getContent()
        getMainResponse.enqueue(object : Callback<GetMainResponse> {
            override fun onFailure(call: Call<GetMainResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetMainResponse>?, response: Response<GetMainResponse>?) {

                if (response!!.isSuccessful) {
                    categoryItems = response.body().data
                    homeAdapter = HomeAdapter(categoryItems,requestManager)
                    homeAdapter.setOnItemClickListener(this@HomeTab)
                    main_home_category.adapter = homeAdapter




                }
            }
        })
    }

}
