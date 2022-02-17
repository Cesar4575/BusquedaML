package com.cesarzorzon.busquedaproductosml.ui.viewModel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cesarzorzon.busquedaproductosml.data.model.Result
import com.cesarzorzon.busquedaproductosml.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val binding = ItemProductBinding.bind(view)

    fun cargarDatos(item: Result, onClickListener:(Result) -> Unit) {

        binding.tvprice.text= "$ "+item.price.toBigDecimal()
        binding.tvTitulo.text = item.title
        Picasso.get().load(item.images).into(binding.ivProduct)
        binding.ivProduct.setOnClickListener { onClickListener(item) }
    }
}













