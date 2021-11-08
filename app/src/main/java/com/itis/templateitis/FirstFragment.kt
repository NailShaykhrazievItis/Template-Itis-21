package com.itis.templateitis

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.itis.templateitis.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var binding: FragmentFirstBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view).apply {
            arguments?.getInt(ARG_COLOR)?.let {
                root.setBackgroundColor(ContextCompat.getColor(requireContext(), it))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val ARG_COLOR = "COLOR"

        fun newInstance(color: Int): FirstFragment = FirstFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_COLOR, color)
            }
        }

    }
}
