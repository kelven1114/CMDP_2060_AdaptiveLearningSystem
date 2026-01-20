public abstract class Lesson{
    private String lessonId;
    private String title;
    private int difficultyLevel;
    private boolean completionStatus = false;

    //public Lesson()
    public Lesson(String lessonId, String title, int difficultyLevel) 
    {
        this.lessonId = lessonId;
        this.title = title;
        this.difficultyLevel = difficultyLevel;
    }

    public abstract int evaluateAnswer(String userAnswer);
    public abstract String getHint();
    public abstract String displayLesson();
    

    public boolean markCompleted(int score) {
        if (score > 0) {
            completionStatus = true;
        }

        return completionStatus;
    }


    public String getTitle(){
        return title;
    }

    public int getDifficultyLevel(){
        return difficultyLevel;
    }

    public String getLessonId(){
        return lessonId;
    }

    public boolean isCompleted(){
        return completionStatus;
    }
}