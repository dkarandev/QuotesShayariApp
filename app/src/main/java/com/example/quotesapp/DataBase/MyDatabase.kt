package com.example.quotesapp.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import com.example.quotesapp.Model.CategoryModel
import com.example.quotesapp.Model.QuotesModel
import com.example.quotesapp.Model.quotesshayri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class MyDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase() {
        if (mNeedUpdate) {
            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false
        }
    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()
    }

    fun CategoryDisplayData(): ArrayList<CategoryModel> {
        var list = ArrayList<CategoryModel>()
        list.clear()
        var db = readableDatabase
        var sql = "select * from categoryTb"
        var cursor: Cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {

            do {

                var id = cursor.getInt(0)
                var name = cursor.getString(1)

                Log.e("TAG", "displayRecord: " + name)
                list.add(CategoryModel(id, name))

            } while (cursor.moveToNext())

        }
        return list
    }

    fun QuotesDisplayData(id: Int): ArrayList<QuotesModel> {
        var quotelist = ArrayList<QuotesModel>()
        quotelist.clear()
        var db = readableDatabase
        var sql = "select * from quotestb where categoryId = $id"
        var cursor: Cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {

            do {

                var id = cursor.getInt(0)
                var quote = cursor.getString(1)
                var cid = cursor.getInt(2)
                var status = cursor.getInt(3)

                Log.e("TAG", "displayRecord: " + id + " " + quote + " " + cid + " " + status)
                quotelist.add(QuotesModel(id, quote, cid, status))

            } while (cursor.moveToNext())

        }
        return quotelist
    }

    fun FavoriteDisplayRecord(): ArrayList<quotesshayri> {
        var DisplayList = ArrayList<quotesshayri>()

        val dbF = readableDatabase
        val SqlF = "Select * from quotesTb where status = 1"
        val c = dbF.rawQuery(SqlF, null)
        if (c.moveToFirst()) {

            do {
                var Shayari_id = c.getInt(0)
                var Shayari = c.getString(1)
                var fav = c.getInt(2)

                Log.e("TAG", "FavoriteDisplayRecord: $Shayari_id $Shayari")
                var shayarimodal = quotesshayri(Shayari_id, Shayari, fav)

                DisplayList.add(shayarimodal)
            } while (c.moveToNext())
        }
        return DisplayList
    }

    fun UpdateData(id: Int, status: Int) {

        val db = writableDatabase
        val k = ContentValues()

        k.put("status", status)

        Log.e(TAG, "UpdateData: " + status)

        val update = "update quotestb Set status=$status where id=$id"
        db.execSQL(update)
    }

    fun UpdatequoteRecord(id: Int,quotes:String){
        var db=writableDatabase

        var c=ContentValues()

        c.put("quotes",quotes)

        val p = "update quotestb Set name=$quotes where id=$id"
        Log.e(TAG, "UpdatequoteRecord: "+id+" "+quotes )

        db.execSQL(p)

    }

    companion object {
        private const val TAG = "MyDatabase"
        private const val DB_NAME = "ExtrnalDatabase.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1
    }

    init {
        if (Build.VERSION.SDK_INT >= 17) DB_PATH =
            context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.readableDatabase
    }
}
