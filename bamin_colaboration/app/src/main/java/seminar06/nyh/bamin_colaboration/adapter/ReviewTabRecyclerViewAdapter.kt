package seminar06.nyh.bamin_colaboration.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import seminar06.nyh.bamin_colaboration.R
import seminar06.nyh.bamin_colaboration.data.MenuData
import seminar06.nyh.bamin_colaboration.data.ReviewData

class ReviewTabRecyclerViewAdapter(val ctx : Context, val data : ArrayList<ReviewData>) : RecyclerView.Adapter<ReviewTabRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.review_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.userId.text = data[position].user_id
        holder.date.text = data[position].writing_time
        holder.content.text = data[position].review_content

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_image_black_24dp)
        requestOptions.error(R.drawable.ic_image_black_24dp)
        requestOptions.centerCrop()
        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(data[position].review_image)
                .into(holder.picture)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userId : TextView = itemView.findViewById(R.id.review_item_id_tv) as TextView
        val date : TextView = itemView.findViewById(R.id.review_item_date_tv) as TextView
        val content : TextView = itemView.findViewById(R.id.review_item_content_tv) as TextView
        val picture : ImageView = itemView.findViewById(R.id.review_item_picture_iv) as ImageView

    }
}