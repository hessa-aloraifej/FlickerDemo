package com.example.flickerdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}


//https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=7f66f9584154461535f96f5966e41e5c&text=cat&per_page=7&format=json&nojsoncallback=1
