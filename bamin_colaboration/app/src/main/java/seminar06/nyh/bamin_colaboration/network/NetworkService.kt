package seminar06.nyh.bamin_colaboration.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import seminar06.nyh.bamin_colaboration.data.GetInfoResponse
import seminar06.nyh.bamin_colaboration.data.GetMenuResponse
import seminar06.nyh.bamin_colaboration.data.GetReviewResponse
import seminar06.nyh.bamin_colaboration.data.PostReviewRequest

interface NetworkService {
    @GET("store/menu/{store_idx}")
    fun getMenu(@Path("store_idx") store_idx : Int) : Call<GetMenuResponse>

    @GET("store/info/{store_idx}")
    fun getInfo(@Path("store_idx") store_idx : Int) : Call<GetInfoResponse>

    @GET("store/review/{store_idx}")
    fun getReview(@Path("store_idx") store_idx : Int) : Call<GetReviewResponse>


    @Multipart
    @POST("store/review/{store_idx}")
    fun postReview(
            @Part("user_idx") user_idx : RequestBody,
            @Part review_image : MultipartBody.Part?,
            @Part("review_content") review_content : RequestBody,
            @Path("store_idx") store_idx : Int
    ) : Call<PostReviewRequest>

}