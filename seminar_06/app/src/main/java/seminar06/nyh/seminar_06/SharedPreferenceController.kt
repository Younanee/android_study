package seminar06.nyh.seminar_06

import android.content.Context

object SharedPreferenceController {
    //AppController처럼
    //언제어디서나 어느 클래스 상관없이 공유데이터를 가진것이 object다.
    //인스턴스 생성 없이 바로 접근가능! 객체화 없이 바로 접근 가능.

    private val USER = "user"

    private val ID = "id"
    private val PWD = "pwd"

    fun setID(context: Context, id : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(ID, id)
        editor.commit()
    }

    fun getID(context: Context) : String {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(ID, "")
    }

    fun setPWD(context: Context, pwd : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(PWD, pwd)
        editor.commit()
    }

    fun getPWD(context: Context) : String {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(PWD, "")
    }

    fun clearSPC(context: Context){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}