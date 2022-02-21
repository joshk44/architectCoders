package com.joseferreyra.architectcoders.extensions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joseferreyra.architectcoders.R
import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import retrofit2.Call

fun Context.getLoader(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorSchemeColors(getColor(R.color.blue_light))
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(context.getLoader())
        .into(this)
}

fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body()!!)))
            false -> {
                response.code().let {
                    Either.Left(Failure.ServerError)
                }
            }
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError)
    }
}