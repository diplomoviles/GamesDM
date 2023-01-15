package com.amaurypm.gamesdm.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.gamesdm.databinding.GameElementBinding
import com.amaurypm.gamesdm.model.Game
import com.amaurypm.gamesdm.view.activities.MainActivity
import com.bumptech.glide.Glide

/**
 * Creado por Amaury Perea Matsumura el 14/01/23
 */
class GamesAdapter(private val context: Context, private val games: ArrayList<Game>): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    class ViewHolder(view: GameElementBinding): RecyclerView.ViewHolder(view.root) {
        val ivThumbnail = view.ivThumbnail
        val tvTitle = view.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = games[position].title

        Glide.with(context)
            .load(games[position].thumbnail)
            .into(holder.ivThumbnail)

        //Para los clicks
        holder.itemView.setOnClickListener{
            if(context is MainActivity) context.selectedGame(games[position])
        }
    }

    override fun getItemCount(): Int = games.size
}