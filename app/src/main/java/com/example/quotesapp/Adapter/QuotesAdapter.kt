package com.example.quotesapp.Adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.DashbordActivity
import com.example.quotesapp.EditDataActivity
import com.example.quotesapp.Model.QuotesModel
import com.example.quotesapp.R

class QuotesAdapter(
    var context: Context,
    var quoteslist: ArrayList<QuotesModel>,
    var like: (id: Int, Any?) -> Unit,
    var shayriseclte:((shayri:String)->Unit),


    var onquotes:((id:Int)->Unit))


:RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var txtquotes: TextView = v.findViewById(R.id.txtquotes)

        var imgbtncopy: ImageButton = v.findViewById(R.id.imgbtncopy)
        var imgbtnedit: ImageButton = v.findViewById(R.id.imgbtnedit)
        var imglike: ImageView = v.findViewById(R.id.imglike)
        var imgbtnshare: ImageButton = v.findViewById(R.id.imgbtnshare)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        context = parent.context

        var v = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item, parent, false)
        var holder = MyViewHolder(v)

        return holder
    }

    override fun getItemCount(): Int {
        return quoteslist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtquotes.text = quoteslist.get(position).quotes

        holder.txtquotes.setOnClickListener {

            onquotes.invoke(quoteslist.get(position).id)
        }


        holder.imgbtncopy.setOnClickListener {

            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", holder.txtquotes.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "text copy", Toast.LENGTH_SHORT).show()

        }

        holder.imgbtnshare.setOnClickListener {

            val k = Intent(Intent.ACTION_SEND)
            k.type = "text/plain"
            k.putExtra(Intent.EXTRA_TEXT, quoteslist[position].quotes)
            k.putExtra(Intent.EXTRA_SUBJECT, quoteslist[position].quotes)
            context.startActivity(k)

        }
        holder.imgbtnedit.setOnClickListener {

            shayriseclte.invoke(quoteslist[position].quotes)
            var i=Intent(holder.imgbtnedit.context,EditDataActivity::class.java)
            i.putExtra("id",quoteslist[position].id)
             i.putExtra("shayari",quoteslist[position].quotes)
            holder.imgbtnedit.context.startActivity(i)

        }

        holder.imglike.setOnClickListener {

            if (quoteslist[position].status == 1) {
                holder.imglike.setImageResource(R.drawable.like1)
                like.invoke(quoteslist[position].id, 1)
            } else {
                holder.imglike.setImageResource(R.drawable.like2)
                like.invoke(quoteslist[position].id, 0)
            }

        }
        holder.imglike.setOnClickListener {

            if (quoteslist[position].status == 1) {

                like.invoke(quoteslist[position].id, 0)
                holder.imglike.setImageResource(R.drawable.like1)
                quoteslist[position].status = 0
            } else {
                like.invoke(quoteslist[position].id, 1)
                holder.imglike.setImageResource(R.drawable.like2)
                quoteslist[position].status = 1

            }
        }
    }
}





