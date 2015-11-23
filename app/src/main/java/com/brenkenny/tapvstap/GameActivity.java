package com.brenkenny.tapvstap;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {

    // Our object to handle the View
    private GameView gameView;
    public int lives;

    // This is where the "Play" button from HomeActivity sends us
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        Intent intent = getIntent();
        if (intent.getStringExtra(SettingsActivity.EXTRA_EVENT_TITLE) != null) {
            lives = Integer.valueOf(intent.getStringExtra(SettingsActivity.EXTRA_EVENT_TITLE));

        }else {
            lives = 3;
        }

        // Create an instance of our Tappy Defender View
        // Also passing in this.
        // Also passing in the screen resolution to the constructor
        gameView = new GameView(this, size.x, size.y, lives);

        // Make our gameView the view for the Activity
        setContentView(gameView);

    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }



}
