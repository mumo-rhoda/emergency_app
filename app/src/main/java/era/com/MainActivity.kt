package era.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {


    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnboardingItems()

    }
    private fun setOnboardingItems() {
        onboardingItemsAdapter =OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    title = "Emergency",
                    description = "Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.paramedic
                ),
                OnboardingItem(
                    title = "Emergency",
                    description = "Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.buttonemergency
               ),
                OnboardingItem(
                    title = "Emergency",
                    description = "Real-time tracking of your location during emergency",
                    onboardingImage = R.drawable.healthtips
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter= onboardingItemsAdapter
    }
}