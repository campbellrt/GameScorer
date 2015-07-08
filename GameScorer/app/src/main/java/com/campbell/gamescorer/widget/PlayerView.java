package com.campbell.gamescorer.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campbell.gamescorer.R;
import com.campbell.gamescorer.db.PlayerScore;

/**
 * Created by Campbell on 27/04/2015.
 */
public class PlayerView implements View.OnClickListener, View.OnLongClickListener {

    private PlayerScore playerScore;

    private TextView scoreTextView, nameTextView, roundScoreTextView, roundScoreNameTextView;

    private View view;
    private Button minusButton, plusButton;


    private Context context;




    public PlayerView(Context context, View view, PlayerScore playerScore) {
        this.view = view;
        this.playerScore = playerScore;
        this.context = context;

        init();
    }

    private void init() {

        nameTextView = (TextView) view.findViewById(R.id.player_name);
        scoreTextView = (TextView) view.findViewById(R.id.text_score);
        roundScoreTextView = (TextView) view.findViewById(R.id.round_text_score);
        roundScoreNameTextView = (TextView) view.findViewById(R.id.name_round_text_score);

        nameTextView.setText(playerScore.getName());

        minusButton = (Button) view.findViewById(R.id.button_minus);
        plusButton = (Button) view.findViewById(R.id.button_plus);

        minusButton.setOnClickListener(this);
        minusButton.setOnLongClickListener(this);
        plusButton.setOnClickListener(this);
        plusButton.setOnLongClickListener(this);
    }

    public View getView() {
        return view;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public TextView getScoreTextView() {
        return scoreTextView;
    }

    public TextView getRoundScoreTextView() {
        return roundScoreTextView;
    }

    public void setRoundScoreNameTextView(String newName) {
        roundScoreNameTextView.setText(newName);
    }

    public Button getMinusButton() {
        return minusButton;
    }

    public Button getPlusButton() {
        return plusButton;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_minus:
                incrementRoundScore(-1);
                break;
            case R.id.button_plus:
                incrementRoundScore(1);
                break;
            }
    }

    public void incrementScore() {
        playerScore.setScore(playerScore.getScore() + playerScore.getRoundScore());
        playerScore.addRoundScores(playerScore.getRoundScore());
        roundScoreTextView.setText("0"); //TODO change to a default score
        playerScore.setRoundScore(0); //TODO change to a default score
        scoreTextView.setText(Long.toString(playerScore.getScore()));

    }

    private void incrementRoundScore(final int delta) {
        playerScore.setRoundScore(playerScore.getRoundScore() + delta);
        roundScoreTextView.setText(Long.toString(playerScore.getRoundScore()));
    }

    private int showAdditionalDeltasDialog(boolean positive){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle(positive ? R.string.title_add : R.string.title_subtract);

        final EditText number = new EditText(context);
        number.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        number.setText("0");
        number.setSelection(0, number.getText().length());
        alert.setView(number);
        if (positive) {
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!TextUtils.isEmpty(number.getText())) {
                        int num = Integer.parseInt(number.getText().toString());
                        incrementRoundScore(num);
                    }
                    dialog.dismiss();
                }
            });
        }
        else {
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!TextUtils.isEmpty(number.getText())) {
                        int num = Integer.parseInt(number.getText().toString());
                        incrementRoundScore(num*-1);
                    }
                    dialog.dismiss();
                }
            });
        }


        alert.setNegativeButton("Cancel", null);

        AlertDialog myDialog = alert.create();
        myDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        myDialog.show();

        //alert.show();

        return 0;
    }


    @Override
    public boolean onLongClick(View view) {
        // on long click, start up the additional delta values popup

        switch (view.getId()) {
            case R.id.button_plus:
                int numToAdd = showAdditionalDeltasDialog(true);
                incrementRoundScore(numToAdd);
                break;
            case R.id.button_minus:
                int numToSub = showAdditionalDeltasDialog(false);
                incrementRoundScore(numToSub*-1);
                break;
            }

        return false;
    }



}
