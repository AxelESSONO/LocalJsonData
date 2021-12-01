package com.axelessono.localjsondata.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axelessono.localjsondata.R
import com.axelessono.localjsondata.model.TVShow
import com.bumptech.glide.Glide

class TVShowAdapter(
    private val mContext: Context,
    private var tvShows: ArrayList<TVShow>,
    private val listener: (TVShow) -> Unit /** Pour Gérer le ckick sur un élément **/
) : RecyclerView.Adapter<TVShowAdapter.TVShowHolder>() {


    inner class TVShowHolder(tvShowView : View):RecyclerView.ViewHolder(tvShowView) {

        @SuppressLint("SetTextI18n")
        fun bindView(tvShow: TVShow, listener: (TVShow) -> Unit) = with(itemView){
            val imageTvShow : ImageView = itemView.findViewById(R.id.imageTvShow)
            val tvShowName : TextView = itemView.findViewById(R.id.tvShowName)
            val networkAndCountry : TextView = itemView.findViewById(R.id.networkAndCountry)
            val startDate : TextView = itemView.findViewById(R.id.startDate)
            val textStatus : TextView = itemView.findViewById(R.id.textStatus)

            // Load ImageTVShow with Glide
            Glide.with(mContext)
                .load(tvShow.imageThumbnailPath)
                .into(imageTvShow)

            // set TextView
            tvShowName.text = tvShow.name
            networkAndCountry.text = "${tvShow.network} (${tvShow.country})"
            startDate.text = "Started on ${tvShow.startDate}"
            textStatus.text = tvShow.status

            // Handle the click on RecyclerView Item
            itemView.setOnClickListener { listener(tvShow) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowHolder {
        val rootView : View = LayoutInflater.from(parent.context).inflate(R.layout.tv_show_item, parent, false)
        return TVShowHolder(rootView)
    }

    override fun onBindViewHolder(holder: TVShowHolder, position: Int) {
        holder.bindView(tvShows[position], listener)
    }

    override fun getItemCount(): Int = tvShows.size
}