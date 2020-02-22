package meister.prism.currender.api.infrastructure.repository

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.google.gson.JsonObject
import meister.prism.currender.api.domain.service.MemoService
import meister.prism.currender.api.infrastructure.entity.ImageMemoEntity

class MemoRepository {
    private val rootRef = FirebaseDatabase.getInstance()
    private val memoImgRef = rootRef.getReference("memo/images")
    private val memoService = MemoService()
    /**
     *　初回起動時にのみ実行される
     */
    fun getImgAll(): Unit {
        rootRef.getReference("/").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val snapshot: String = dataSnapshot.getValue(Any::class.java).toString()
                val body: ArrayList<ImageMemoEntity> = mappingImgAll(snapshot)
                // map 後のデータをwsへ
                memoService.sendAllMemos(body)
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    private fun mappingImgAll(snapshot: String): ArrayList<ImageMemoEntity> {
        val tmp = Gson().fromJson(snapshot, JsonObject::class.java).getAsJsonObject("images")
        var all = ArrayList<ImageMemoEntity>()
        for(entry in tmp.entrySet()){
            val e = entry.value.asJsonObject
            all.add(ImageMemoEntity(e.get("ts").toString().replace("\"",""), e.get("imgMemo").toString().replace("\"","")))
        }
        return all
    }

    fun addImg(imageMemoEntity: ImageMemoEntity): String {
        memoImgRef.push().setValueAsync(imageMemoEntity)
        return "success"
    }

    fun deleteImg(){

    }
}