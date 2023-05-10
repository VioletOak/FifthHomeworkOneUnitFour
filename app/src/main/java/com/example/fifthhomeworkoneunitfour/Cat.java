package com.example.fifthhomeworkoneunitfour;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cat {

    private int x = 0, y = 0;
    private int width, height;
    private int wingCounter = 0;
    private Bitmap cat1, cat2;
    private boolean isGoingUp = false;

    public Cat(int screenX, int screenY, Resources resources) {
        cat1 = BitmapFactory.decodeResource(resources, R.drawable.cat_remove_one);
        cat2 = BitmapFactory.decodeResource(resources, R.drawable.cat_remove_tree);
        width = cat1.getWidth() / 3;
        height = cat1.getHeight() / 3;
        width = (int) (width * 1920f / screenX);
        height = (int) (height * 1080f / screenY);
        cat1 = Bitmap.createScaledBitmap(cat1, width, height, false);
        cat2 = Bitmap.createScaledBitmap(cat2, width, height, false);
        y = screenY / 2;
        x = screenX / 21;
    }
    public Bitmap getCat() {
        if (wingCounter == 0) {
            wingCounter++;
            return cat1;
        } else if (wingCounter > 0) {
            wingCounter--;
            return cat2;
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isGoingUp() {
        return false;
    }

    public void setGoingUp(boolean b) {
    }

    public int getHeight() {
        return 0;
    }
}
