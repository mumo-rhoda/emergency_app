package era.com

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import era.com.databinding.ActivityRegistrationBinding
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.regex.Pattern

class Registration : AppCompatActivity() {


    //view binding
    private lateinit var binding: ActivityRegistrationBinding



    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    //progress dialog
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkBox.setOnClickListener{
            if (checkBox.isChecked){
                inputpassword.inputType = 1
            }
            else
                inputpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        }



        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //init progress dialog, will show while creating account | Register user

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)




        binding.btnsignup.setOnClickListener {
            /*Steps
        1. Input Data
        2.validate
        3. Create Account - Firebase Auth
        4. Save User Info
         */
            validateData()

        }
    }
    private var fullname =""
    private var username = ""
    private var email = ""
    private var password = ""
    private var phonenumber =""


    private fun validateData() {
        fullname = binding.inputfullname.text.toString().trim()
        username = binding.regusername.text.toString().trim()
        email = binding.txtemailaddress.text.toString().trim()
        password = binding.inputpassword.text.toString().trim()
        phonenumber = binding.inputphonenumber.text.toString().trim()

        if (fullname.isEmpty()){
            Toast.makeText(this,"Enter your Full Name...", Toast.LENGTH_SHORT).show()
        }
        else if (username.isEmpty()){
            Toast.makeText(this,"Enter username please...", Toast.LENGTH_SHORT).show()
        }
        else if (username == fullname){
            Toast.makeText(this,"Enter a unique Username", Toast.LENGTH_SHORT).show()
        }
        else if (email.isEmpty()){
            Toast.makeText(this,"Enter your Email", Toast.LENGTH_SHORT).show()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Invalid Email!", Toast.LENGTH_SHORT).show()
        }
        else if (password.isEmpty()){
            Toast.makeText(this,"Enter Password", Toast.LENGTH_SHORT).show()
        }
        else if (!password.isValidPassword()){
            Toast.makeText(this,"Enter Strong Password", Toast.LENGTH_SHORT).show()
        }
        else
        {
            createUserAccount()
        }
    }

    private fun createUserAccount() {
        //create firebase Auth account

        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //account created
                updateUserInfo()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"Failed creating account due to ${e.message}", Toast.LENGTH_SHORT).show()

            }

    }

    private fun updateUserInfo() {
        //saving user info
        progressDialog.setMessage("Saved user info ...")
        //progressDialog.show()


        val timestamp= System.currentTimeMillis()

        val uid = firebaseAuth.uid

        //setup data to add in db

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["fullname"] = fullname
        hashMap["username"] = username
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timestamp
        hashMap["phonenumber"]=phonenumber


        //set data to db
        val db = Firebase.firestore
        db.collection("Users").document()
            .set(hashMap)


        //val ref = FirebaseDatabase.getInstance().getReference("Users")
        // ref.child(uid!!)
          //  .setValue(hashMap)
            .addOnSuccessListener {

                progressDialog.dismiss()
                Toast.makeText(this,"Account created ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Registration, login::class.java))


            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this,"Failed creating account due to ${e.message}", Toast.LENGTH_SHORT).show()
            }



    }

    private fun CharSequence.isValidPassword(): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@$%^&+=!])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }



}