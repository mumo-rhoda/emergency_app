package era.com

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class FragmentHomeConnection : AppCompatActivity() {
    private val fragmentManger = supportFragmentManager
    private val homeFrag= Homepage()
    private val healthFrag= healthtips()
    private val notificationFrag= Notifications()
    private val settingsFrag= Settingpage()
    /**call id*/
    private lateinit var btnHo : ImageButton
    private lateinit var btnHe : ImageButton
    private lateinit var btnN : ImageButton
    private lateinit var btnS : ImageButton
    private lateinit var manger :FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home_connection)
        /**set find id view*/
        btnHo= findViewById(R.id.home)
        btnHe= findViewById(R.id.healthtips)
        btnN= findViewById(R.id.notications)
        btnS= findViewById(R.id.settings)
        /**set First Fragment*/
        manger = fragmentManger.beginTransaction()
            .replace(R.id.myView,homeFrag)
        manger.commit()
        btnHo.setImageResource(R.drawable.ic_baseline_home_24)
    }

    fun addFragOnClick(view: View){
        /**set replace fragment*/
        manger = fragmentManger.beginTransaction()
        when(view){
            btnHo -> {
                manger.replace(R.id.myView,homeFrag)
                    .commit()
                btnHo.setImageResource(R.drawable.ic_baseline_home_24)
                btnHe.setImageResource(R.drawable.ic_dashboard_black_24dp)
                btnN.setImageResource(R.drawable.ic_notifications_black_24dp)
                btnS.setImageResource(R.drawable.blacksettings)

            }
            btnHe -> {
                manger.replace(R.id.myView,healthFrag)
                    .commit()
                btnHo.setImageResource(R.drawable.ic_home_black_24dp)
                btnHe.setImageResource(R.drawable.ic_baseline_dashboard_24)
                btnN.setImageResource(R.drawable.ic_notifications_black_24dp)
                btnS.setImageResource(R.drawable.blacksettings)

            }
            btnN -> {
                manger.replace(R.id.myView,notificationFrag)
                    .commit()
                btnHo.setImageResource(R.drawable.ic_home_black_24dp)
                btnHe.setImageResource(R.drawable.ic_dashboard_black_24dp)
                btnN.setImageResource(R.drawable.ic_baseline_pinknotifications)
                btnS.setImageResource(R.drawable.blacksettings)

            }
            btnS -> {
                manger.replace(R.id.myView,settingsFrag)
                    .commit()
                btnHo.setImageResource(R.drawable.ic_home_black_24dp)
                btnHe.setImageResource(R.drawable.ic_dashboard_black_24dp)
                btnN.setImageResource(R.drawable.ic_notifications_black_24dp)
                btnS.setImageResource(R.drawable.pinksettings)

            }
        }
    }
}