package to.jobo.avatarish

import androidx.lifecycle.*
import to.jobo.avatarish.core.GetAvatarFromNameUseCase
import to.jobo.avatarish.data.RestAvatarRepository

class MainViewModel(avatarRepository: RestAvatarRepository) : ViewModel() {
    private val getAvatarFromNameUseCase: GetAvatarFromNameUseCase =
        GetAvatarFromNameUseCase(avatarRepository)

    val viewState: LiveData<MainViewState> =
        getAvatarFromNameUseCase.execute("")
            .asLiveData(
                viewModelScope.coroutineContext
            )
            .map {
                if (it?.imagePath != null) {
                    MainViewState.Success(it.imagePath as String)
                } else {
                    MainViewState.Error("err", {})
                }
            }

}