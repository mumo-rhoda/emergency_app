package era.com

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Reports(
    val uid: String?,
    var reportID: String,
    var emergencyType: String,
    var reportParty: String,
    var description: String,
    var latitude: Double,
    var longitude: Double,
    val timestamp: Long,

    var reportStatus: String="Pending"



    )