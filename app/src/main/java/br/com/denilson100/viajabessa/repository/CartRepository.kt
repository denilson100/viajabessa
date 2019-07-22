package br.com.denilson100.viajabessa.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.denilson100.viajabessa.asynctask.BaseAsyncTask
import br.com.denilson100.viajabessa.database.dao.CartDao
import br.com.denilson100.viajabessa.model.Cart

class CartRepository(
    private val dao: CartDao
    ) {

    private val listCart = MutableLiveData<Resource<List<Cart>?>>()
    private val mediatorLiveData = MediatorLiveData<Resource<List<Cart>?>>()

    fun getAll(): LiveData<Resource<List<Cart>?>> {

        mediatorLiveData.addSource(getDatabase()) {
            mediatorLiveData.value = Resource(data = it)
        }
        return mediatorLiveData
    }

    private fun getDatabase() : LiveData<List<Cart>> {
        return dao.getAll()
    }

    fun saveInDatabase(
        cart: Cart,
        onSuccess: () -> Unit
    ) {
        BaseAsyncTask(didExecut = {
            dao.save(cart)
        }, didFinally = { it ->
            onSuccess()
        }).execute()
    }

    fun delete(
        cart: Cart,
        onSuccess: () -> Unit
    ) {
        BaseAsyncTask(didExecut = {
            dao.delete(cart)
        }, didFinally = {
            onSuccess()
        }).execute()
    }

}