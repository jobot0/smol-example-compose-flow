package to.jobo.avatarish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.jobo.avatarish.data.RestAvatarRepository

class MainViewModelFactory(private val avatarRepository: RestAvatarRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(avatarRepository) as T
}