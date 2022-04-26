package com.hotelset.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hotelset.R
import com.hotelset.adapter.NoticiaAdapter
import com.hotelset.adapter.SimboloAdapter
import com.hotelset.databinding.FragmentNoticiasBinding
import com.hotelset.databinding.FragmentSimbologiaBinding
import com.hotelset.viewmodel.NoticiaViewModel
import com.hotelset.viewmodel.SimboloViewModel

class SimbologiaFragment : Fragment() {

    private var _binding: FragmentSimbologiaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val simboloViewModel =
            ViewModelProvider(this).get(SimboloViewModel::class.java)

        _binding = FragmentSimbologiaBinding.inflate(inflater, container, false)


        val simboloAdapter= SimboloAdapter()
        val simboloRecycler = binding.symbolsRecycler

        simboloRecycler.adapter = simboloAdapter
        simboloRecycler.layoutManager = LinearLayoutManager(requireContext())

        simboloViewModel.getAllSymbols.observe(viewLifecycleOwner) { simbolos ->
            simboloAdapter.setData(simbolos)
        }

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}