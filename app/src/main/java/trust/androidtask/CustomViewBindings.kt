package trust.androidtask

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class CustomViewBindings {
    companion object {




        @BindingAdapter("imageUrlRect")
        @JvmStatic
        fun loadImageUrlRect(imageView: ImageView, imageUrl: String?) {

            if (imageUrl != null) {
                // If we don't do this, you'll see the old image appear briefly
                // before it's replaced with the current image
                if (imageView.getTag(R.id.image_url) == null || imageView.getTag(R.id.image_url) != imageUrl) {
                    imageView.setImageBitmap(null)
                    imageView.setTag(R.id.image_url, imageUrl)

                    Glide.with(imageView).load(imageUrl)
                        .apply(
                            RequestOptions().placeholder(R.drawable.ic_launcher_background)

                                .diskCacheStrategy(
                                    DiskCacheStrategy.ALL
                                )
                        )
                        .into(imageView)
                }
            } else {
                imageView.setTag(R.id.image_url, null)
                imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }


    }
}