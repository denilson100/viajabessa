package br.com.denilson100.viajabessa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Travel(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var image: String = ""
) : Serializable
