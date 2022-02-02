package com.example.detroit_outlet_base.produto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.detroit_outlet_base.databinding.FragmentProdutoBinding

class ProdutoFragment : Fragment() {

    private var _binding: FragmentProdutoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProdutoBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textProduto
//        textView.text = "joao"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}