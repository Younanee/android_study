package seminar08.nyh.seminar08

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_splash.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class SplashActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        callbackManager = CallbackManager.Factory.create()
        splash_fb_login_btn.setOnClickListener {
            loginWithFaceBook()
        }
        //해쉬키 얻는 코드
//        try {
//            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//            for (signature in info.signatures) {
//                val md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace()
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        }

    }

    fun loginWithFaceBook(){
        LoginManager.getInstance().logInWithReadPermissions(this,
                Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request: GraphRequest
                request = GraphRequest.newMeRequest(loginResult.accessToken) { user, response ->
                    if (response.error != null) {

                    } else {
                        //id값이나 토큰 값을 통해서 유저를 판단하는데, 보통 id값을 가지고 유저를 분류한다.
                        //토큰값을 서버로 보내면 토큰 유효기간때문에 안될 수 도 있다.
                        //서버와 연동 시 id 값이 서버로 request하면 server에서 성공 유무의 message를 넘겨줄것이다.
                        Log.i("TAG", "user: " + user.toString())
                        Log.i("TAG", "AccessToken: " + loginResult.accessToken.token)
                        Log.i("TAG", "AccessToken: " + loginResult.accessToken.userId)

                        setResult(Activity.RESULT_OK)
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday")
                request.parameters = parameters
                request.executeAsync()
            }
            override fun onCancel() {
            }
            override fun onError(error: FacebookException) {
                Log.e("facebook login error", error.toString())
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

    }
}
