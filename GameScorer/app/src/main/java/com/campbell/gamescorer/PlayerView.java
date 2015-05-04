package com.campbell.gamescorer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Campbell on 27/04/2015.
 */
public class PlayerView implements View.OnClickListener, View.OnLongClickListener {

    private PlayerScore playerScore;

    private TextView scoreTextView, nameTextView;

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
                increment(-1);
                break;
            case R.id.button_plus:
                increment(1);
                break;
            }
    }

    private void increment(final int delta) {
        playerScore.setScore(playerScore.getScore() + delta);
        scoreTextView.setText(Long.toString(playerScore.getScore()));
    }

    @Override
    public boolean onLongClick(View view) {
        // on long click, start up the additional delta values popup

        switch (view.getId()) {
            case R.id.button_plus:
                //showAdditionalDeltasPopup(true);
                return true;
            case R.id.button_minus:
                //showAdditionalDeltasPopup(false);
                return true;
            }

        return false;
    }



}
