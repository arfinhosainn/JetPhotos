package com.example.jetphotos.MainPresentation.Home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.jetphotos.R
import com.example.jetphotos.ui.theme.LightGray
import com.example.jetphotos.ui.theme.TextWhite

@Composable
fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    state: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(LightGray)
            .padding(5.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(35.dp)
                .padding(start = 12.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            BasicTextField(value = state,
                onValueChange = {
                    onTextChange(it)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(state)
                    }
                ),
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = TextWhite),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isHintDisplayed = !it.isFocused
                    }
            )
            if (isHintDisplayed) {
                Text(
                    text = hint, color = TextWhite,
                    modifier = Modifier
                )
            }
        }

    }
}
