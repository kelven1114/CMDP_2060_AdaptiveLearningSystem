public class AdaptiveLearningSystem 
{
    public static void main ( String[] args ) 
    {
        String studentName = "Kelven";
        String studentID = "700052260";
        int totalLessons = 5;
        int age = 20;
        int scores = 90;
        int level = 2;
        double averageScore = 33.33;

        /*
        FileManager.saveStudent ( studentName, studentID, age, scores );
        FileManager.loadStudent ( studentName );
        FileManager.listStudents();
        FileManager.exportReport ( studentID, studentName, totalLessons, averageScore, level );
        */

        FileManager.loadPatternLessons();
        
    }
}