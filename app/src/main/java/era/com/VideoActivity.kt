package era.com

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)



        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoview)


        val offlineUri:Uri = Uri.parse("android.resource://$packageName/${R.raw.video}")

        videoview.setMediaController(mediaController)
        videoview.setVideoURI(offlineUri)
        videoview.requestFocus()
        videoview.start()
    }
}