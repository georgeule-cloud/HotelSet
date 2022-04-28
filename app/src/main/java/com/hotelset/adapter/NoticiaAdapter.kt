package com.hotelset.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hotelset.databinding.NoticiaFilaBinding
import com.hotelset.model.Noticia

class NoticiaAdapter: RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    private var noticiasList = emptyList<Noticia>()

    fun setData(noticias: List<Noticia>){
        this.noticiasList = noticias
        notifyDataSetChanged()
    }

    inner class NoticiaViewHolder(private val itemBinding: NoticiaFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun binData(noticia: Noticia){
            itemBinding.noticiaTitle.text = noticia.name
            itemBinding.noticiaDescription.text = noticia.body
            Glide.with(itemBinding.root.context)
                .load(noticia.image)
                .sizeMultiplier(0.5F)
                .into(itemBinding.noticiaImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val itemBinding = NoticiaFilaBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return NoticiaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
    val noticia = noticiasList[position]
        holder.binData(noticia)
    }

    override fun getItemCount(): Int {
        return noticiasList.size

    }


}