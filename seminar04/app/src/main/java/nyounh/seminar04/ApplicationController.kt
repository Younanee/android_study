package nyounh.seminar04

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application() {
    lateinit var networkService: NetworkService
    private val baseUrl = "http://13.125.118.111:3009/"

    companion object {
        lateinit var instence : ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        //앱 어디서든 쓸수 있도록 자기자신을 static
        instence = this
        buildNetwork()
        //어플이 뜨기도 전에 실행
    }

    fun buildNetwork(){
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        networkService = retrofit.create(NetworkService::class.java)
    }
}