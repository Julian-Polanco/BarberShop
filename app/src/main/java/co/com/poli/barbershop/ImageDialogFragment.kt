package co.com.poli.barbershop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.bumptech.glide.Glide

class ImageDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val imageResId = it.getInt("imageResId")
            val description = it.getString("description")
            val price = it.getString("price")
            val imageView = view.findViewById<android.widget.ImageView>(R.id.imageView)
            Glide.with(this).load(imageResId).into(imageView)
            val textView = view.findViewById<android.widget.TextView>(R.id.description_text)
            textView.text = description
            val priceView = view.findViewById<android.widget.TextView>(R.id.price_text)
            priceView.text = price
        }
        val closeButton = view.findViewById<android.widget.Button>(R.id.close_button)
        closeButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(imageResId: Int, description: String, price: String): ImageDialogFragment {
            val fragment = ImageDialogFragment()
            val args = Bundle()
            args.putInt("imageResId", imageResId)
            args.putString("description", description)
            args.putString("price", price)
            fragment.arguments = args
            return fragment
        }
    }
}