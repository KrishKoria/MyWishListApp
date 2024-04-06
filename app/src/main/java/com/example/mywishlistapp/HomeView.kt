package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish


@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            AppBar(title = "My Wish List", onBackNavClicked = {
                Toast.makeText(context, "Back Button Clicked", Toast.LENGTH_SHORT).show()
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    Toast.makeText(context, "Floating Button Clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.AddDetailScreen.route + "/0L")
                },
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                shape = FloatingActionButtonDefaults.largeShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item Button")
            }
        }

    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { wish -> wish.id }) { wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                            show = false
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Color(0xFFFF1744)
                            DismissDirection.EndToStart -> Color(0xFFFF1744)
                            null -> Color.Transparent
                        }
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(color)
                                .padding(top = 4.dp, start = 4.dp, end = 4.dp)

                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White
                            )
                        }
                    },
                    dismissThresholds = {FractionalThreshold(0.5f)},
                    directions = setOf(DismissDirection.EndToStart),
                    dismissContent = {
                        WishItem(wish = wish, onWishClicked = {
                            val id = wish.id
                            navController.navigate(Screen.AddDetailScreen.route + "/$id")
                        })
                    }
                )
            }
        }
    }
}

@Composable
fun WishItem(
    wish: Wish,
    onWishClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, start = 2.dp, end = 2.dp)
            .clickable { onWishClicked() },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}
