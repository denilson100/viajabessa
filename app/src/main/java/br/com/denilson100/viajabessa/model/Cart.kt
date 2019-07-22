package br.com.denilson100.viajabessa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cart(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var destination: String = "",
    var price: Double = 0.0
)