package com.example.proyecto_integrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(list: MutableList<Order_ticket>?) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

private var ListOrders : MutableList<Order_ticket>? = null

    init {
        ListOrders= list
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var Order : Order_ticket? = null
        var itemImage: ImageView
        var itemTitle: TextView
        var itemQuantity: TextView
       // var itemPrice: TextView
        var itemTotal: TextView
        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemQuantity = itemView.findViewById(R.id.item_quantity)
        //    itemPrice = itemView.findViewById(R.id.item)
            itemTotal = itemView.findViewById(R.id.item_total)

        }

        fun asignarDatos(data: Order_ticket) {

            itemImage.setImageResource(data.image)
            itemTitle.text = data.title
            itemQuantity.text = "Cant: "+data.quantity.toString()
            itemTotal.text = "$ "+data.Stotal


        }


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {



        viewHolder.asignarDatos(ListOrders!!.get(i))
    }

    override fun getItemCount(): Int {
        return ListOrders!!.size

    }
}