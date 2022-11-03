package com.xilef.tic_tac_toe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DB_PLAYERS = "Players"
val TBL_PLAYERS = "Players"
val COL_IMAGE = "Image"
val COL_NAME = "Name"
val COL_SCORE = "Score"
val COL_ID = "Id"

class GestionDatabase (var context: Context) : SQLiteOpenHelper(context, DB_PLAYERS, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table $TBL_PLAYERS($COL_ID INTEGER primary key, $COL_NAME varchar(25), $COL_SCORE INTEGER, $COL_IMAGE INTEGER)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {



    }

    fun insertData(player: MainActivity.Player) {
        val db = this.writableDatabase
        val id = readData().size
        val col_info = ContentValues()
        col_info.put(COL_ID, id)
        col_info.put(COL_NAME, player.name)
        col_info.put(COL_SCORE, 0)
        col_info.put(COL_IMAGE, R.color.white)

        val result = db.insert(TBL_PLAYERS, null, col_info)
        if (result != (0).toLong()) {
            Toast.makeText(context, "Insertion Successfull", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Insertion Failed", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData() : ArrayList<MainActivity.Player> {
        val list : ArrayList<MainActivity.Player> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TBL_PLAYERS"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var name : String = result.getString(result.getColumnIndexOrThrow(COL_NAME))
                var score : Int = result.getInt(result.getColumnIndexOrThrow(COL_SCORE))
                var image : Int = result.getInt(result.getColumnIndexOrThrow(COL_IMAGE))
                var player = MainActivity.Player(name, score, image)
                list.add(player)

            } while (result.moveToNext())
        }
        return list
    }

    fun readRow(id: Int) :MainActivity.Player {

       // val id = this.readRow(id).
        val name = this.readRow(id).name
        val score = this.readRow(id).score
        val image = this.readRow(id).symbol

        return MainActivity.Player(name,score,image)



    }

    fun deleteTBL() {

        val db = this.writableDatabase
        db?.execSQL("DROP TABLE $TBL_PLAYERS")


    }

    fun deleteData() {

//        val db = this.writableDatabase
//        db?.execSQL("DROP TABLE $TBL_PLAYERS")

        val db = this.writableDatabase
        val result = db.delete(TBL_PLAYERS, null, null)
        if (result < (0).toLong()) {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "All rows have been deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun update(score:Int,name:String, id: Int) {
        val db = this.writableDatabase

        val players = readData()
        var newScore = players[id].score +1
        val cv = ContentValues().apply {
            put("Score", newScore)
        }
        db.update(TBL_PLAYERS, cv , "Id=$id", null)
        //db.execSQL("UPDATE $TBL_PLAYERS SET Score = $score WHERE Id = $id")
        db.close()
        val players2 = readData()
        println(players2)
        //db.update(TBL_PLAYERS, cv, "Id=$id",null)

    }


}
