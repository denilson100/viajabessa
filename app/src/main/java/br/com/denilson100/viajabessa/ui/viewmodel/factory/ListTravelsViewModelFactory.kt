package br.com.denilson100.viajabessa.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.denilson100.viajabessa.repository.TravelRepository
import br.com.denilson100.viajabessa.ui.viewmodel.ListTravelsViewModel

class ListTravelsViewModelFactory(
    private val repository: TravelRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListTravelsViewModel(repository) as T
    }

}