package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.countryinfo.models.Country

@Composable
fun CountryInfoScreen(viewModel: CountryListViewModel = viewModel()) {
    val countries = viewModel.countries.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllCountries()
    }

    Column {
        when {
            errorMessage.value != null -> {
                Text("Error: ${errorMessage.value}")
            }
            else -> {
                LazyColumn {
                    items(countries.value) { country ->
                        CountryRow(country)
                    }
                }
            }
        }
    }
}

@Composable
fun CountryRow(country: Country) {
    Column {
        Text(text = country.commonName)
//        Text(text = country.capital?.joinToString() ?: "No capital")
//        Text(text = "Population: ${country.population}")
        // Add more details if needed
    }
}

@Preview
@Composable
fun CountryInfoScreenPreview() {
    CountryInfoScreen()
}
