package com.hotelset.ui.hotel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.wear.tiles.material.Colors
import com.hotelset.R
import com.hotelset.adapter.HotelAdapter
import com.hotelset.databinding.FragmentHotelBinding
import com.hotelset.viewmodel.HotelViewModel

class HotelFragment : Fragment() {

    private lateinit var hotelViewModel: HotelViewModel

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val hotelViewModel =
            ViewModelProvider(this).get(HotelViewModel::class.java)

        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        val hotelAdapter=HotelAdapter()
        val hotelRecycler = binding.hotelsRecycler

        hotelRecycler.adapter = hotelAdapter
        hotelRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_hotel_to_nav_addHotel)
        }

        binding.allButton.setOnClickListener{
            hotelViewModel.getAllHotels.observe(viewLifecycleOwner) { hoteles ->
                hotelAdapter.setData(hoteles)
            }
            Toast.makeText(this.context,"Mostrando todos los hoteles", Toast.LENGTH_LONG).show()
            binding.playaButton.setBackgroundColor(0)
            binding.montanaButton.setBackgroundColor(0)
            binding.allButton.setBackgroundColor(Color.parseColor("#7711F4"))
            binding.cityButton.setBackgroundColor(0)
        }
        binding.playaButton.setOnClickListener{
            hotelViewModel.getPlayaHotels.observe(viewLifecycleOwner) { hoteles ->
                hotelAdapter.setData(hoteles)
            }
            Toast.makeText(this.context,"Mostrando hoteles de Playa", Toast.LENGTH_LONG).show()
            binding.playaButton.setBackgroundColor(Color.parseColor("#7711F4"))
            binding.montanaButton.setBackgroundColor(0)
            binding.allButton.setBackgroundColor(0)
            binding.cityButton.setBackgroundColor(0)
        }
        binding.montanaButton.setOnClickListener{
            hotelViewModel.getMontanaHotels.observe(viewLifecycleOwner) { hoteles ->
                hotelAdapter.setData(hoteles)
            }
            Toast.makeText(this.context,"Mostrando hoteles de Montaña", Toast.LENGTH_LONG).show()
            binding.playaButton.setBackgroundColor(0)
            binding.montanaButton.setBackgroundColor(Color.parseColor("#7711F4"))
            binding.allButton.setBackgroundColor(0)
            binding.cityButton.setBackgroundColor(0)
        }
        binding.cityButton.setOnClickListener{
            hotelViewModel.getCiudadHotels.observe(viewLifecycleOwner) { hoteles ->
                hotelAdapter.setData(hoteles)
            }
            Toast.makeText(this.context,"Mostrando hoteles de Ciudad", Toast.LENGTH_LONG).show()
            binding.playaButton.setBackgroundColor(0)
            binding.montanaButton.setBackgroundColor(0)
            binding.allButton.setBackgroundColor(0)
            binding.cityButton.setBackgroundColor(Color.parseColor("#7711F4"))
        }





        hotelViewModel.getAllHotels.observe(viewLifecycleOwner) { hoteles ->
            hotelAdapter.setData(hoteles)
        }

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}