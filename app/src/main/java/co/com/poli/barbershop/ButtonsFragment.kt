package co.com.poli.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import co.com.poli.barbershop.data_base.DatabaseHelper
import co.com.poli.barbershop.interfaces.OnLoginSuccess
import co.com.poli.barbershop.interfaces.OnRegistrationSuccessListener

class ButtonsFragment : Fragment(), OnRegistrationSuccessListener, OnLoginSuccess {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var profileButton: Button
    private lateinit var logoutButton: Button
    private lateinit var dialogFragment: CustomDialogFragment

    override fun onRegistrationSuccess() {
        activity?.runOnUiThread {
            dialogFragment.dismiss()
            Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
        }
        updateButtonVisibility()

    }

    override fun onLoginSuccess() {
        activity?.runOnUiThread {
            dialogFragment.dismiss()
            Toast.makeText(context, "Usuario logueado correctamente", Toast.LENGTH_SHORT).show()
        }
        updateButtonVisibility()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DatabaseHelper(requireContext())

        loginButton = view.findViewById(R.id.login_display_button)
        registerButton = view.findViewById(R.id.sing_up_display_button)
        profileButton = view.findViewById(R.id.profile_display_button)
        logoutButton = view.findViewById(R.id.logout_display_button)

        profileButton.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.changeFragment(ProfileFragment(), R.id.profile_line)
        }

        logoutButton.setOnClickListener {
            dbHelper.deleteToken("token")
            updateButtonVisibility()
        }

        loginButton.setOnClickListener {
            dialogFragment = CustomDialogFragment.newInstance("LoginFragment")
            dialogFragment.show(parentFragmentManager, "LoginDialogFragment")
            dialogFragment.loginListener = this

        }

        registerButton.setOnClickListener {
            dialogFragment = CustomDialogFragment.newInstance("RegisterFragment")
            dialogFragment.show(parentFragmentManager, "RegisterDialogFragment")
            dialogFragment.registrationListener = this

        }

        updateButtonVisibility()
    }

    override fun onResume() {
        super.onResume()
        updateButtonVisibility()
    }

    private fun updateButtonVisibility() {
        val token = dbHelper.getToken("token")

        if (token.isNotEmpty()) {
            profileButton.visibility = View.VISIBLE
            logoutButton.visibility = View.VISIBLE
            loginButton.visibility = View.GONE
            registerButton.visibility = View.GONE
        } else {
            loginButton.visibility = View.VISIBLE
            registerButton.visibility = View.VISIBLE
            profileButton.visibility = View.GONE
            logoutButton.visibility = View.GONE
        }
    }

}