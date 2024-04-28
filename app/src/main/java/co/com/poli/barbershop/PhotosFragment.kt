package co.com.poli.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class PhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radius = resources.getDimensionPixelSize(R.dimen.radius)
        val requestOptions = RequestOptions().transform(RoundedCorners(radius))

        Glide.with(this).load(R.drawable.simple_cut).apply(requestOptions)
            .into(view.findViewById(R.id.image_simple_cut))

        Glide.with(this).load(R.drawable.simple_cut_and_beard).apply(requestOptions)
            .into(view.findViewById(R.id.image_simple_cut_and_beard))

        Glide.with(this).load(R.drawable.specialized_cut).apply(requestOptions)
            .into(view.findViewById(R.id.image_specialized_cut))

        Glide.with(this).load(R.drawable.only_beard).apply(requestOptions)
            .into(view.findViewById(R.id.image_only_beard))

        Glide.with(this).load(R.drawable.paint_cut).apply(requestOptions)
            .into(view.findViewById(R.id.image_painted_hair))

        Glide.with(this).load(R.drawable.eye_brows).apply(requestOptions)
            .into(view.findViewById(R.id.image_eye_brows))

        val simpleCutFrame = view.findViewById<View>(R.id.frame_simple_cut)
        simpleCutFrame.setOnClickListener{
            ImageDialogFragment.newInstance(R.drawable.simple_cut,"Corte de pelo simple y elegante. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como cejas, figuras o líneas. Ideal para aquellos que buscan un look fresco y sin complicaciones.", "Precio: 14.000")
                .show(childFragmentManager, "ImageDialogFragment")
        }
    }

}