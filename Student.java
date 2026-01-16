import java.util.*;

public class Student {
    // CLASS FIELDS

    private int studentId;
    private String name;
    private int age;
    private int currentLevel;
    private ArrayList<Integer> progressScores;

    
    //PARAMETER CONSTRUCTOR
    
    public Student(int studentId, String name, int age, int currentLevel,ArrayList<Integer> progressScores ) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.currentLevel =currentLevel;
        this.progressScores = progressScores;
    }

    // METHOD: updateProgress
    public void updateProgress(int score) {
        if (score >= 0 && score <= 60) {
            progressScores.add(score);// move to method getProgressScore
        } else {
            System.out.println("Invalid Score.");
        }
    }


    // METHOD: getStatistics
    public String getStatistics() {
    double avgScore = getAverageScore();

    int totalLessons = progressScores.size();

    String statisticsReport =
            "Student: " + name +
            ", Level: " + currentLevel +
            ", Average Score: " + avgScore + "%" +
            ", Total Lessons: " + totalLessons;
            
        return statisticsReport;
    }
    
    // METHOD: setCurrentLevel
    
    public void setCurrentLevel(int score) {
        if (score >= 30) {
            currentLevel++;
        }
    }

    // METHOD: getCurrentLevel

    public int getCurrentLevel() {
        return currentLevel;
    }


    // METHOD: getProgressScore

    public ArrayList<Integer> getProgressScore() {
        return progressScores;
    }


    //METHOD:getAverageScore
    public double getAverageScore() {

    if (progressScores.isEmpty()) {
        return 0.0;
    }

    int total = 0;
    for (int score : progressScores) {
        total += score;
    }

    return (double) total / progressScores.size();
    }
}
