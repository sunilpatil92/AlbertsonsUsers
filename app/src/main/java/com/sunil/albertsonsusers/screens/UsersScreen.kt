package com.sunil.albertsonsusers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.sunil.albertsonsusers.model.Result
import com.sunil.albertsonsusers.states.UserStates
import com.sunil.albertsonsusers.viewModels.UserViewModel

@Composable
fun UsersScreen(viewModel: UserViewModel, controller: NavController, input: Int?) {

    val userStates = viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUsers(input)
    }

    when (userStates.value) {

        is UserStates.loading -> {
            LoadingScreen()
        }

        is UserStates.Sucess -> {
            UserListView((userStates.value as UserStates.Sucess).response,controller)
        }

        is UserStates.Error -> {
            ErrorScreen((userStates.value as UserStates.Error).error)
        }
        else -> {
            LoadingScreen()
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListView(users: List<Result>, controller: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "User List",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2196F3)),
                actions = {}
            )
        },
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                LazyColumn {
                    itemsIndexed(users) {index,item ->
                        itemCard(item,controller,index)
                    }
                }

            }


        }

    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun itemCard(it: Result, controller: NavController, index: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 4.dp, 10.dp, 4.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            //val json = gson.toJson(it)
            controller.navigate("UserDetail/$index")
        }
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.picture.medium)
                        .size(Size.ORIGINAL)
                        .build()
                ),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${it.name.first} ${it.name.last}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Icon(imageVector = Icons.Default.LocationOn,
                        modifier = Modifier.size(20.dp), contentDescription = "")
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${it.location.street.number} ${it.location.street.number} ${it.location.city} ${it.location.state} ${it.location.country} ${it.location.postcode}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


        }

    }

}
