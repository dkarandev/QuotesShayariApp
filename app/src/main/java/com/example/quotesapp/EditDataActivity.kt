package com.example.quotesapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesapp.DataBase.MyDatabase
import com.example.quotesapp.databinding.ActivityEditDataBinding
import java.io.IOException

class EditDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditDataBinding
    var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_edit_data)

        initview()
    }

    private fun initview() {


        var db = MyDatabase(this)


        if (intent != null) {
            id = intent.getIntExtra("id", 0)
            var shayari = intent.getStringExtra("shayari")
            binding.edtquotes.setText(shayari)
        }

        binding.imgadd.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "sdfghjkl", Toast.LENGTH_SHORT).show()
//            val i = Intent()
//            i.type = "image/*"
//            i.action = Intent.ACTION_GET_CONTENT

//            startActivityForResult(Intent.createChooser(i, "Select Picture"), 100)
        })
        binding.imgsave.setOnClickListener {

            var quotes=binding.edtquotes.text.toString()

            db.UpdatequoteRecord(id,quotes)

            Toast.makeText(this, "save images", Toast.LENGTH_SHORT).show()

            finish()

        }

        binding.imgdownlod.setOnClickListener {

            val z: View = binding.imgimage
            z.isDrawingCacheEnabled = true
            val totalHeight: Int = z.getHeight()
            val totalWidth: Int = z.getWidth()
            z.layout(0, 0, totalWidth, totalHeight)
            z.buildDrawingCache(true)
            val bm: Bitmap = Bitmap.createBitmap(z.drawingCache)
            z.isDrawingCacheEnabled = false
            Toast.makeText(this@EditDataActivity, "Download Successfully", Toast.LENGTH_SHORT)
                .show()
            MediaStore.Images.Media.insertImage(contentResolver, bm, null, null)

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedImageUri = data!!.data
            var selectedImageBitmap: Bitmap? = null
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                    this.contentResolver,
                    selectedImageUri
                )
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("TAG", "onActivityResult: " + e.message)
            }
            binding.imgimage.setImageBitmap(selectedImageBitmap)
        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            val photo = data!!.extras!!["data"] as Bitmap?
        }
    }}





