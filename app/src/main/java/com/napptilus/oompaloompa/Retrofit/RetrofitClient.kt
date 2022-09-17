package com.napptilus.oompaloompa.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}