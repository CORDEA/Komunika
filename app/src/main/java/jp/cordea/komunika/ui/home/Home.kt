package jp.cordea.komunika.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import jp.cordea.komunika.ui.theme.KomunikaTheme

@Composable
fun Home(viewModel: HomeViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Komunika") }) }
    ) {
        HomeContent(viewModel)
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel) {
    val items by viewModel.items.observeAsState(emptyList())
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.map { item { HomeItem(viewModel = it) } }
    }
}

@Composable
private fun HomeItem(viewModel: HomeItemViewModel) {
    Card(Modifier.height(320.dp)) {
        Box {
            Image(
                painter = rememberImagePainter(data = viewModel.thumbnailUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text(viewModel.title)
                Spacer(Modifier.height(8.dp))
                Text(viewModel.body)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeItem() {
    KomunikaTheme {
        HomeItem(
            HomeItemViewModel(
                id = 0L,
                title = "title",
                body = "body",
                thumbnailUrl = ""
            )
        )
    }
}
