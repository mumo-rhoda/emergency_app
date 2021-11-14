package era.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Registration : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val logginbutton = findViewById<Button>(R.id.btnsignup)

       logginbutton.setOnClickListener {
           val Intent = Intent(this, login::class.java)
           startActivity(Intent)
       }
    }
}