package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import data.Product
import data.ProductViewModel
import data.Result

@Composable
fun DetailScreen(
    productId: Int,
    vm: ProductViewModel,
    onBack: (() -> Unit)? = null
) {
    val state by vm.products.collectAsState()
    val product = (state as? Result.Success<List<Product>>)?.data?.find { it.id == productId }
    if (product == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text("Product not found", color = Color.Red)
        }
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        if (onBack != null) {
            Button(onClick = onBack) {
                Text("Back")
            }
            Spacer(Modifier.height(16.dp))
        }
        AsyncImage(
            model = product.images.firstOrNull(),
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            product.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "$${product.price}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(Modifier.height(12.dp))
        Text(
            product.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}