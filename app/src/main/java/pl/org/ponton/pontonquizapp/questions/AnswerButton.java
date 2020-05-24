package pl.org.ponton.pontonquizapp.questions;

import android.content.Context;
import android.util.AttributeSet;

import pl.org.ponton.pontonquizapp.R;

public class AnswerButton extends androidx.appcompat.widget.AppCompatButton {

    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOTTOM_LEFT = 2;
    private static final int BOTTOM_RIGHT = 3;

    private boolean isCorrect = false;

    private boolean isChecked = false;

    public AnswerButton(Context context) {
        super(context);
    }

    public AnswerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setCorrectBackground(int position) {
        switch (position) {
            case TOP_LEFT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_top_left_correct));
                break;

            case TOP_RIGHT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_top_right_correct));
                break;

            case BOTTOM_LEFT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_bottom_left_correct));
                break;

            case BOTTOM_RIGHT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_bottom_right_correct));
                break;
        }
    }

    public void setBadBackground(int position) {
        switch (position) {
            case TOP_LEFT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_top_left_bad));
                break;

            case TOP_RIGHT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_top_right_bad));
                break;

            case BOTTOM_LEFT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_bottom_left_bad));
                break;

            case BOTTOM_RIGHT:
                this.setBackground(getResources().getDrawable(R.drawable.answer_button_bottom_right_bad));
                break;
        }
    }
}
