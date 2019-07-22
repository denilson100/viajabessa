package br.com.denilson100.viajabessa.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.denilson100.viajabessa.R
import br.com.denilson100.viajabessa.database.AppDatabase
import br.com.denilson100.viajabessa.database.AppDatabaseCart
import br.com.denilson100.viajabessa.model.Cart
import br.com.denilson100.viajabessa.model.Travel
import br.com.denilson100.viajabessa.repository.CartRepository
import br.com.denilson100.viajabessa.repository.TravelRepository
import br.com.denilson100.viajabessa.ui.adapter.CartAdapter
import br.com.denilson100.viajabessa.ui.adapter.ListTravelsAdapter
import br.com.denilson100.viajabessa.ui.animation.Animations
import br.com.denilson100.viajabessa.ui.viewmodel.CartViewModel
import br.com.denilson100.viajabessa.ui.viewmodel.ListTravelsViewModel
import br.com.denilson100.viajabessa.ui.viewmodel.factory.CartViewModelFactory
import br.com.denilson100.viajabessa.ui.viewmodel.factory.ListTravelsViewModelFactory
import kotlinx.android.synthetic.main.activity_list_travels.*

private const val TITULO_APPBAR = "Pacotes Viajabessa"

class ListTravelsActivity : AppCompatActivity() {

    private val adapter by lazy {
        ListTravelsAdapter(context = this)
    }

    private val adapterCart by lazy {
        CartAdapter(context = this)
    }

    private val viewModel by lazy {
        val repository = TravelRepository(AppDatabase.getInstance(this).travelDao)
        val factory = ListTravelsViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider.get(ListTravelsViewModel::class.java)
    }

    private val viewModelCart by lazy {
        val repository = CartRepository(AppDatabaseCart.getInstance(this).cartDao)
        val factory = CartViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider.get(CartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_travels)
        toolbar.title = TITULO_APPBAR
        configureRecyclerView()

        activity_list_travels_cart.setOnClickListener { view ->
            Animations().showCircularAnimationRigthTop(this, activity_list_travels_content_cart, main_view.id)
        }

        activity_list_travels_content_cart_close.setOnClickListener { view ->
            Animations().showCircularAnimationRigthTop(this, activity_list_travels_content_cart, main_view.id)
        }
    }

    private fun configureRecyclerView() {
        val divisor = DividerItemDecoration(this, VERTICAL)

        // pacotes
        activity_list_travels_recyclerview.addItemDecoration(divisor)
        activity_list_travels_recyclerview.adapter = adapter

        // itens carrinho
        activity_list_itens_cart_recyclerview.addItemDecoration(divisor)
        activity_list_itens_cart_recyclerview.adapter = adapterCart

        configuraAdapter()
        getItensCart()
        getTravles()
    }

    private fun getTravles() {
        viewModel.getAll().observe(this, Observer { resource ->
            resource.data?.let {
                adapter.updateList(it)
                progress.visibility = View.GONE
            }
            resource.error?.let {
                showToast("Ops! Tente novamente mais tarde.")
                progress.visibility = View.GONE
            }
        })
    }

    private fun getItensCart() {
        viewModelCart.getAll().observe(this, Observer { resource ->
            resource.data?.let {
                adapterCart.updateList(it)
            }
            resource.error?.let {
                showToast("Ops! Tente novamente mais tarde.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()    }

    private fun configuraAdapter() {
        adapter.onClick = this::openViewTravel
        adapterCart.onClick = this::deleteItemCart
    }

    fun openViewTravel(it: Travel) {
        val intent = Intent(this, DetailTravelActivity::class.java)
        intent.putExtra("travelSelected", it)
        startActivity(intent)
    }

    fun deleteItemCart(item: Cart) {
        viewModelCart.delete(cart = item)
        showToast("Pacote para " + item.destination + " apagado com sucesso!")
    }

    override fun onBackPressed() {
        if (activity_list_travels_content_cart.visibility == View.VISIBLE) {
            Animations().showCircularAnimationRigthTop(this, activity_list_travels_content_cart, main_view.id)
            return
        }
        finish()
    }

}


