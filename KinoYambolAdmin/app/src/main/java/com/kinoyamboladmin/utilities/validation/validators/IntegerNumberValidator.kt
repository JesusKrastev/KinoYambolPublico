package com.kinoyamboladmin.utilities.validation.validators

import android.util.Range
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class IntegerNumberValidator(
    val range: Range<Int> = Range(0, Int.MAX_VALUE),
    val error: UiText
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !texto.matches(Regex("^[+-]?[0-9]+$"))
                        ||
                        !range.contains(texto.toInt())
            override val errorMessage: UiText
                get() = error
        }
    }
}