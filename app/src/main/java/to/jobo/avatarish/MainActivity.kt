package to.jobo.avatarish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import to.jobo.avatarish.data.RestAvatarRepository
import to.jobo.avatarish.ui.AvatarishTheme

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val avatarRepository = RestAvatarRepository(application)
        val mainViewModel: MainViewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(avatarRepository)
            ).get(MainViewModel::class.java)

        setContent {
            App(viewModel = mainViewModel)
        }
    }
}

@Composable
fun App(viewModel: MainViewModel) {
    AvatarishTheme {
        val mainViewState by viewModel.viewState.observeAsState()
        mainViewState?.render()
    }
}
