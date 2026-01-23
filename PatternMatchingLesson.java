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
        String[] userShapes = parseUserAnswer( userAnswer, pattern );
        
        int correctCount = 0;
        int totalShapes = pattern.length;
        
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

    
    // Helper
    private String[] parseUserAnswer( String userAnswer, String[] expectedPattern )
    {
        String cleanAnswer = userAnswer.replaceAll( "[,\\s]+", "" ).toLowerCase();
        
        ArrayList<String> userShapes = new ArrayList<>();
        
        // Match shapes
        int position = 0;
        while( position < cleanAnswer.length() )
        {
            boolean foundMatch = false;
            
            for( String expectedShape : expectedPattern )
            {
                String lowerShape = expectedShape.toLowerCase();
                if( cleanAnswer.startsWith( lowerShape, position ) )
                {
                    userShapes.add( expectedShape );
                    position += lowerShape.length();
                    foundMatch = true;
                    break;
                }
            }
            
            if( !foundMatch )
            {
                String remaining = cleanAnswer.substring( position );
                
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
                    if( userShapes.isEmpty() )
                    {
                        userShapes.add( cleanAnswer.substring( position, Math.min( position + 1, cleanAnswer.length() ) ) );
                    }
                    else
                    {
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