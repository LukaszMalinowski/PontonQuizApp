package pl.org.ponton.pontonquizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import pl.org.ponton.pontonquizapp.R;
import pl.org.ponton.pontonquizapp.levels.Level;
import pl.org.ponton.pontonquizapp.user.User;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private Button buttonMenu;

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

        initMenu();

        initScoreText();

        initSelectLevelButtons();
    }

    private void initMenu() {
        buttonMenu = findViewById(R.id.button_menu);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.popupMenuStyle);
                PopupMenu popup = new PopupMenu(wrapper, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_quiz:
                                //TODO
                                return true;

                            case R.id.item_o_nas:
                                //TODO
                                return true;

                            case R.id.item_kontakt:
                                //TODO
                                return true;

                            case R.id.item_web_page:
                                Intent openWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ponton.org.pl/"));
                                startActivity(openWebPage);
                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }
        });
    }

    private void initSelectLevelButtons() {
        buttonLevel1 = findViewById(R.id.button_level1);
        buttonLevel2 = findViewById(R.id.button_level2);

        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL1);

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                intent.putExtra(QuestionActivity.QUESTION_COUNT_EXTRA_VALUE, 1);

                startActivity(intent);
            }
        });

        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL2);

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                intent.putExtra(QuestionActivity.QUESTION_COUNT_EXTRA_VALUE, 1);

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
