package co.com.poli.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.com.poli.barbershop.data_base.DatabaseHelper
import co.com.poli.barbershop.interfaces.OnRegistrationSuccessListener
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class RegisterFragment : Fragment() {
    var listener: OnRegistrationSuccessListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerButton = view.findViewById<Button>(R.id.btnRegister)
        registerButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.etName).text.toString()
            val email = view.findViewById<EditText>(R.id.etLoginEmail).text.toString()
            val password = view.findViewById<EditText>(R.id.etLoginPassword).text.toString()
            val confirmPassword =
                view.findViewById<EditText>(R.id.etConfirmPassword).text.toString()
            val experience = view.findViewById<EditText>(R.id.etExperience).text.toString()
            val studies = view.findViewById<EditText>(R.id.etStudies).text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || experience.isEmpty() || studies.isEmpty()) {
                Toast.makeText(context, "Porfavor completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (password != confirmPassword) {
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                createUser(name, email, password, experience, studies)
            }
        }

    }

    private fun createUser(
        name: String,
        email: String,
        password: String,
        experience: String,
        studies: String
    ) {
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("name", name)
            .add("email", email)
            .add("password", password)
            .add("experience", experience)
            .add("studies", studies)
            .build()

        val request = Request.Builder()
            .url("http://10.0.2.2:3000/user/create")
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }

                val responseString = response.body?.string()
                val jsonObject = responseString?.let { JSONObject(it) }
                val token = jsonObject?.getString("token")

                val dbHelper = DatabaseHelper(context!!)
                if (token != null) {
                    dbHelper.addToken("token", token)
                    activity?.runOnUiThread {
                        listener?.onRegistrationSuccess()
                    }
                }

            }
        })
    }


}
