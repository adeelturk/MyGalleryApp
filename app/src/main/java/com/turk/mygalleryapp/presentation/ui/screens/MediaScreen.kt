package com.turk.mygalleryapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.turk.mygalleryapp.R
import com.turk.mygalleryapp.domain.model.Media
import com.turk.mygalleryapp.presentation.GalleryViewModel
import com.turk.mygalleryapp.presentation.ui.theme.smallUnit


@Composable
fun MediaScreen(viewModel: GalleryViewModel,openMediaFile:(Media)->Unit){

    Box ( modifier = Modifier
        .fillMaxSize()
        .padding(smallUnit)
        .background(color = Color.Transparent),
        contentAlignment = Alignment.TopStart){
        MediaFilesGrid(viewModel.getSelectedAlbumMediaFiles(),openMediaFile)
    }

}

@Composable
fun MediaFilesGrid(list:List<Media>,openMediaFile:(Media)->Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
    ) {
        itemsIndexed(list) { index, item ->
            MediaFilesCellItem(item,openMediaFile)
        }
    }
}

@Composable
fun MediaFilesCellItem(data: Media,openMediaFile:(Media)->Unit){
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.uri)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .placeholderMemoryCacheKey(data.toString())
            .scale(Scale.FIT)
            .build(),
        contentScale = ContentScale.FillBounds
    )
    Box (Modifier.padding(5.dp)) {
        Image(
            modifier = Modifier
                .size(98.dp)
                .clip(Shapes().medium).clickable {
                    openMediaFile(data)
                },
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription ="thumbnail of album ${data.label}"
        )

        if(data.isVideo) {

            Image(painter = painterResource(id = R.drawable.play),
                contentDescription = "play button",
                modifier = Modifier.size(30.dp).align(Alignment.Center)

            )
        }

    }

}
