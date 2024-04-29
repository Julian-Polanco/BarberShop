package co.com.poli.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ButtonsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById<Button>(R.id.login_display_button)
        loginButton.setOnClickListener {
            val dialogFragment = CustomDialogFragment.newInstance("LoginFragment")
            dialogFragment.show(parentFragmentManager, "LoginDialogFragment")
        }

        val registerButton = view.findViewById<Button>(R.id.sing_up_display_button)
        registerButton.setOnClickListener {
            val dialogFragment = CustomDialogFragment.newInstance("RegisterFragment")
            dialogFragment.show(parentFragmentManager, "RegisterDialogFragment")
        }
    }

}