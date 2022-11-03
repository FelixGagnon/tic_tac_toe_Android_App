package com.xilef.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.xilef.tic_tac_toe.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    data class Player(val name: String, var score: Int, var symbol : Int)




    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = GestionDatabase(this)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnReload = findViewById<Button>(R.id.btnReload)


        fun getPlayersData() {
            var players = db.readData()
            var adapter = MyAdapter(players)
            rvPlayer.adapter = adapter
            rvPlayer.layoutManager = LinearLayoutManager(this)
            adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {

                    val intent = Intent(this@MainActivity,ImagePickerActivity::class.java)
                    intent.putExtra("nameP1", players[position].name)
                    intent.putExtra("id", position)
                    intent.putExtra("scoreP1", players[position].score)
                    startActivity(intent)

                }


            })
        }

        getPlayersData()


        btnAdd.setOnClickListener {
            if(etPlayerName.text.isNotEmpty()) {
                val newPlayer = Player(etPlayerName.text.toString(), 0, R.color.white)
                db.insertData(newPlayer)
                getPlayersData()
            }
        }

        btnReload.setOnClickListener {
            getPlayersData()
        }


        btnDelete.setOnClickListener {
            db.deleteData()
            getPlayersData()
        }
    }

}


