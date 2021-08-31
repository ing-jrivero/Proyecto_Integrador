package com.example.proyecto_integrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

private val ListaOrdenes : MutableList<Order_ticket>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView
        var itemTitle: TextView
        var itemQuantity: TextView
        var itemTotal: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemQuantity = itemView.findViewById(R.id.item_quantity)
            itemTotal = itemView.findViewById(R.id.item_total)
        }

    }
    val title = arrayOf("Hamburguesa","Papas Fritas","Ensalada","Cafe")
    val quantity = arrayOf("8","9","3","8")
    val images = arrayOf(R.drawable.chipotleking,R.drawable.bk_papas_supremas_atumanera,R.drawable.bk_web_ensalada_cranberry,R.drawable.cafe_dunkin)
    val totales = arrayOf("800","23","3","2223")


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = title[i]
        viewHolder.itemQuantity.text = "Cantidad: "+quantity[i]
        viewHolder.itemTotal.text = "SubTotal: $" +totales[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return title.size

    }
}