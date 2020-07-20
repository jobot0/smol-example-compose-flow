package to.jobo.avatarish.core

import kotlinx.coroutines.flow.Flow

class GetAvatarFromNameUseCase(private val avatarRepository: AvatarRepository) {
    fun execute(name: String): Flow<Avatar?> = avatarRepository.getAvatarFromName(name)
}