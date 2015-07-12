package com.campbell.gamescorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.campbell.gamescorer.db.Game;


public class NewGameActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextWatcher {

    private Spinner numPlayersSpinner;
    private Spinner gameTypeSpinner;
    //private Spinner turnTypeSpinner;
    private Spinner winTypeSpinner;
    private EditText winningScoreEditText;
    private ArrayAdapter<CharSequence> adapterNumPlayers, adapterGameType, adapterWinType;
    private boolean flagGameTypeChangeText, flagGameTypeChangeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);


        setUpWidgets();
        flagGameTypeChangeSpinner = true;
        flagGameTypeChangeText = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
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
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_confirm_players:
                Game newGame = createNewGame();
                startNamePlayersActivity(newGame);

                break;

        }

    }

    private void setUpWidgets(){
        Button confirmButton = (Button) findViewById(R.id.button_confirm_players);
        confirmButton.setOnClickListener(this);

        numPlayersSpinner = (Spinner) findViewById(R.id.playersNumberSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapterNumPlayers = ArrayAdapter.createFromResource(this,
                R.array.num_players_array, R.layout.custom_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterNumPlayers.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        numPlayersSpinner.setAdapter(adapterNumPlayers);
        numPlayersSpinner.setOnItemSelectedListener(this);


        gameTypeSpinner = (Spinner) findViewById(R.id.gameTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapterGameType = ArrayAdapter.createFromResource(this,
                R.array.game_type_array, R.layout.custom_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGameType.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gameTypeSpinner.setAdapter(adapterGameType);
        gameTypeSpinner.setOnItemSelectedListener(this);

        /*TODO Implement turns
        turnTypeSpinner = (Spinner) findViewById(R.id.turnTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTurnType = ArrayAdapter.createFromResource(this,
                R.array.turn_type_array, R.layout.custom_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTurnType.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        turnTypeSpinner.setAdapter(adapterTurnType);
        turnTypeSpinner.setOnItemSelectedListener(this);
        */

        winTypeSpinner = (Spinner) findViewById(R.id.winTypeSpinner);
        adapterWinType = ArrayAdapter.createFromResource(this,
                R.array.win_type_array, R.layout.custom_spinner_item);
        adapterWinType.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        winTypeSpinner.setAdapter(adapterWinType);
        winTypeSpinner.setOnItemSelectedListener(this);

        winningScoreEditText = (EditText) findViewById(R.id.winningScoreEditText);
        winningScoreEditText.setText(getString(R.string.default_winning_score));//default 100
        winningScoreEditText.setSelection(winningScoreEditText.getText().length());
        winningScoreEditText.addTextChangedListener(this);

    }


    private void setGameSettings(){
        switch (gameTypeSpinner.getSelectedItem().toString()) {
            case "Hearts":
                //TODO store info somewhere (not hard coded)
                flagGameTypeChangeSpinner = true;
                flagGameTypeChangeText = true;
                numPlayersSpinner.setSelection(adapterNumPlayers.getPosition("4"));
                winTypeSpinner.setSelection(adapterWinType.getPosition("Lowest Score"));
                winningScoreEditText.setText("100");
                winningScoreEditText.setSelection(winningScoreEditText.getText().length());
                break;

            case "500 (6 people)":
                //TODO store info somewhere (not hard coded)
                flagGameTypeChangeSpinner = true;
                flagGameTypeChangeText = true;
                numPlayersSpinner.setSelection(adapterNumPlayers.getPosition("6"));
                winTypeSpinner.setSelection(adapterWinType.getPosition("Lowest Score"));
                winningScoreEditText.setText("500");
                winningScoreEditText.setSelection(winningScoreEditText.getText().length());
                break;
        }
    }

    private Game createNewGame(){
        Game game = new Game();
        int numPlayers = Integer.parseInt(numPlayersSpinner.getSelectedItem().toString());
        game.setNumPlayers(numPlayers);

        String gameType = gameTypeSpinner.getSelectedItem().toString();
        game.setGameType(gameType);

        //String turnType = turnTypeSpinner.getSelectedItem().toString(); //TODO implement turns
        String turnType = "Rounds";
        game.setTurnType(turnType);

        String winType = winTypeSpinner.getSelectedItem().toString();
        game.setWinType(winType);

        int winningScore = -1;
        try
        {
            winningScore = Integer.parseInt(winningScoreEditText.getText().toString());
        }
        catch(NumberFormatException e)
        {
        }

        game.setWinningScore(winningScore);

        game.setNumRounds(1); //TODO: Make a default value (not hardcoded)

        return game;
    }

    private void startNamePlayersActivity(Game game) {
        Intent intent = new Intent(this, NamePlayersActivity.class);
        intent.putExtra(NamePlayersActivity.EXTRA_GAME, game);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gameTypeSpinner:
                setGameSettings();
                break;
            default:
                if (!flagGameTypeChangeSpinner) {
                    gameTypeSpinner.setSelection(adapterGameType.getPosition("Custom"));
                }
                else {
                    flagGameTypeChangeSpinner = false;
                }
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!flagGameTypeChangeText) {
            gameTypeSpinner.setSelection(adapterGameType.getPosition("Custom"));
        }
        else {
            flagGameTypeChangeText = false;
        }
    }
}
