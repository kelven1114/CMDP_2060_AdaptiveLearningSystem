import java.util.*;

public class WordBuildingLesson extends Lesson {

    private String targetWord;
    private String[] scrambledLetters;

    public WordBuildingLesson(
        String lessonId,
        String title,
        int difficultyLevel,
        String targetWord) 
    {
        super(lessonId, title, difficultyLevel);
        this.targetWord = targetWord;
        scrambleLetters();
    }

    // compares user input with target word
    @Override
    public int evaluateAnswer(String userAnswer) {

        if (userAnswer.equalsIgnoreCase(targetWord)) {
            return 100;   // correct answer
        } else {
            return 0;     // incorrect answer
        }
    }

    // shuffle the letters of the target word
    private void scrambleLetters() {

        char[] chars = targetWord.toCharArray();
        Random rand = new Random();

        for (int i = chars.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        // convert char[] to String[]
        scrambledLetters = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            scrambledLetters[i] = String.valueOf(chars[i]);
        }
    }

    @Override
    public String displayLesson() {

        StringBuilder display = new StringBuilder();
        display.append("Rearrange the letters:\n");
        for (String letter : scrambledLetters) {
            display.append(letter).append(" ");
        }
        
        return display.toString().trim();
    }

    @Override
    public String getHint() {

        return "The first letter is: " + targetWord.charAt(0);
    }

    public String[] getScrambledLetters() {
        return scrambledLetters;
    }

    public String getTargetWord() {
        return targetWord;
    }
}