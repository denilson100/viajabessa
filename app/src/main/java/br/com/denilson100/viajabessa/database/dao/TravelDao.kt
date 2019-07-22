package br.com.denilson100.viajabessa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.denilson100.viajabessa.model.Travel

@Dao
interface TravelDao {

    @Query("SELECT * FROM Travel")
    fun getAll(): LiveData<List<Travel>>

    @Insert(onConflict = REPLACE)
    fun save(travel: Travel)

    @Insert(onConflict = REPLACE)
    fun save(noticias: List<Travel>)

    @Query("SELECT * FROM Travel WHERE id = :id")
    fun getById(id: Int): LiveData<Travel?>

}