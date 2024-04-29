package co.com.poli.barbershop

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class CustomDialogFragment : DialogFragment() {
    var listener: OnRegistrationSuccessListener? = null

    companion object {
        fun newInstance(fragmentType: String): CustomDialogFragment {
            val args = Bundle()
            args.putString("fragmentType", fragmentType)
            val dialogFragment = CustomDialogFragment()
            dialogFragment.arguments = args
            return dialogFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val fragmentType = arguments?.getString("fragmentType")
        val fragment = when (fragmentType) {
            "LoginFragment" -> LoginFragment()
            "RegisterFragment" ->
                RegisterFragment().apply {
                listener = this@CustomDialogFragment.listener
            }

            else -> throw IllegalArgumentException("Invalid fragment type")
        }
        childFragmentManager.beginTransaction().replace(R.id.fragment_container_dialog, fragment)
            .commit()
        return inflater.inflate(R.layout.fragment_custom_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closeButton = view.findViewById<Button>(R.id.close_button_custom_dialog)
        closeButton.setOnClickListener {
            dismiss()
        }
        val rootView = activity?.window?.decorView?.rootView
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height

            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                closeButton.visibility = View.GONE
            } else {
                closeButton.visibility = View.VISIBLE
            }
        }


    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
    }


}