package com.example.myapplication2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    ImageButton switchButton;
    private Button[][] buttons = new Button[3][3];
    private boolean playerX = true; // Сначала X начинает игру
    private int roundCount = 0;
    private int playerXWins = 0;
    private int playerOWins = 0;
    private int draws = 0;
    private TextView textViewPlayerX;
    private TextView textViewPlayerO;
    private TextView textViewDraws;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences("Theme", MODE_PRIVATE);
        editor = settings.edit();
        switchButton = findViewById(R.id.imageButton);
        setContentView(R.layout.activity_main);

        textViewPlayerX = findViewById(R.id.text_view_player_x);
        textViewPlayerO = findViewById(R.id.text_view_player_o);
        textViewDraws = findViewById(R.id.text_view_draws);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGridButtonClick((Button) v);
                    }
                });
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetGame();
            }
        });
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTheme();
            }
        });
    }
    private void switchTheme() {
        boolean isNightMode = settings.getBoolean("NightMode", false);
        if (isNightMode) {
            // Switch to day theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor.putBoolean("NightMode", false);
            switchButton.setImageResource(R.drawable.ic_sun);
        } else {
            // Switch to night theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor.putBoolean("NightMode", true);
            switchButton.setImageResource(R.drawable.ic_night);
        }
        editor.apply();
    }

   private void onGridButtonClick(Button button) {
        if (!button.getText().toString().equals("")) {
            return;
        }

        if (playerX) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (playerX) {
                playerXWins++;
            } else {
                playerOWins++;
            }
            updateStats();
            endGame();
        } else if (roundCount == 9) {
            draws++;
            updateStats();
            endGame();
        } else {
            playerX = !playerX;
        }
    }

    private boolean checkForWin() {
        // Проверка выигрышных комбинаций
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private void endGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

        String message;
        if (playerX) {
            message = "Игрок X победил!";
        } else {
            message = "Игрок O победил!";
        }

        if (roundCount == 9) {
            message = "Ничья!";
        }

        // Показать сообщение о победе/ничьей (например, в AlertDialog)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Новая игра", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetGame();
                    }
                });
        builder.create().show();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }

        playerX = true;
        roundCount = 0;
        updateStats();
    }

    private void updateStats() {
        textViewPlayerX.setText("Игрок X: " + playerXWins);
        textViewPlayerO.setText("Игрок O: " + playerOWins);
        textViewDraws.setText("Ничьих: " + draws);
    }
}
