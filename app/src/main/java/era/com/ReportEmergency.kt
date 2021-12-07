package era.com


import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_report_emergency.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp

import java.util.*


class ReportEmergency : AppCompatActivity() {

    private lateinit var filepath : Uri
    private lateinit var progressDialog: ProgressDialog


    //private lateinit var binding: ReportEmergency

    private lateinit var buttonimage: Button
    private lateinit var imageView: ImageView
    private lateinit var buttonreport: Button
    private val timestamp= System.currentTimeMillis()
    private val uid = FirebaseAuth.getInstance().uid
    private val reportID = "$uid,$timestamp"


    private val reportRef = Firebase.firestore.collection("Reports").document(reportID)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_emergency)

        buttonimage = findViewById(R.id.takeimage)
        imageView = findViewById(R.id.imageEmergency)
        buttonreport = findViewById(R.id.Report_Now)

        val intent = intent
        val latitude = intent.getDoubleExtra("lat", 0.00)
        val longitude = intent.getDoubleExtra("long",0.00)

        Longitude.text = "$longitude"
        Latitude.text = "$latitude"

        buttonimage.setOnClickListener {
            chooseimage()
        }

        buttonreport.setOnClickListener {

            val emergencyType = Choose_Emergency.text.toString()
            val reportParty= partyreport.text.toString()
            val description = emegencydescription.text.toString()

            val reportStatus = "Pending"



            val report = Reports(uid,reportID,emergencyType,reportParty,description,latitude,longitude,timestamp,reportStatus)

            saveReports(report)


        }

    }

    private fun saveReports(report: Reports) = CoroutineScope(Dispatchers.IO).launch {
        try{
            reportRef.set(report)

            withContext(Dispatchers.Main){
                Toast.makeText(this@ReportEmergency, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }


        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@ReportEmergency, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun chooseimage() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Choose Picture"), 111)

    }

    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==111 && resultCode == RESULT_OK && data != null){
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageView.setImageBitmap(bitmap)
        }
        if (filepath!=null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

            var imageRef = FirebaseStorage.getInstance().reference.child("Images/$filepath")
            imageRef.putFile(filepath)
                .addOnSuccessListener {
                    pd.dismiss()
                    Toast.makeText(applicationContext,"File uploaded", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { p0 ->
                    var progress:Double = (100.0 * p0.bytesTransferred) /p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")


                }

        }
    }


}
