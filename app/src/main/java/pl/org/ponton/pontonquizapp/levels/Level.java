package pl.org.ponton.pontonquizapp.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import pl.org.ponton.pontonquizapp.exceptions.NoMoreQuestionsException;
import pl.org.ponton.pontonquizapp.exceptions.NoMoreWrongQuestionsException;
import pl.org.ponton.pontonquizapp.questions.QuestionWrapper;

public class Level {

    private static Level level = new Level();

    private Random random;

    public enum LevelType {LEVEL1, LEVEL2, LEVEL3}

    private List<QuestionWrapper> questions;

    private List<QuestionWrapper> wrongQuestions;

    private Level() {
        random = new Random();
    }

    public static Level getInstance() {
        return level;
    }

    public void loadLevel(LevelType levelType) {
        switch (levelType) {
            case LEVEL1:
                loadLevel1();
                break;
            case LEVEL2:
                loadLevel2();
                break;
            case LEVEL3:
                loadLevel3();
                break;
        }
    }

    private void loadLevel3() {
        questions = new ArrayList<>();
        wrongQuestions = new ArrayList<>();

        questions.add(new QuestionWrapper("Jak nazywa się obecny prezydent Polski?",
                Arrays.asList("TAndrzej Duda","FAdrian Dupa","FBronisław Komorowski","FJarosław Kaczyński")));
        questions.add(new QuestionWrapper("Najlepszy energetyk to - ",
                Arrays.asList("TMonster","FTiger","FBlack","FBurn")));
        questions.add(new QuestionWrapper("Kto jest dyrektorem artystycznym Teatru Muzycznego Roma?",
                Arrays.asList("TWojciech Kępczyński","FSebastian Gonciarz","FEwa Bara","FEwelina Kulawiuk")));
        questions.add(new QuestionWrapper("Do których z poniższych teamów NIE należał Dylan Rieder?",
                Arrays.asList("TNike SB","FHuf","FFucking Awesome","FSpitfire")));
    }

    private void loadLevel2() {
        questions = new ArrayList<>();
        wrongQuestions = new ArrayList<>();

        questions.add(new QuestionWrapper("2 * 2 = ?",
                Arrays.asList("F1","F6","T4","F22")));
        questions.add(new QuestionWrapper("Wonsz żeczny -",
                Arrays.asList("FW kolory tęczy","TJest niebezpieczny","FJest bardzo beczny","FMa ząbek mleczny")));
        questions.add(new QuestionWrapper("Nie będziemy tu wpierdalać z jednej wazy -",
                Arrays.asList("FBo szanujesz płazy","FZjadłbym sobie zrazy","FPotrzebuję metrowej gazy","TBo nie czaisz bazy")));
        questions.add(new QuestionWrapper("Jak nazywała się postać grana w spektaklu \"Piloci\" przez Zofię Nowakowską?",
                Arrays.asList("FBasia","FZofia","FGrażyna","TNina")));
    }

    private void loadLevel1() {
        questions = new ArrayList<>();
        wrongQuestions = new ArrayList<>();

        questions.add(new QuestionWrapper("Jaka metoda najskuteczniej chroni przed zakażeniem wirusem HIV?",
                Arrays.asList("Ftabletki","Fwkładka","Tprezerwatywa","Fplastry")));
        questions.add(new QuestionWrapper("Jakie owady przenoszą wirusa HIV?",
                Arrays.asList("Fkomary","Fmuchy","Fpająki","Towady nie przenoszą wirusa HIV")));
        questions.add(new QuestionWrapper("Zakazić się wirusem HIV czy zarazić? Która forma jest poprawna?",
                Arrays.asList("Tzakazić","Fzarazić","Fobie są poprawne","Fżadna nie jest poprawna")));
        questions.add(new QuestionWrapper("Ile żyją osoby z wirusem HIV, które poddają się leczeniu?",
                Arrays.asList("Ttyle samo, co osoby zdrowe","Fśrednio 10 lat krócej niż osoby zdrowe","Fśrednio 15 lat krócej niż osoby zdrowe","Fśrednio 5 lat dłużej niż osoby zdrowe")));
        questions.add(new QuestionWrapper("Jakie dokumenty trzeba przynieść na badanie w punkcie konsultacyjno-diagnostycznym?",
                Arrays.asList("Fdowód osobisty","Fubezpieczenie","Fskierowanie od lekarza","Tnie trzeba przynosić żadnych dokumentów")));
        questions.add(new QuestionWrapper("Ile kosztuje badanie na HIV w punktach konsultacyjno-diagnostycznych?",
                Arrays.asList("F20 zł","F45 zł","F15 zł","Tbadanie jest bezpłatne")));
        questions.add(new QuestionWrapper("Poprzez kontakt z którą substancją nie można się zakazić?",
                Arrays.asList("Tślina","Fkrew","Fśluz szyjkowy","Fpreejakulat")));
    }

    public QuestionWrapper getQuestion() throws NoMoreQuestionsException {
        if(questions == null || questions.isEmpty())
            throw new NoMoreQuestionsException("Brak pytań");

        int randIndex = random.nextInt(questions.size());

        QuestionWrapper questionWrapper = questions.get(randIndex);

        questions.remove(randIndex);

        return questionWrapper;
    }

    public QuestionWrapper getWrongQuestions() throws NoMoreWrongQuestionsException {
        if(wrongQuestions == null || wrongQuestions.isEmpty())
            throw new NoMoreWrongQuestionsException("Brak pytań");

        int randIndex = random.nextInt(wrongQuestions.size());

        QuestionWrapper questionWrapper = wrongQuestions.get(randIndex);

        wrongQuestions.remove(randIndex);

        return questionWrapper;
    }

    public void addWrongQuestion(QuestionWrapper question) {
        wrongQuestions.add(question);
    }
}
