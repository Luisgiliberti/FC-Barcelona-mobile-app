package com.example.sportteam

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayerDetailActivity : AppCompatActivity() {

    private val playerList = listOf(
        Player(1, "Marc-Andre ter Stegen", 1, "Goalkeeper", 32, R.drawable.player1, "Germany", "Borussia Mönchengladbach", false, PlayerStats(0, 0, 8, 15,"La Liga Best Goalkeeper: 2020-21", "German Footballer of the Year: 2022")),
        Player(2, "Pau Cubarsi", 2, "Defender", 17, R.drawable.player2, "Spain", "La Masia", true, PlayerStats(0, 0, 0, 0, "The player has not", "won individual awards")),
        Player(3, "Alejandro Balde", 3, "Defender", 20, R.drawable.player3, "Spain", "La Masia", true, PlayerStats(0, 4, 5, 0,"Golden Boy Nominee: 2022","UEFA European Under-19 Championship Team of the Tournament: 2022")),
        Player(4, "Ronald Araujo", 4, "Defender", 25, R.drawable.player4, "Uruguay", "Boston River", false, PlayerStats(1, 0, 0, 0,"La Liga Best Defender: 2021-22","Barcelona Player of the Season: 2021-22")),
        Player(5, "Jules Kounde", 23, "Defender", 25, R.drawable.player23, "France", "Sevilla FC", false, PlayerStats(0, 2, 0, 0, "UNFP Ligue 1 Player of the Year: 2021","UEFA Champions League Team of the Season: 2021-22")),
        Player(6, "Pablo Gavi", 6, "Midfielder", 20, R.drawable.player6, "Spain", "La Masia", true, PlayerStats(3, 2, 0, 0,"Golden Boy Award: 2022","La Liga Best Young Player: 2021-22")),
        Player(7, "Pedri Gonzalez", 8, "Midfielder", 21, R.drawable.player8, "Spain", "Las Palmas", false, PlayerStats(2, 4, 0, 0,"Golden Boy Award: 2021","UEFA Team of the Year: 2021")),
        Player(8, "Frenkie de Jong", 21, "Midfielder", 27, R.drawable.player21, "Netherlands", "Ajax", false, PlayerStats(1, 3, 0, 0,"UEFA Team of the Year: 2019","La Liga Best Midfielder: 2021-22")),
        Player(9, "Robert Lewandowski", 9, "Forward", 36, R.drawable.player9, "Poland", "Bayern Munich", false, PlayerStats(15, 3, 0, 0,"The Best FIFA Men’s Player: 2020 & 2021","La Liga Best Player: 2022-23")),
        Player(10, "Raphina Dias", 11, "Forward", 27, R.drawable.player11, "Brazil", "Leeds United", false, PlayerStats(5, 5, 0, 0,"Best Young Player in Copa Libertadores: 2018","South American Footballer of the Year: 2021")),
        Player(11, "Lamine Yamal", 19, "Forward", 17, R.drawable.player19, "Spain", "La Masia", true, PlayerStats(3, 2, 0, 0,"The player has not", "won individual awards"))
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerId = intent.getIntExtra("PLAYER_ID", -1)
        val player = playerList.find { it.id == playerId }

        val parentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#004c99"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        val scrollView = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        }

        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val headerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER
        }

        val logoImageView = ImageView(this).apply {
            setImageResource(R.drawable.logo)
            layoutParams = LinearLayout.LayoutParams(
                250,
                250
            ).apply { setMargins(0, 0, 0, 24) }
        }



        player?.let {
            val detailsContainer = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 24, 24, 24)
                background = GradientDrawable().apply {
                    setColor(Color.WHITE)
                    setStroke(5, Color.BLACK)
                    cornerRadius = 16f
                }
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(16, 16, 16, 16) }
            }

            val imageView = ImageView(this).apply {
                setImageResource(it.imageRes)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    940
                ).apply { setMargins(0, 0, 0, 24) }
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            val detailsView = TextView(this).apply {
                text = """
                    Name: ${it.name}
                    Number: ${it.number}
                    Position: ${it.position}
                    Age: ${it.age}
                    Country: ${it.country}
                    Previous Team: ${if (it.isLaMasiaGraduate) "La Masia" else it.previousTeam}
                    
                    2023/24 Season Stats:
                    Goals: ${it.stats.goals}
                    Assists: ${it.stats.assists}
                    Clean Sheets: ${it.stats.cleanSheets}
                    Goals Conceded: ${it.stats.goalsConceded}
                    
                    Individual Awards:
                    #1: ${it.stats.award1}
                    #2: ${it.stats.award2}
                """.trimIndent()
                textSize = 16f
                setTextColor(Color.BLACK)
            }

            val starButton = TextView(this).apply {
                text = "\u2606"
                textSize = 30f
                setTextColor(Color.BLACK)
            }

            val preferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)
            val favoritePlayers = preferences.getStringSet("favorites_set", mutableSetOf()) ?: mutableSetOf()

            if (favoritePlayers.contains(player.id.toString())) {
                starButton.text = "\u2605"
                starButton.setTextColor(Color.YELLOW)
            }

            starButton.setOnClickListener {
                val editor = preferences.edit()
                if (favoritePlayers.contains(player.id.toString())) {
                    favoritePlayers.remove(player.id.toString())
                    starButton.text = "\u2606"
                    starButton.setTextColor(Color.BLACK)
                    Toast.makeText(this, "${player.name} removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    favoritePlayers.add(player.id.toString())
                    starButton.text = "\u2605"
                    starButton.setTextColor(Color.YELLOW)
                    Toast.makeText(this, "${player.name} added to favorites", Toast.LENGTH_SHORT).show()
                }
                editor.putStringSet("favorites_set", favoritePlayers)
                editor.apply()
            }

            detailsContainer.addView(imageView)
            detailsContainer.addView(detailsView)
            detailsContainer.addView(starButton)
            mainLayout.addView(detailsContainer)
        }

        val bottomNavLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 24, 0, 0) }
            setPadding(16, 16, 16, 16)
            setBackgroundColor(Color.parseColor("#a60042"))
        }

        val navPlayerListButton = Button(this).apply {
            text = "Players"
            setOnClickListener { startActivity(Intent(this@PlayerDetailActivity, PlayerListActivity::class.java)) }
        }

        val navAchievementsButton = Button(this).apply {
            text = "Achievements"
            setOnClickListener { startActivity(Intent(this@PlayerDetailActivity, TeamAchievementsActivity::class.java)) }
        }

        val navFavoritesButton = Button(this).apply {
            text = "Favorites"
            setOnClickListener { startActivity(Intent(this@PlayerDetailActivity, FavoritesActivity::class.java)) }
        }

        headerLayout.addView(logoImageView)
        mainLayout.addView(headerLayout)
        scrollView.addView(mainLayout)
        parentLayout.addView(scrollView)
        bottomNavLayout.addView(navPlayerListButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navAchievementsButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navFavoritesButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        parentLayout.addView(bottomNavLayout)
        setContentView(parentLayout)
    }
}
