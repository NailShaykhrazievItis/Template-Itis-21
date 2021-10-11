package com.itis.templateitis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.templateitis.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        childFragmentManager.beginTransaction().run {
            commit()
        }

        parentFragmentManager.let {

        }

        parentFragment?.let {

        }

        activity?.let {

        }
    }

    // второй способ через метод INFLATE
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater, container, false)?.let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {

        }

        arguments?.getInt("Id")?.let {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        fun newInstance2(id: Int): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putInt("Id", id)
            bundle.putString("KEY_STRING", "sdas")
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(id: Int): HomeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putInt("Id", id)
                putString("KEY_STRING", "sdas")
            }
        }
    }
}
