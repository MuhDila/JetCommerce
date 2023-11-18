package com.muhdila.jetcommerce.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.muhdila.jetcommerce.R

// Set of Material typography styles to start with
//val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)
//
//val fontName = GoogleFont("Maven Pro")
//
//val fontFamily = FontFamily(
//    Font(googleFont = fontName, fontProvider = provider),
//    androidx.compose.ui.text.font.Font(resId = R.font.mavenpro_regular)
//)

val myFont = FontFamily(
    Font(R.font.mavenpro_regular),
    Font(R.font.mavenpro_bold, FontWeight.Bold),
    Font(R.font.mavenpro_black, FontWeight.Black),
    Font(R.font.mavenpro_medium, FontWeight.Medium),
    Font(R.font.mavenpro_extrabold, FontWeight.ExtraBold),
    Font(R.font.mavenpro_semibold, FontWeight.SemiBold),
)

val myFont2 = FontFamily(
    Font(R.font.g_regular),
    Font(R.font.g_medium, FontWeight.Medium),
    Font(R.font.g_bold, FontWeight.Bold),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = myFont2,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)