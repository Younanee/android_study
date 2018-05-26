package seminar06.nyh.seminar_06

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(SharedPreferenceController.getID(this) == ""){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            } else {
                val id = SharedPreferenceController.getID(this)
                startActivity(Intent(this,MainActivity::class.java))
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }



        },1000)

    }
}
