package data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.ui.theme.FakeStoreTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import networking.FakeStoreApi
import networking.RetroFitClient
import kotlinx.coroutines.flow.StateFlow


class ProductViewModel : ViewModel(){
    private val _products = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val products: StateFlow<Result<List<Product>>> = _products
    private val api = RetroFitClient.retroFit.create(FakeStoreApi::class.java)
    init {
        fetchProducts()
    }
    private fun fetchProducts(){
        viewModelScope.launch {
            try {
                _products.value = Result.Loading
                val data = api.getProducts()
                _products.value = Result.Success(data)
            } catch (e: Exception) {
                _products.value = Result.Error(e)
            }
        }
    }
}