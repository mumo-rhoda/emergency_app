package era.com

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragment_home_connection.*

class FragmentHomeConnection : AppCompatActivity() {
    private val phoneNumber = "+25716234610"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home_connection)

        quickcall.setOnClickListener{

            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)

        }

        getEmergencytips.setOnClickListener {
            val intent = Intent( this, EmergencyTipsActivity::class.java)
            startActivity(intent)
        }



    }




}