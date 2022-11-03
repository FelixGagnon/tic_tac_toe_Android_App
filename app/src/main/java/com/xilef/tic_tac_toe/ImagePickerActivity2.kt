package com.xilef.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import kotlinx.android.synthetic.main.activity_image_picker2.*

class ImagePickerActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker2)

        val bundle : Bundle? = intent.extras
        val idP1 = bundle!!.getInt("idP1")
        val idP2 = bundle.getInt("idP2")
        val imageP1 = bundle.getString("imageP1")
        val nameP1 = bundle.getString("nameP1")
        val nameP2 = bundle.getString("nameP2")

        val db = GestionDatabase(this)
        val players = db.readData()
        val player = players[idP2]

        when (imageP1) {
            "cross" -> {
                image2_X.isClickable = false
                image2_X.isEnabled = false
                image2_X.visibility = GONE
            }
            "circle" -> {
                image2_O.isClickable = false
                image2_O.isEnabled = false
                image2_O.visibility = GONE
            }
            "ghost" -> {
                image2_1.isClickable = false
                image2_1.isEnabled = false
                image2_1.visibility = GONE
            }
            "gaming" -> {
                image2_2.isClickable = false
                image2_2.isEnabled = false
                image2_2.visibility = GONE
            }
            "mushroom" -> {
                image2_3.isClickable = false
                image2_3.isEnabled = false
                image2_3.visibility = GONE
            }
        }

        image2_X.setOnClickListener{
            val image = "cross"
            //  db.update(image,id)

            val intent = Intent(this@ImagePickerActivity2,GameActivity::class.java)
            intent.putExtra("idP1", idP1)
            intent.putExtra("imageP1", imageP1)
            intent.putExtra("nameP1", nameP1)
            intent.putExtra("idP2", idP2)
            intent.putExtra("nameP2", nameP2)
            intent.putExtra("imageP2", image)
            startActivity(intent)

        }

        image2_O.setOnClickListener{
            val image = "circle"
            // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity2,GameActivity::class.java)
            intent.putExtra("idP1", idP1)
            intent.putExtra("imageP1", imageP1)
            intent.putExtra("nameP1", nameP1)
            intent.putExtra("idP2", idP2)
            intent.putExtra("nameP2", nameP2)
            intent.putExtra("imageP2", image)
            startActivity(intent)

        }

        image2_1.setOnClickListener{
            val image = "ghost"
            // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity2,GameActivity::class.java)
            intent.putExtra("idP1", idP1)
            intent.putExtra("imageP1", imageP1)
            intent.putExtra("nameP1", nameP1)
            intent.putExtra("idP2", idP2)
            intent.putExtra("nameP2", nameP2)
            intent.putExtra("imageP2", image)
            startActivity(intent)

        }

        image2_2.setOnClickListener{
            val image = "gaming"
            // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity2,GameActivity::class.java)
            intent.putExtra("idP1", idP1)
            intent.putExtra("imageP1", imageP1)
            intent.putExtra("nameP1", nameP1)
            intent.putExtra("idP2", idP2)
            intent.putExtra("nameP2", nameP2)
            intent.putExtra("imageP2", image)
            startActivity(intent)

        }

        image2_3.setOnClickListener{
            val image = "mushroom"
            // db.update(image,id)

            val intent = Intent(this@ImagePickerActivity2,GameActivity::class.java)
            intent.putExtra("idP1", idP1)
            intent.putExtra("imageP1", imageP1)
            intent.putExtra("nameP1", nameP1)
            intent.putExtra("idP2", idP2)
            intent.putExtra("nameP2", nameP2)
            intent.putExtra("imageP2", image)
            startActivity(intent)

        }
    }
}