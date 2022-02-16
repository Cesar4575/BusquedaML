package com.cesarzorzon.busquedaproductosml.ui.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cesarzorzon.busquedaproductosml.R
import com.cesarzorzon.busquedaproductosml.data.model.Result


class  ProductAdapter(private val Resultado: List<Result>, private val  onClickListener:(Result) -> Unit):RecyclerView.Adapter<ProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = Resultado[position]
        holder.bind(item, onClickListener)
    }
    override fun getItemCount(): Int = Resultado.size

}







