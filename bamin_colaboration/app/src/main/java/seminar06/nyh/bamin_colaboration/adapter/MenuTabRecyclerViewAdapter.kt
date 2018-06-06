package seminar06.nyh.bamin_colaboration.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import seminar06.nyh.bamin_colaboration.R
import seminar06.nyh.bamin_colaboration.data.MenuData

class MenuTabRecyclerViewAdapter(val ctx : Context, val menuItems : ArrayList<MenuData>) : RecyclerView.Adapter<MenuTabRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.menu_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = menuItems.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = menuItems[position].menu_name
        holder.price.text = "${menuItems[position].menu_price}원"
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.menu_name_tv) as TextView
        val price : TextView = itemView.findViewById(R.id.menu_price_tv) as TextView
    }
}