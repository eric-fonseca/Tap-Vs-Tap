package com.brenkenny.tapvstap;

/**
 * Created by bkishere11 on 10/19/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TDView extends SurfaceView implements Runnable {

    private int p1BlueCircleX;
    private int p1RedCircleX;
    private int p1GreenCircleX;
    private int p1YellowCircleX;
    private int p1OrangeCircleX;

    private int p2BlueCircleX;
    private int p2RedCircleX;
    private int p2GreenCircleX;
    private int p2YellowCircleX;
    private int p2OrangeCircleX;

    private boolean p1BluePressed;
    private boolean p1RedPressed;
    private boolean p1GreenPressed;
    private boolean p1YellowPressed;
    private boolean p1OrangePressed;

    private boolean p2BluePressed;
    private boolean p2RedPressed;
    private boolean p2GreenPressed;
    private boolean p2YellowPressed;
    private boolean p2OrangePressed;

    private Arrow arrow;

    ArrayList<Arrow> arrowList = new ArrayList<Arrow>();

    private Context context;

    private int screenX;
    private int screenY;

    volatile boolean playing;
    Thread gameThread = null;

    // For drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    // For saving and loading the high score
    private SharedPreferences prefs;


    TDView(Context context, int x, int y) {
        super(context);
        this.context  = context;

        p1BlueCircleX = 10000;
        p1RedCircleX = 10000;
        p1GreenCircleX = 10000;
        p1YellowCircleX = 10000;
        p1OrangeCircleX = 10000;

        p2BlueCircleX = -10000;
        p2RedCircleX = -10000;
        p2GreenCircleX = -10000;
        p2YellowCircleX = -10000;
        p2OrangeCircleX = -10000;

        screenX = x;
        screenY = y;

        // Initialize our drawing objects
        ourHolder = getHolder();
        paint = new Paint();

        //arrow = new Arrow(context, screenX, screenY);

        // Load fastest time
        prefs = context.getSharedPreferences("HiScores", context.MODE_PRIVATE);
        // Initialize the editor ready

        //downPressed = false;
        startGame();
    }

    private void startGame(){



    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        //arrow.update();

        p1BlueCircleX += 35;
        p1RedCircleX += 35;
        p1GreenCircleX += 35;
        p1YellowCircleX += 35;
        p1OrangeCircleX += 35;

        p2BlueCircleX -= 35;
        p2RedCircleX -= 35;
        p2GreenCircleX -= 35;
        p2YellowCircleX -= 35;
        p2OrangeCircleX -= 35;

        for(int i = 0; i < arrowList.size(); i++){
           Arrow mArrow = arrowList.get(i);
            mArrow.update();
        }
    }

    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            paint.setColor(Color.argb(255, 0, 0, 255)); //blue
            canvas.drawCircle(200, 200, 100, paint);
            canvas.drawCircle(2300, 200, 100, paint);
            canvas.drawCircle(p1BlueCircleX, 200, 100, paint);
            canvas.drawCircle(p2BlueCircleX, 200, 100, paint);

            paint.setColor(Color.argb(255, 255, 0, 0)); //red
            canvas.drawCircle(200, 450, 100, paint);
            canvas.drawCircle(2300, 450, 100, paint);
            canvas.drawCircle(p1RedCircleX, 450, 100, paint);
            canvas.drawCircle(p2RedCircleX, 450, 100, paint);

            paint.setColor(Color.argb(255, 0, 255, 0)); //green
            canvas.drawCircle(200, 700, 100, paint);
            canvas.drawCircle(2300, 700, 100, paint);
            canvas.drawCircle(p1GreenCircleX, 700, 100, paint);
            canvas.drawCircle(p2GreenCircleX, 700, 100, paint);

            paint.setColor(Color.argb(255, 255, 255, 0)); //yellow
            canvas.drawCircle(200, 950, 100, paint);
            canvas.drawCircle(2300, 950, 100, paint);
            canvas.drawCircle(p1YellowCircleX, 950, 100, paint);
            canvas.drawCircle(p2YellowCircleX, 950, 100, paint);

            paint.setColor(Color.argb(255, 255, 140, 0)); //orange
            canvas.drawCircle(200, 1200, 100, paint);
            canvas.drawCircle(2300, 1200, 100, paint);
            canvas.drawCircle(p1OrangeCircleX, 1200, 100, paint);
            canvas.drawCircle(p2OrangeCircleX, 1200, 100, paint);

            for(int i = 0; i < arrowList.size(); i++){
                Arrow mArrow = arrowList.get(i);
                canvas.drawBitmap(mArrow.getBitmap(), mArrow.getX(), mArrow.getY(), paint);
            }

//            //Player 1 Pressed
//            if (p1BluePressed == true) {
//                if (p1BlueCircleX > 2500) p1BlueCircleX = 200;
//                //canvas.drawBitmap(arrow.getBitmap(), p1BlueCircleX, 200, paint);
//
//            }
//            if ( p1RedPressed == true){
//                if(p1RedCircleX > 2500) p1RedCircleX = 200;
//                canvas.drawBitmap(arrow.getBitmap(), p1RedCircleX, 450, paint);
//            }
//            if ( p1GreenPressed == true){
//                if(p1GreenCircleX > 2500) p1GreenCircleX = 200;
//            }
//            if ( p1YellowPressed == true){
//                if(p1YellowCircleX > 2500) p1YellowCircleX = 200;
//            }
//            if ( p1OrangePressed == true){
//                if(p1OrangeCircleX > 2500) p1OrangeCircleX = 200;
//            }

            //Player 2 Pressed
            if (p2BluePressed == true){
                if(p2BlueCircleX < 0) p2BlueCircleX = 2300;
            }
            if ( p2RedPressed == true){
                if(p2RedCircleX < 0) p2RedCircleX = 2300;
            }
            if ( p2GreenPressed == true){
                if(p2GreenCircleX < 0) p2GreenCircleX = 2300;
            }
            if ( p2YellowPressed == true){
                if(p2YellowCircleX < 0) p2YellowCircleX = 2300;
            }
            if ( p2OrangePressed == true){
                if(p2OrangeCircleX < 0) p2OrangeCircleX = 2300;
            }

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {

        }
    }

    // SurfaceView allows us to handle the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // There are many different events in MotionEvent
        // We care about just 2 - for now.
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            // Has the player lifted there finger up?
            case MotionEvent.ACTION_UP:
                p1BluePressed = false;
                p1RedPressed = false;
                p1GreenPressed = false;
                p1YellowPressed = false;
                p1OrangePressed = false;

                p2BluePressed = false;
                p2RedPressed = false;
                p2GreenPressed = false;
                p2YellowPressed = false;
                p2OrangePressed = false;

                break;

            // Has the player touched the screen?
            case MotionEvent.ACTION_DOWN:


                //Player 1 Buttons
                if ( 100 < motionEvent.getX() && motionEvent.getX() < 300  &&  100 < motionEvent.getY() && motionEvent.getY() < 300) {

                    arrow = new Arrow(context, 50, 100, "blue");
                    arrowList.add(arrow);
                }

                if ( 100 < motionEvent.getX() && motionEvent.getX() < 300  &&  350 < motionEvent.getY() && motionEvent.getY() < 550) {

                    arrow = new Arrow(context, 50, 350, "red");
                    arrowList.add(arrow);
                }
                if ( 100 < motionEvent.getX() && motionEvent.getX() < 300  &&  600 < motionEvent.getY() && motionEvent.getY() < 800) {

                    arrow = new Arrow(context, 50, 600, "green");
                    arrowList.add(arrow);
                }
                if ( 100 < motionEvent.getX() && motionEvent.getX() < 300  &&  850 < motionEvent.getY() && motionEvent.getY() < 1050) {
                    arrow = new Arrow(context, 50, 850, "yellow");
                    arrowList.add(arrow);
                }
                if ( 100 < motionEvent.getX() && motionEvent.getX() < 300  &&  1100 < motionEvent.getY() && motionEvent.getY() < 1300) {
                    arrow = new Arrow(context, 50, 1100, "orange");
                    arrowList.add(arrow);
                }


                //Player 2 Buttons
                if ( 2200 < motionEvent.getX() && motionEvent.getX() < 2400  &&  100 < motionEvent.getY() && motionEvent.getY() < 300) {

                    p2BluePressed = true;
                }

                if ( 2200 < motionEvent.getX() && motionEvent.getX() < 2400  &&  350 < motionEvent.getY() && motionEvent.getY() < 550) {

                    p2RedPressed = true;
                }
                if ( 2200 < motionEvent.getX() && motionEvent.getX() < 2400  &&  600 < motionEvent.getY() && motionEvent.getY() < 800) {

                    p2GreenPressed = true;
                }
                if ( 2200 < motionEvent.getX() && motionEvent.getX() < 2400  &&  850 < motionEvent.getY() && motionEvent.getY() < 1050) {

                    p2YellowPressed = true;
                }
                if ( 2200 < motionEvent.getX() && motionEvent.getX() < 2400  &&  1100 < motionEvent.getY() && motionEvent.getY() < 1300) {

                    p2OrangePressed = true;
                }


                break;
        }
        return true;
    }

    // Clean up our thread if the game is interrupted or the player quits
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }

    // Make a new thread and start it
    // Execution moves to our R
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

}


