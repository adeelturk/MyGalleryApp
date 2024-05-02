package com.turk.mygalleryapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.turk.mygalleryapp.R
import com.turk.mygalleryapp.domain.model.Album
import com.turk.mygalleryapp.presentation.GalleryViewModel
import com.turk.mygalleryapp.presentation.ui.theme.SmallBody
import com.turk.mygalleryapp.presentation.ui.theme.SmallHeading
import com.turk.mygalleryapp.presentation.ui.theme.SmallTitleBold
import com.turk.mygalleryapp.presentation.ui.theme.smallUnit


@Composable
fun GalleryScreen(viewModel:GalleryViewModel,viewMediaGallery:()->Unit){


    val galleryDataState= viewModel.galleryData.collectAsState()
    val showLoading= viewModel.resultsRecieved.collectAsState()
    viewModel.getAlbums()
    Box ( modifier = Modifier
        .fillMaxSize()
        .padding(smallUnit)
        .background(color = Color.Transparent),
        contentAlignment = Alignment.TopStart){

        if(!showLoading.value) {
            SmallHeading(
                text = stringResource(id = R.string.loading),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        GalleryGrid(galleryDataState.value.albumsList){
            viewModel.selectedIndex=it
            viewMediaGallery()
        }


    }

}

@Composable
fun GalleryGrid(list:List<Album>,viewMediaGallery:(Int)->Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        ) {
        itemsIndexed(list) { index, item ->
            GalleryCellItem(item){
                viewMediaGallery(index)
            }
        }
    }
}

@Composable
fun GalleryCellItem(data: Album,viewMediaGallery:()->Unit){

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.uri)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .placeholderMemoryCacheKey(data.toString())
            .scale(Scale.FIT)
            .build(),
        contentScale = ContentScale.FillBounds
    )

    Column (
        Modifier
            .padding(5.dp)
            .clickable {
                viewMediaGallery()
            }) {
        Image(
            modifier = Modifier
                .size(98.dp)
                .clip(Shapes().medium),
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription ="thumbnail of album ${data.label}"
        )

        SmallTitleBold(text = data.label, color = Color.White)
        SmallBody(text = "${data.mediaCount}", color = Color.Gray)
    }

}


@Preview
@Composable
fun ShowGallleryScreen(){

    GalleryScreen(hiltViewModel()){}
}