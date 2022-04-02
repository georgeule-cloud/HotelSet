package com.hotelset.ui.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hotelset.R
import com.hotelset.databinding.FragmentAddHotelBinding
import com.hotelset.model.Hotel
import com.hotelset.viewmodel.HotelViewModel

class AddHotelFragment : Fragment() {
    private lateinit var hotelViewModel: HotelViewModel

    private var _binding: FragmentAddHotelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         hotelViewModel =
            ViewModelProvider(this).get(HotelViewModel::class.java)

        _binding = FragmentAddHotelBinding.inflate(inflater, container, false)
        binding.btAddHotel.setOnClickListener{
            addHotel()
        }
        return binding.root
    }

    private fun addHotel() {
        val name = binding.etAddHotelName.text.toString()
        if (name.isNotEmpty()){
            val email = binding.etAddHotelEmail.text.toString()
            val phone = binding.etAddHotelPhonenumber.text.toString()
            val address = binding.etAddHotelAddress.text.toString()
            val description = binding.etAddHotelDescription.text.toString()
            val website = binding.etAddHotelWebsite.text.toString()
            val hotel = Hotel("",name,address,description,phone,website,email,0.0,0.0,0.0,2,0.0,"")
            hotelViewModel.addHotel(hotel)
            Toast.makeText(requireContext(),getString(R.string.msg_add_lugar), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addHotelFragment_to_nav_hotel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}