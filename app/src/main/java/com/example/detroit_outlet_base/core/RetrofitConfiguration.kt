package com.example.detroit_outlet_base.core

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val baseUrl = "http://192.168.1.26:9000/"
        private val credential = Credentials.basic("detroit", "outlet")

        private fun getRetrofitInstance(): Retrofit {
            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", credential)
                    .build()
                chain.proceed(newRequest)
            }

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}