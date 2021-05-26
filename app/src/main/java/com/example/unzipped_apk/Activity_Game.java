package com.example.unzipped_apk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};

    public Activity_Game() {
        super();
    }

    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_game);
        final String idNumber = this.getIntent().getStringExtra(EXTRA_ID);
        if (idNumber.length() == this.steps.length) {
            int i = 0;
            while (true) {
                final int[] steps = this.steps;
                if (i >= steps.length) {
                    break;
                }
                steps[i] = Integer.valueOf(String.valueOf(idNumber.charAt(i))) % 4;
                ++i;
            }
        }
        this.findViews();
        this.initViews();
    }

    private void findViews() {
        this.arrows = new ImageButton[]{(ImageButton) findViewById(R.id.game_BTN_left),
                (ImageButton) findViewById(R.id.game_BTN_right),
                (ImageButton) findViewById(R.id.game_BTN_up),
                (ImageButton) findViewById(R.id.game_BTN_down)};
    }

    private void arrowClicked(int currentLevel) {
        if (this.goodToGo && currentLevel != this.steps[this.currentLevel]) {
            this.goodToGo = false;
        }
        currentLevel = this.currentLevel + 1;
        this.currentLevel += 1;
        if (currentLevel >= this.steps.length) {
            this.finishGame();
        }
    }

    private void finishGame() {
        final String stringExtra = this.getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText((Context) this, (CharSequence) ("Survived in " + stringExtra), Toast.LENGTH_LONG).show();
            Log.e("pttt", "Survived in " + stringExtra);
        } else {
            Toast.makeText((Context) this, (CharSequence) "You Failed ", Toast.LENGTH_LONG).show();
            Log.e("pttt", "You Failed");
        }
        this.finish();
    }

    private void initViews() {
        int i = 0;
        while (true) {
            ImageButton[] imageButtonArr = this.arrows;
            if (i < imageButtonArr.length) {
                final int finalI = i;
                imageButtonArr[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Activity_Game.this.arrowClicked(finalI);
                    }
                });
                i++;
            } else {
                return;
            }
        }
    }
}
