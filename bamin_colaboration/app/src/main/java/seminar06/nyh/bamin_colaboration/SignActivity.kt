package com.user.sopt_delivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.user.sopt_delivery.post.PostSignupResponse
import kotlinx.android.synthetic.main.activity_sign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.ApplicationController
import seminar06.nyh.bamin_colaboration.R
import seminar06.nyh.bamin_colaboration.network.NetworkService

class SignActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        networkService = ApplicationController.instance.networkService

        signup_sign_btn.setOnClickListener {
            postSignup()
        }

    }

    fun postSignup() {
        val id = signup_id_tv.text.toString()
        val pw = signup_pw_tv.text.toString()
        val postSignupResponse = networkService.postSignup(id, pw)

        postSignupResponse.enqueue(object : Callback<PostSignupResponse> {
            override fun onFailure(call: Call<PostSignupResponse>?, t: Throwable?) {

                Toast.makeText(applicationContext, "Already Joined or Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PostSignupResponse>?, response: Response<PostSignupResponse>?) {
                var message: String?

                try {
                    message = response!!.body()!!.message
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    message = "Already Joined"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
}
