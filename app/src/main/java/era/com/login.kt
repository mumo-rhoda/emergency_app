package era.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val home = findViewById<Button>(R.id.btnsigin)

         home.setOnClickListener {
            val Intent = Intent(this,FragmentHomeConnection::class.java)
            startActivity(Intent)}
    }
}