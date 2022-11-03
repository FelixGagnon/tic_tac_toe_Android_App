package com.xilef.tic_tac_toe

import android.content.Intent
import com.xilef.tic_tac_toe.MainActivity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.xilef.tic_tac_toe.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity()  {



    private var firstTurn = Turn.player1
    private var currentTurn = Turn.player1
    lateinit var player1name : String
    lateinit var player2name : String
    private var p1Image : String? = "blank"
    private var p2Image : String? = "blank"
    private var player1Image = R.color.white
    private var player2Image = R.color.white
    private var player1Score = 0
    private var player2Score = 0
    private var player1Id = 0
    private var player2Id = 0


    private var imageList = mutableMapOf<String,Int>()
    private var boardList = mutableMapOf<ImageButton,String>()

    lateinit var binding : ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
        disableAllBtn()
        val bundle : Bundle? = intent.extras
        val idP1 = bundle!!.getInt("idP1")
        val imageP1 = bundle?.getString("imageP1")
        val nameP1 = bundle?.getString("nameP1")
        val idP2 = bundle!!.getInt("idP2")
        val imageP2 = bundle?.getString("imageP2")
        val nameP2 = bundle?.getString("nameP2")
        val db = GestionDatabase(this)
        var players = db.readData()

        player1name = players[idP1].name
        player2name = players[idP2].name
        player1Score = players[idP2].score
        player2Score = players[idP2].score
        p1Image = imageP1
        p2Image = imageP2
        player1Id = idP1
        player2Id = idP2

        initImages()


    }
    enum class Turn
    {
        player1,
        player2
    }

    private fun initImages(){

        when (p1Image) {
            "cross" -> {
                imageList["player1"] = R.drawable.cross
            }
            "circle" -> {
                imageList["player1"] = R.drawable.circle
            }
            "ghost" ->{
                imageList["player1"] = R.drawable.ghost
            }
            "gaming" -> {
                imageList["player1"] = R.drawable.gaming
            }
            "mushroom" -> {
                imageList["player1"] = R.drawable.mushroom
            }
        }
        when (p2Image) {
            "cross" -> {
                imageList["player2"] = R.drawable.cross
            }
            "circle" -> {
                imageList["player2"] = R.drawable.circle
            }
            "ghost" ->{
                imageList["player2"] = R.drawable.ghost
            }
            "gaming" -> {
                imageList["player2"] = R.drawable.gaming
            }
            "mushroom" -> {
                imageList["player2"] = R.drawable.mushroom
            }
        }


    }

    private fun initBoard() {
        boardList[binding.a1] = ""
        boardList[binding.a2] = ""
        boardList[binding.a3] = ""
        boardList[binding.b1] = ""
        boardList[binding.b2] = ""
        boardList[binding.b3] = ""
        boardList[binding.c1] = ""
        boardList[binding.c2] = ""
        boardList[binding.c3] = ""
    }

    private fun fullBoard(): Boolean
    {
        for(button in boardList)
        {
            if(button.value=="")
                return false
        }
        return true
    }
    private fun resetGame() {
        firstTurn = Turn.player1
        currentTurn = Turn.player1
        player1name = "player1"
        player2name = "player2"

        player1Score = 0
        player2Score = 0


        btnSolo.text = "player 1"

        for(button in boardList)
        {
            button.setValue("")
            button.key.setImageResource(R.color.white)
            button.key.isEnabled = false
            button.key.isClickable = false
        }


    }

    private fun disableAllBtn() {
        for(button in boardList)
        {
            button.key.isEnabled = false
            button.key.isClickable = false
        }
    }

    private fun resetBoard()
    {
        for(button in boardList)
        {
            button.setValue("")
            button.key.setImageResource(R.color.white)
            button.key.isEnabled = true
            button.key.isClickable = true
        }
    }

    fun btnSoloClick(view: View) {

        if (btnSolo.text == "QUIT") {
            val intent = Intent(this@GameActivity,MainActivity::class.java)
            startActivity(intent)
        }
        else if (btnSolo.text == "MAIN MENU") {
            val intent = Intent(this@GameActivity,MainActivity::class.java)
            startActivity(intent)
        }

        else if (btnSolo.text == "PLAY AGAIN") {
            resetBoard()
            setTurnLabel()

        }

        else if (btnSolo.text == "START") {
            resetBoard()
            btnSolo.text = "QUIT"
            setTurnLabel()
        }
    }


    private fun match(button: ImageButton, symbol : String): Boolean = boardList[button] == symbol

    private fun checkForVictory(s: String): Boolean
    {
        //Horizontal Victory
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
            return true
        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true
        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s))
            return true

        //Vertical Victory
        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s))
            return true
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s))
            return true
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s))
            return true

        //Diagonal Victory
        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s))
            return true
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s))
            return true

        return false
    }

    fun btnClick(view: View) {

        if(view !is ImageButton) {
            return
        }
        addToBoard(view)

        if(checkForVictory("player1"))
        {
            val db = GestionDatabase(this)
            disableAllBtn()
            player1Score++


            db.update(player1Score,player1name,player1Id)
            txtTurn.text ="$player1name Win!"
            currentTurn = Turn.player1

            btnSolo.text = "MAIN MENU"

        }
        else if(checkForVictory("player2"))
        {
            val db = GestionDatabase(this)
            disableAllBtn()
            player2Score++
            db.update(player2Score,player2name,player2Id)
            txtTurn.text ="$player2name Win!"
            currentTurn = Turn.player2

            btnSolo.text = "MAIN MENU"

        }

        if(fullBoard())
        {
            txtTurn.text ="Draw"
            btnSolo.text = "PLAY AGAIN"
            if(firstTurn == Turn.player1)
                firstTurn = Turn.player2
            else if(firstTurn == Turn.player2)
                firstTurn = Turn.player1

            currentTurn = firstTurn


        }
    }

    private fun addToBoard(imageButton: ImageButton) {
        imageButton.isEnabled = false

        if(currentTurn == Turn.player1)
        {
            boardList[imageButton]="player1"
            var image : Int = imageList["player1"]?.toInt() ?: 0
            imageButton.setImageResource(image)
            currentTurn = Turn.player2
        }
        else if(currentTurn == Turn.player2)
        {
            boardList[imageButton]="player2"
            var image : Int = imageList["player2"]?.toInt() ?: 0
            imageButton.setImageResource(image)
            currentTurn = Turn.player1
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        if (currentTurn == Turn.player1) {
            txtTurn.text = "Turn $player1name"
        }
        else {
            txtTurn.text = "Turn $player2name"
        }

    }

}
