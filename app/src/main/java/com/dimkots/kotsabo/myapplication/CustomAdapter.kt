package com.dimkots.kotsabo.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(private val game: Game) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.game.rounds
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewPoints1.text = this.game.firstPlayer.arrayPoints[position].toString()
        holder.textViewPoints2.text = this.game.secondPlayer.arrayPoints[position].toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPoints1 = itemView.findViewById(R.id.textViewPoints1) as TextView
        val textViewPoints2 = itemView.findViewById(R.id.textViewPoints2) as TextView
    }

}