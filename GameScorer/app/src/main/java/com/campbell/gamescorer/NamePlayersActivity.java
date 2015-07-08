package com.campbell.gamescorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.campbell.gamescorer.db.Game;
import com.campbell.gamescorer.db.PlayerScore;

import java.util.ArrayList;
import java.util.List;


public class NamePlayersActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String EXTRA_GAME = "newGame";

    private static final int[] PLAYER_VIEW_IDS = { R.id.player_1, R.id.player_2,
            R.id.player_3, R.id.player_4, R.id.player_5, R.id.player_6,
            R.id.player_7, R.id.player_8, R.id.player_9, R.id.player_10,
            R.id.player_11, R.id.player_12, R.id.player_13, R.id.player_14,
            R.id.player_15, R.id.player_16, R.id.player_17, R.id.player_18,
            R.id.player_19, R.id.player_20, R.id.player_21, R.id.player_22,
            R.id.player_23, R.id.player_24, R.id.player_25, R.id.player_26,
            R.id.player_27, R.id.player_28, R.id.player_29, R.id.player_30
    };

    private Button okButton;

    private Game game;

    private ArrayList<AutoCompleteTextView> playerEditTexts = new ArrayList<AutoCompleteTextView>();
    private ArrayList<View> playerViews = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // prevents the soft keyboard from immediately popping up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        game = getIntent().getParcelableExtra(EXTRA_GAME);
        //numPlayers = getIntent().getIntExtra(EXTRA_NUM_PLAYERS, 0);

        setContentView(R.layout.activity_name_players);

        setUpWidgets();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_name_players, menu);
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
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_ok:
                // ok button clicked
                addPlayersToGame();

                //GameActivityHelper.newGame(this, playerNames);

                Intent intent = new Intent(this, PlayerScreen.class);

                intent.putExtra(PlayerScreen.EXTRA_GAME, game);

                startActivity(intent);

                break;
            default:
               break;
        }
    }


    private void setUpWidgets() {

        for(int i = 0; i <game.getNumPlayers(); i++){
            int id  = PLAYER_VIEW_IDS[i];
            View view = getPlayerView(id);
            playerViews.add(view);
            playerEditTexts.add((AutoCompleteTextView) view.findViewById(R.id.player_name_edit_text));
        }

        for (int i = 0; i < playerEditTexts.size(); i++) {
            AutoCompleteTextView playerEditText = playerEditTexts.get(i);
            if (playerEditText == null) {
                continue;
            }
            String hint = getString(R.string.text_player) + ' ' + (i + 1);
            playerEditText.setHint(hint);

            // final edit text does "action done"
            if (i == game.getNumPlayers() - 1) {
                playerEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }
        }

        okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(this);
    }

    private String[] getPlayerNames() {
        String[] playerNames = new String[game.getNumPlayers()];

        for (int i = 0; i < game.getNumPlayers(); i++) {

            if (TextUtils.isEmpty(playerEditTexts.get(i).getText())) {
                playerNames[i] = playerEditTexts.get(i).getHint().toString();
            }
            else {
                playerNames[i] = playerEditTexts.get(i).getText().toString();
            }
        }
        return playerNames;
    }

    private View getPlayerView(int resId) {
        // either get the view, or inflate from the ViewStub
        View view = findViewById(resId);
        if (view instanceof ViewStub) {
            return ((ViewStub) view).inflate();
        }
        return view;
    }

    private void addPlayersToGame() {

        String[] playerNames = getPlayerNames();

        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

        for (int i = 0; i < playerNames.length; i++) {

            PlayerScore playerScore = new PlayerScore();

            playerScore.setName(playerNames[i]);
            playerScore.setPlayerNumber(i);
            playerScore.setScore(0); //TODO: change to a default value
            playerScore.setRoundScore(0); //TODO: change to a default value
            playerScore.initRoundScores();
            playerScores.add(playerScore);
        }

        game.setPlayerScores(playerScores);
    }


}
