package seminar06.nyh.seminar_06

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    lateinit var memberRealm : Realm
    lateinit var memberVO: MemberVO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        init()

        signin_check_btn.setOnClickListener {
            val id = signin_id_et.text.toString()
            if (getMemberList(id).isEmpty()){
                insertMemberList()
                Toast.makeText(this, "가입완료", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }



    fun init(){
        Realm.init(this)
        memberRealm = Realm.getDefaultInstance()
    }

    fun getMemberList(id:String) : RealmResults<MemberVO>{
        return memberRealm.where(MemberVO::class.java).equalTo("id",id).findAll()
    }
    fun insertMemberList(){
        memberVO = MemberVO()

        memberVO.id = signin_id_et.text.toString()
        memberVO.pwd = signin_pw_et.text.toString()

        memberRealm.beginTransaction()
        memberRealm.copyToRealm(memberVO)
        memberRealm.commitTransaction()
    }
}
