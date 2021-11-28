package era.com


import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_report_emergency.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ReportEmergency : AppCompatActivity() {


   private lateinit var filepath : Uri


    private lateinit var binding: ReportEmergency

    private lateinit var buttonimage: Button
    private lateinit var imageView: ImageView
    private lateinit var buttonreport: Button

    private val reportCollectionRef = Firebase.firestore.collection( "Reports")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_emergency)

        buttonimage = findViewById(R.id.takeimage)
        imageView = findViewById(R.id.imageEmergency)
        buttonreport = findViewById(R.id.Report_Now)


        buttonimage.setOnClickListener {
            chooseimage()
        }

        buttonreport.setOnClickListener {
            val reporttype = Choose_Emergency.text.toString()
            val party = partyreport.text.toString()
            val reportdescription = emegencydescription.text.toString()
            val report = Reports(reporttype, party, reportdescription)
            saveReports(report)
        }

    }

    private fun saveReports(report: Reports) = CoroutineScope(Dispatchers.IO).launch {
        try{
            reportCollectionRef.add(report)

            withContext(Dispatchers.Main){
                Toast.makeText(this@ReportEmergency, "Successfully saved data.", Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@ReportEmergency, e.message,Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun chooseimage() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Choose Picture"), 111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==111 && resultCode == Activity.RESULT_OK && data != null){
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageView.setImageBitmap(bitmap)
        }
        if (filepath!=null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

            var imageRef = FirebaseStorage.getInstance().reference.child("EmergencyImages/$filepath")
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
