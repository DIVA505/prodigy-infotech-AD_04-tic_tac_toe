package com.example.tic_tac_toe

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var activePlayer = 1
    private var gameIsActive = true
    private var count = 0
    private var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private val winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    fun dropIn(view : View) {
        val counter = view as ImageView
        val txt = findViewById<TextView>(R.id.winner1)
        val layout = findViewById<LinearLayout>(R.id.winner)
        val tappedCounter = counter.tag.toString().toInt()
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.circle1)
                activePlayer = 0
                count++
                gameState[tappedCounter] = 1
            } else {
                counter.setImageResource(R.drawable.circle2)
                activePlayer = 1
                count++
                gameState[tappedCounter] = 0
            }
            counter.translationY = -1000f
            counter.animate().translationYBy(1000f).rotationY(1800f).duration = 1000
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    if (gameState[winningPosition[0]] == 0) txt.text =
                        getString(R.string.red_player_wins)
                    else if (gameState[winningPosition[0]] == 1) txt.text =
                        getString(R.string.green_player_wins)
                    layout.visibility = View.VISIBLE
                    gameIsActive = false
                }
            }
        }
        if (gameIsActive && count == 9) {
            txt.text = getString(R.string.draw)
            layout.visibility = View.VISIBLE
            gameIsActive = false
        }
    }
    @Suppress("UNUSED_PARAMETER")
    fun playAgain(view: View?) {
        activePlayer = 1
        gameIsActive = true
        count = 0
        val linearLayout = findViewById<LinearLayout>(R.id.winner)
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        linearLayout.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) {
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0)
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}

