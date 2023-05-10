package com.example.fifthhomeworkoneunitfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;
    private  int screenX, screenY;
    private Paint paint;
    private float screenRatioX, screenRatioY;
    private Cat cat;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.setX(screenX);
        paint = new Paint();
        cat = new Cat(screenX, screenY, getResources());
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }
    private void update() {
        background1.setX(background1.getX() - (int)(10 * screenRatioX));
        background2.setX(background2.getX() - (int)(10 * screenRatioX));

        if ((background1.getX() + background1.getBackground().getWidth()) <= 0) {
            background1.setX(screenX);
        }
        if ((background2.getX() + background2.getBackground().getWidth()) <= 0) {
            background2.setX(screenX);
        }
        if (cat.isGoingUp()) {
            cat.setY(cat.getY() - (int) (30 * screenRatioY));
        } else {
            cat.setY(cat.getY() + (int) (30 * screenRatioY));
        }
        if (cat.getY() < 0) {
            cat.setY(0);
        } else if (cat.getY() >= screenY - cat.getHeight()) {
            cat.setY(screenY - cat.getHeight());
        }
    }
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.getBackground(), background1.getX(), background1.getY(), paint);
            canvas.drawBitmap(background2.getBackground(), background2.getX(), background2.getY(), paint);
            canvas.drawBitmap(cat.getCat(), cat.getX(), cat.getY(), paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    private void sleep() {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeThread() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
    public void pauseThread(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < (screenX / 2)) {
                    cat.setGoingUp(true);
                } else if (event.getX() >= (screenX / 2)) {

                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                cat.setGoingUp(false);
                break;
        }
        return true;
    }
}
