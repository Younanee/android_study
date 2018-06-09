package com.user.sopt_delivery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.user.sopt_delivery.post.PostSigninResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.ApplicationController
import seminar06.nyh.bamin_colaboration.R
import seminar06.nyh.bamin_colaboration.network.NetworkService

class LoginActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService
    lateinit var intent1: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        intent1 = Intent(this, InitActivity::class.java)
        networkService = ApplicationController.instance.networkService
        signin_btn.setOnClickListener {
            postSignin()

        }
        signup_btn.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }

    fun postSignin() {
        val id = signin_id_tv.text.toString()
        val pw = signin_pw_tv.text.toString()
        val postSigninResponse = networkService.postSignin(id, pw)

        postSigninResponse.enqueue(object : Callback<PostSigninResponse> {
            override fun onFailure(call: Call<PostSigninResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PostSigninResponse>?, response: Response<PostSigninResponse>?) {
                var message: String?
                var user_idx : Int?
                user_idx = 1
                try {
                    message = response!!.body()!!.message
                    user_idx  = response!!.body()!!.data.user_idx

                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                    intent1.putExtra("user_idx", user_idx)
                    startActivity(intent1)

                } catch (e: Exception) {
                    message = "failed!"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                }
                signin_id_tv.setText("")
                signin_pw_tv.setText("")
                signin_id_tv.clearFocus()
                signin_pw_tv.clearFocus()
            }

        })
    }
}

