package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kodeco.android.countryinfo.ui.components.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.components.CountryListViewModel
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Pass the service in to the CountryInfoScreen composable below.
        val viewModelFactory = viewModelFactory {
            initializer {
                CountryListViewModel(APIService.api)
            }
        }

        setContent {
            MyApplicationTheme {
                val viewModel: CountryListViewModel = viewModel(factory = viewModelFactory)
                CountryInfoScreen(viewModel = viewModel)
            }
        }
    }
}


