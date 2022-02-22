package com.hotelset.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_hotel_to_addHotelFragment)
        }

        val hotelAdapter=HotelAdapter()
        val hotelRecycler = binding.hotelsRecycler

        hotelRecycler.adapter = hotelAdapter
        hotelRecycler.layoutManager = LinearLayoutManager(requireContext())

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