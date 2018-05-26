package seminar06.nyh.seminar_06

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var memberRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        login_checkin_btn.setOnClickListener {
            val id = login_id_tv.text.toString()
            val pwd = login_pw_tv.text.toString()
            if(!getMemberList(id).isEmpty()){
                Toast.makeText(this, "존재하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                if(login_check_cb.isChecked){
                    //객체화 필요없이 바로 사용함
                    SharedPreferenceController.setID(this, id)
                    SharedPreferenceController.setPWD(this, pwd)
                }
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }

        }

        login_signin_btn.setOnClickListener {
            startActivity(Intent(this,SigninActivity::class.java))
        }
    }

    private fun init(){
        Realm.init(this)
        memberRealm = Realm.getDefaultInstance()
    }

    private fun getMemberList(id:String) : RealmResults<MemberVO> {
        return memberRealm.where(MemberVO::class.java).equalTo("id",id).findAll()
    }
}
