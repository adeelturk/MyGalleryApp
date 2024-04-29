package com.turk.mygalleryapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


/**
 * The single source of truth for all Text Composables in the system.
 * There will be a number of public interfaces for the main composable below
 * like [LargeHeading] , [MediumHeading], [SmallHeading],[LargeTitle] , [MediumTitle], [SmallTitle] etc
 * */
@Composable
fun MyGalleryText(
  modifier: Modifier = Modifier,
  maxLines: Int = Int.MAX_VALUE,
  fontSize: FontSize = FontSize.Medium,
  fontFamily: FontFamily = FontFamily.Body,
  textAlign: TextAlign? = null,
  fontWeight: FontWeight? = null,
  text: String,
  color: Color = Color.Unspecified,
  isTextWithShadow:Boolean=false
) {

  val baseStyle = getFontStyle(fontSize = fontSize, fontFamily = fontFamily)
  val textStyle = baseStyle.copy(fontWeight = fontWeight).takeIf { fontWeight != null } ?: baseStyle
  val shadowTextStyle = textStyle.copy(shadow = Shadow(color=Color.Black,offset= Offset(3f,3f), blurRadius = 2f))

  Text(
    modifier = modifier,
    style = if(isTextWithShadow)shadowTextStyle else textStyle,
    text = text,
    color = color,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign
  )
}

@Composable
fun MyGalleryTextAnnotatedString(
  modifier: Modifier = Modifier,
  maxLines: Int = Int.MAX_VALUE,
  fontSize: FontSize = FontSize.Medium,
  fontFamily: FontFamily = FontFamily.Body,
  textAlign: TextAlign? = null,
  fontWeight: FontWeight? = null,
  annotatedString: AnnotatedString,
  color: Color = Color.Unspecified,
) {

  val baseStyle = getFontStyle(fontSize = fontSize, fontFamily = fontFamily)
  val textStyle = baseStyle.copy(fontWeight = fontWeight).takeIf { fontWeight != null } ?: baseStyle
  Text(
    modifier = modifier,
    style = textStyle,
    text = annotatedString,
    color = color,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign
  )
}

@Composable
private fun getFontStyle(fontSize: FontSize, fontFamily: FontFamily): TextStyle {
  return when (fontSize) {
    FontSize.XLarge -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineLarge.copy(fontSize = 40.sp)
        FontFamily.Title -> MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp)
        FontFamily.Body -> MaterialTheme.typography.bodyLarge.copy(fontSize = 40.sp)
        FontFamily.Label -> MaterialTheme.typography.labelLarge.copy(fontSize = 40.sp)
      }
    }

    FontSize.Large -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineLarge
        FontFamily.Title -> MaterialTheme.typography.titleLarge
        FontFamily.Body -> MaterialTheme.typography.bodyLarge
        FontFamily.Label -> MaterialTheme.typography.labelLarge
      }
    }

    FontSize.Medium -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineMedium
        FontFamily.Title -> MaterialTheme.typography.titleMedium
        FontFamily.Body -> MaterialTheme.typography.bodyMedium
        FontFamily.Label -> MaterialTheme.typography.labelMedium
      }
    }

    FontSize.Small -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineSmall
        FontFamily.Title -> MaterialTheme.typography.titleSmall
        FontFamily.Body -> MaterialTheme.typography.bodySmall
        FontFamily.Label -> MaterialTheme.typography.labelSmall
      }
    }


  }
}

enum class FontFamily {
  Headline,
  Title,
  Body,
  Label,
}


enum class FontSize {
  XLarge,
  Large,
  Medium,
  Small
}

@Composable
fun LargeHeading(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified) {
  MyGalleryText(
    modifier = modifier,
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Headline,
    fontWeight = FontWeight.W400,
    text = text,
    color = color
  )
}

@Composable
fun MediumHeading(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified) {
  MyGalleryText(
    modifier = modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Headline,
    text = text,
    color = color
  )
}

@Composable
fun SmallHeading(modifier: Modifier = Modifier, textAlign: TextAlign? = null,text: String, color: Color = Color.Unspecified) {
  MyGalleryText(
    modifier=modifier,
    fontSize = FontSize.Small,
    fontFamily = FontFamily.Headline,
    text = text,
    color = color,
    textAlign=textAlign
  )
}

@Composable
fun LargeTitle(
  modifier: Modifier = Modifier,
  textAlign: TextAlign? = null,
  text: String,
  fontFamily: FontFamily = FontFamily.Title,
  color: Color = Color.Unspecified,
  isTextWithShadow:Boolean=false
) {
  MyGalleryText(
    modifier = modifier,
    textAlign = textAlign,
    fontSize = FontSize.Large,
    fontFamily = fontFamily,
    text = text,
    color = color,
    isTextWithShadow=isTextWithShadow
  )
}

@Composable
fun MediumTitle(text: String, fontWeight: FontWeight? = null, color: Color = Color.Unspecified,modifier: Modifier=Modifier,isTextWithShadow:Boolean=false) {
  MyGalleryText(
    modifier=modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Title,
    fontWeight = fontWeight,
    text = text,
    color = color,
    isTextWithShadow=isTextWithShadow
  )
}

@Composable
fun SmallTitle(
  modifier: Modifier = Modifier,
  textAlign: TextAlign? = null,
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  color: Color = Color.Unspecified,
  isTextWithShadow:Boolean=false
) {
  MyGalleryText(
    modifier = modifier,
    fontSize = FontSize.Small,
    maxLines = maxLines,
    fontFamily = FontFamily.Title,
    textAlign = textAlign,
    text = text,
    color = color,
    isTextWithShadow=isTextWithShadow
  )
}

@Composable
fun SmallTitleBold(
  modifier: Modifier = Modifier,
  textAlign: TextAlign? = null,
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  color: Color = Color.Unspecified,
  isTextWithShadow:Boolean=false
) {
  MyGalleryText(
    modifier = modifier,
    fontSize = FontSize.Small,
    maxLines = maxLines,
    fontFamily = FontFamily.Title,
    textAlign = textAlign,
    text = text,
    color = color,
    isTextWithShadow=isTextWithShadow,
    fontWeight = FontWeight.Bold
  )
}


@Composable
fun LargeBody(text: String, color: Color = Color.Unspecified,isTextWithShadow:Boolean=false) {
  MyGalleryText(
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Body,
    text = text,
    color = color,
    isTextWithShadow=isTextWithShadow
  )
}

@Composable
fun MediumBody(
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null,
  isTextWithShadow:Boolean=false
) {
  MyGalleryText(
    fontSize = FontSize.Medium,
    maxLines = maxLines,
    fontFamily = FontFamily.Body,
    textAlign = textAlign,
    text = text,
    color = color,
    isTextWithShadow = isTextWithShadow
  )
}

@Composable
fun SmallBody(
  modifier: Modifier = Modifier,
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  fontWeight: FontWeight? = null,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null
) {
  MyGalleryText(
    modifier=modifier,
    fontSize = FontSize.Small,
    fontWeight = fontWeight,
    fontFamily = FontFamily.Body,
    textAlign = textAlign,
    text = text,
    color = color,
    maxLines = maxLines,
  )
}

@Composable
fun SmallAnnotatedBody(
  modifier: Modifier = Modifier,
  text: AnnotatedString,
  maxLines: Int = Int.MAX_VALUE,
  fontWeight: FontWeight? = null,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null
) {
  MyGalleryTextAnnotatedString(
    fontSize = FontSize.Small,
    fontWeight = fontWeight,
    fontFamily = FontFamily.Body,
    textAlign = textAlign,
    annotatedString = text,
    color = color,
    maxLines = maxLines,
  )
}

