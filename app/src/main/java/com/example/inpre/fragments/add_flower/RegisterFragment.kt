package com.example.inpre.fragments.add_flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.data.firebase.AUTH
import com.example.data.firebase.initFirebase
import com.example.data.firebase.resendToken
import com.example.data.firebase.storedVerificationId
import com.example.inpre.MainActivity
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentRegisterBinding
import com.example.inpre.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun FragmentRegisterBinding.onBindView(savedInstanceState: Bundle?) {
        initFirebase()
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        it.result.user
                        showToast("всё гуд")
                        navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToAddFlower2())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                storedVerificationId = id
                resendToken = token
                navController.navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToCodeFragment(
                        mPhoneNumber,
                        id
                    )
                )
            }
        }
        getCode.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.enterPhone.text.toString().isEmpty()) {
            showToast("Enter your number")
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = binding.enterPhone.text.toString()
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setActivity(activity as MainActivity)
            .setPhoneNumber(mPhoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(mCallBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}