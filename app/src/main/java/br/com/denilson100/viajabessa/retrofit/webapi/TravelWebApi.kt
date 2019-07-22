package br.com.denilson100.viajabessa.retrofit.webapi

import br.com.denilson100.viajabessa.model.Travel
import br.com.denilson100.viajabessa.retrofit.AppRetrofit
import br.com.denilson100.viajabessa.retrofit.service.TravelService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAIL_REQUEST = "Ocorreu um erro. Tente novamente mais tarde."

class TravelWebApi(
    private val service: TravelService = AppRetrofit().travelService
) {
    private fun <T> executaRequisicao(
        call: Call<T>,
        onSuccess: (getTravels: T?) -> Unit,
        onFail: (erro: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onFail(FAIL_REQUEST)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onFail(t.message)
            }
        })
    }

    fun getAll(
        onSuccess: (getTravels: List<Travel>?) -> Unit,
        onFail: (erro: String?) -> Unit
    ) {
        executaRequisicao(
            service.getAll(),
            onSuccess,
            onFail
        )
    }
}