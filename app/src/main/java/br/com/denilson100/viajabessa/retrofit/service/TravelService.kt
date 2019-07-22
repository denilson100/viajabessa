package br.com.denilson100.viajabessa.retrofit.service

import br.com.denilson100.viajabessa.model.Travel
import retrofit2.Call
import retrofit2.http.GET

interface TravelService {

    @GET("travels")
    fun getAll(): Call<List<Travel>>
}