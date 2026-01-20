public class PatternMatchingLesson extends Lesson
{
    private String[] pattern;

    public PatternMatchingLesson ( String lessonId, String title, int difficultyLevel, String[] pattern )
    {
        super ( lessonId, title, difficultyLevel );
        this.pattern = pattern;
    }


    // Answer checking logic
    @Override
    public int evaluateAnswer ( String userAnswer )
    {
        String correctPattern = String.join ( ",", pattern );

        if ( userAnswer.replace ( " ", "" ).equalsIgnoreCase ( correctPattern ) )
        {
            markCompleted (1);
            return 1;
        }

        return 0;
    }


    @Override
    public String getHint ()
    {
        if ( pattern.length > 0 )
        {
            return "The pattern starts with: " + pattern[0];
        }

        return "";
    }


    @Override
    public String displayLesson ()
    {
        return "Memorize and recreate this pattern:\n" + String.join(", ", pattern);
    }


    public String[] getPattern ()
    {
        return pattern.clone ();
    }
}