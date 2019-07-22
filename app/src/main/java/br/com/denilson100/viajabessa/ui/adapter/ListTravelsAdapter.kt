package br.com.denilson100.viajabessa.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.denilson100.viajabessa.R
import br.com.denilson100.viajabessa.model.Travel
import com.balysv.materialripple.MaterialRippleLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_travel.view.*

class ListTravelsAdapter(
    private val context: Context,
    private val travels: MutableList<Travel> = mutableListOf(),
    var onClick: (travel: Travel) -> Unit = {}
) : RecyclerView.Adapter<ListTravelsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTravelsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            MaterialRippleLayout.on(inflater.inflate(R.layout.item_travel, parent, false))
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(-0xa7a7a8)
                .rippleHover(true)
                .create()
        )
    }

    override fun getItemCount(): Int {
        return travels.size
    }

    override fun onBindViewHolder(holder: ListTravelsAdapter.ViewHolder, position: Int) {
        val travel = travels[position]
        holder.link(travel)
    }

    fun updateList(listTravels: List<Travel>) {
        notifyItemRangeRemoved(0, this.travels.size)
        this.travels.clear()
        this.travels.addAll(listTravels)
        notifyItemRangeInserted(0, this.travels.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var travel: Travel

        init {
            itemView.setOnClickListener {
                if (::travel.isInitialized) {
                    onClick(travel)
                }
            }
        }

        fun link(travel: Travel) {
            this.travel = travel
            itemView.item_travel_title.text = travel.title
            itemView.item_travel_description.text = travel.description
            itemView.item_travel_price.text = "R$ ${travel.price}"
            val progressBar = itemView.progress
            val imageUrl = travel.image

            Picasso.get()
                .load(imageUrl)
                .into(itemView.item_travel_image, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                    }
                })
        }
    }
}