package com.example.quotesapp.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.Model.quotesshayri

class FavoriteAdapter(var like: (Int, Int) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    var list = ArrayList<quotesshayri>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtshayari: TextView = view.findViewById(R.id.txtshayari)
        var imglikes: ImageView = view.findViewById(R.id.imglikes)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtshayari.text = list[position].Shayari_item

        holder.imglikes.setImageResource(R.drawable.like2)

        holder.imglikes.setOnClickListener {
            like.invoke(list[position].Shayari_id, 0)
            holder.imglikes.setImageResource(R.drawable.like1)
            list[position].fav = 0
            deleteItem(position)
        }

    }

    fun updateList(list: ArrayList<quotesshayri>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

}