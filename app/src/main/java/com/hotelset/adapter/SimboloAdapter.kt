package com.hotelset.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hotelset.databinding.SimboloFilaBinding
import com.hotelset.model.Simbolo
import com.bumptech.glide.Glide


class SimboloAdapter: RecyclerView.Adapter<SimboloAdapter.SimboloViewHolder>() {

    private var simbolosList = emptyList<Simbolo>()

    fun setData(simbolos: List<Simbolo>){
        this.simbolosList = simbolos
        notifyDataSetChanged()
    }

    inner class SimboloViewHolder(private val itemBinding: SimboloFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun binData(simbolo: Simbolo){
            itemBinding.simboloTitle.text = simbolo.title
            Glide.with(itemBinding.root.context)
                .load(simbolo.image)
                .sizeMultiplier(0.5F)
                .into(itemBinding.simboloImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimboloViewHolder {
        val itemBinding = SimboloFilaBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return SimboloViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: SimboloViewHolder, position: Int) {
    val simbolo = simbolosList[position]
        holder.binData(simbolo)
    }

    override fun getItemCount(): Int {
        return simbolosList.size

    }


}