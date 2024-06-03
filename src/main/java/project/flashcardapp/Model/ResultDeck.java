package project.flashcardapp.Model;

public class ResultDeck {
    private String question;
    private String answer;
    private String youranswer;
    private String yourresult;

    public ResultDeck(String question, String answer,  String youranswer,  String yourresult) {
        this.question = question;
        this.answer = answer;
        this.youranswer = youranswer;
        this.yourresult = yourresult;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getYouranswer() {
        return youranswer;
    }
    public void setYouranswer(String youranswer) {
        this.youranswer = youranswer;
    }
    public String getYourresult() {
        return yourresult;
    }
    public void setYourresult(String yourresult) {
        this.yourresult = yourresult;
    }
}

