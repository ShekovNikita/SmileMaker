package com.sheniv.inpre.fragments.add_flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.sheniv.data.firebase.AUTH
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentCodeBinding
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : BaseFragment<FragmentCodeBinding>() {

    private val args: CodeFragmentArgs by navArgs()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCodeBinding.inflate(inflater, container, false)

    override fun FragmentCodeBinding.onBindView(savedInstanceState: Bundle?) {
        val string = password.text.toString()
        code.setOnClickListener {
            enterCode(args.mPhoneNumber, args.id)
        }
    }

    private fun enterCode(mPhoneNumber: String, id: String) {
        val code = binding.password.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "всё гуд", Toast.LENGTH_SHORT).show()
                navController.navigate(CodeFragmentDirections.actionCodeFragmentToAddFlower())
            }
        }
    }
}