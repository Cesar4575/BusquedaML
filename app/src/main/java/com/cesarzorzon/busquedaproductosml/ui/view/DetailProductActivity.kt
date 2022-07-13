package com.cesarzorzon.busquedaproductosml.ui.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cesarzorzon.busquedaproductosml.retrofit.RetrofitHelper
import com.cesarzorzon.busquedaproductosml.data.network.APIService
import com.cesarzorzon.busquedaproductosml.databinding.ActivityDetailProductBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val retrofit = RetrofitHelper.getRetrofit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title: String? = intent.getStringExtra("titulo")
        val image: String? = intent.getStringExtra("imagen")
        val id: String? = intent.getStringExtra("id")

        binding.tvDescripcion.movementMethod = ScrollingMovementMethod()
        binding.tvTitulo.text = title
        Picasso.get().load(image).into(binding.ivProduct)
        if (id != null) {
            description(id)
        }else{
            showError()
        }
    }

    private fun description(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = retrofit.create(APIService::class.java).getDescription("items/$id/description")
            val mensaje = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val descripcion = mensaje?.description
                    binding.tvDescripcion.text=descripcion
                }else{
                    showError()
                }
            }
        }
    }
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}
