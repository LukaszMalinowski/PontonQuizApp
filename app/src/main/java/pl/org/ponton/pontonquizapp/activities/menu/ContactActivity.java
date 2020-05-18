package pl.org.ponton.pontonquizapp.activities.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import pl.org.ponton.pontonquizapp.R;
import pl.org.ponton.pontonquizapp.activities.MainActivity;

public class ContactActivity extends AppCompatActivity {

    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        initButtonBack();

        initMenu();
    }

    private void initButtonBack() {
        Button buttonBack = findViewById(R.id.button_return);

        System.out.println(buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

        buttonWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ponton.org.pl"));
                startActivity(intent);
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

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
