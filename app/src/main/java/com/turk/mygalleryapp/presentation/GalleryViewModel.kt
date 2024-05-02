package com.turk.mygalleryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turk.mygalleryapp.domain.model.GalleryData
import com.turk.mygalleryapp.domain.model.Media
import com.turk.mygalleryapp.domain.usecase.FetchGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val galleryUseCase: FetchGalleryUseCase) :ViewModel() {

    private val _galleryData = MutableStateFlow(GalleryData())
    val galleryData = _galleryData.asStateFlow()

    var resultsRecieved  = MutableStateFlow(false)

    var selectedIndex=-1

    fun getSelectedAlbumMediaFiles():ArrayList<Media> {

        return  if(selectedIndex<0){
            ArrayList()
        }else {
             galleryData.value.albumsList.get(selectedIndex).mediaList
        }
    }
    fun getAlbums(){

        viewModelScope.launch {

            galleryUseCase.invoke().collectLatest {
                resultsRecieved.value=true
                _galleryData.emit(it)
            }


        }
    }
}