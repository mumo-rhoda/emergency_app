package era.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {


    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainers:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnboardingItems()

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