package com.exercise.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by user on 2015/12/15.
 */
public class FishView  extends SurfaceView  implements SurfaceHolder.Callback {

    private final SurfaceHolder holder;
    private final Bitmap back;
    private final Bitmap[] fishs;

    public FishView(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder();
        holder.addCallback(this);
        back = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        fishs = new Bitmap[10];
        for (int i = 0; i < 10; i++) {
            try {
                int fishId = ((Integer) R.drawable.class.getField("fish" + i).get(null));
                fishs[i] = BitmapFactory.decodeResource(getResources(), fishId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
