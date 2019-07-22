package br.com.denilson100.viajabessa.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.denilson100.viajabessa.model.Cart
import br.com.denilson100.viajabessa.repository.CartRepository
import br.com.denilson100.viajabessa.repository.Resource

class CartViewModel(
    private val repository: CartRepository
) : ViewModel() {
    fun getAll() : LiveData<Resource<List<Cart>?>> {
        return repository.getAll()
    }

    fun saveInDatabase(cart: Cart) {
        repository.saveInDatabase(cart, { })
    }

    fun delete(cart: Cart) {
        repository.delete(cart, {})
    }
}