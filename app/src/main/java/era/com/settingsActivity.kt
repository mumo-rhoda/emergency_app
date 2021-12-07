package era.com

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class settingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val sharedPref=this?.getPreferences(Context.MODE_PRIVATE)?:return
        logout.setOnClickListener {
            sharedPref.edit().remove("Email").apply()
            var intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()
        }
    }
}