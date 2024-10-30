package com.example.sportteam

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
import androidx.appcompat.app.AppCompatActivity

class TeamAchievementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#004c99"))
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
            ).apply {
                setMargins(0, 0, 0, 24)
            }
        }

        val achievementsTextView = TextView(this).apply {
            text = "OUR ACHIEVEMENTS"
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

        val achievementsView = TextView(this).apply {
            text = """
                Domestic Achievements:
                
                    La Liga Titles: 26
                    Copa del Rey Titles: 31
                    Supercopa de España: 14
                
                International Achievements:
                
                    UEFA Champions League Titles: 5
                    UEFA Super Cup Titles: 3
                    FIFA Club World Cup Titles: 3
                
                Key Milestones:
                
                    1899: FC Barcelona was founded by a group of Swiss, Catalan, German, and English footballers led by Joan Gamper.
                    1929: The club won its first La Liga title.
                    2009: The team achieved its first treble (La Liga, Copa del Rey, and UEFA Champions League in the same season).
                    2015: The club won the treble for the second time.
                    
                Recent Highlights:
                
                    2020-21: Victory in the Copa del Rey.
                    2022-23: Clinched the La Liga title.
            """.trimIndent()
            textSize = 18f
            setTextColor(Color.BLACK)
            setPadding(16, 16, 16, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val achievementsBackground = GradientDrawable().apply {
                setColor(Color.WHITE)
                setStroke(2, Color.BLACK)
                cornerRadius = 16f
            }
            background = achievementsBackground
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
                startActivity(Intent(this@TeamAchievementsActivity, PlayerListActivity::class.java))
            }
        }

        val navAchievementsButton = Button(this).apply {
            text = "Achievements"
        }

        val navFavoritesButton = Button(this).apply {
            text = "Favorites"
            setOnClickListener {
                startActivity(Intent(this@TeamAchievementsActivity, FavoritesActivity::class.java))
            }
        }

        headerLayout.addView(logoImageView)
        mainLayout.addView(headerLayout)
        mainLayout.addView(achievementsTextView)
        mainLayout.addView(achievementsView)
        scrollView.addView(mainLayout)
        parentLayout.addView(scrollView)
        bottomNavLayout.addView(navPlayerListButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navAchievementsButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        bottomNavLayout.addView(navFavoritesButton, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        parentLayout.addView(bottomNavLayout)
        setContentView(parentLayout)
    }
}
