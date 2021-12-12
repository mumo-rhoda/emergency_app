package era.com

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_fragment_home_connection.*
import kotlinx.android.synthetic.main.activity_maps.*

class FragmentHomeConnection : AppCompatActivity() {
    private val phoneNumber = "00999"

    private lateinit var firebaseAuth: FirebaseAuth

    private var database: FirebaseDatabase? = null

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home_connection)
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        videotips.setOnClickListener{
            val intent = Intent( this, VideoActivity::class.java )
            startActivity(intent)

        }





        historybutton.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }


        settings.setOnClickListener {
            val intent1 = Intent(this, settingsActivity::class.java)
            startActivity(intent1)
        }
        reportingbutton.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }





        quickcall.setOnClickListener {

            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)

        }

        articletips.setOnClickListener{
            val intent = Intent( this, EmergencyTipsActivity::class.java)
            startActivity(intent)
        }

        getEmergencytips.setOnClickListener {
            val intent2 = Intent(this, EmergencyTipsActivity::class.java)
            startActivity(intent2)
        }

        getReportPage.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

}


