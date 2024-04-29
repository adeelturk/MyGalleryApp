package com.turk.mygalleryapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object MyGalleryAppTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun MyGalleryAppTheme(
    colors: CustomColors = MyGalleryAppTheme.colors,
    typography: CustomTypography = MyGalleryAppTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography
    ) {
        content()
    }
}