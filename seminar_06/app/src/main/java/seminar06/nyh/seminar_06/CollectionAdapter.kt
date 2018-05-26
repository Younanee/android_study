package seminar06.nyh.seminar_06

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CollectionAdapter(val ctx : Context, val collectionItems : ArrayList<PokemonVO>) : RecyclerView.Adapter<CollectionAdapter.Holder>() {
    lateinit var onItemClick : View.OnClickListener
    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.collection_item, parent, false)
        setOnItemClickListener(onItemClick)
        return Holder(view)
    }

    override fun getItemCount(): Int = collectionItems.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.num.text = collectionItems[position].num.toString()
        holder.name.text = collectionItems[position].name
        holder.type.text = collectionItems[position].type
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val num : TextView = itemView.findViewById(R.id.collection_item_num_tv) as TextView
        val name : TextView = itemView.findViewById(R.id.collection_item_name_tv) as TextView
        val type : TextView = itemView.findViewById(R.id.collection_item_type_tv) as TextView
    }

}