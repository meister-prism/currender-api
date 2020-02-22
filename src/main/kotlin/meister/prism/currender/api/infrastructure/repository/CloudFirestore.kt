package meister.prism.currender.api.infrastructure.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.FileInputStream

class CloudFirestore {
    fun getMemo(): Unit {
        val ref = FirebaseDatabase.getInstance()
        val memoRef = ref.getReference("memo")
        memoRef.setValueAsync("hoge")
    }
}
