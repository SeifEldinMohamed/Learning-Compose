package com.seif.learningcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RepoItemExpandable(
    repositoriesEntity: TrendingRepositoriesEntity,
    itemPosition:Int,
    onItemClick:(Int)-> Unit
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(itemPosition)
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.nyc_in_snow),
            contentDescription = "image",
            modifier = Modifier
                .size(40.dp)
                .padding(top = 8.dp, start = 8.dp)
                .clip(RoundedCornerShape(80.dp))
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = repositoriesEntity.name)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = repositoriesEntity.author)
            Spacer(modifier = Modifier.height(15.dp))

            if (repositoriesEntity.isExpanded.value) {
                Text(text = repositoriesEntity.description)
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Box( // circle
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(12.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(android.graphics.Color.parseColor(repositoriesEntity.languageColor)))

                    )

                    Text(text = repositoriesEntity.language, Modifier.padding(end = 5.dp))

                    Image( // star
                        painter = painterResource(id = R.drawable.ic_star_yellow_16),
                        contentDescription = "start icon",
                        Modifier
                            .size(30.dp)
                            .padding(end = 5.dp)
                    )

                    Text(text = repositoriesEntity.stars.toString(), Modifier.padding(end = 5.dp))

                    Image( // fork
                        painter = painterResource(id = R.drawable.ic_fork_black_16),
                        contentDescription = "fork icon",
                        Modifier
                            .size(30.dp)
                            .padding(end = 5.dp)
                    )

                    Text(text = repositoriesEntity.forks.toString(), Modifier.padding(end = 5.dp))
                }
            }
        }
    }
    Divider()
}