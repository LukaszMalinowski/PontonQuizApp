package pl.org.ponton.pontonquizapp.questions;

import java.util.List;

public class QuestionWrapper{

    private String question;
    private List<String> answers;

    public QuestionWrapper(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
