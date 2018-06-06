package seminar06.nyh.bamin_colaboration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_information_tab.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.data.GetInfoResponse
import seminar06.nyh.bamin_colaboration.data.InfoData
import seminar06.nyh.bamin_colaboration.network.NetworkService


class InformationTab : Fragment(){
    lateinit var networkService : NetworkService
    lateinit var infoItem : ArrayList<InfoData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_information_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        infoItem = ArrayList()
        networkService = ApplicationController.instance.networkService
        val getInfoResponse = networkService.getInfo(2)
        getInfoResponse.enqueue(object : Callback<GetInfoResponse>{
            override fun onFailure(call: Call<GetInfoResponse>?, t: Throwable?) {
                toast("info request fail")
            }

            override fun onResponse(call: Call<GetInfoResponse>?, response: Response<GetInfoResponse>?) {
                infoItem = response!!.body().data
                setView()
            }
        })
    }

    fun setView(){
        information_tab_review_count_tv.text = infoItem[0].review_count.toString()
        info_tab_content_tv.text = infoItem[0].store_info
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_image_black_24dp)
        requestOptions.error(R.drawable.ic_image_black_24dp)
        requestOptions.centerCrop()
        Glide.with(context!!)
                .setDefaultRequestOptions(requestOptions)
                .load(infoItem[0].store_image)
                .into(information_tab_picture_iv)
    }
}