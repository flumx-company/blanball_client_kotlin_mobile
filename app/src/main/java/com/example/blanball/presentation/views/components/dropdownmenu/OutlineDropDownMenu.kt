package com.example.blanball.presentation.views.components.dropdownmenu

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.blanball.R
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlineDropDownMenu(
    value: String,
    state: UiState,
    onValueChange: (String) -> Unit,
) {
    val listItems = listOf(
        stringResource(id = R.string.any_position),
        stringResource(id = R.string.goalkeeper),
        stringResource(id = R.string.right_defender),
        stringResource(id = R.string.left_defender),
        stringResource(id = R.string.central_defender),
        stringResource(id = R.string.left_flank_defender),
        stringResource(id = R.string.right_flank_defender),
        stringResource(id = R.string.supporting_mid_defender),
        stringResource(id = R.string.left_mid_defender),
        stringResource(id = R.string.attacking_mid_defender),
        stringResource(id = R.string.right_winger),
        stringResource(id = R.string.left_winger),
        stringResource(id = R.string.right_flank_attacker),
        stringResource(id = R.string.left_flank_attacker),
        stringResource(id = R.string.central_forward),
        stringResource(id = R.string.left_forward),
        stringResource(id = R.string.right_forward),
        stringResource(id = R.string.forward_striker),
        )

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = true,
            label = { Text(text = stringResource(id = R.string.game_position)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = defaultLightGray,
                focusedBorderColor = selectedDarkGray,
                textColor = Color.Black,
                errorBorderColor = errorRed,
                focusedLabelColor = primaryDark,
                cursorColor = mainGreen,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            listItems.forEach { selectedOption ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onValueChange(selectedOption)
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}