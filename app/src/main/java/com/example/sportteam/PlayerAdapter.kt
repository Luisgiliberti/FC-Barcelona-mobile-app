package com.example.sportteam

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(
    private val players: List<Player>,
    private val onClick: (Player) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val favoritePlayers = mutableSetOf<Int>().apply {
        addAll(sharedPreferences.getStringSet("favorites_set", emptySet())!!.map { it.toInt() })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val context = parent.context
        val linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }

            background = GradientDrawable().apply {
                setColor(Color.WHITE)
                setStroke(5, Color.BLACK)
                cornerRadius = 16f
            }
            setPadding(16, 16, 16, 16)
        }

        val imageView = ImageView(context).apply {
            layoutParams = LinearLayout.LayoutParams(470, 470).apply {
                gravity = android.view.Gravity.CENTER
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        val nameView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        val starButton = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 8
                gravity = android.view.Gravity.CENTER
            }
            text = "\u2606"
            textSize = 30f
            setTextColor(Color.BLACK)
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        linearLayout.addView(imageView)
        linearLayout.addView(nameView)
        linearLayout.addView(starButton)

        return PlayerViewHolder(linearLayout, imageView, nameView, starButton)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        holder.imageView.setImageResource(player.imageRes)
        holder.nameView.text = "${player.name}\nNumber: ${player.number}\nPosition: ${player.position}\nAge: ${player.age}"
        holder.nameView.setTextColor(Color.BLACK)

        if (favoritePlayers.contains(player.id)) {
            holder.starButton.text = "\u2605"
            holder.starButton.setTextColor(Color.YELLOW)
        } else {
            holder.starButton.text = "\u2606"
            holder.starButton.setTextColor(Color.BLACK)
        }

        holder.starButton.setOnClickListener {
            val editor = sharedPreferences.edit()

            if (favoritePlayers.contains(player.id)) {
                favoritePlayers.remove(player.id)
                holder.starButton.text = "\u2606"
                holder.starButton.setTextColor(Color.BLACK)
            } else {
                favoritePlayers.add(player.id)
                holder.starButton.text = "\u2605"
                holder.starButton.setTextColor(Color.YELLOW)
            }

            editor.putStringSet("favorites_set", favoritePlayers.map { it.toString() }.toSet())
            editor.apply()
        }

        holder.itemView.setOnClickListener { onClick(player) }
    }


    override fun getItemCount() = players.size

    class PlayerViewHolder(itemView: View, val imageView: ImageView, val nameView: TextView, val starButton: TextView) :
        RecyclerView.ViewHolder(itemView)
}
