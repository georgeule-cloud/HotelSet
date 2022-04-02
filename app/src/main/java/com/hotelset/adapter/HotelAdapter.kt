package com.hotelset.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hotelset.databinding.HotelCardBinding
import com.hotelset.model.Hotel

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

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val itemBinding = HotelCardBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return HotelViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
    val hotel = hotelsList[position]
        holder.binData(hotel)
    }

    override fun getItemCount(): Int {
        return hotelsList.size

    }


}