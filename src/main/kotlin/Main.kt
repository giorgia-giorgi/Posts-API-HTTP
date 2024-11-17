import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.runBlocking

fun main() {
    val url = "https://jsonplaceholder.typicode.com/posts"
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    runBlocking {
        try {
            val posts: List<Post> = client.get(url).body()
            for (post in posts) {
                println("Post ID : ${post.id}")
                println("Post Title: ${post.title}")
                println("Post Body: ${post.body}")
                println("")
                println("------------------")
            }
        } catch (exception: Exception) {
            println("Error while fetching posts")
        } finally {
            client.close()
        }
    }
}