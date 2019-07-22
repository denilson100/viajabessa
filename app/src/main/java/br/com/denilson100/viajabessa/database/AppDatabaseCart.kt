package br.com.denilson100.viajabessa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.denilson100.viajabessa.database.dao.CartDao
import br.com.denilson100.viajabessa.model.Cart

private const val DB = "Cart.db"

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class AppDatabaseCart : RoomDatabase() {
    abstract val cartDao: CartDao

    companion object {
        private lateinit var db: AppDatabaseCart
        fun getInstance(context: Context): AppDatabaseCart {
            if (::db.isInitialized) return db
            db = Room.databaseBuilder(
                context,
                AppDatabaseCart::class.java,
                DB).build()
            return db
        }
    }
}
