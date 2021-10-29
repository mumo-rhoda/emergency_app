package era.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.xml.sax.HandlerBase

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreentimeout : Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()



        }, splashScreentimeout)
    }
}