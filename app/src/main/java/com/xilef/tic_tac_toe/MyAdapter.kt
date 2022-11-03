package com.xilef.tic_tac_toe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val playerList : ArrayList<MainActivity.Player>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {

        mListener = listener

    }

    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvScore = itemView.findViewById<TextView>(R.id.tvScore)
        val ivSymbole = itemView.findViewById<ImageView>(R.id.ivSymbole)

        init {

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val playerView = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(playerView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val player : MainActivity.Player = playerList.get(position)

        holder.tvName.text = player.name
        holder.tvScore.text = player.score.toString()
        holder.ivSymbole.setImageResource(R.drawable.mushroom)

    }

    override fun getItemCount(): Int {
        return playerList.size
    }


}


