package co.com.poli.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import co.com.poli.barbershop.data_base.DatabaseHelper
import co.com.poli.barbershop.interfaces.OnLoginSuccess
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ProfileFragment : Fragment(), OnLoginSuccess {
    private var textViewName: TextView? = null
    private var textViewStudies: TextView? = null
    private var textViewExperience: TextView? = null
    private var buttonLoginProfile: Button? = null
    private lateinit var dialogFragment: CustomDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewName = view.findViewById(R.id.tv_name_profile)
        textViewStudies = view.findViewById(R.id.tv_studies_profile)
        textViewExperience = view.findViewById(R.id.tv_experience_profile)
        getUserInfo()
        buttonLoginProfile = view.findViewById(R.id.btnLoginProfile)
        buttonLoginProfile?.setOnClickListener {
            showLoginDialog()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showLoginDialog() {
        dialogFragment = CustomDialogFragment.newInstance("LoginFragment")
        dialogFragment.loginListener = this
        dialogFragment.show(childFragmentManager, "loginDialog")

    }

    override fun onLoginSuccess() {
        activity?.runOnUiThread {
            dialogFragment.dismiss()
            Toast.makeText(context, "Usuario logueado correctamente", Toast.LENGTH_SHORT).show()
        }
        getUserInfo()
    }

    private fun getUserInfo() {
        val client = OkHttpClient()
        val token = DatabaseHelper(requireContext()).getToken("token")
        val request =
            Request.Builder().url("http://10.0.2.2:3000/user").header("x-json-token", token).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(
                        context, "Error al obtener la información del usuario", Toast.LENGTH_SHORT
                    ).show()
                }
                buttonLoginProfile?.visibility = View.VISIBLE

            }

            override fun onResponse(call: Call, response: Response) {
                val jsonData = response.body?.string()
                val jsonObject = jsonData?.let { JSONObject(it) }
                if (!response.isSuccessful) {
                    val responseOk = jsonObject?.getString("ok")
                    if (responseOk.equals("false")) {
                        activity?.runOnUiThread {
                            buttonLoginProfile?.visibility = View.VISIBLE
                        }
                        return
                    }
                } else {
                    if (jsonObject?.has("user") == true) {
                        val user = jsonObject.getJSONObject("user")
                        val name = "Nombre: ${user.getString("name")}"
                        val studies = "Estudios: ${user.getString("studies")}"
                        val experience = "Experiencia: ${user.getString("experience")}"

                        activity?.runOnUiThread {
                            textViewName?.text = name
                            textViewExperience?.text = experience
                            textViewStudies?.text = studies
                            buttonLoginProfile?.visibility = View.GONE
                        }
                    } else {
                        activity?.runOnUiThread {
                            buttonLoginProfile?.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }
}