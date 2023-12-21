package com.example.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.Adapter.QuotesAdapter
import com.example.quotesapp.DataBase.MyDatabase
import com.example.quotesapp.Model.QuotesModel
import com.example.quotesapp.databinding.ActivityQuotesBinding

class QuotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuotesBinding
    lateinit var database: MyDatabase

    lateinit var myadapter: QuotesAdapter
    var quotelist= ArrayList<QuotesModel>()
    lateinit var  db : MyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {


        db = MyDatabase(this)

        val id = intent.getIntExtra("id",0)

        if(id == 1)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Happy"
        }
        if(id == 2)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Sad"
        }
        if(id == 3)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Life"
        }
        if(id == 4)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Father"
        }
        if(id == 5)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Mother"
        }
        if(id == 6)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Brother"
        }
        if(id == 7)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Principal"
        }
        if(id == 8)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Sister"
        }
        if(id == 9)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Techaer"
        }
        if(id == 10)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Business"
        }
        if(id == 11)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Body"
        }
        if(id == 12)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Car"
        }
        if(id == 13)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Food"
        }
        if(id == 14)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Blood"
        }
        if(id == 15)
        {
            db.QuotesDisplayData(id)
            binding.txtquotes.text="Friend"
        }

       quotelist = db.QuotesDisplayData(id)

        myadapter= QuotesAdapter(this,quotelist, onquotes = {



        }, shayriseclte = {}, like =  { id, status->

            db.UpdateData(id, status as Int)
            Log.e("TAG", "updatedata: "+id+""+status)


        })

        val layoutManager=LinearLayoutManager(this@QuotesActivity,LinearLayoutManager.VERTICAL,false)
        binding.recycleryview.layoutManager=layoutManager

        binding.recycleryview.adapter=myadapter

    }


}