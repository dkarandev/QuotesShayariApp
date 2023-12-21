package com.example.quotesapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.Model.CategoryModel
import com.example.quotesapp.R

class CategoryAdapter(var list:ArrayList<CategoryModel>, var oncategory:((id:Int)->Unit)) :RecyclerView.Adapter<CategoryAdapter.MyViewHolder>(){
    class MyViewHolder(v:View):RecyclerView.ViewHolder(v) {

        var txtcategory:TextView=v.findViewById(R.id.txtcategory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var v=LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        var holder= MyViewHolder(v)

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtcategory.text=list.get(position).categoryname

        holder.txtcategory.setOnClickListener {

            oncategory.invoke(list.get(position).id)
        }

        Log.e("TAG", "onBindViewHolder: "+holder.txtcategory )

    }

    override fun getItemCount(): Int {

        return list.size

    }
}

