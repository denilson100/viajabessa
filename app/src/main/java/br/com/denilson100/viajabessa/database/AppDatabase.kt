package br.com.denilson100.viajabessa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.denilson100.viajabessa.database.dao.TravelDao
import br.com.denilson100.viajabessa.model.Travel

private const val DB = "Travel1.db"

@Database(entities = [Travel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val travelDao: TravelDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB).build()
        }
    }
}
