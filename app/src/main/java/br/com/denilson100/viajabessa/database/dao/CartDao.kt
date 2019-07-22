package br.com.denilson100.viajabessa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.denilson100.viajabessa.model.Cart

@Dao
interface CartDao {

    @Query("SELECT * FROM Cart")
    fun getAll(): LiveData<List<Cart>>

    @Insert(onConflict = REPLACE)
    fun save(cart: Cart)

    @Query("SELECT * FROM Cart WHERE id = :id")
    fun getById(id: Int): LiveData<Cart?>

    @Delete
    fun delete(cart: Cart)
}