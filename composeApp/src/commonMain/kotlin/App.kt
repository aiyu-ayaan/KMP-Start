import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import api.Model
import api.PostService
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val client = PostService.create()
    MaterialTheme {
        val post by produceState<List<Model>>(
            initialValue = emptyList(),
            producer = {
                value = client.getPosts()
            }
        )
        AnimatedVisibility(visible = post.isEmpty()){
            CircularProgressIndicator()
        }
        LazyColumn {
            items(post){
                PostItem(
                    model = it
                )
            }
        }
    }
}