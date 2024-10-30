package com.example.sportteam

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xFF004C99.toInt())
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        val topSpaceLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                0.5f
            )
        }

        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(16, 16, 16, 16)
        }

        val logoImageView = ImageView(this).apply {
            setImageResource(R.drawable.logo)
            layoutParams = LinearLayout.LayoutParams(
                400,
                400
            ).apply {
                gravity = Gravity.CENTER
                setMargins(0, 0, 0, 24)
            }
        }

        val welcomeTextView = TextView(this).apply {
            text = "Welcome to the FC Barcelona App!"
            textSize = 24f
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                setMargins(0, 0, 0, 24)
            }
        }

        val bottomSpaceLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                0.5f
            )
        }

        val bottomNavLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 24, 0, 0)
            }
            setPadding(16, 16, 16, 16)
            gravity = Gravity.BOTTOM
            setBackgroundColor(Color.parseColor("#a60042"))
        }

        val navPlayerListButton = Button(this).apply {
            text = "Players"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, PlayerListActivity::class.java))
            }
        }

        val navAchievementsButton = Button(this).apply {
            text = "Achievements"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, TeamAchievementsActivity::class.java))
            }
        }

        val navFavoritesButton = Button(this).apply {
            text = "Favorites"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, FavoritesActivity::class.java))
            }
        }

        mainLayout.addView(logoImageView)
        mainLayout.addView(welcomeTextView)
        parentLayout.addView(topSpaceLayout)
        parentLayout.addView(mainLayout)
        parentLayout.addView(bottomSpaceLayout)
        bottomNavLayout.addView(navPlayerListButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navAchievementsButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navFavoritesButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        parentLayout.addView(bottomNavLayout)
        setContentView(parentLayout)
    }
}
