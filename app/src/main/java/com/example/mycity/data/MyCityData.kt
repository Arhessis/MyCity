package com.example.mycity.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(val id: Int, val name: String, val icon: ImageVector)
data class Place(val id: Int, val name: String, val description: String, val categoryId: Int)

object LocalData {
    val categories = listOf(
        Category(1, "Cafés", Icons.Default.LocalCafe),
        Category(2, "Restaurants", Icons.Default.Restaurant),
        Category(3, "Parcs", Icons.Default.Park)
    )

    val places = listOf(
        Place(1, "Le Petit Rétro", "Un café parisien authentique avec une terrasse agréable.", 1),
        Place(2, "Café de la Gare", "Idéal pour un café rapide avant de prendre le train.", 1),
        Place(3, "Bistrot des Amis", "Cuisine locale et ambiance chaleureuse.", 2),
        Place(4, "Parc Monceau", "Grand parc fleuri parfait pour une promenade en famille.", 3)
    )
}