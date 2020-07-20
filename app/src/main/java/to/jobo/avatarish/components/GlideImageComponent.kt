package to.jobo.avatarish.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.*
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeightIn
import androidx.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget

@Composable
fun GlideImageComponent(
    url: String, modifier: Modifier = Modifier.fillMaxWidth() +
            Modifier.preferredHeightIn(maxHeight = 200.dp)
) {
    var image by state<ImageAsset?> { null }
    var drawable by state<Drawable?> { null }
    val context = ContextAmbient.current
    onCommit(url) {
        val glide = Glide.with(context)

        val target = object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                image = null
                drawable = placeholder
            }

            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                image = resource.asImageAsset()

            }
        }

        glide
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            glide.clear(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        // Box is a predefined convenience composable that allows you to apply common draw & layout
        // logic. In addition we also pass a few modifiers to it.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. In this example, we configure the
        // Box composable to have a max height of 200dp and fill out the entire available
        // width.
        Box(
            modifier = modifier,
            gravity = ContentGravity.Center
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
            Image(asset = theImage)
        }
    } else if (theDrawable != null) {
        Canvas(modifier = modifier) {
            drawCanvas { canvas, _ ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}