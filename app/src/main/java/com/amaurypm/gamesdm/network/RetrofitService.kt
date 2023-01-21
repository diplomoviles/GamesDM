package com.amaurypm.gamesdm.network

import com.amaurypm.gamesdm.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creado por Amaury Perea Matsumura el 18/01/23
 */
object RetrofitService{
    private var INSTANCE: Retrofit? = null

    fun getRetrofit(): Retrofit{
        return INSTANCE ?: synchronized(this){
            val instance = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            INSTANCE = instance

            instance
        }
    }
}