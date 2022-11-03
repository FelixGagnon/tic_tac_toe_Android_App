package com.xilef.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_picker.*


class ImagePickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)


        val bundle : Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        val nameP1 = bundle.getString("nameP1")
        val db = GestionDatabase(this)
        val players = db.readData()
        val player = players[id]




        image_X.setOnClickListener{
            val image = "cross"
          //  db.update(image,id)

            val intent = Intent(this@ImagePickerActivity,MainActivity2::class.java)
            intent.putExtra("idP1", id)
            intent.putExtra("imageP1", image)
            intent.putExtra("nameP1", nameP1)
            startActivity(intent)

        }

        image_O.setOnClickListener{
            val image = "circle"
           // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity,MainActivity2::class.java)
            intent.putExtra("idP1", id)
            intent.putExtra("imageP1", image)
            intent.putExtra("nameP1", nameP1)
            startActivity(intent)

        }

        image_1.setOnClickListener{
            val image = "ghost"
           // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity,MainActivity2::class.java)
            intent.putExtra("idP1", id)
            intent.putExtra("imageP1", image)
            intent.putExtra("nameP1", nameP1)
            startActivity(intent)

        }

        image_2.setOnClickListener{
            val image = "gaming"
           // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity,MainActivity2::class.java)
            intent.putExtra("idP1", id)
            intent.putExtra("imageP1", image)
            intent.putExtra("nameP1", nameP1)
            startActivity(intent)

        }

        image_3.setOnClickListener{
            val image = "mushroom"
           // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity,MainActivity2::class.java)
            intent.putExtra("idP1", id)
            intent.putExtra("imageP1", image)
            intent.putExtra("nameP1", nameP1)
            startActivity(intent)

        }


    }

}