import java.util.ArrayList;

public class PatternMatchingLesson extends Lesson
{
    private String[] pattern;

    public PatternMatchingLesson( String lessonId, String title, int difficultyLevel, String[] pattern )
    {
        super( lessonId, title, difficultyLevel );
        this.pattern = pattern;
    }

    @Override
    public int evaluateAnswer( String userAnswer )
    {
        // Clean up the user's answer
        String[] userShapes = parseUserAnswer( userAnswer, pattern );
        
        int correctCount = 0;
        int totalShapes = pattern.length;
        
        // Compare each shape
        for( int i = 0; i < Math.min( totalShapes, userShapes.length ); i++ )
        {
            if( userShapes[i].equalsIgnoreCase( pattern[i] ) )
            {
                correctCount++;
            }
        }
        
        if( totalShapes > 0 )
        {
            double scorePercentage = ( ( double ) correctCount / totalShapes ) * 100;
            return ( int ) Math.round( scorePercentage );
        }
        
        return 0;
    }

    // Helper method to parse user answer, now with shape boundary detection
    private String[] parseUserAnswer( String userAnswer, String[] expectedPattern )
    {
        // First, clean the input
        String cleanAnswer = userAnswer.replaceAll( "[,\\s]+", "" ).toLowerCase();
        
        ArrayList<String> userShapes = new ArrayList<>();
        
        // Try to match known shape names from the pattern
        int position = 0;
        while( position < cleanAnswer.length() )
        {
            boolean foundMatch = false;
            
            // Try each shape in the pattern to see if it matches at the current position
            for( String expectedShape : expectedPattern )
            {
                String lowerShape = expectedShape.toLowerCase();
                if( cleanAnswer.startsWith( lowerShape, position ) )
                {
                    userShapes.add( expectedShape ); // Store with original case
                    position += lowerShape.length();
                    foundMatch = true;
                    break;
                }
            }
            
            // If no shape matched, check for common shape endings
            if( !foundMatch )
            {
                // Try to find the next capital letter (if any mixed case)
                String remaining = cleanAnswer.substring( position );
                
                // Try common shape endings
                if( remaining.startsWith( "circle" ) )
                {
                    userShapes.add( "Circle" );
                    position += 6;
                }
                else if( remaining.startsWith( "square" ) )
                {
                    userShapes.add( "Square" );
                    position += 6;
                }
                else if( remaining.startsWith( "triangle" ) )
                {
                    userShapes.add( "Triangle" );
                    position += 8;
                }
                else if( remaining.startsWith( "star" ) )
                {
                    userShapes.add( "Star" );
                    position += 4;
                }
                else
                {
                    // No match found, take the next character as part of current shape
                    if( userShapes.isEmpty() )
                    {
                        // Start a new shape
                        userShapes.add( cleanAnswer.substring( position, Math.min( position + 1, cleanAnswer.length() ) ) );
                    }
                    else
                    {
                        // Append to last shape
                        String lastShape = userShapes.get( userShapes.size() - 1 );
                        userShapes.set( userShapes.size() - 1, lastShape + cleanAnswer.charAt( position ) );
                    }
                    position++;
                }
            }
        }
        
        return userShapes.toArray( new String[0] );
    }

    @Override
    public String getHint()
    {
        if( pattern.length > 0 )
        {
            return "The pattern starts with: " + pattern[0];
        }
        
        return "";
    }

    @Override
    public String displayLesson()
    {
        StringBuilder display = new StringBuilder();
        display.append( "Memorize and recreate this pattern:\n" );
        
        for( int i = 0; i < pattern.length; i++ )
        {
            display.append( pattern[i] );
            if( i < pattern.length - 1 )
            {
                display.append( ", " );
            }
        }
        
        return display.toString();
    }

    public String[] getPattern()
    {
        return pattern.clone();
    }
}