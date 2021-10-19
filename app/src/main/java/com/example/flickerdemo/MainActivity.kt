package com.example.flickerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainRV: RecyclerView
    lateinit var imgSearch:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgSearch=findViewById(R.id.imgsearch)
        mainRV = findViewById(R.id.rvMain)
        var btn=findViewById<Button>(R.id.button)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        btn.setOnClickListener {
               val search=imgSearch.text.toString()
            apiInterface!!.getPhoto(search)?.enqueue(object: Callback<Flucker?> {

                override fun onFailure(call: Call<Flucker?>, t: Throwable) {
                    call.cancel()
                }

                override fun onResponse(call: Call<Flucker?>, response: Response<Flucker?>) {
                    val list = response.body()!!.photos.photo
                    updateView(list)
                }
            })


        }

    }

    private fun updateView(list: List<Photo>) {
        mainRV.adapter = RVAdapter(list)
        mainRV.layoutManager = LinearLayoutManager(this)
    }
}




//  override fun onFailure(call: Call<Flucker>, t: Throwable) {
//                    call.cancel()
//                }
// override fun onResponse(call: Call<Flucker>, response: Response<Flucker>) {
//                    val list = response.body()!!.photos.photo
//                    updateView(list)
//





