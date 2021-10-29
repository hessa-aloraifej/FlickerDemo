package com.example.flickerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
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
    private fun update(list: List<Flicker>) {
        mainRV.adapter = RVAdapter(this,null,list)
        mainRV.layoutManager = LinearLayoutManager(this)
    }
    private fun updateView(list: List<Photo>) {
        mainRV.adapter = RVAdapter(this,list,null)
        mainRV.layoutManager = LinearLayoutManager(this)
    }
    fun save(flicker:Flicker){

        val likedimg = Flicker(flicker.id,flicker.url,flicker.title)

        CoroutineScope(IO).launch {
            FlickerDatabase.getInstance(applicationContext).FlickerDao().insertImg(likedimg)
        }
    }
    fun remove(flicker:Flicker){

        FlickerDatabase.getInstance(applicationContext).FlickerDao().delete(flicker)
        update(FlickerDatabase.getInstance(applicationContext).FlickerDao().getAllImg())
    }

}




//  override fun onFailure(call: Call<Flucker>, t: Throwable) {
//                    call.cancel()
//                }
// override fun onResponse(call: Call<Flucker>, response: Response<Flucker>) {
//                    val list = response.body()!!.photos.photo
//                    updateView(list)
//





