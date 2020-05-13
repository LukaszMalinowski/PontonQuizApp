package pl.org.ponton.pontonquizapp.questions;

import android.content.Context;
import android.util.AttributeSet;

public class AnswerButton extends androidx.appcompat.widget.AppCompatButton {

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
}
