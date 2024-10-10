package com.sunil.albertsonsusers.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.sunil.albertsonsusers.ui.theme.AlbertsonsUsersTheme
import com.sunil.albertsonsusers.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(index: Int, controller: NavHostController, viewModel: UserViewModel) {



    val user = viewModel.userData.value?.results?.get(index)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${user?.name?.title} ${user?.name?.first} ${user?.name?.last}",
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
                    .padding(10.dp,0.dp,10.dp,0.dp)
                    .padding(innerPadding)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user?.picture?.large)
                                .size(Size.ORIGINAL)
                                .build()
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                }


                DataRow(title = "UserName", value ="${user?.login?.username}" )
                DataRow(title = "Password", value ="${user?.login?.password}" )
                DataRow(title = "Name", value ="${user?.name?.first} ${user?.name?.last}" )
                DataRow(title = "Email", value ="${user?.email}" )
                DataRow(title = "Phone", value ="${user?.phone} , ${user?.cell} " )
                val location = user?.location
                DataRow(title = "Address", value ="${location?.street?.number} ${location?.street?.number} ${location?.city} ${location?.state} ${location?.country} ${location?.postcode}" )
                DataRow(title = "Gender", value ="${user?.gender}" )
                DataRow(title = "Age", value ="${user?.dob?.age} years old" )
                DataRow(title = "Id", value ="${user?.id?.name} - ${user?.id?.value}" )
                DataRow(title = "Registered", value ="${user?.registered?.date}" )

            }


        }

    )

}

@Composable
fun DataRow(title : String, value : String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp, 10.dp, 2.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlbertsonsUsersTheme {
        DataRow("Dev","Sunil Patil")
    }
}