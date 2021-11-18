package era.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {


    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainers:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnboardingItems()

        val getstarted = findViewById<Button>(R.id.buttonGetStarted)

        getstarted.setOnClickListener {
            val Intent = Intent(this,Registration::class.java)
            startActivity(Intent)
        }

        val loginbutton = findViewById<Button>(R.id.buttonlogin)
        loginbutton.setOnClickListener {
            val Intent = Intent(this,login::class.java)
            startActivity(Intent)
        }

    }
    private fun setOnboardingItems() {
        onboardingItemsAdapter =OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    title = "Remote Tracking",
                    description = "Allow your device location for Rescuer to find your location and safety status. Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.realtime
                ),
                OnboardingItem(
                    title = "Emergency",
                    description = "Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.choosemergency
               ),
                OnboardingItem(
                    title = "Emergency",
                    description = "Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.emegencytips
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter= onboardingItemsAdapter
    }
}