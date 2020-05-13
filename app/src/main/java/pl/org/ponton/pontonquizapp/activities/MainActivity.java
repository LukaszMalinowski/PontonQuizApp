package pl.org.ponton.pontonquizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.org.ponton.pontonquizapp.R;
import pl.org.ponton.pontonquizapp.levels.Level;
import pl.org.ponton.pontonquizapp.user.User;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private Button buttonLevel1;

    private Button buttonLevel2;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        User.loadUser(preferences.getInt("score", 0));

        initScoreText();

        initSelectLevelButtons();
    }

    private void initSelectLevelButtons() {
        buttonLevel1 = findViewById(R.id.button_level1);
        buttonLevel2 = findViewById(R.id.button_level2);

        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL1);

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL2);

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        //TODO lock buttons if score is not big enough
        buttonLevel2.setClickable(false);
    }

    private void initScoreText() {
        scoreTextView = findViewById(R.id.textView_score);
        scoreTextView.setText("aktualny wynik: " + User.getUser().getScore());
    }
}
