import java.util.*;

public class Student
{
    private int studentId;
    private String name;
    private int age;
    private int currentLevel;
    private ArrayList<Integer> progressScores;

    public Student( int studentId, String name, int age, int currentLevel, ArrayList<Integer> progressScores )
    {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.currentLevel = currentLevel;
        this.progressScores = progressScores;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public void setCurrentLevel( int level )
    {
        if( level >= 1 && level <= 3 )
        {
            this.currentLevel = level;
        }
    }

    public void updateProgress( int score )
    {
        progressScores.add( score );
        checkAndUpdateLevel();
    }

    public void checkAndUpdateLevel()
    {
        double averageScore = getAverageScore();
        
        if( currentLevel == 1 && averageScore >= 60.0 )
        {
            currentLevel = 2;
        }
        else if( currentLevel == 2 && averageScore >= 80.0 )
        {
            currentLevel = 3;
        }
    }

    public String getStatistics()
    {
        double avgScore = getAverageScore();
        int totalLessons = progressScores.size();
        int correctAnswers = 0;
        
        for( int score : progressScores )
        {
            if( score == 100 )
            {
                correctAnswers++;
            }
        }
        
        return String.format(
            "Student: %s\n" +
            "Age: %d\n" +
            "Current Level: %d\n" +
            "Total Lessons Completed: %d\n" +
            "Perfect Scores: %d\n" +
            "Average Score: %.0f%%\n" +
            "Progress Scores: %s",
            name, age, currentLevel, totalLessons, correctAnswers, 
            avgScore, progressScores.toString()
        );
    }

    public ArrayList<Integer> getProgressScores()
    {
        return progressScores;
    }

    public double getAverageScore()
    {
        if( progressScores.isEmpty() )
        {
            return 0.0;
        }

        int total = 0;
        for( int score : progressScores )
        {
            total += score;
        }

        double average = ( double ) total / progressScores.size();
        
        return Math.ceil( average );
    }
}