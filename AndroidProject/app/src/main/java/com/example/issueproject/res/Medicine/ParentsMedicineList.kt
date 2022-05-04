package com.example.issueproject.res.Medicine
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapterimport.MedicineListAdapter
import com.example.issueproject.databinding.ActivityMedicineListBinding
import com.example.issueproject.dto.MedicineManage
import com.example.issueproject.res.Medicine.Parents_MedicineInfo
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "SchoolTeacherActivity"
class ParentsMedicineList : AppCompatActivity() {
    lateinit var MedicineListAdapter: MedicineListAdapter

    private val binding by lazy {
        ActivityMedicineListBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val room = intent.getStringExtra("room").toString()
        ShowRecycler(room)

        binding.medicinelistButtonAdd.setOnClickListener {
            val add : Boolean = true
            var intent = Intent(this@ParentsMedicineList, Parents_MedicineInfo::class.java).apply {
                putExtra("add", add)
            }
            startActivity(intent)
        }
    }

    private fun initRecycler(list: MutableList<MedicineManage>) {
        MedicineListAdapter = MedicineListAdapter(list)
        binding.RoomMedicineListRV.apply {
            adapter = MedicineListAdapter
            layoutManager = LinearLayoutManager(context)

            MedicineListAdapter.setItemClickListener(object: MedicineListAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    val add : Boolean = false
                    var intent = Intent(this@ParentsMedicineList, Parents_MedicineInfo::class.java).apply {

                        putExtra("add", add)

                        putExtra(
                            "cname",
                            MedicineListAdapter.MedicineListViewHolder(v).cname.toString()
                        )
                        putExtra(
                            "cname",
                            MedicineListAdapter.MedicineListViewHolder(v).mname.toString()
                        )
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private fun ShowRecycler(room: String) {
        ResponseService().MedicineListShow(
            room,
            object : RetrofitCallback<MutableList<MedicineManage>> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: MutableList<MedicineManage>) {
                    Log.d(TAG, "onSuccess: $responseData")
                    initRecycler(responseData)
                }

                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }

            })

    }
}