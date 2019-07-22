package br.com.denilson100.viajabessa.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.denilson100.viajabessa.R
import br.com.denilson100.viajabessa.model.Cart
import com.balysv.materialripple.MaterialRippleLayout
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(
    private val context: Context,
    private val carts: MutableList<Cart> = mutableListOf(),
    var onClick: (cart: Cart) -> Unit = {}
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            MaterialRippleLayout.on(inflater.inflate(R.layout.item_cart, parent, false))
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(-0xfbc02d)
                .rippleHover(true)
                .create()
        )
    }

    override fun getItemCount(): Int {
        return carts.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val cart = carts[position]
        holder.link(cart)
    }

    fun updateList(listCart: List<Cart>) {
        notifyItemRangeRemoved(0, this.carts.size)
        this.carts.clear()
        this.carts.addAll(listCart)
        notifyItemRangeInserted(0, this.carts.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var cart: Cart

        init {
            itemView.setOnClickListener {
                if (::cart.isInitialized) {
                    onClick(cart)
                }
            }
        }

        fun link(cart: Cart) {
            this.cart = cart
            itemView.item_cart.text = cart.destination + " - R$ ${cart.price}"
        }
    }
}