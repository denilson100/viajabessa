package br.com.denilson100.viajabessa.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.denilson100.viajabessa.model.Travel
import br.com.denilson100.viajabessa.repository.Resource
import br.com.denilson100.viajabessa.repository.TravelRepository

class ListTravelsViewModel(
    private val repository: TravelRepository
) : ViewModel() {
    fun getAll() : LiveData<Resource<List<Travel>?>> {
        return repository.getAll()
    }

    fun saveInDatabase(travel: Travel) {
        repository.saveInDatabase(travel, { })
    }
}