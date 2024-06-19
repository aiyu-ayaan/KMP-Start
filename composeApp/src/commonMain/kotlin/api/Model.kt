package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.apache5.Apache5
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable

@Serializable
data class Model(
    val userId: Int, val id: Int, val title: String, val body: String
)

interface PostService {
    suspend fun getPosts(): List<Model>

    companion object {
        fun create(): PostService {
            return PostServiceImp(
                client = HttpClient(Apache5) {
                    install(Logging) {
                        logger = Logger.SIMPLE
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}

class PostServiceImp(
    private val client: HttpClient
) : PostService {
    override suspend fun getPosts(): List<Model> {
        return try {
            client.get {
                url("https://jsonplaceholder.typicode.com/posts")
            }.body()
        } catch (e: Exception) {
            println(e)
            emptyList()
        }
    }
}

