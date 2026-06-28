package com.example.ytrecipeapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val CustomDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB787),
    onPrimary = Color(0xFF512400),
    primaryContainer = Color(0xFF6B3500),
    onPrimaryContainer = Color(0xFFFFDCC2),

    secondary = Color(0xFFE0C0A8),
    onSecondary = Color(0xFF3F2C1B),
    secondaryContainer = Color(0xFF5A4330),
    onSecondaryContainer = Color(0xFFFCDCC4),

    tertiary = Color(0xFFC2CC8F),
    onTertiary = Color(0xFF2D3411),
    tertiaryContainer = Color(0xFF434B25),
    onTertiaryContainer = Color(0xFFDEE9AA),

    background = Color(0xFF19120D),
    onBackground = Color(0xFFF0DFD7),

    surface = Color(0xFF19120D),
    onSurface = Color(0xFFF0DFD7),
    surfaceVariant = Color(0xFF4F4339),
    onSurfaceVariant = Color(0xFFD3C3B7),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF9C8D80),
    outlineVariant = Color(0xFF4F4339)
)

private val CustomLightColorScheme = lightColorScheme(
    primary = Color(0xFF8B4F24),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDCC2),
    onPrimaryContainer = Color(0xFF2E1500),

    secondary = Color(0xFF735943),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFCDCC4),
    onSecondaryContainer = Color(0xFF2A1707),

    tertiary = Color(0xFF59643A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFDEE9AA),
    onTertiaryContainer = Color(0xFF171E03),

    background = Color(0xFFFFF8F5),
    onBackground = Color(0xFF221A15),

    surface = Color(0xFFFFF8F5),
    onSurface = Color(0xFF221A15),
    surfaceVariant = Color(0xFFF1DFD3),
    onSurfaceVariant = Color(0xFF504338),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF837469),
    outlineVariant = Color(0xFFD3C3B7)
)

@Composable
fun YTRecipeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> CustomDarkColorScheme
        else -> CustomLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}