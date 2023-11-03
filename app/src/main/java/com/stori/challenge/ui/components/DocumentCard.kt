package com.stori.challenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.stori.challenge.R
import com.stori.challenge.ui.states.UiState
import com.stori.challenge.ui.theme.LocalDim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentCard(
    modifier: Modifier,
    text: String,
    state: UiState = UiState.None,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    val dimens = LocalDim.current

    Card(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.5.dp,
            color = if (state.isError) colors.error else colors.onSecondaryContainer,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            focusedElevation = dimens.mediumElevation,
            pressedElevation = dimens.mediumElevation,
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors.outlineVariant,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(all = dimens.spaceMedium)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimens.spaceExtraSmall),
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.id_card_id),
                    contentDescription = "",
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                )
            }

            when (state) {
                UiState.Idle -> Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_circle_checked),
                    contentDescription = state.name,
                )

                UiState.Error -> Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = state.name,
                    tint = colors.error,
                )

                else -> Unit
            }
        }
    }
}
