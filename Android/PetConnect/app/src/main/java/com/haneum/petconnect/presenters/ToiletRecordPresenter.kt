package com.haneum.petconnect.presenters

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.haneum.petconnect.contracts.ToiletRecordContract
import com.haneum.petconnect.data.ToiletRecord
class ToiletRecordPresenter(private val view: ToiletRecordContract.View) : ToiletRecordContract.Presenter {

    private val database = FirebaseDatabase.getInstance()
    private val recordRef = database.getReference("toilet_records")

    override fun saveToiletRecord(type: String) {
        // 이 부분에 사용자 입력을 Firebase에 저장하는 코드를 추가하세요.
        // 예를 들어, 사용자 입력을 받아서 Firebase에 저장하는 코드를 여기에 추가합니다.
    }

    override fun loadToiletRecords() {
        // Firebase에서 데이터 읽기
        recordRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val records = mutableListOf<ToiletRecord>()
                for (postSnapshot in dataSnapshot.children) {
                    val record = postSnapshot.getValue(ToiletRecord::class.java)
                    record?.let { records.add(it) }
                }
                view.showToiletRecords(records)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 에러 처리
            }
        })
    }
}
