package com.isaacdelosreyes.valorantforlogixs.utils

import androidx.compose.ui.text.intl.Locale

object LocaleUtil {

    private const val SPANISH = "es"

    private const val SPANISH_CODE = "es-ES"
    private const val ENGLISH_CODE = "en-US"

    fun getLanguageForWebServices() = if (Locale.current.language == SPANISH) {
        SPANISH_CODE

    } else {
        ENGLISH_CODE
    }
}