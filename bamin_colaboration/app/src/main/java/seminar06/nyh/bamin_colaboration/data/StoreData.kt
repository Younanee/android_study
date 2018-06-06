package seminar06.nyh.bamin_colaboration.data

import java.io.File

//GET Store Menu
data class GetMenuResponse(var message : String,
                           var data : ArrayList<MenuData>)
data class MenuData(var menu_name : String,
                    var menu_price : String)
//GET Store Info
data class GetInfoResponse(var message: String,
                           var data : ArrayList<InfoData>)
data class InfoData(var store_info : String,
                    var store_image : String,
                    var review_count : Int)

//GET Store Review
data class GetReviewResponse(var message: String,
                           var data : ArrayList<ReviewData>)
data class ReviewData(var review_content : String,
                      var review_image : String,
                      var writing_time : String,
                      var user_id : String)

//Post Store REview
data class PostReviewRequest(
        var message : String
)