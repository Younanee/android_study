package com.nyoun_000.seminar_03

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.nyoun_000.seminar_03.adapter.KakaoAdapter
import com.nyoun_000.seminar_03.data.KakaoData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val idx : Int = main_recycleView.getChildAdapterPosition(v)//아이템을 눌렀을때 해당 인덱스가 넘어감
        val name : String = kakaoItems[idx].name
        val profile : Int = kakaoItems[idx].profile
        var intent : Intent = Intent(applicationContext, ChatActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("profile", profile)
        startActivity(intent)
    }

    lateinit var kakaoItems : ArrayList<KakaoData>
    //var kakaoItem2 : ArrayList<KakaoData> = ArrayList()
    //var kakaoItem3 : ArrayList<KakaoData>? = null

    lateinit var kakaoAdapter: KakaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kakaoItems = ArrayList()
        kakaoItems.add(KakaoData(R.drawable.p1, "친구들", "예??", "오후 4:50"))
        kakaoItems.add(KakaoData(R.drawable.p2, "특론1 팀플", "낼 6시 회의요", "오후 4:15"))
        kakaoItems.add(KakaoData(R.drawable.p3, "캡스톤 팀플", "어디까지 구현함?", "오후 4:11"))
        kakaoItems.add(KakaoData(R.drawable.p4, "4차 산업혁명 팀플", "저희 다음주 발표에요", "오후 4:01"))
        kakaoItems.add(KakaoData(R.drawable.p5, "가족 톡방", "저녁밥 뭐먹어?", "오후 3:10"))
        kakaoItems.add(KakaoData(R.drawable.p6, "컴공 스터디 톡방", "과행사있어요~ 참여점", "2:50"))
        kakaoItems.add(KakaoData(R.drawable.p7, "SOPT 안드 4조 톡방", "세미나 자료04", "오후 2:31"))
        kakaoItems.add(KakaoData(R.drawable.p8, "SOPT 안드로이드 톡방", "뒷풀이 가실분!", "오후 2:15"))
        kakaoItems.add(KakaoData(R.drawable.p9, "강연 조", "수고했어요~~~", "오후 1:15"))
        kakaoItems.add(KakaoData(R.drawable.p10, "동창회", "언제봄?", "오후 12:16"))

        kakaoAdapter = KakaoAdapter(kakaoItems)
        //클릭 리스너 넣어주는 작업
        kakaoAdapter.setOnItemClickListener(this)
        //리사이클뷰가 적용될 레이아웃, 어떤식으로 보여질지
        main_recycleView.layoutManager = LinearLayoutManager(this)
        //main_recycleView.layoutManager = GridLayoutManager(this,3)
        //어댑터 달아줌!
        main_recycleView.adapter = kakaoAdapter

    }


}
