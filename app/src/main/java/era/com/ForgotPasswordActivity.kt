package era.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        signafter.setOnClickListener{
            startActivity(Intent(this, login::class.java))
        }

        sendemail.setOnClickListener {
            val email: String = editTextTextPersonName3.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Please enter email address.",
                    Toast.LENGTH_SHORT

                ).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Email sent successfully to reset your password!",
                                Toast.LENGTH_LONG

                            ).show()
                            finish()
                        }else{
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG

                            ).show()
                        }
                    }
            }

        }




    }
}