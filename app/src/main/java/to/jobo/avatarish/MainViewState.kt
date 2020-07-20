package to.jobo.avatarish

import android.util.Log
import androidx.compose.Composable
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.currentTextStyle
import androidx.ui.layout.Column
import androidx.ui.layout.InnerPadding
import androidx.ui.material.OutlinedButton
import androidx.ui.unit.dp
import to.jobo.avatarish.components.GlideImageComponent

sealed class MainViewState {
    @Composable
    abstract fun render()

    object Loading : MainViewState() {
        @Composable
        override fun render() {
            Log.d("TODO", "IMPLEMENT LOADING STATE")
            TODO("Not yet implemented")
        }
    }

    class Error(private val reason: String, private val retry: () -> Unit) : MainViewState() {
        @Composable
        override fun render() {
            Column() {
                InnerPadding(16.dp)
                Text(text = reason, style = currentTextStyle())
                OutlinedButton(onClick = {
                    retry()
                }, text = { "Retry" })

            }
        }
    }

    class Success(private val content: String) : MainViewState() {
        @Composable
        override fun render() {
            Box(padding = 16.dp) {
                GlideImageComponent(url = content)
            }
        }

    }
}