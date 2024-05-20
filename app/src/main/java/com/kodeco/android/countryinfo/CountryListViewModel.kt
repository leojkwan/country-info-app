package com.kodeco.android.countryinfo.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.APIService
import com.kodeco.android.countryinfo.APIServiceType
import com.kodeco.android.countryinfo.models.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryListViewModel(private val apiService: APIServiceType) : ViewModel() {
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getAllCountries() {
        viewModelScope.launch {
            try {
                val response = apiService.getAllCountries()
                if (response.isSuccessful) {
                    _countries.value = response.body() ?: emptyList()
                    _errorMessage.value = null
                } else {
                    println("Failed to fetch countries")
                    _errorMessage.value = "Failed to fetch countries"
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
