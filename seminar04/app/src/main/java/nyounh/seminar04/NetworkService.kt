package nyounh.seminar04

import nyounh.seminar04.get.GetBoardResponse
import nyounh.seminar04.post.PostBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkService {
    @GET("board")
    fun getContent() : Call<GetBoardResponse>
    //파일과 함께 전송
    @Multipart
    @POST("board")
    fun postBoard(
            @Part boardImg : MultipartBody.Part?,
            @Part("user_id") id : RequestBody,
            @Part("board_title") title : RequestBody,
            @Part("board_content") content : RequestBody
    ) : Call<PostBoardResponse>
}