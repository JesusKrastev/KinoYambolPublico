package com.kinoyamboladmin.ui.composables

import android.telephony.PhoneNumberUtils
import android.util.Log
import android.util.Range
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.kinoyamboladmin.utilities.validation.CompositeValidation
import com.kinoyamboladmin.utilities.validation.CompositeValidator
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.validators.EmailValidator
import com.kinoyamboladmin.utilities.validation.validators.IntegerNumberValidator
import com.kinoyamboladmin.utilities.validation.validators.PhoneValidator
import com.kinoyamboladmin.utilities.validation.validators.ValidatorMaxLenghtText
import com.kinoyamboladmin.utilities.validation.validators.ValidatorMinLengthText
import com.kinoyamboladmin.utilities.validation.validators.ValidatorNonEmptyText

@Composable
fun TextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textState: String,
    hintText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validationState: Validation,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = textState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = hintText,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(if (validationState.hasError) "${label}*" else label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validationState.hasError) {
                Text(text = validationState.errorMessage!!)
            }
        },
        isError = validationState.hasError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun OutlinedTextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textState: String,
    hintText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validationState: Validation,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = hintText,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(if (validationState.hasError) "${label}*" else label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validationState.hasError) {
                Text(text = validationState.errorMessage!!)
            }
        },
        isError = validationState.hasError,
        keyboardActions = keyboardActions
    )
}

@Suppress("unused")
@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave",
    informationalIcon: Painter = rememberVectorPainter(image = Icons.Filled.Lock),
) {
    var passwordHidden: Boolean by remember { mutableStateOf(true) }

    TextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(if (validationState.hasError) "${label}*" else label) },
        supportingText = {
            if (validationState.hasError) {
                Text(text = validationState.errorMessage!!)
            }
        },
        isError = validationState.hasError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = informationalIcon,
                contentDescription = label
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) labelShow else labelHide
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}

@Composable
fun OutlinedTextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave",
    informationalIcon: Painter = rememberVectorPainter(image = Icons.Filled.Lock),
) {
    var passwordHidden: Boolean by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(if (validationState.hasError) "${label}*" else label) },
        supportingText = {
            if (validationState.hasError) {
                Text(text = validationState.errorMessage!!)
            }
        },
        isError = validationState.hasError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = informationalIcon,
                contentDescription = label
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) labelShow else labelHide
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}

@Suppress("unused")
@Composable
fun TextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    phoneState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit
) {
    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textState = phoneState,
        hintText = "999 99 99 99",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Teléfono"
            )
        },
        validationState = validationState,
        onValueChange = {
            var text = it
            if (!validationState.hasError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {
                    Log.println(Log.ERROR, "Error", e.message.toString())
                }
            }
            onValueChange(text)
        }
    )
}

@Composable
fun OutlinedTextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    phoneState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textState = phoneState,
        hintText = "999999999",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Teléfono"
            )
        },
        validationState = validationState,
        onValueChange = {
            var text = it
            if (!validationState.hasError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {
                    Log.println(Log.ERROR, "Error", e.message.toString())
                }
            }
            onValueChange(text)
        }
    )
}

@Suppress("unused")
@Composable
fun TextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit
) {
    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textState = emailState,
        hintText = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email"
            )
        },
        validationState = validationState,
        onValueChange = { onValueChange(it) }
    )
}

@Composable
fun OutlinedTextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validationState: Validation,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textState = emailState,
        hintText = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email"
            )
        },
        validationState = validationState,
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true)
@Composable
fun FiledTextCommonTest() {
    Column {
        var edadState by remember { mutableStateOf("") }
        var passwordState by remember { mutableStateOf("") }
        var aviso by remember { mutableStateOf("") }

        var validacionEdad by remember { mutableStateOf(object : Validation {} as Validation) }
        val validadorEdad = CompositeValidator<String>()
            .add(ValidatorNonEmptyText("La edad puede estar vacía"))
            .add(IntegerNumberValidator(Range(0, 120), "La edad debe estar entre 0 y 120"))

        var nombreState by remember { mutableStateOf("") }
        var validacionNombre by remember { mutableStateOf(object : Validation {} as Validation) }
        val validadorNombre = ValidatorMaxLenghtText(20)

        var correoState by remember { mutableStateOf("") }
        var validacionCorreo by remember { mutableStateOf(object : Validation {} as Validation) }
        val validadorCorreo = CompositeValidator<String>()
            .add(ValidatorNonEmptyText("El correo puede estar vacío"))
            .add(EmailValidator("El correo no es válido"))

        var telefonoState by remember { mutableStateOf("") }
        var validacionTelefono by remember { mutableStateOf(object : Validation {} as Validation) }
        val validadorTelefono = CompositeValidator<String>()
            .add(ValidatorNonEmptyText("El teléfono puede estar vacío"))
            .add(ValidatorMinLengthText(9, "El teléfono debe tener 9 caracteres"))
            .add(ValidatorMaxLenghtText(18, "El teléfono debe tener 18 caracteres"))
            .add(PhoneValidator("El teléfono no es válido"))

        var validacionClave by remember { mutableStateOf(object : Validation {} as Validation) }
        val validadorClave = ValidatorMinLengthText(minimumLength = 8)

        TextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Edad",
            textState = edadState,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            validationState = validacionEdad,
            onValueChange = {
                edadState = it
                validacionEdad = validadorEdad.validate(it)
                if (!validacionEdad.hasError)
                    edadState = it.toInt().toString()
            }
        )

        OutlinedTextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Nombre",
            textState = nombreState,
            validationState = validacionNombre,
            onValueChange = {
                nombreState = it
                validacionNombre = validadorNombre.validate(it)
            }
        )

        OutlinedTextFieldEmail(
            modifier = Modifier.fillMaxWidth(),
            emailState = correoState,
            validationState = validacionCorreo,
            onValueChange = {
                correoState = it
                validacionCorreo = validadorCorreo.validate(it)
            })

        OutlinedTextFieldPhone(
            modifier = Modifier.fillMaxWidth(),
            phoneState = telefonoState,
            validationState = validacionTelefono,
            onValueChange = {
                telefonoState = it
                validacionTelefono = validadorTelefono.validate(it)
            }
        )

        OutlinedTextFieldPassword(
            modifier = Modifier.fillMaxWidth(),
            passwordState = passwordState,
            validationState = validacionClave,
            onValueChange = {
                passwordState = it
                validacionClave = validadorClave.validate(it)
            }
        )

        Text(
            text = aviso,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                validacionEdad = validadorEdad.validate(edadState)
                validacionNombre = validadorNombre.validate(nombreState)
                validacionTelefono = validadorTelefono.validate(telefonoState)
                validacionCorreo = validadorCorreo.validate(correoState)
                validacionClave = validadorClave.validate(passwordState)
                aviso = if (CompositeValidation()
                        .add(validacionEdad)
                        .add(validacionNombre)
                        .add(validacionTelefono)
                        .add(validacionCorreo)
                        .add(validacionClave)
                        .hasError
                )
                    "Hay errores"
                else ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Aceptar")
        }
    }
}
