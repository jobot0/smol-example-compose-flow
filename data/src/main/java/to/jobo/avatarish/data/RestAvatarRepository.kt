package to.jobo.avatarish.data

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import to.jobo.avatarish.core.Avatar
import to.jobo.avatarish.core.AvatarRepository
import java.io.File
import java.time.Instant

class RestAvatarRepository(private val application: Application) : AvatarRepository {
    private val client: HttpClient = HttpClient(Android)

    override fun getAvatarFromName(name: String): Flow<Avatar?> = flow {

        val response: HttpResponse = client.get("https://placekitten.com/450/450")
        val folderPath = "${application.applicationContext.filesDir}/"
        val tmpFile = File(folderPath, "${name}-${Instant.now().epochSecond}.jpg")

        tmpFile.writeBytes(response.readBytes())
        when (response.status) {
            OK -> this.emit(Avatar("file://${tmpFile.path}"))
            else -> this.emit(null)
        }
    }


}