package nyounh.seminar04

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import nyounh.seminar04.get.GetBoardResponseData

class BoardAdapter(var boardItems : ArrayList<GetBoardResponseData>, var requestManager : RequestManager) : RecyclerView.Adapter<BoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return BoardViewHolder(mainView)
    }

    override fun getItemCount(): Int = boardItems.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.boardContent.text = boardItems[position].board_content
        holder.boardId.text = boardItems[position].user_id
        holder.boardTime.text = boardItems[position].board_writetime
        holder.boardTitle.text = boardItems[position].board_title
        //adapter는 바로 activity나 fragment랑 연결된게 아니라 requestManager를 통해서 사용
        //activity나 fragment에서는 바로 사용가능.
        requestManager.load(boardItems[position].board_photo).into(holder.boardProfile)

    }

}