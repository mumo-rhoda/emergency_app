package era.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_emergency_tips.*

class EmergencyTipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_tips)

        val arrayList = ArrayList<Model>()
        arrayList.add(Model( "Cuts and Burns", "These are the immediate Steps to take when having bleeding cuts", R.drawable.emegencytips))
        arrayList.add(Model( "Drowning", "These are the immediate Steps to take when drawning", R.drawable.emegencytips))
        arrayList.add(Model( "Accidents", "These are the immediate Steps to take when vehicle accidents happen", R.drawable.emegencytips))
        arrayList.add(Model( "Fire Burns", "These are the immediate Steps to take when having fire outbreaks", R.drawable.paramedic))
        arrayList.add(Model( "Animal Attacks", "These are the immediate Steps to take when Animal Attacks", R.drawable.emegencytips))
        arrayList.add(Model( "Building Collapse", "These are the immediate Steps to take during a building colapse", R.drawable.emegencytips))
        val myAdapter = MyAdapter(arrayList, this)
        recyclerView.layoutManager = LinearLayoutManager( this)
        recyclerView.adapter=myAdapter
    }
}