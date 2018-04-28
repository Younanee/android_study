package nyounh.seminar04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_main.*
import nyounh.seminar04.get.GetBoardResponse
import nyounh.seminar04.get.GetBoardResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var networkService: NetworkService
    lateinit var boardAdapter: BoardAdapter
    lateinit var boardItems: ArrayList<GetBoardResponseData>
    lateinit var requestManager : RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instence.networkService
        requestManager = Glide.with(this)
        main_board_list_rv.layoutManager = LinearLayoutManager(this)
        main_write_btn.setOnClickListener {
            startActivity(Intent(applicationContext, BoardActivity::class.java))
        }

        val getBoardResponse = networkService.getContent()
        getBoardResponse.enqueue(object : Callback<GetBoardResponse>{
            override fun onFailure(call: Call<GetBoardResponse>?, t: Throwable?) {
                //요청에 대한 응답 실패시
            }

            override fun onResponse(call: Call<GetBoardResponse>?, response: Response<GetBoardResponse>?) {
                //제대로 값을 보냈고 통신도 잘 연결되있다면
                if (response!!.isSuccessful){
                    boardItems = response.body().data
                    boardAdapter = BoardAdapter(boardItems, requestManager)
                    main_board_list_rv.adapter = boardAdapter
                }
            }

        })
    }


}
