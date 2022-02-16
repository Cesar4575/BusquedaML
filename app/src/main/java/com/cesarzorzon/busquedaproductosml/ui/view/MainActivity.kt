package com.cesarzorzon.busquedaproductosml.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesarzorzon.busquedaproductosml.data.model.Result
import com.cesarzorzon.busquedaproductosml.retrofit.RetrofitHelper
import com.cesarzorzon.busquedaproductosml.data.network.APIService
import com.cesarzorzon.busquedaproductosml.databinding.ActivityMainBinding
import com.cesarzorzon.busquedaproductosml.ui.viewModel.ProductAdapter
import com.cesarzorzon.busquedaproductosml.ui.viewModel.ResultadoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val retrofit = RetrofitHelper.getRetrofit()
    private lateinit var resulted: ResultadoViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.svProductos.setOnQueryTextListener(this)
        initRecyclerView()

   }
    private fun initRecyclerView() {
        resulted =ViewModelProvider(this).get(ResultadoViewModel::class.java)
       adapter = ProductAdapter(resulted.resultado) { resulted -> onItemSelected(resulted) }
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
       binding.rvProduct.adapter = adapter

    }

    private fun onItemSelected(result: Result){
        Toast.makeText(this, result.title, Toast.LENGTH_LONG).show()
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("titulo", result.title)
        intent.putExtra("imagen", result.images)
        intent.putExtra("id", result.id)
        startActivity(intent)
    }

    private fun buscarProducto(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = retrofit.create(APIService::class.java).getProducts(query)
            this.coroutineContext
            val productos = call.body()
            this@MainActivity.runOnUiThread {
                if (call.isSuccessful) {
                    val imagen = productos?.resulted?: emptyList()
                    resulted.resultado.clear()
                    resulted.resultado.addAll(imagen)
                    adapter.notifyDataSetChanged()

                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }
    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.ViewContainer.windowToken, 0)
        }
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            buscarProducto(query.lowercase())
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}