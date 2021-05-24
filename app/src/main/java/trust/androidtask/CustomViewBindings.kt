package trust.androidtask

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class CustomViewBindings {
    companion object {


        @BindingAdapter("htmlText")
        @JvmStatic
        fun setHtmlTextValue(textView: TextView, htmlText: String?) {
            if (htmlText == null) return
            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(htmlText)
            }
            textView.text = result
        }


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