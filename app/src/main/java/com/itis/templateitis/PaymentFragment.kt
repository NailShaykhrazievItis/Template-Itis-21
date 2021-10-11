package com.itis.templateitis

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.templateitis.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private var binding: FragmentPaymentBinding? = null

    // первый вариант через метод BIND
    // мне этот вариант больше нравится, ибо так кода меньше,
    // и мы можем id лайоута передавать сразу в конструкторе родителя
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentBinding.bind(view)

        binding?.run {
            tvTitle.text = "PAYMENT"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
