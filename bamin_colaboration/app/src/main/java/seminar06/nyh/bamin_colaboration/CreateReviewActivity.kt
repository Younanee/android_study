package seminar06.nyh.bamin_colaboration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_create_review.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seminar06.nyh.bamin_colaboration.data.PostReviewRequest
import seminar06.nyh.bamin_colaboration.network.NetworkService
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class CreateReviewActivity : AppCompatActivity() {
    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data : Uri
    private var image : MultipartBody.Part? = null
    lateinit var networkService : NetworkService


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_review_appbar_item, menu)
        val addBtn = menu?.findItem(R.id.action_add)
        addBtn!!.setOnMenuItemClickListener {
            toast("I am add button")
            true
        }
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_review)
        networkService = ApplicationController.instance.networkService
        settingInitView()
        create_review_appbar_back_btn_iv.setOnClickListener {
            finish()
        }
        create_review_add_btn_bt.setOnClickListener {
            postReview()
            finish()
        }
        create_review_get_img_iv.setOnClickListener {
            changeImage()
        }
    }

    private fun settingInitView(){
        setSupportActionBar(findViewById(R.id.create_review_appbar_tb))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE){
            if (resultCode == Activity.RESULT_OK){
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    image = MultipartBody.Part.createFormData("photo", photo.name, photoBody)

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    val requestOptions = RequestOptions()
                    requestOptions.placeholder(R.drawable.ic_image_black_24dp)
                    requestOptions.error(R.drawable.ic_image_black_24dp)
                    requestOptions.centerCrop()
                    Glide.with(this!!)
                            .setDefaultRequestOptions(requestOptions)
                            .load(data.data)
                            .into(create_review_get_img_iv)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    fun postReview(){
        //임시 유저 인덱스
        val user_idx = RequestBody.create(MediaType.parse("text/plain"), "1")
        val review_image = image
        val review_content = RequestBody.create(MediaType.parse("text/plain"), create_review_content_et.text.toString())
        //임시 음식점 인덱스
        val store_idx = 2

        val postReviewResponse = networkService.postReview(user_idx,review_image,review_content,store_idx)
        postReviewResponse.enqueue(object : Callback<PostReviewRequest>{
            override fun onFailure(call: Call<PostReviewRequest>?, t: Throwable?) {
                Log.e("포스트 실패", t.toString())
            }

            override fun onResponse(call: Call<PostReviewRequest>?, response: Response<PostReviewRequest>?) {
                if (response!!.isSuccessful){
                    Log.e("성공 메시지", response!!.body().message)
                }
            }
        })
    }
}
