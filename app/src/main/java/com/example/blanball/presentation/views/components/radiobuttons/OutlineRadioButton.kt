
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun OutlineRadioButton(
    state: StartScreensMainContract.State,
    text: String,
    selected: Boolean,
    icon: Painter,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color  = if (selected) mainGreen else defaultLightGray,
                shape = shapes.medium
            )
            .padding(8.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = mainGreen,
                unselectedColor = primaryDark,
            ),
        )
        Text(text = text, style = typography.caption)
        Icon(painter = icon, contentDescription = null)
    }
}