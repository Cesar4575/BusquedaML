package com.cesarzorzon.busquedaproductosml.ui.viewModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cesarzorzon.busquedaproductosml.R
import com.cesarzorzon.busquedaproductosml.data.model.Result
import com.squareup.picasso.Picasso

class ProductViewHolder(view:View):RecyclerView.ViewHolder(view) {


    private val imagen = view.findViewById(R.id.ivProduct) as ImageView
    private val titulo = view.findViewById(R.id.tvTitulo) as TextView
    private val price = view.findViewById(R.id.tvprice) as TextView

    fun bind(item: Result, onClickListener:(Result) -> Unit) {
        price.text= "$"+ item.price
        titulo.text = item.title
        Picasso.get().load(item.images).into(imagen)
        imagen.setOnClickListener { onClickListener(item) }
    }
}













