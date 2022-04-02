package com.hotelset.ui.noticias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hotelset.databinding.FragmentNoticiasBinding
import com.hotelset.viewmodel.NoticiaViewModel
import com.hotelset.adapter.NoticiaAdapter

class NoticiasFragment : Fragment() {

    private var _binding: FragmentNoticiasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(NoticiaViewModel::class.java)

        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)

        val noticiaAdapter= NoticiaAdapter()
        val noticiaRecycler = binding.noticiasRecycler

        noticiaRecycler.adapter = noticiaAdapter
        noticiaRecycler.layoutManager = LinearLayoutManager(requireContext())
        
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}