package com.hotelset.ui.noticias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hotelset.R
import com.hotelset.databinding.FragmentNoticiasBinding
import com.hotelset.viewmodel.NoticiaViewModel
import com.hotelset.adapter.NoticiaAdapter

class NoticiasFragment : Fragment() {

    private lateinit var noticiaViewModel: NoticiaViewModel

    private var _binding: FragmentNoticiasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noticiaViewModel =
            ViewModelProvider(this).get(NoticiaViewModel::class.java)

        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)

        binding.floatingActionButtonNoticiasAdd.setOnClickListener{
            findNavController().navigate(R.id.action_nav_noticias_to_addNoticiaFragment)
        }

        val noticiaAdapter= NoticiaAdapter()
        val noticiaRecycler = binding.noticiasRecycler

        noticiaRecycler.adapter = noticiaAdapter
        noticiaRecycler.layoutManager = LinearLayoutManager(requireContext())

        noticiaViewModel.getAllNoticias.observe(viewLifecycleOwner) { noticias -> noticiaAdapter.setData(noticias)}

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}