package com.example.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.Adapter.FavoriteAdapter
import com.example.quotesapp.DataBase.MyDatabase
import com.example.quotesapp.Model.quotesshayri
import com.example.quotesapp.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {

    lateinit var db : MyDatabase
    var list = ArrayList<quotesshayri>()
    lateinit var adapter : FavoriteAdapter

    lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase(this)
        initview()
    }

    private fun initview() {

        list = db.FavoriteDisplayRecord()


        binding.imgback.setOnClickListener{
            onBackPressed()
        }

        adapter = FavoriteAdapter { Shayari_id,fav ->
                db.UpdateData(Shayari_id, fav)
            }

        var manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.favrecyclerview.layoutManager = manager
        binding.favrecyclerview.adapter = adapter


        adapter.updateList(list)

    }
}