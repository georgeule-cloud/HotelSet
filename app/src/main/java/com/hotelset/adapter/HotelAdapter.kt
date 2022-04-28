package com.hotelset.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hotelset.databinding.HotelCardBinding
import com.hotelset.model.Hotel
import com.hotelset.ui.hotel.HotelFragmentDirections

class HotelAdapter: RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    private var hotelsList = emptyList<Hotel>()

    fun setData(hotels: List<Hotel>){
        this.hotelsList = hotels
        notifyDataSetChanged()
    }

    inner class HotelViewHolder(private val itemBinding: HotelCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun binData(hotel: Hotel){
            itemBinding.tvName.text = hotel.name
            itemBinding.tvDescription.text = hotel.description
            itemBinding.tvPhonenumber.text = hotel.phonenumber
            itemBinding.rbStars.rating = (1..5).random().toFloat()

            Glide.with(itemBinding.root.context)
                .load(hotel.rutaImagen)
                .circleCrop()
                .into(itemBinding.ivImage)

            val accion = HotelFragmentDirections.actionNavHotelToUpdateHotelFragment(hotel)
            itemBinding.viewCard.setOnClickListener{
                itemView.findNavController().navigate(accion)
            }
            itemBinding.button.setOnClickListener{
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val itemBinding = HotelCardBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return HotelViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
    val hotel = hotelsList[position]
        holder.binData(hotel)
    }

    override fun getItemCount(): Int {
        return hotelsList.size

    }

}