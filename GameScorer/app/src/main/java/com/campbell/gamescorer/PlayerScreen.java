package com.campbell.gamescorer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import java.util.ArrayList;
import java.util.List;


public class PlayerScreen extends ActionBarActivity {

    //public static final String EXTRA_PLAYER_NAMES = "playerNames";
    public static final String EXTRA_GAME = "game";

    //protected List<PlayerScore> playerScores;

    private static final int[] PLAYER_VIEW_IDS = { R.id.player_1, R.id.player_2,
            R.id.player_3, R.id.player_4, R.id.player_5, R.id.player_6,
            R.id.player_7, R.id.player_8, R.id.player_9, R.id.player_10,
            R.id.player_11, R.id.player_12, R.id.player_13, R.id.player_14,
            R.id.player_15, R.id.player_16, R.id.player_17, R.id.player_18,
            R.id.player_19, R.id.player_20, R.id.player_21, R.id.player_22,
            R.id.player_23, R.id.player_24, R.id.player_25, R.id.player_26,
            R.id.player_27, R.id.player_28, R.id.player_29, R.id.player_30
    };

    protected List<PlayerView> playerViews;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player_screen);
        createGame();
        setUpWidgets();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_screen, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpWidgets() {

        playerViews = new ArrayList<PlayerView>();

        for (int i = 0; i < game.getPlayerScores().size(); i++) {

            PlayerScore playerScore = game.getPlayerScores().get(i);
            int resId = PLAYER_VIEW_IDS[i];
            View view = getPlayerScoreView(resId);

            PlayerView playerView = new PlayerView(this, view, playerScore);

            playerViews.add(playerView);
        }

    }

    protected View getPlayerScoreView(int resId) {
        // either get the view, or inflate from the ViewStub
        View view = findViewById(resId);
        if (view instanceof ViewStub) {
            return ((ViewStub) view).inflate();
        }
        return view;
    }

    private void createGame() {

        if (getIntent().hasExtra(EXTRA_GAME)) {
            // starting a new game
            game = getIntent().getParcelableExtra(EXTRA_GAME);
        }
    }

}
