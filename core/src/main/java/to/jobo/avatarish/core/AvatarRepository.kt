package to.jobo.avatarish.core

import kotlinx.coroutines.flow.Flow

interface AvatarRepository {
    fun getAvatarFromName(name: String): Flow<Avatar?>
}
