package com.zbigniew.futuremind.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zbigniew.futuremind.R;

/**
 * Created by zbigniew on 01/03/2017.
 */

public class PicassoUtil {

    public static void loadPicture(Context context, String pictureUrl, ImageView imageLoader) {
        imageLoader.setImageResource(R.drawable.placeholder);
        if(pictureUrl != null)
            Picasso.with(context)
                    .load(pictureUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(imageLoader);
    }
}
