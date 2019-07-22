package br.com.denilson100.viajabessa.ui.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.denilson100.viajabessa.R
import br.com.denilson100.viajabessa.database.AppDatabaseCart
import br.com.denilson100.viajabessa.model.Cart
import br.com.denilson100.viajabessa.model.Travel
import br.com.denilson100.viajabessa.repository.CartRepository
import br.com.denilson100.viajabessa.ui.viewmodel.CartViewModel
import br.com.denilson100.viajabessa.ui.viewmodel.factory.CartViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_travel.*
import kotlinx.android.synthetic.main.content_detail_travel.*

class DetailTravelActivity : AppCompatActivity() {

    private val viewModelCart by lazy {
        val repository = CartRepository(AppDatabaseCart.getInstance(this).cartDao)
        val factory = CartViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider.get(CartViewModel::class.java)
    }

    private var travel: Travel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_travel)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->

            if (travel != null) {
                val item = Cart(0, travel!!.title, travel!!.price)
                saveItemInCart(item)
                Snackbar.make(view, "Pacote para " + travel!!.title + " adicionado com sucesso!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                Snackbar.make(view, "Ops! Item não adicionado.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        travel = intent.extras.getSerializable("travelSelected") as? Travel
        toolbar_layout.title = travel?.title
        activity_detail_price.text = "Pacote R$ " + travel?.price
        activity_detail_description.text = travel?.description
        val imageView = activity_detail_image
        Picasso.get().load(travel?.image).into(imageView)
    }

    private fun saveItemInCart(item: Cart) {
        viewModelCart.saveInDatabase(item)
    }
}
