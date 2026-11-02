package com.example.mycity

import androidx.lifecycle.ViewModel
import com.example.mycity.model.Category
import com.example.mycity.model.LocalData
import com.example.mycity.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MyCityUiState(
    val currentCategory: Category = LocalData.categories.first(),
    val currentPlace: Place? = null,
    val isShowingCategoryList: Boolean = true,
    val isShowingPlaceList: Boolean = false
)

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun selectCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                currentCategory = category,
                isShowingCategoryList = false,
                isShowingPlaceList = true,
                currentPlace = null
            )
        }
    }

    fun selectPlace(place: Place) {
        _uiState.update { currentState ->
            currentState.copy(
                currentPlace = place,
                isShowingPlaceList = false
            )
        }
    }

    fun navigateBack() {
        _uiState.update { currentState ->
            if (!currentState.isShowingPlaceList && !currentState.isShowingCategoryList) {
                currentState.copy(isShowingPlaceList = true, currentPlace = null)
            } else {
                currentState.copy(isShowingCategoryList = true, isShowingPlaceList = false)
            }
        }
    }
}