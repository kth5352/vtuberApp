package com.example.smartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.smartapp.ui.theme.SmartappTheme

class MainActivity : ComponentActivity() {
    private val viewModel: VtuberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: VtuberViewModel) {
    val vtuberList by viewModel.vtuberList.observeAsState(emptyList())

    SmartappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            VtuberList(vtuberList)
        }
    }
}

@Composable
fun VtuberList(list: List<Vtuber>) {
    LazyColumn {
        items(list) { vtuber ->
            VtuberItem(vtuber)
        }
    }
}

@Composable
fun VtuberItem(vtuber: Vtuber) {
    Text("이름: ${vtuber.name}")
}


data class Vtuber(
    val vtuberId: Int = 0,
    val name: String,
    val vtuberDetail: String? = null,
    val vtuberLink: String? = null,
    val graduation: Int? = null,
    val imageLink: String? = null
)