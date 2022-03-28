package com.hotelset.ui.noticias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hotelset.R
import com.hotelset.databinding.FragmentAddNoticiaBinding
import com.hotelset.model.Noticia
import com.hotelset.viewmodel.NoticiasViewModel

class AddNoticiaFragment : Fragment() {
    private lateinit var noticiasViewModel: NoticiasViewModel

    private var _binding: FragmentAddNoticiaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noticiasViewModel =
            ViewModelProvider(this).get(NoticiasViewModel::class.java)

        _binding = FragmentAddNoticiaBinding.inflate(inflater, container, false)
        binding.btAddNoticia.setOnClickListener{
            addNoticia()
        }
        return binding.root
    }

    private fun addNoticia() {
        val name = binding.etAddNoticiaTitle.text.toString()
        if (name.isNotEmpty()){
            val body = binding.etAddNoticiaBody.text.toString()
            val noticia = Noticia(0,name,body/*,image*/)
            noticiasViewModel.addNoticia(noticia)
            Toast.makeText(requireContext(),getString(R.string.msg_add_new), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addNoticiaFragment_to_nav_noticias)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}