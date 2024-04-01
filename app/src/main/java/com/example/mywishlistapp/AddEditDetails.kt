package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddEditDetails(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppBar(
                title = if (id != 0L) stringResource(id = R.string.edit_wish) else stringResource(id = R.string.add_wish),
                onBackNavClicked = { navController.navigateUp() }
            )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = { viewModel.onWishTitleChange(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChange = { viewModel.onWishDescriptionChange(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
//                    viewModel.updateWish(id)
                } else {
//                    viewModel.addWish()
                }
                navController.popBackStack()
            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.edit_wish) else stringResource(
                        id = R.string.add_wish
                    ),
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

        }

    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Black
        )
    )
}


@Preview(showBackground = true)
@Composable
fun AddEditDetailsPreview() {
    CustomTextField(label = "text", value = "text", onValueChange = {})
}