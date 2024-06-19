import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import api.Model
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    model: Model
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = model.title
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                style = MaterialTheme.typography.body1,
                text = model.body
            )
        }
    }
}

