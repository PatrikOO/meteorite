package com.patrik.meteorite.ui.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MeteoriteListItem(
    modifier: Modifier = Modifier,
    title: String = "name date weight: 20kg distance: 200km",
    onShowOnMap: () -> Unit = {}
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(all = 16.dp),
            text = title
        )
    }
}