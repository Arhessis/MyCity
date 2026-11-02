package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.model.Category
import com.example.mycity.model.LocalData
import com.example.mycity.model.Place
import com.example.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityTheme {
                val windowSize = calculateWindowSizeClass(this)
                MyCityApp(windowSize.widthSizeClass)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ma Ville") },
                navigationIcon = {
                    if (!uiState.isShowingCategoryList && !isExpandedScreen) {
                        IconButton(onClick = { viewModel.navigateBack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                        }
                    }
                },

            )
        }
    ) { innerPadding ->
        if (isExpandedScreen) {
            Row(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                CategoryListScreen(
                    categories = LocalData.categories,
                    onCategoryClick = { viewModel.selectCategory(it) },
                    modifier = Modifier.weight(1f)
                )
                val places = LocalData.places.filter { it.categoryId == uiState.currentCategory.id }
                PlaceListScreen(
                    places = places,
                    onPlaceClick = { viewModel.selectPlace(it) },
                    modifier = Modifier.weight(1f)
                )
                uiState.currentPlace?.let { place ->
                    PlaceDetailScreen(place = place, modifier = Modifier.weight(1f))
                }
            }
        } else {
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                when {
                    uiState.isShowingCategoryList -> {
                        CategoryListScreen(
                            categories = LocalData.categories,
                            onCategoryClick = { viewModel.selectCategory(it) }
                        )
                    }
                    uiState.isShowingPlaceList -> {
                        val places = LocalData.places.filter { it.categoryId == uiState.currentCategory.id }
                        PlaceListScreen(
                            places = places,
                            onPlaceClick = { viewModel.selectPlace(it) }
                        )
                    }
                    else -> {
                        uiState.currentPlace?.let { PlaceDetailScreen(place = it) }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryListScreen(categories: List<Category>, onCategoryClick: (Category) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCategoryClick(category) }
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Icon(category.icon, contentDescription = null, modifier = Modifier.padding(end = 16.dp))
                    Text(text = category.name, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@Composable
fun PlaceListScreen(places: List<Place>, onPlaceClick: (Place) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(places) { place ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onPlaceClick(place) }
            ) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun PlaceDetailScreen(place: Place, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = place.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = place.description, style = MaterialTheme.typography.bodyLarge)
    }
}