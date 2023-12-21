package com.example.quotesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.Adapter.CategoryAdapter
import com.example.quotesapp.DataBase.MyDatabase
import com.example.quotesapp.Model.CategoryModel
import com.example.quotesapp.databinding.ActivityDashbordBinding


class DashbordActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashbordBinding
    lateinit var myadapter: CategoryAdapter

    lateinit var databasehelper: MyDatabase
    var list= ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDashbordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initview()
    }

    private fun initview() {

        databasehelper = MyDatabase(this)
        list = databasehelper.CategoryDisplayData()


        binding.imgbtnback.setOnClickListener {
            onBackPressed()
        }
        binding.imgheart.setOnClickListener {

            var i=Intent(this@DashbordActivity,FavoriteActivity::class.java)
            startActivity(i)

        }

        myadapter = CategoryAdapter(list, oncategory = {id ->

            var i=Intent(this@DashbordActivity,QuotesActivity::class.java)
            i.putExtra("id",id)
            startActivity(i)
        })

        val layoutManager=LinearLayoutManager(this@DashbordActivity,LinearLayoutManager.VERTICAL,false)
        binding.rtlrecyclerview.layoutManager=layoutManager

        binding.rtlrecyclerview.adapter=myadapter

    }

}