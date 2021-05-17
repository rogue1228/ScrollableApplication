package com.junhwa.scrollableapplication.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.junhwa.scrollableapplication.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {

    private lateinit var likeViewModel: LikeViewModel
    private var _binding: FragmentLikeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        likeViewModel =
                ViewModelProvider(this).get(LikeViewModel::class.java)

        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}