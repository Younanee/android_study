package nyounh.seminar04

import nyounh.seminar04.get.GetBoardResponse
import nyounh.seminar04.post.PostBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @GET("board")
    fun getContent() : Call<GetBoardResponse>
    //파일과 함께 전송
    @Multipart
    @POST("board")
    fun postBoard(
            //자동으로 바디(post전송 방식인)를 만들어서 슝슝보내줌
            @Part boardImg : MultipartBody.Part?,
            @Part("user_id") id : RequestBody,
            @Part("board_title") title : RequestBody,
            @Part("board_content") content : RequestBody
    ) : Call<PostBoardResponse>


}