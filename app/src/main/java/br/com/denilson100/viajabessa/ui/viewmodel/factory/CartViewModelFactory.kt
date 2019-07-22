package br.com.denilson100.viajabessa.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.denilson100.viajabessa.repository.CartRepository
import br.com.denilson100.viajabessa.ui.viewmodel.CartViewModel

class CartViewModelFactory(
    private val repository: CartRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(repository) as T
    }

}