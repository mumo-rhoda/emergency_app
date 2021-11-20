package era.com

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import era.com.databinding.ActivityLoginBinding
import era.com.databinding.ActivityRegistrationBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*

class login : AppCompatActivity() {


    //view binding
    private lateinit var binding: ActivityLoginBinding



    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebase auth

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //init progress dialog, will show while creating account | Register user

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
//handle click, not have account
        binding.txtsignup.setOnClickListener{
            startActivity(Intent(this, Registration::class.java))
        }
        //handle login
        binding.btnsigin.setOnClickListener {
            validateData()
        }



        checkBox2.setOnClickListener{
            if (checkBox2.isChecked){
                InputPassword.inputType = 1
            }
            else
                InputPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        }
    }

    private var password =""
    private var email =""

    private fun validateData() {
        email = binding.InputUsername.text.toString().trim()
        password = binding.InputPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Invalid Email!", Toast.LENGTH_SHORT).show()

        }
        else if ( password.isEmpty()){
            Toast.makeText(this,"Enter Password", Toast.LENGTH_SHORT).show()
        }
        else{
            loginUser()

        }

    }

    private fun loginUser() {
        //firebase Auth

        progressDialog.setMessage("Logging In.")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
               startActivity(Intent(this@login, FragmentHomeConnection::class.java))
                finish()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }


}