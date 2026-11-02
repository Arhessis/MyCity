# My City - Adaptive Android App

An adaptive Android application built with Jetpack Compose that recommends places across multiple categories (Cafés, Restaurants, Parks) in a city. The application dynamically adjusts its layout to provide an optimized user experience across compact (mobile phones) and expanded (tablets, foldables, and desktop) screen sizes.

---

## ✨ Features

- **Adaptive Screen Layouts:** Dynamically switches between single-screen navigation on phone form factors and a multi-pane Master-Detail view on expanded screens using Material 3 `WindowSizeClass`.
- **Architectural Separation (MVVM):** Implements an architecture using `MyCityViewModel` and Kotlin `StateFlow` (`asStateFlow()`, `update`) to manage UI state, selections, and navigation logic.
- **Dynamic Content Navigation:** Filter places based on selected category and view full location descriptions with backward stack support.
- **Modern Material 3 UI:** Built entirely with declarative Jetpack Compose components (`Scaffold`, `TopAppBar`, `LazyColumn`, `Card`, and MaterialTheme styling).

---

## 📐 Adaptive UI Behavior

| Screen Size | WindowWidthSizeClass | Layout Experience |
| :--- | :--- | :--- |
| **Compact / Medium** (Phones, Portrait) | `Compact` / `Medium` | Sequential single-screen navigation (Category list ➔ Places list ➔ Place details) with top bar back navigation. |
| **Expanded** (Tablets, Foldables, Landscape) | `Expanded` | 3-pane Master-Detail layout displaying Categories, filtered Places, and selected Details side-by-side simultaneously. |

---

## 🛠️ Tech Stack & Architecture

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose (Material 3)
- **Architecture:** Model-View-ViewModel (MVVM) + Unidirectional Data Flow (UDF)
- **State Management:** `StateFlow`, `MutableStateFlow`, `collectAsState()`
- **Adaptive Layout API:** `androidx.compose.material3:material3-window-size-class`
- **Build System:** Gradle (Kotlin DSL `.kts`)

---

## 📂 Project Structure

```text
com.example.mycity/
│
├── model/
│   ├── Category.kt        # Data model for place categories
│   ├── Place.kt           # Data model for location items
│   └── LocalData.kt       # Local mock dataset (Cafes, Restaurants, Parks)
│
├── ui/
│   ├── theme/             # Material 3 colors, shapes, and typography
│   └── screens/           # CategoryListScreen, PlaceListScreen, PlaceDetailScreen
│
├── MyCityViewModel.kt     # ViewModel managing MyCityUiState and selection events
└── MainActivity.kt        # Main entry point calculating WindowWidthSizeClass and hosting Scaffold
