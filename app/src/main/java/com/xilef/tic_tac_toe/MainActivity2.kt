package com.xilef.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle : Bundle? = intent.extras
        val idP1 = bundle!!.getInt("idP1")
        val imageP1 = bundle.getString("imageP1")
        val nameP1 = bundle.getString("nameP1")
        val db = GestionDatabase(this)
        val header = findViewById<TextView>(R.id.tv_name)

        header.text = "Choisir l'opposant de $nameP1"

        fun getPlayersData() {
            var players = db.readData()
            var adapter = MyAdapter(players)
            rvPlayer.adapter = adapter
            rvPlayer.layoutManager = LinearLayoutManager(this)
            adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val intent = Intent(this@MainActivity2,ImagePickerActivity2::class.java)

                    intent.putExtra("idP2", position)
                    intent.putExtra("nameP2",players[position].name)
                    intent.putExtra("idP1", idP1)
                    intent.putExtra("imageP1", imageP1)
                    intent.putExtra("nameP1", nameP1)
                    startActivity(intent)

                }


            })
        }

        getPlayersData()
    }
}