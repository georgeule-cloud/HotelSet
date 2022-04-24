package com.hotelset.ui.hotel

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.hotelset.R
import com.hotelset.databinding.FragmentAddHotelBinding
import com.hotelset.model.Hotel
import com.hotelset.viewmodel.HotelViewModel
import com.lugares.utiles.Audios
import com.lugares.utiles.Images

class AddHotelFragment : Fragment() {
    private lateinit var hotelViewModel: HotelViewModel

    private var _binding: FragmentAddHotelBinding? = null
    private val binding get() = _binding!!

    private lateinit var audios: Audios

    private lateinit var images: Images
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         hotelViewModel =
            ViewModelProvider(this).get(HotelViewModel::class.java)

        _binding = FragmentAddHotelBinding.inflate(inflater, container, false)
        binding.btAddHotel.setOnClickListener{
            //addHotel()
            binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.text = getString(R.string.msg_subiendo_audio)
            binding.msgMensaje.visibility = TextView.VISIBLE
            subeAudioNube()

        }

        audios = Audios(

            requireActivity(),
            requireContext(),
            binding.btAccion,
            binding.btPlay,
            binding.btDelete,
            getString(R.string.msg_graba_audio),
            getString(R.string.msg_detener_audio)
        )

        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK) {
                images.actualizaFoto()
            }
        }

        images = Images(
            requireContext(),
            binding.btPhoto,
            binding.btRotai,
            binding.btRotad,
            binding.imagen,
            tomarFotoActivity
        )

        ubicaGPS()
        return binding.root
    }

    private fun subeAudioNube() {
        val audioFile = audios.audioFile

        if (audioFile.exists() && audioFile.isFile && audioFile.canRead()){
            val ruta = Uri.fromFile(audioFile)
            val rutaNube="lugaresApp/${Firebase.auth.currentUser?.email}/audios/${audioFile.name}"

            val referencia: StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaAudio = it.toString()
                            subeImagenNube(rutaAudio)
                        }
                }
                .addOnFailureListener{
                    subeImagenNube("")
                }
        }else{
            subeImagenNube("")
        }
    }

    private fun subeImagenNube(rutaAudio: String) {
        val imagenFile = images.imagenFile
        if (imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()){
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube="lugaresApp/${Firebase.auth.currentUser?.email}/imagenes/${imagenFile.name}"

            val referencia: StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaImagen = it.toString()
                            addHotel(rutaAudio,"")
                        }
                }
                .addOnFailureListener{
                    addHotel(rutaAudio,"")
                }
        }else{
            addHotel(rutaAudio,"")
        }
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

    private fun addHotel(rutaAudio: String,rutaImagen: String) {
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
                altura,2,0.0,rutaAudio,rutaImagen)
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