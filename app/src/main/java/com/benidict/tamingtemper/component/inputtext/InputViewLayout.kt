package com.benidict.tamingtemper.component.inputtext

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.benidict.tamingtemper.R

@Composable
fun InputViewLayout(
    isPassword: Boolean = false,
    value: String,
    label: String,
    onTextChanged: (String) -> Unit
) {
    var passwordToggle: Boolean by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = {
            onTextChanged(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
        ),
        visualTransformation = if (passwordToggle.not() && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = {
                    passwordToggle = passwordToggle.not()
                }) {
                    Icon(
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp),
                        painter = painterResource(
                            if (passwordToggle) R.drawable.ic_show
                            else R.drawable.ic_hide
                        ),
                        contentDescription = ""
                    )
                }
            }
        }
    )
}