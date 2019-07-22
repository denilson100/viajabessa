package br.com.denilson100.viajabessa.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.denilson100.viajabessa.asynctask.BaseAsyncTask
import br.com.denilson100.viajabessa.database.dao.TravelDao
import br.com.denilson100.viajabessa.model.Travel
import br.com.denilson100.viajabessa.retrofit.webapi.TravelWebApi

class TravelRepository(
    private val dao: TravelDao,
    private val webApi: TravelWebApi = TravelWebApi()
    ) {

    private val mediatorLiveData = MediatorLiveData<Resource<List<Travel>?>>()

    fun getAll(): LiveData<Resource<List<Travel>?>> {
        mediatorLiveData.addSource(getDatabase()) {
            mediatorLiveData.value = Resource(data = it)
        }

        val failsWebApiLiveData = MutableLiveData<Resource<List<Travel>?>>()
        mediatorLiveData.addSource(failsWebApiLiveData) {
            val resource = mediatorLiveData.value
            if (resource != null) {
                Resource(data = resource.data, error = it.error)
            } else {
                Resource(data = null, error = it.error)
            }
        }

        getWebApi(
            onFail = { error ->
                failsWebApiLiveData.value = Resource(data = null, error = error)
            })

        return mediatorLiveData
    }

    private fun getDatabase() : LiveData<List<Travel>> {
        return dao.getAll()
    }

    fun saveInDatabase(
        travel: Travel,
        onSuccess: () -> Unit
    ) {
        BaseAsyncTask(didExecut = {
            dao.save(travel)
        }, didFinally = { it ->
            onSuccess()
        }).execute()
    }

    fun saveInDatabase(
        travels: List<Travel>
    ) {
        BaseAsyncTask(didExecut = {
            dao.save(travels)
        }, didFinally = {}
        ).execute()
    }

    // Web API
    private fun getWebApi(
        onFail: (error: String?) -> Unit
    ) {
        webApi.getAll(
            onSuccess = { travelsNews ->
                travelsNews?.let {
                    saveInDatabase(travelsNews) }
            }, onFail = onFail
        )
    }
}