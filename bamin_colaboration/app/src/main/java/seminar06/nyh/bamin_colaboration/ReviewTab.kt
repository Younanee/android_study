package seminar06.nyh.bamin_colaboration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_review_tab.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.adapter.ReviewTabRecyclerViewAdapter
import seminar06.nyh.bamin_colaboration.data.GetReviewResponse
import seminar06.nyh.bamin_colaboration.data.ReviewData
import seminar06.nyh.bamin_colaboration.network.NetworkService

class ReviewTab : Fragment() {
    val REQUEST_CODE_REVIEW_TAB = 1001
    lateinit var reviewItems : ArrayList<ReviewData>
    lateinit var networkService : NetworkService

    lateinit var recyclerViewAdapter : ReviewTabRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        reviewItems = ArrayList()
        networkService = ApplicationController.instance.networkService
        val getMenuResponse = networkService.getReview(2)
        getMenuResponse.enqueue(object : Callback<GetReviewResponse>{
            override fun onFailure(call: Call<GetReviewResponse>?, t: Throwable?) {
                Log.e("실패 로그", t.toString())
            }

            override fun onResponse(call: Call<GetReviewResponse>?, response: Response<GetReviewResponse>?) {
                if (response!!.isSuccessful){
                    reviewItems = response.body().data
                    setView()
                    toast(response.message())
                }
            }
        })

        review_tab_add_review_btn_fb.setOnClickListener {
            val intent = Intent(context, CreateReviewActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE_REVIEW_TAB)
        }
        review_tab_list_refresh_layout.setOnRefreshListener {
            setView()
        }
    }


    private fun setView(){
        review_tab_list_refresh_layout.isRefreshing = true

        recyclerViewAdapter = ReviewTabRecyclerViewAdapter(context!!,reviewItems.take(20) as ArrayList<ReviewData>)
        review_tab_item_list_rv.layoutManager = LinearLayoutManager(context)
        review_tab_item_list_rv.adapter = recyclerViewAdapter
        review_tab_list_refresh_layout.isRefreshing = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REVIEW_TAB){
            if (resultCode == Activity.RESULT_OK){
                //추가시 리플래시
            }
        }
    }
}