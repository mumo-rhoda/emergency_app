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
    private val phoneNumber = "999"

    private lateinit var firebaseAuth: FirebaseAuth

    private var database: FirebaseDatabase? = null
    private var databaseReference : DatabaseReference? =null
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home_connection)
        firebaseAuth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")
        val sharedPref=this?.getPreferences(Context.MODE_PRIVATE)?:return
        val isLogin=sharedPref.getString("Email", "1")





        if(isLogin=="1") {

            var email=intent.getStringExtra("email")
            if(email!=null)
            {
                setText(email)
                with(sharedPref.edit())
                {
                    putString("Email",email)
                    apply()
                }
            }
        }
        else
        {
           var intent = Intent(this,Registration::class.java)
            startActivity(intent)
            finish()
        }







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

    private fun setText(email: String) {
        db=FirebaseFirestore.getInstance()
        if (email!= null){
            db.collection("Users").document().get()
                .addOnSuccessListener{
                    tasks->
                    Welcome.text=tasks.get("Name").toString()
                }
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