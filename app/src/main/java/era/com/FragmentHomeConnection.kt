package era.com

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fragment_home_connection.*
import kotlinx.android.synthetic.main.activity_maps.*

class FragmentHomeConnection : AppCompatActivity() {
    private val phoneNumber = "999"

    private lateinit var firebaseAuth: FirebaseAuth

    private var database: FirebaseDatabase? = null
    private var databaseReference : DatabaseReference? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home_connection)
        firebaseAuth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")

        loadProfile()

        historybutton.setOnClickListener{
            val intent = Intent(this, ReportEmergency::class.java)
            startActivity(intent)
        }


        settings.setOnClickListener {
            val intent1 = Intent( this, settingsActivity::class.java)
            startActivity(intent1)
        }

        mapviewimage.setOnClickListener {
            val intent= Intent(this, map::class.java)
            startActivity(intent)

        }


        quickcall.setOnClickListener{

            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)

        }

        getEmergencytips.setOnClickListener {
            val intent2 = Intent( this, EmergencyTipsActivity::class.java)
            startActivity(intent2)
        }

        getReportPage.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java )
            startActivity(intent)
        }



    }

    private fun loadProfile() {
        val user = firebaseAuth.currentUser
        val userreference= databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                Welcome.text = "Welcome to Emergency response App"+snapshot.child("username").value.toString()

            }
            override fun onCancelled(error: DatabaseError){

            }
        })
    }


}