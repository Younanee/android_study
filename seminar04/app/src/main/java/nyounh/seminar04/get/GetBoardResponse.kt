package nyounh.seminar04.get

data class GetBoardResponse (
        var message : String,
        var data : ArrayList<GetBoardResponseData>
)