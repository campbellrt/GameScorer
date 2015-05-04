package com.campbell.gamescorer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewGameActivity extends ActionBarActivity implements View.OnClickListener {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        numberPickerSetup();

        confirmButton = (Button) findViewById(R.id.button_confirm_players);
        confirmButton.setOnClickListener(this);

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
                NumberPicker np = (NumberPicker) findViewById(R.id.playersNumberPicker);
                int numPlayers = np.getValue();
                startNamePlayersActivity(numPlayers);

                break;

        }

    }

    private void startNamePlayersActivity(int numPlayers) {
        Intent intent = new Intent(this, NamePlayersActivity.class);
        intent.putExtra(NamePlayersActivity.EXTRA_NUM_PLAYERS, numPlayers);
        startActivity(intent);

    }

    private void numberPickerSetup(){
        NumberPicker np = (NumberPicker) findViewById(R.id.playersNumberPicker);
        np.setMaxValue(30);
        np.setMinValue(2);
        np.setWrapSelectorWheel(false);

    }
}
