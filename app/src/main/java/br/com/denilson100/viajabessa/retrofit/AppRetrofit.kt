package br.com.denilson100.viajabessa.retrofit

import br.com.denilson100.viajabessa.retrofit.service.TravelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder


private const val BASE_URL = "http://private-d689dd-denilsonmonteiro.apiary-mock.com/"

class AppRetrofit {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }

    private val retrofit by lazy {

        val gson = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val travelService: TravelService by lazy {
        retrofit.create(TravelService::class.java)
    }
}