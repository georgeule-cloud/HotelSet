package com.hotelset.ui.hotel

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hotelset.R
import com.hotelset.databinding.FragmentUpdateHotelBinding
import com.hotelset.model.Hotel
import com.hotelset.viewmodel.HotelViewModel

class UpdateHotelFragment : Fragment() {

    private lateinit var hotelViewModel: HotelViewModel
    private var _binding: FragmentUpdateHotelBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateHotelFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         hotelViewModel =
            ViewModelProvider(this).get(HotelViewModel::class.java)

        _binding = FragmentUpdateHotelBinding.inflate(inflater, container, false)


        binding.etAddHotelName.setText(args.hotel.name)
        binding.etAddHotelAddress.setText(args.hotel.address)
        binding.etAddHotelDescription.setText(args.hotel.description)
        binding.etAddHotelPhonenumber.setText(args.hotel.phonenumber)
        binding.etAddHotelWebsite.setText(args.hotel.website)
        binding.etAddHotelEmail.setText(args.hotel.email)
        binding.tvLatitud.text = args.hotel.latitude.toString()
        binding.tvLongitud.text = args.hotel.longitude.toString()
        binding.tvAltura.text = args.hotel.height.toString()

        binding.btUpdateHotel.setOnClickListener{
            updateHotel()
        }
        binding.btWhatsapp.setOnClickListener { enviarWhatsapp() }
        binding.btLocation.setOnClickListener { verMapa() }
        binding.btPhone.setOnClickListener { hacerLlamda() }




        setHasOptionsMenu(true) //Activa el menu del fragmento adicional

        return binding.root
    }

    private fun hacerLlamda() {

        val telefono = binding.etAddHotelPhonenumber.text.toString()
        if (telefono.isNotEmpty()) {

            val intent = Intent(Intent.ACTION_CALL)

            intent.data = Uri.parse("tel:$telefono")


            if (requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requireActivity().requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 105)
            } else {
                requireActivity().startActivity(intent)
            }


        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_datos), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun verMapa() {
        val latitud = binding.tvLatitud.text.toString().toDouble()
        val longitud = binding.tvLongitud.text.toString().toDouble()
        if (latitud.isFinite() && longitud.isFinite()) {

            val location = Uri.parse("geo:$latitud,$longitud?z=18")

            val intent = Intent(Intent.ACTION_VIEW, location)

            startActivity(intent)

        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_datos), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun enviarWhatsapp() {
        val telefono = binding.etAddHotelPhonenumber.text.toString()
        if (telefono.isNotEmpty()) {

            val intent = Intent(Intent.ACTION_VIEW)//Se llama algo desde el app
            val uri = "whatsapp://send?phone=506$telefono&text=" +
                    getString(R.string.msg_saludos)

            intent.setPackage("com.whatsapp")//Se establece el app a utilizar
            intent.data = Uri.parse(uri)
            startActivity(intent)

        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_datos), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.btn_delete){
            deleteHotel()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateHotel() {
        val name = binding.etAddHotelName.text.toString()
        if (name.isNotEmpty()){
            val email = binding.etAddHotelEmail.text.toString()
            val phone = binding.etAddHotelPhonenumber.text.toString()
            val address = binding.etAddHotelAddress.text.toString()
            val description = binding.etAddHotelDescription.text.toString()
            val website = binding.etAddHotelWebsite.text.toString()
            val hotel = Hotel("",name,address,description,phone,website,email,
                args.hotel.latitude,args.hotel.longitude,args.hotel.height,2,0.0,"")
            hotelViewModel.updateHotel(hotel)
            Toast.makeText(requireContext(),getString(R.string.msg_update_hotel), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateHotelFragment_to_nav_hotel)
        }
    }

    private fun deleteHotel(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.bt_delete)
        builder.setMessage(getString(R.string.msg_borrar)+ " ${args.hotel.name}?")
        builder.setNegativeButton(getString(R.string.no)) {_,_->}
        builder.setPositiveButton(getString(R.string.si)) {_,_->
            hotelViewModel.deleteHotel(args.hotel)
            findNavController().navigate(R.id.action_updateHotelFragment_to_nav_hotel)
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}