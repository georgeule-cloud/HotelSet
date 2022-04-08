package com.hotelset.ui.hotel

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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



        ubicaGPS()
        return binding.root
    }
    private var conPermisos: Boolean = true


    private fun ubicaGPS() {


        val ubicacion: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 105
            )

        }

        if (conPermisos) {
            ubicacion.lastLocation.addOnSuccessListener { location: Location? ->

                if (location != null) {

                    binding.tvLatitud.text = "${location.latitude}"
                    binding.tvLongitud.text = "${location.longitude}"
                    binding.tvAltura.text = "${location.altitude}"
                } else {
                    binding.tvLatitud.text = "0.0"
                    binding.tvLongitud.text = "0.0"
                    binding.tvAltura.text = "0.0"
                }
            }
        }
    }

    private fun addHotel() {
        val name = binding.etAddHotelName.text.toString()
        if (name.isNotEmpty()){
            val email = binding.etAddHotelEmail.text.toString()
            val phone = binding.etAddHotelPhonenumber.text.toString()
            val address = binding.etAddHotelAddress.text.toString()
            val description = binding.etAddHotelDescription.text.toString()
            val website = binding.etAddHotelWebsite.text.toString()
            val latitud = binding.tvLatitud.text.toString().toDouble()
            val longitud = binding.tvLongitud.text.toString().toDouble()
            val altura = binding.tvAltura.text.toString().toDouble()
            val hotel = Hotel("",name,address,description,phone,website,email,latitud,longitud,
                altura,2,0.0,"")
            hotelViewModel.addHotel(hotel)
            Toast.makeText(requireContext(),getString(R.string.msg_add_hotel), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addHotelFragment_to_nav_hotel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}