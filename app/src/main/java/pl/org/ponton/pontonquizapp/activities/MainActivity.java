package pl.org.ponton.pontonquizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import pl.org.ponton.pontonquizapp.R;
import pl.org.ponton.pontonquizapp.activities.menu.AboutUsActivity;
import pl.org.ponton.pontonquizapp.activities.menu.ContactActivity;
import pl.org.ponton.pontonquizapp.activities.menu.QuizActivity;
import pl.org.ponton.pontonquizapp.levels.Level;
import pl.org.ponton.pontonquizapp.user.User;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private Button buttonKnowlageBook;

    private Button buttonMenu;

    private Button buttonLevel1;

    private Button buttonLevel2;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO add onBackPressed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        User.loadUser(preferences.getInt("score", 0));

        initMenu();

        initScoreText();

        initSelectLevelButtons();

        initKnowlageBookButton();
    }

    private void initKnowlageBookButton() {
        buttonKnowlageBook = findViewById(R.id.button_book);

        buttonKnowlageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KnowledgeBookActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void initMenu() {
        buttonMenu = findViewById(R.id.button_menu);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
    }

    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.menu_layout, null);

        final Button buttonWebPage = layout.findViewById(R.id.menu_button_web_page);

        final Button buttonAboutUs = layout.findViewById(R.id.menu_button_abous_us);

        final Button buttonContact = layout.findViewById(R.id.menu_button_contact);

        final Button buttonQuiz = layout.findViewById(R.id.menu_button_quiz);

        final Button buttonFacebook = layout.findViewById(R.id.menu_button_facebook);

        final Button buttonInstagram = layout.findViewById(R.id.menu_button_instagram);

        buttonWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewWebPageActivity("http://ponton.org.pl");
            }
        });

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    MainActivity.this.getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/40637122051"));
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/grupaponton"));
                }

                startActivity(intent);
            }
        });

        buttonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewWebPageActivity("https://www.instagram.com/grupaponton");
            }
        });

        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(AboutUsActivity.class);
            }
        });

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(ContactActivity.class);
            }
        });

        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(QuizActivity.class);
            }
        });

        layout.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        PopupWindow popup = new PopupWindow(layout, FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, true);
        popup.showAsDropDown(buttonMenu, 5, 5);
    }

    private void startNewWebPageActivity(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
