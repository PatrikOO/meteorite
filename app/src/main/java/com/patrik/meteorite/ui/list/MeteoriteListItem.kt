package com.patrik.meteorite.ui.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MeteoriteListItem(
    title: String,
    onShowOnMap: () -> Unit,
    modifier: Modifier = Modifier
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