package com.hotelset.ui.noticias

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.hotelset.R
import com.hotelset.databinding.FragmentAddNoticiaBinding
import com.hotelset.media.ImagesNoticia
import com.hotelset.model.Noticia
import com.hotelset.viewmodel.NoticiaViewModel

class AddNoticiaFragment : Fragment() {
    private lateinit var noticiasViewModel: NoticiaViewModel

    private var _binding: FragmentAddNoticiaBinding? = null
    private val binding get() = _binding!!

    private lateinit var images: ImagesNoticia
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noticiasViewModel =
            ViewModelProvider(this).get(NoticiaViewModel::class.java)

        _binding = FragmentAddNoticiaBinding.inflate(inflater, container, false)
        binding.btAddNoticia.setOnClickListener{
            //addNoticia()
            subeImagenNube()
        }

        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK) {
                images.actualizaFotoN()
            }
        }

        images = ImagesNoticia(
            requireContext(),
            binding.btAddImagesN,
            binding.imageView2,
            tomarFotoActivity
        )

        return binding.root
    }

    private fun subeImagenNube() {
        val imagenFile = images.imagenFile
        if (imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()){
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube="hotelAPP/${Firebase.auth.currentUser?.email}/imagenesNoticia/${imagenFile.name}"

            val referencia: StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaImagen = it.toString()
                            addNoticia(rutaImagen)
                        }
                }
                .addOnFailureListener{
                    addNoticia("")
                }
        }else{
            addNoticia("")
        }
    }

    private fun addNoticia(rutaImagenNoticia: String) {
        val name = binding.etAddNoticiaTitle.text.toString()
        if (name.isNotEmpty()){
            val body = binding.etAddNoticiaBody.text.toString()
            val noticia = Noticia("",name,body,rutaImagenNoticia)
            noticiasViewModel.addNoticia(noticia)
            Toast.makeText(requireContext(),getString(R.string.msg_add_noticia), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addNoticiaFragment_to_nav_noticias)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}