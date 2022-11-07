package com.sheniv.inpre.fragments.add_flower

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.sheniv.inpre.firebase.AUTH
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentCodeBinding
import com.google.firebase.auth.PhoneAuthProvider
import com.sheniv.inpre.MainActivity
import com.sheniv.inpre.R
import com.sheniv.inpre.firebase.*
import com.sheniv.inpre.utilits.showToast
import java.util.concurrent.TimeUnit

class CodeFragment : BaseFragment<FragmentCodeBinding>() {

    private val args: CodeFragmentArgs by navArgs()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCodeBinding.inflate(inflater, container, false)

    override fun FragmentCodeBinding.onBindView(savedInstanceState: Bundle?) {
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                repeatCode.isClickable = false
                tvAboutRepeatSendCode.text =
                    "Повторно получить код можно будет через 00:${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                repeatCode.isClickable = true
                tvAboutRepeatSendCode.text = "Не получили код? - Запросите повторную отправку:"
            }
        }
        timer.start()

        code.setOnClickListener {
            enterCode(args.mPhoneNumber, args.id)
        }
        repeatCode.setOnClickListener {
            authRepeatUser()
            timer.start()
        }
    }

    private fun enterCode(mPhoneNumber: String, id: String) {

        val code = binding.password.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                REF_DATABASE_ROOT.child(NODE_PHONES).child(mPhoneNumber).setValue(uid)
                    .addOnFailureListener { task1 -> showToast(task1.message.toString()) }
                    .addOnSuccessListener {
                        REF_DATABASE_ROOT.child(NODE_USER).child(uid)
                            .updateChildren(mapOf(CHILD_PHONE to mPhoneNumber, CHILD_ID to uid))
                            .addOnSuccessListener {
                                showToast("Добро пожаловать\n$mPhoneNumber")
                                navController.navigate(R.id.navigation_main)
                                activity?.let {ma -> (ma as MainActivity).initUserMain() }
                            }
                            .addOnFailureListener {task -> showToast(task.message.toString()) }
                    }
            } else showToast("Неверно введен код")
        }
    }

    private fun authRepeatUser() {
        val m = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        it.result.user
                        findNavController().navigate(R.id.navigation_main)
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }
        }
        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(AUTH)
                .setActivity(activity as MainActivity)
                .setPhoneNumber(args.mPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(m)
                .setForceResendingToken(resendToken)
                .build()
        )
    }
}