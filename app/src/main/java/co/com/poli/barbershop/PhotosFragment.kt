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
        simpleCutFrame.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.simple_cut,
                "Corte de pelo simple y elegante. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como cejas, barba, figuras o líneas. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 14.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentSimpleCut")
        }

        val cutWithBeard = view.findViewById<View>(R.id.frame_simple_cut_and_beard)
        cutWithBeard.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.simple_cut_and_beard,
                "Corte de pelo simple y elegante con barba. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como cejas, figuras o líneas. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 18.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentCutWithBeard")
        }

        val specializedCut = view.findViewById<View>(R.id.frame_specialized_cut)
        specializedCut.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.specialized_cut,
                "Corte de pelo especializado. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como cejas, ni barba. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 20.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentSpecializedCut")
        }

        val onlyBeard = view.findViewById<View>(R.id.frame_only_beard)
        onlyBeard.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.only_beard,
                "Barba. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como cejas, ni corte de pelo. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 10.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentOnlyBeard")
        }

        val paintedHair = view.findViewById<View>(R.id.frame_painted_hair)
        paintedHair.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.paint_cut,
                "Corte de pelo pintado. Este servicio se centra en proporcionar un estilo limpio y ordenado, con detalles adicionales como cejas, barba, figuras o líneas. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 25.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentPaintedHair")
        }
        val eyeBrows = view.findViewById<View>(R.id.frame_eye_brows)
        eyeBrows.setOnClickListener {
            ImageDialogFragment.newInstance(
                R.drawable.eye_brows,
                "Cejas. Este servicio se centra en proporcionar un estilo limpio y ordenado, sin incluir detalles adicionales como corte de pelo, barba, figuras o líneas. Ideal para aquellos que buscan un look fresco y sin complicaciones.",
                "Precio: 4.000"
            )
                .show(childFragmentManager, "ImageDialogFragmentEyeBrows")
        }

    }
}