package pl.org.ponton.pontonquizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.org.ponton.pontonquizapp.R;
import pl.org.ponton.pontonquizapp.exceptions.NoMoreQuestionsException;
import pl.org.ponton.pontonquizapp.exceptions.NoMoreWrongQuestionsException;
import pl.org.ponton.pontonquizapp.levels.Level;
import pl.org.ponton.pontonquizapp.questions.AnswerButton;
import pl.org.ponton.pontonquizapp.questions.QuestionWrapper;
import pl.org.ponton.pontonquizapp.user.User;

public class QuestionActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    private List<AnswerButton> buttonList;

    private Button buttonNextLevel;

    private Level level;

    private QuestionWrapper question;

    private boolean addQuestions = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        editor = preferences.edit();

        initButtonBack();

        level = Level.getInstance();

        try {
            question = level.getQuestion();
        } catch (NoMoreQuestionsException e) {
            try {
                addQuestions = false;
                question = level.getWrongQuestions();
            } catch (NoMoreWrongQuestionsException ex) {
                editor.putInt("score", User.getUser().getScore());
                editor.commit();
                onBackPressed();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        initButtons();

        initQuestionText();

        addButtonListeners();

        initNextQuestionButton();
    }

    private void initNextQuestionButton() {
        buttonNextLevel = findViewById(R.id.button_next);

        buttonNextLevel.setVisibility(View.INVISIBLE);

        buttonNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void addButtonListeners() {
        for (final AnswerButton button : buttonList) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setChecked(true);
                    buttonNextLevel.setVisibility(View.VISIBLE);
                    buttonNextLevel.setClickable(true);
                    confirmActionHandler(v);
                }
            });
        }
    }

    private void initQuestionText() {
        TextView questionTextView = findViewById(R.id.textView_questionValue);

        questionTextView.setText(question.getQuestion());
    }

    private void initButtons() {
        buttonList = new ArrayList<>();

        buttonList.add((AnswerButton) findViewById(R.id.button_top_left));
        buttonList.add((AnswerButton) findViewById(R.id.button_top_right));
        buttonList.add((AnswerButton) findViewById(R.id.button_bottom_left));
        buttonList.add((AnswerButton) findViewById(R.id.button_bottom_right));

        for (AnswerButton button : buttonList) {
            button.setChecked(false);
            button.setCorrect(false);
            button.getBackground().clearColorFilter();
        }

        List<AnswerButton> tempList = new ArrayList<>();

        tempList.add(buttonList.get(0));
        tempList.add(buttonList.get(1));
        tempList.add(buttonList.get(2));
        tempList.add(buttonList.get(3));

        Random random = new Random();

        List<String> tempAnswers = new ArrayList();

        tempAnswers.addAll(question.getAnswers());

        while (!tempList.isEmpty()) {

            int randomIndex = random.nextInt(tempList.size());

            AnswerButton button = tempList.get(randomIndex);

            tempList.remove(randomIndex);

            randomIndex = random.nextInt(tempAnswers.size());
            String answer = tempAnswers.get(randomIndex);
            tempAnswers.remove(randomIndex);

            if(answer.startsWith("T"))
                button.setCorrect(true);

            answer = answer.substring(1);

            button.setText(answer);
        }
    }

    private void initButtonBack() {
        Button buttonBack = findViewById(R.id.button_return);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void confirmActionHandler(View v) {
        for (AnswerButton button : buttonList) {
            for (AnswerButton tempButton : buttonList) {
                tempButton.setClickable(false);

                if(button.isCorrect())
                    button.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }

            if(!button.isChecked())
                continue;

            if(button.isCorrect()) {
                User.getUser().setScore(User.getUser().getScore() + 100);
            }
            else {
                button.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                if(addQuestions)
                    level.addWrongQuestion(question);
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
