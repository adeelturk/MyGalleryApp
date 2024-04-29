package com.turk.mygalleryapp.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * The single source of truth for the current app state.
 *
 * This hosts all the state related information of the app for example
 * 1 ) Navigation State
 * 2 ) Logged In User if any
 * 3 ) Current Selected Language if we have multiple language support
 * 4 ) Current Country of access
 * or any other piece of information that would be needed app wide .
 *
 * */
class AppState(
  val snackbarHostState: SnackbarHostState,
  val navController: NavHostController,
  val coroutineScope: CoroutineScope,
) {
  val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination


  fun showSnackbar(message:String){

    coroutineScope.launch {
      snackbarHostState.showSnackbar(
        message = message,
      )
    }

  }

}

@Composable
fun rememberAppState(
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  navController: NavHostController = rememberNavController(),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
  remember(snackbarHostState, navController, coroutineScope) {
    AppState(snackbarHostState, navController, coroutineScope)
  }


