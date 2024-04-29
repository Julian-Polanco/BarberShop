package co.com.poli.barbershop

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController


class VideoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoUri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.hair_cut)
        val videoView = view.findViewById<android.widget.VideoView>(R.id.videoView)
        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        videoView.start()


    }
}