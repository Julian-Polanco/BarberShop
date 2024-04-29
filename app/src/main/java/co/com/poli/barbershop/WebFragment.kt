package co.com.poli.barbershop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.net.MalformedURLException
import java.net.URL

class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton = view.findViewById<Button>(R.id.search_button)
        val urlInput = view.findViewById<EditText>(R.id.url_input)
        searchButton.setOnClickListener {
            var url = urlInput.text.toString()
            if (!url.startsWith("www.")) {
                url = "https://www.google.com/search?q=$url"
            }
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            try {
                val validUrl = URL(url)
                println("URL is valid: $validUrl")
                if (url.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Por Favor Ingresa Una Url", Toast.LENGTH_SHORT).show()
                }
            } catch (e: MalformedURLException) {
                Toast.makeText(context, "Url Invalida", Toast.LENGTH_SHORT).show()
                println("URL is not valid: $url")
            }
        }
    }
}