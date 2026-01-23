import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class AdaptiveLearningSystem
{
    private static JFrame mainFrame;
    private static List<PatternMatchingLesson> patternMatchingLessons;
    private static PatternMatchingLesson currentPatternLesson;
    private static List<WordBuildingLesson> wordBuildingLessons;
    private static WordBuildingLesson currentWordLesson;
    private static Student currentStudent;
    
    private static JTextField idField;
    private static JTextField nameField;
    private static JTextField ageField;
    private static JLabel errorLabel;


    // Launch application
    public static void main( String[] args )
    {
        showStudentRegistration();
    }


    // Display registration
    private static void showStudentRegistration()
    {
        mainFrame = createFrame( "Adaptive Learning System - Welcome", 500, 400 );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JPanel mainPanel = createMainPanel();
        JPanel titlePanel = createTitlePanel( "Welcome to", "Adaptive Learning System" );
        JPanel centerPanel = createRegistrationCenterPanel();
        JPanel bottomPanel = createRegistrationBottomPanel();

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( centerPanel, BorderLayout.CENTER );
        mainPanel.add( bottomPanel, BorderLayout.SOUTH );

        mainFrame.add( mainPanel );
        mainFrame.setVisible( true );
    }


    // Create frame
    private static JFrame createFrame( String title, int width, int height )
    {
        JFrame frame = new JFrame( title );
        frame.setSize( width, height );
        frame.getContentPane().setBackground( Color.WHITE );
        frame.setLocationRelativeTo( null );
        return frame;
    }


    // Create panel
    private static JPanel createMainPanel()
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );
        return panel;
    }


    // Create title
    private static JPanel createTitlePanel( String line1, String line2 )
    {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout( new BoxLayout( titlePanel, BoxLayout.Y_AXIS ) );
        titlePanel.setBackground( Color.WHITE );
        titlePanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 30, 0 ) );

        JPanel line1Panel = createTitleLine( line1 );
        JPanel line2Panel = createTitleLine( line2 );
        line2Panel.setBorder( BorderFactory.createEmptyBorder( 1, 0, 12, 0 ) );

        titlePanel.add( line1Panel );
        titlePanel.add( line2Panel );

        return titlePanel;
    }


    // Create line
    private static JPanel createTitleLine( String text )
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( new Color( 70, 130, 180 ) );
        panel.setBorder( BorderFactory.createEmptyBorder( 12, 0, 12, 0 ) );
        panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel label = new JLabel( text, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        label.setForeground( Color.WHITE );
        panel.add( label, BorderLayout.CENTER );

        return panel;
    }


    // Create center
    private static JPanel createRegistrationCenterPanel()
    {
        JPanel panel = new JPanel( new GridLayout( 1, 2, 30, 0 ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 40, 20, 40, 20 ) );

        JButton newStudentButton = createStyledButton( "New Student", new Color( 60, 179, 113 ), 18 );
        JButton loadStudentButton = createStyledButton( "Load Student", new Color( 70, 130, 180 ), 18 );

        newStudentButton.setBorder( BorderFactory.createEmptyBorder( 80, 20, 80, 20 ) );
        loadStudentButton.setBorder( BorderFactory.createEmptyBorder( 80, 20, 80, 20 ) );

        newStudentButton.addActionListener( e -> showNewStudentForm() );
        loadStudentButton.addActionListener( e -> {
            mainFrame.dispose();
            showLoadStudentWindow();
        } );

        panel.add( newStudentButton );
        panel.add( loadStudentButton );

        return panel;
    }


    // Create button
    private static JButton createStyledButton( String text, Color color, int fontSize )
    {
        JButton button = new JButton( text );
        button.setFont( new Font( "Arial", Font.BOLD, fontSize ) );
        button.setBackground( color );
        button.setForeground( Color.WHITE );
        button.setFocusPainted( false );

        button.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                button.setBackground( color.darker() );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                button.setBackground( color );
            }
        } );

        return button;
    }


    // Create panel
    private static JPanel createRegistrationBottomPanel()
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        panel.setBackground( Color.WHITE );
        
        JButton exitButton = createStyledButton( "Exit", Color.GRAY, 14 );
        exitButton.setPreferredSize( new Dimension( 100, 35 ) );
        
        exitButton.addActionListener( e -> showExitConfirmation() );
        
        panel.add( exitButton );
        return panel;
    }


    // Confirm exit
    private static void showExitConfirmation()
    {
        int option = JOptionPane.showConfirmDialog(
            mainFrame,
            "Exit application?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION
        );

        if ( option == JOptionPane.YES_OPTION )
        {
            System.exit( 0 );
        }
    }


    // Show form
    private static void showNewStudentForm()
    {
        JDialog dialog = createDialog( mainFrame, "Create New Student", 500, 400 );
        
        JPanel mainPanel = createMainPanel();
        JPanel titlePanel = createDialogTitle( "Create Student Profile" );
        JPanel formPanel = createStudentFormPanel( dialog );
        JPanel buttonPanel = createStudentFormButtons( dialog );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( formPanel, BorderLayout.CENTER );
        mainPanel.add( buttonPanel, BorderLayout.SOUTH );

        dialog.add( mainPanel );
        dialog.setVisible( true );
    }


    // Create dialog
    private static JDialog createDialog( JFrame parent, String title, int width, int height )
    {
        JDialog dialog = new JDialog( parent, title, true );
        dialog.setSize( width, height );
        dialog.setLocationRelativeTo( parent );
        dialog.getContentPane().setBackground( Color.WHITE );
        return dialog;
    }


    // Create title
    private static JPanel createDialogTitle( String title )
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( new Color( 70, 130, 180 ) );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );

        JLabel label = new JLabel( title, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        label.setForeground( Color.WHITE );
        panel.add( label, BorderLayout.CENTER );

        return panel;
    }


    // Create panel
    private static JPanel createStudentFormPanel( JDialog dialog )
    {
        idField = new JTextField( 20 );
        nameField = new JTextField( 20 );
        ageField = new JTextField( 20 );
        errorLabel = new JLabel( "", SwingConstants.CENTER );
        
        idField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        nameField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        ageField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        errorLabel.setFont( new Font( "Arial", Font.PLAIN, 12 ) );
        errorLabel.setForeground( Color.RED );

        JPanel panel = new JPanel( new GridBagLayout() );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20 ) );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets( 10, 10, 10, 10 );
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addFormField( panel, gbc, "Student ID:", idField, 0 );
        addFormField( panel, gbc, "Name:", nameField, 1 );
        addFormField( panel, gbc, "Age:", ageField, 2 );

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add( errorLabel, gbc );

        return panel;
    }


    // Add field
    private static void addFormField( JPanel panel, GridBagConstraints gbc, String labelText, JTextField field, int row )
    {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        
        JLabel label = new JLabel( labelText );
        label.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        label.setForeground( Color.BLACK );
        panel.add( label, gbc );

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        
        field.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        panel.add( field, gbc );
    }


    // Create buttons
    private static JPanel createStudentFormButtons( JDialog dialog )
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 0 ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 0, 0 ) );

        JButton saveButton = createStyledButton( "Save Student", new Color( 60, 179, 113 ), 16 );
        saveButton.setPreferredSize( new Dimension( 150, 40 ) );

        JButton cancelButton = createStyledButton( "Cancel", Color.GRAY, 14 );
        cancelButton.setPreferredSize( new Dimension( 150, 40 ) );

        saveButton.addActionListener( e -> saveNewStudent( dialog ) );
        cancelButton.addActionListener( e -> dialog.dispose() );

        panel.add( saveButton );
        panel.add( cancelButton );

        return panel;
    }


    // Save student
    private static void saveNewStudent( JDialog dialog )
    {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        
        if ( validateStudentForm( idText, name, ageText, errorLabel ) )
        {
            try
            {
                int studentId = Integer.parseInt( idText );
                int age = Integer.parseInt( ageText );
                
                Student existingStudent = FileManager.loadStudent( idText );
                if ( existingStudent != null )
                {
                    handleExistingStudent( dialog, existingStudent, errorLabel );
                    return;
                }
                
                currentStudent = new Student( studentId, name, age, 1, new ArrayList<>() );
                FileManager.saveStudent( currentStudent );
                
                showSuccessMessage( dialog, name, studentId );
                dialog.dispose();
                mainFrame.dispose();
                createMainDashboard();
            }
            catch ( NumberFormatException exception )
            {
                if ( errorLabel != null ) errorLabel.setText( "Student ID and Age must be numbers." );
            }
        }
    }


    // Validate form
    private static boolean validateStudentForm( String id, String name, String age, JLabel errorLabel )
    {
        if ( id.isEmpty() || name.isEmpty() || age.isEmpty() )
        {
            if ( errorLabel != null ) errorLabel.setText( "Please fill in all fields." );
            return false;
        }
        
        try
        {
            int ageValue = Integer.parseInt( age );
            if ( ageValue <= 0 || ageValue > 120 )
            {
                if ( errorLabel != null ) errorLabel.setText( "Please enter a valid age (1-120)." );
                return false;
            }
        }
        catch ( NumberFormatException e )
        {
            if ( errorLabel != null ) errorLabel.setText( "Age must be a number." );
            return false;
        }
        
        return true;
    }


    // Handle existing
    private static void handleExistingStudent( JDialog dialog, Student existingStudent, JLabel errorLabel )
    {
        int option = JOptionPane.showConfirmDialog(
            dialog,
            "Student ID already exists. Load existing profile?",
            "Existing Student",
            JOptionPane.YES_NO_OPTION
        );

        if ( option == JOptionPane.YES_OPTION )
        {
            currentStudent = existingStudent;
            dialog.dispose();
            mainFrame.dispose();
            createMainDashboard();
        }
        else
        {
            if ( errorLabel != null ) errorLabel.setText( "Please use a different Student ID." );
        }
    }


    // Show success
    private static void showSuccessMessage( JDialog dialog, String name, int id )
    {
        JOptionPane.showMessageDialog(
            dialog,
            "Student profile created successfully!\nName: " + name + "\nID: " + id,
            "Registration Successful",
            JOptionPane.INFORMATION_MESSAGE
        );
    }


    // Load student
    private static void showLoadStudentWindow()
    {
        JFrame loadFrame = createFrame( "Load Existing Student", 500, 400 );
        loadFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        JPanel mainPanel = createMainPanel();
        JPanel titlePanel = createDialogTitle( "Select a Student Profile" );
        JPanel listPanel = createStudentListPanel( loadFrame );
        JPanel bottomPanel = createLoadStudentBottomPanel( loadFrame );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( listPanel, BorderLayout.CENTER );
        mainPanel.add( bottomPanel, BorderLayout.SOUTH );

        loadFrame.add( mainPanel );
        loadFrame.setVisible( true );
    }


    // Create list
    private static JPanel createStudentListPanel( JFrame frame )
    {
        JPanel listPanel = new JPanel();
        listPanel.setLayout( new BoxLayout( listPanel, BoxLayout.Y_AXIS ) );
        listPanel.setBackground( Color.WHITE );
        
        String[] studentList = FileManager.listStudents();
        
        if ( studentList.length == 0 )
        {
            JPanel noStudentsPanel = new JPanel( new BorderLayout() );
            noStudentsPanel.setBackground( new Color( 70, 130, 180 ) );
            noStudentsPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
            
            JLabel noStudentsLabel = new JLabel( "No student profiles found.", SwingConstants.CENTER );
            noStudentsLabel.setFont( new Font( "Arial", Font.PLAIN, 16 ) );
            noStudentsLabel.setForeground( Color.WHITE );
            noStudentsPanel.add( noStudentsLabel, BorderLayout.CENTER );
            
            listPanel.add( noStudentsPanel );
        }
        else
        {
            for ( String studentId : studentList )
            {
                listPanel.add( createStudentButton( studentId, frame ) );
                listPanel.add( Box.createVerticalStrut( 5 ) );
            }
        }
        
        return createScrollablePanel( listPanel );
    }


    // Create button
    private static JPanel createStudentButton( String studentId, JFrame frame )
    {
        JPanel studentPanel = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
        studentPanel.setBackground( Color.WHITE );
        studentPanel.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 10 ) );
        
        JButton button = createStyledButton( "Student ID: " + studentId, new Color( 70, 130, 180 ), 14 );
        button.setPreferredSize( new Dimension( 350, 40 ) );
        
        button.addActionListener( e -> loadStudent( studentId, frame ) );
        
        studentPanel.add( button );
        return studentPanel;
    }


    // Create scrollable
    private static JPanel createScrollablePanel( JPanel content )
    {
        JScrollPane scrollPane = new JScrollPane( content );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        scrollPane.setPreferredSize( new Dimension( 400, 250 ) );
        
        return new JPanel( new BorderLayout() )
        {
            {
                add( scrollPane, BorderLayout.CENTER );
                setBackground( Color.WHITE );
            }
        };
    }


    // Load student
    private static void loadStudent( String studentId, JFrame frame )
    {
        currentStudent = FileManager.loadStudent( studentId );
        if ( currentStudent != null )
        {
            frame.dispose();
            createMainDashboard();
        }
        else
        {
            JOptionPane.showMessageDialog(
                frame,
                "Error loading student profile.",
                "Load Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // Create panel
    private static JPanel createLoadStudentBottomPanel( JFrame frame )
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        panel.setBackground( Color.WHITE );
        
        JButton backButton = createStyledButton( "Back", Color.GRAY, 14 );
        backButton.setPreferredSize( new Dimension( 100, 35 ) );
        
        backButton.addActionListener( e -> {
            frame.dispose();
            showStudentRegistration();
        } );
        
        panel.add( backButton );
        return panel;
    }


    // Create dashboard
    public static void createMainDashboard()
    {
        patternMatchingLessons = FileManager.loadPatternLessons();
        wordBuildingLessons = FileManager.loadWordLessons();

        if ( mainFrame != null )
        {
            mainFrame.dispose();
        }

        mainFrame = createFrame( "Adaptive Learning System", 900, 700 );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setLayout( new BorderLayout() );

        JPanel topPanel = createDashboardTopPanel();
        JPanel mainButtonPanel = createDashboardButtonPanel();

        mainFrame.add( topPanel, BorderLayout.NORTH );
        mainFrame.add( mainButtonPanel, BorderLayout.CENTER );

        mainFrame.setVisible( true );
    }


    // Create panel
    private static JPanel createDashboardTopPanel()
    {
        JPanel topPanel = new JPanel( new BorderLayout() );
        topPanel.setBackground( Color.WHITE );
        topPanel.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 10 ) );

        // Center panel for title
        JPanel titlePanel = createDialogTitle( "Adaptive Learning System" );
        topPanel.add( titlePanel, BorderLayout.CENTER );

        // Logout button panel (top-right)
        JPanel logoutPanel = createLogoutPanel();
        topPanel.add( logoutPanel, BorderLayout.EAST );

        // Welcome panel below
        if ( currentStudent != null )
        {
            updateStudentLevel();
            FileManager.saveStudent( currentStudent );
            
            JPanel welcomePanel = createWelcomePanel();
            JPanel southPanel = new JPanel( new BorderLayout() );
            southPanel.setBackground( Color.WHITE );
            southPanel.add( welcomePanel, BorderLayout.CENTER );
            topPanel.add( southPanel, BorderLayout.SOUTH );
        }

        return topPanel;
    }


    // Create panel
    private static JPanel createLogoutPanel()
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        panel.setBackground( new Color( 70, 130, 180 ) ); // Set to blue to match title
        panel.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 10 ) );

        JButton logoutButton = new JButton( "Log Out" );
        logoutButton.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        logoutButton.setBackground( Color.RED );
        logoutButton.setForeground( Color.WHITE );
        logoutButton.setFocusPainted( false );
        logoutButton.setBorder( null );
        logoutButton.setMargin( new Insets( 0, 0, 0, 0 ) );
        logoutButton.setPreferredSize( new Dimension( 80, 30 ) );
        logoutButton.addActionListener( e -> logout() );

        logoutButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                logoutButton.setBackground( new Color( 200, 0, 0 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                logoutButton.setBackground( Color.RED );
            }
        } );

        panel.add( logoutButton );
        return panel;
    }


    // Logout user
    private static void logout()
    {
        int option = JOptionPane.showConfirmDialog(
            mainFrame,
            "Are you sure you want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if ( option == JOptionPane.YES_OPTION )
        {
            currentStudent = null;
            mainFrame.dispose();
            showStudentRegistration();
        }
    }


    // Create panel
    private static JPanel createWelcomePanel()
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( new Color( 70, 130, 180 ) );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
        panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        int currentLevel = currentStudent.getCurrentLevel();
        double averageScore = currentStudent.getAverageScore();
        
        String welcomeText = "Welcome, " + currentStudent.getName() + "! (Level: " + 
                           currentLevel + ", Average Score: " + 
                           String.format( "%.0f", averageScore ) + "%)";
        
        JLabel label = new JLabel( welcomeText, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.ITALIC, 18 ) );
        label.setForeground( Color.WHITE );
        panel.add( label, BorderLayout.CENTER );

        return panel;
    }


    // Create panel
    private static JPanel createDashboardButtonPanel()
    {
        JPanel panel = new JPanel( new GridLayout( 2, 2, 30, 30 ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 50, 20, 50 ) );

        Color buttonColor = new Color( 70, 130, 180 );
        
        JButton patternButton = createDashboardButton( "Pattern Matching", buttonColor );
        JButton wordButton = createDashboardButton( "Word Building", buttonColor );
        JButton progressButton = createDashboardButton( "View Progress", buttonColor );
        JButton exportButton = createDashboardButton( "Export Report", buttonColor );

        patternButton.addActionListener( e -> startPatternMatchingLesson() );
        wordButton.addActionListener( e -> startWordBuildingLesson() );
        progressButton.addActionListener( e -> showProgressWindow() );
        exportButton.addActionListener( e -> exportReport() );

        panel.add( patternButton );
        panel.add( wordButton );
        panel.add( progressButton );
        panel.add( exportButton );

        return panel;
    }


    // Create button
    private static JButton createDashboardButton( String text, Color color )
    {
        JButton button = new JButton( text );
        button.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        button.setForeground( Color.WHITE );
        button.setBackground( color );
        button.setFocusPainted( false );

        button.setBorder( BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder( Color.WHITE, 2 ),
            BorderFactory.createEmptyBorder( 80, 40, 80, 40 )
        ) );

        button.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                button.setBackground( color.darker() );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                button.setBackground( color );
            }
        } );

        return button;
    }


    // Start lesson
    private static void startPatternMatchingLesson()
    {
        selectPatternMatchingLesson();
        
        if ( currentPatternLesson == null )
        {
            JOptionPane.showMessageDialog( mainFrame, "Error loading lesson." );
            return;
        }
        
        createPatternMatchingWindow();
    }


    // Select lesson
    private static void selectPatternMatchingLesson()
    {
        if ( patternMatchingLessons == null || patternMatchingLessons.isEmpty() )
        {
            currentPatternLesson = null;
            return;
        }

        int userLevel = currentStudent.getCurrentLevel();
        
        if ( userLevel == 1 && patternMatchingLessons.size() > 0 )
        {
            currentPatternLesson = patternMatchingLessons.get( 0 );
        }
        else if ( userLevel == 2 && patternMatchingLessons.size() > 1 )
        {
            currentPatternLesson = patternMatchingLessons.get( 1 );
        }
        else if ( userLevel == 3 && patternMatchingLessons.size() > 2 )
        {
            currentPatternLesson = patternMatchingLessons.get( 2 );
        }
        else
        {
            currentPatternLesson = patternMatchingLessons.get( 0 );
        }
    }


    // Create window
    private static void createPatternMatchingWindow()
    {
        Color blueColor = new Color( 70, 130, 180 );
        
        // Adjust window size based on number of patterns
        int numShapes = currentPatternLesson.getPattern().length;
        int windowWidth = ( numShapes > 4 ) ? 700 : 600; // Wider for 5 shapes
        
        JDialog dialog = createLessonDialog( mainFrame, "Pattern Matching", windowWidth, 500, blueColor );
        
        JPanel mainPanel = createLessonMainPanel();
        JPanel instructionPanel = createLessonInstructionPanel( "Memorize this pattern:", blueColor );
        JPanel patternPanel = createShapePatternPanel();
        JPanel buttonPanel = createPatternMatchingReadyPanel( dialog, Color.GRAY );

        mainPanel.add( instructionPanel );
        mainPanel.add( Box.createVerticalStrut( 30 ) );
        mainPanel.add( patternPanel );
        mainPanel.add( Box.createVerticalGlue() );
        mainPanel.add( buttonPanel );

        dialog.add( mainPanel, BorderLayout.CENTER );
        dialog.setVisible( true );
    }


    // Create dialog
    private static JDialog createLessonDialog( JFrame parent, String title, int width, int height, Color color )
    {
        JDialog dialog = new JDialog( parent, title, true );
        dialog.setSize( width, height );
        dialog.setLocationRelativeTo( parent );
        dialog.getContentPane().setBackground( Color.WHITE );
        dialog.setLayout( new BorderLayout() );
        return dialog;
    }


    // Create panel
    private static JPanel createLessonMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 30, 20, 20, 20 ) );
        return panel;
    }


    // Create panel
    private static JPanel createLessonInstructionPanel( String text, Color color )
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( color );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
        panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel label = new JLabel( text, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        label.setForeground( Color.WHITE );
        panel.add( label, BorderLayout.CENTER );

        return panel;
    }


    // Create panel
    private static JPanel createShapePatternPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setBackground( Color.WHITE );
        panel.setAlignmentX( Component.CENTER_ALIGNMENT );
        panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 250 ) ); // Increased height

        // Create a horizontal panel for shapes
        JPanel shapesPanel = new JPanel();
        shapesPanel.setLayout( new FlowLayout( FlowLayout.CENTER, 20, 10 ) );
        shapesPanel.setBackground( Color.WHITE );
        shapesPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        // Calculate width based on number of patterns
        int numShapes = currentPatternLesson.getPattern().length;
        int panelWidth = numShapes * 120 + ( numShapes - 1 ) * 20; // 120 width per shape, 20 spacing
        shapesPanel.setPreferredSize( new Dimension( Math.min( panelWidth, 600 ), 150 ) );
        shapesPanel.setMaximumSize( new Dimension( Math.min( panelWidth, 600 ), 150 ) );

        for ( String shape : currentPatternLesson.getPattern() )
        {
            shapesPanel.add( createShapeWithLabel( shape, new Color( 70, 130, 180 ) ) );
        }

        panel.add( shapesPanel );
        return panel;
    }


    // Create shape
    private static JPanel createShapeWithLabel( String shape, Color color )
    {
        JPanel container = new JPanel();
        container.setLayout( new BoxLayout( container, BoxLayout.Y_AXIS ) );
        container.setBackground( Color.WHITE );
        container.setAlignmentX( Component.CENTER_ALIGNMENT );
        container.setAlignmentY( Component.CENTER_ALIGNMENT );
        
        // Calculate shape size based on number of patterns
        int numShapes = currentPatternLesson.getPattern().length;
        int shapeSize = ( numShapes > 4 ) ? 80 : 100;
        
        // Shape drawing (without text)
        JPanel shapePanel = createShapeDrawing( shape, color );
        shapePanel.setPreferredSize( new Dimension( shapeSize, shapeSize ) );
        shapePanel.setMinimumSize( new Dimension( shapeSize, shapeSize ) );
        shapePanel.setMaximumSize( new Dimension( shapeSize, shapeSize ) );
        
        // Center the shape panel
        JPanel shapeWrapper = new JPanel( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
        shapeWrapper.setBackground( Color.WHITE );
        shapeWrapper.add( shapePanel );
        
        container.add( shapeWrapper );
        
        // Add space between shape and label
        container.add( Box.createVerticalStrut( 5 ) );
        
        // Create label box for shape name (OUTSIDE the shape box)
        JLabel nameLabel = new JLabel( shape.substring( 0, 1 ).toUpperCase() + shape.substring( 1 ), SwingConstants.CENTER );
        nameLabel.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        nameLabel.setForeground( Color.BLACK );
        nameLabel.setOpaque( true );
        nameLabel.setBackground( Color.LIGHT_GRAY );
        nameLabel.setBorder( BorderFactory.createLineBorder( Color.BLACK, 1 ) );
        nameLabel.setPreferredSize( new Dimension( shapeSize, 25 ) );
        nameLabel.setMinimumSize( new Dimension( shapeSize, 25 ) );
        nameLabel.setMaximumSize( new Dimension( shapeSize, 25 ) );
        
        // Center the label
        JPanel labelWrapper = new JPanel( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
        labelWrapper.setBackground( Color.WHITE );
        labelWrapper.add( nameLabel );
        
        container.add( labelWrapper );
        return container;
    }


    // Draw shape
    private static JPanel createShapeDrawing( String shape, Color color )
    {
        JPanel shapePanel = new JPanel()
        {
            @Override
            protected void paintComponent( Graphics g )
            {
                super.paintComponent( g );
                Graphics2D g2d = ( Graphics2D ) g;
                g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
                
                int width = getWidth();
                int height = getHeight();
                int padding = 10;
                int drawWidth = width - 2 * padding;
                int drawHeight = height - 2 * padding;
                
                // Draw the shape ONLY (no text)
                g2d.setColor( color );
                
                switch ( shape.toLowerCase() )
                {
                    case "circle":
                        g2d.fillOval( padding, padding, drawWidth, drawHeight );
                        break;
                    case "square":
                        g2d.fillRect( padding, padding, drawWidth, drawHeight );
                        break;
                    case "triangle":
                        int[] xPoints = { width / 2, padding, width - padding };
                        int[] yPoints = { padding, height - padding, height - padding };
                        g2d.fillPolygon( xPoints, yPoints, 3 );
                        break;
                    case "star":
                        // Draw a simple 5-pointed star
                        int centerX = width / 2;
                        int centerY = height / 2;
                        int outerRadius = Math.min( drawWidth, drawHeight ) / 2;
                        int innerRadius = outerRadius / 2;
                        
                        int[] xPointsStar = new int[10];
                        int[] yPointsStar = new int[10];
                        
                        for ( int i = 0; i < 5; i++ )
                        {
                            double angle = Math.PI / 2 + i * 2 * Math.PI / 5;
                            xPointsStar[2 * i] = ( int ) ( centerX + outerRadius * Math.cos( angle ) );
                            yPointsStar[2 * i] = ( int ) ( centerY - outerRadius * Math.sin( angle ) );
                            
                            angle += Math.PI / 5;
                            xPointsStar[2 * i + 1] = ( int ) ( centerX + innerRadius * Math.cos( angle ) );
                            yPointsStar[2 * i + 1] = ( int ) ( centerY - innerRadius * Math.sin( angle ) );
                        }
                        
                        g2d.fillPolygon( xPointsStar, yPointsStar, 10 );
                        break;
                    default:
                        // Fallback to text if shape not recognized
                        g2d.setColor( Color.BLACK );
                        g2d.drawString( shape, width / 2 - 10, height / 2 );
                }
            }
        };
        
        shapePanel.setBackground( Color.WHITE );
        shapePanel.setBorder( BorderFactory.createLineBorder( Color.BLACK, 1 ) );
        
        return shapePanel;
    }


    // Create panel
    private static JPanel createPatternMatchingReadyPanel( JDialog dialog, Color color )
    {
        JPanel panel = new JPanel( new BorderLayout() );
        Color greenColor = new Color( 60, 179, 113 );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 50, 20, 30, 20 ) );

        JButton readyButton = createStyledButton( "I'm Ready!", greenColor , 16 );
        readyButton.setPreferredSize( new Dimension( 0, 40 ) );

        readyButton.addActionListener( e -> {
            dialog.dispose();
            showPatternMatchingTest();
        } );

        panel.add( readyButton, BorderLayout.CENTER );
        return panel;
    }


    // Show test
    private static void showPatternMatchingTest()
    {
        Color blueColor = new Color( 70, 130, 180 );
        JDialog testDialog = createTestDialog( mainFrame, "Pattern Matching Test", 500, 350, blueColor );
        
        JPanel mainPanel = createTestMainPanel();
        JPanel instructionPanel = createTestInstructionPanel( "Recreate the pattern (e.g., CircleSquareCircle):", blueColor );
        JTextField answerField = createAnswerField( blueColor );
        JPanel buttonPanel = createPatternMatchingTestButtonPanel( testDialog, answerField, blueColor );

        JPanel inputPanel = createTestInputPanel( instructionPanel, answerField );
        mainPanel.add( inputPanel );
        mainPanel.add( buttonPanel );

        testDialog.add( mainPanel );
        testDialog.setVisible( true );
        answerField.requestFocusInWindow();
    }


    // Create panel
    private static JPanel createPatternMatchingTestButtonPanel( JDialog dialog, JTextField field, Color color )
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 0 ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );

        JButton hintButton = createStyledButton( "Get Hint", color, 14 );
        hintButton.setPreferredSize( new Dimension( 100, 35 ) );

        JButton submit = createStyledButton( "Submit", color, 14 );
        submit.setPreferredSize( new Dimension( 100, 35 ) );

        JButton cancel = createStyledButton( "Cancel", Color.GRAY, 14 );
        cancel.setPreferredSize( new Dimension( 100, 35 ) );

        hintButton.addActionListener( e -> showPatternMatchingHint() );
        submit.addActionListener( e -> submitTestAnswer( dialog, field.getText().trim(), color ) );
        cancel.addActionListener( e -> dialog.dispose() );

        field.addActionListener( e -> submitTestAnswer( dialog, field.getText().trim(), color ) );
        dialog.getRootPane().setDefaultButton( submit );

        panel.add( hintButton );
        panel.add( submit );
        panel.add( cancel );

        return panel;
    }


    // Show hint
    private static void showPatternMatchingHint()
    {
        String hint = currentPatternLesson.getHint();
        JOptionPane.showMessageDialog(
            mainFrame,
            "Hint: " + hint,
            "Pattern Matching Hint",
            JOptionPane.INFORMATION_MESSAGE
        );
    }


    // Create dialog
    private static JDialog createTestDialog( JFrame parent, String title, int width, int height, Color color )
    {
        JDialog dialog = new JDialog( parent, title, true );
        dialog.setSize( width, height );
        dialog.setLocationRelativeTo( parent );
        dialog.getContentPane().setBackground( Color.WHITE );
        return dialog;
    }


    // Create panel
    private static JPanel createTestMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );
        return panel;
    }


    // Create panel
    private static JPanel createTestInstructionPanel( String text, Color color )
    {
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setBackground( color );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
        panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel label = new JLabel( text, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        label.setForeground( Color.WHITE );
        panel.add( label, BorderLayout.CENTER );

        return panel;
    }


    // Create field
    private static JTextField createAnswerField( Color color )
    {
        JTextField field = new JTextField();
        field.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        field.setMaximumSize( new Dimension( 400, 30 ) );
        field.setAlignmentX( Component.CENTER_ALIGNMENT );
        field.setBackground( Color.WHITE );
        field.setBorder( BorderFactory.createLineBorder( color, 2 ) );
        
        return field;
    }


    // Create panel
    private static JPanel createTestInputPanel( JPanel instructionPanel, JTextField answerField )
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 20, 0 ) );

        panel.add( instructionPanel );
        panel.add( Box.createVerticalStrut( 20 ) );
        panel.add( answerField );

        return panel;
    }


    // Submit answer
    private static void submitTestAnswer( JDialog dialog, String answer, Color color )
    {
        if ( answer.isEmpty() )
        {
            showWarningMessage( dialog, "Please enter an answer." );
            return;
        }

        dialog.dispose();
        
        if ( color.equals( new Color( 70, 130, 180 ) ) )
        {
            processPatternMatchingAnswer( answer );
        }
        else
        {
            processWordBuildingAnswer( answer );
        }
    }


    // Show warning
    private static void showWarningMessage( Component parent, String message )
    {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Input Required",
            JOptionPane.WARNING_MESSAGE
        );
    }


    // Process answer
    private static void processPatternMatchingAnswer( String userAnswer )
    {
        int score = currentPatternLesson.evaluateAnswer( userAnswer );
        updateStudentProgress( score );

        String feedback = generatePatternMatchingFeedback( userAnswer );
        showResultMessage( feedback, "Pattern Matching Results" );
    }


    // Generate feedback
    private static String generatePatternMatchingFeedback( String userAnswer )
    {
        String[] pattern = currentPatternLesson.getPattern();
        String[] userShapes = parseUserAnswer( userAnswer, pattern );
        
        int correctCount = 0;
        int totalShapes = pattern.length;
        
        StringBuilder feedback = new StringBuilder();
        feedback.append( "Pattern Matching Results:\n\n" );
        
        for ( int i = 0; i < totalShapes; i++ )
        {
            if ( i < userShapes.length && userShapes[i].equalsIgnoreCase( pattern[i] ) )
            {
                correctCount++;
                feedback.append( "✓ " ).append( pattern[i] ).append( " - Correct!\n" );
            }
            else
            {
                String userShape = i < userShapes.length ? userShapes[i] : "Missing";
                feedback.append( "✗ " ).append( pattern[i] )
                    .append( " (You entered: " ).append( userShape ).append( ")\n" );
            }
        }

        double scorePercentage = ( ( double ) correctCount / totalShapes ) * 100;
        
        feedback.append( "\nScore Breakdown:\n" );
        feedback.append( "Correct: " ).append( correctCount ).append( "/" ).append( totalShapes ).append( "\n" );
        feedback.append( "This lesson score: " ).append( String.format( "%.0f", scorePercentage ) ).append( "%\n" );
        
        if ( scorePercentage == 100 )
        {
            feedback.append( "\nPerfect score! Well done!" );
        }
        else if ( scorePercentage >= 60 )
        {
            feedback.append( "\nGood work! You're progressing well!" );
        }
        else
        {
            feedback.append( "\nKeep practicing! Every attempt helps!" );
        }

        return feedback.toString();
    }


    // Show results
    private static void showResultMessage( String message, String title )
    {
        JOptionPane.showMessageDialog(
            mainFrame,
            message,
            title,
            JOptionPane.INFORMATION_MESSAGE
        );
    }


    // Update progress
    private static void updateStudentProgress( int score )
    {
        if ( currentStudent != null )
        {
            currentStudent.updateProgress( score );
            
            updateStudentLevel();
            
            FileManager.saveStudent( currentStudent );
            
            createMainDashboard();
        }
    }


    // Start lesson
    private static void startWordBuildingLesson()
    {
        if ( wordBuildingLessons == null || wordBuildingLessons.isEmpty() )
        {
            showWarningMessage( mainFrame, "No word building lessons available." );
            return;
        }
        
        selectWordBuildingLesson();
        
        if ( currentWordLesson == null )
        {
            showWarningMessage( mainFrame, "Error loading lesson." );
            return;
        }
        
        showWordBuildingTest();
    }


    // Select lesson
    private static void selectWordBuildingLesson()
    {
        if ( wordBuildingLessons == null || wordBuildingLessons.isEmpty() )
        {
            currentWordLesson = null;
            return;
        }

        int userLevel = currentStudent.getCurrentLevel();
        
        List<WordBuildingLesson> levelLessons = new ArrayList<>();
        
        for ( WordBuildingLesson lesson : wordBuildingLessons )
        {
            if ( lesson.getDifficultyLevel() == userLevel )
            {
                levelLessons.add( lesson );
            }
        }
        
        if ( levelLessons.isEmpty() )
        {
            currentWordLesson = wordBuildingLessons.get( 0 );
        }
        else
        {
            Random random = new Random();
            int randomIndex = random.nextInt( levelLessons.size() );
            currentWordLesson = levelLessons.get( randomIndex );
        }
    }


    // Show test
    private static void showWordBuildingTest()
    {
        Color greenColor = new Color( 60, 179, 113 );
        JDialog testDialog = createTestDialog( mainFrame, "Word Building Test", 600, 400, greenColor );
        
        JPanel mainPanel = createWordBuildingTestMainPanel();
        
        int wordLength = currentWordLesson.getTargetWord().length();
        String instruction = "Rearrange these letters to form a " + wordLength + "-letter word:";
        
        JPanel instructionPanel = createTestInstructionPanel( instruction, greenColor );
        JPanel lettersPanel = createWordBuildingLettersPanel( greenColor );
        JTextField answerField = createAnswerField( greenColor );
        JPanel buttonPanel = createWordBuildingTestButtonPanel( testDialog, answerField, greenColor );

        mainPanel.add( instructionPanel );
        mainPanel.add( Box.createVerticalStrut( 20 ) );
        mainPanel.add( lettersPanel );
        mainPanel.add( Box.createVerticalStrut( 20 ) );
        mainPanel.add( answerField );
        mainPanel.add( Box.createVerticalStrut( 20 ) );
        mainPanel.add( buttonPanel );

        testDialog.add( mainPanel );
        testDialog.setVisible( true );
        answerField.requestFocusInWindow();
    }


    // Create panel
    private static JPanel createWordBuildingTestMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );
        return panel;
    }


    // Create panel
    private static JPanel createWordBuildingLettersPanel( Color color )
    {
        JPanel panel = new JPanel();
        panel.setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        panel.setBackground( Color.WHITE );
        panel.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        String[] scrambledLetters = currentWordLesson.getScrambledLetters();
        int wordLength = currentWordLesson.getTargetWord().length();
        
        if ( wordLength > 6 )
        {
            panel.setMaximumSize( new Dimension( 550, 100 ) );
        }
        else
        {
            panel.setMaximumSize( new Dimension( 550, 150 ) );
        }

        for ( String letter : scrambledLetters )
        {
            panel.add( createWordBuildingLetterLabel( letter, color, wordLength ) );
        }

        return panel;
    }


    // Create label
    private static JLabel createWordBuildingLetterLabel( String letter, Color color, int wordLength )
    {
        String displayLetter = letter.toUpperCase();
        
        JLabel label = new JLabel( displayLetter, SwingConstants.CENTER );
        label.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        
        int size;
        if ( wordLength > 8 )
        {
            size = 45;
        }
        else if ( wordLength > 6 )
        {
            size = 55;
        }
        else
        {
            size = 65;
        }
        
        label.setPreferredSize( new Dimension( size, size ) );
        label.setMinimumSize( new Dimension( size, size ) );
        label.setMaximumSize( new Dimension( size, size ) );
        
        label.setBorder( BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder( Color.BLACK, 1 ),
            BorderFactory.createEmptyBorder( 10, 10, 10, 10 )
        ) );
        label.setOpaque( true );
        label.setBackground( color );
        label.setForeground( Color.WHITE );

        return label;
    }


    // Create panel
    private static JPanel createWordBuildingTestButtonPanel( JDialog dialog, JTextField field, Color color )
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 0 ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );

        JButton hintButton = createStyledButton( "Get Hint", color, 14 );
        hintButton.setPreferredSize( new Dimension( 100, 35 ) );

        JButton submit = createStyledButton( "Submit", color, 14 );
        submit.setPreferredSize( new Dimension( 100, 35 ) );

        JButton cancel = createStyledButton( "Cancel", Color.GRAY, 14 );
        cancel.setPreferredSize( new Dimension( 100, 35 ) );

        hintButton.addActionListener( e -> showWordBuildingHint() );
        submit.addActionListener( e -> submitTestAnswer( dialog, field.getText().trim(), color ) );
        cancel.addActionListener( e -> dialog.dispose() );

        field.addActionListener( e -> submitTestAnswer( dialog, field.getText().trim(), color ) );
        dialog.getRootPane().setDefaultButton( submit );

        panel.add( hintButton );
        panel.add( submit );
        panel.add( cancel );

        return panel;
    }


    // Show hint
    private static void showWordBuildingHint()
    {
        String hint = currentWordLesson.getHint();
        JOptionPane.showMessageDialog(
            mainFrame,
            "Hint: " + hint,
            "Word Building Hint",
            JOptionPane.INFORMATION_MESSAGE
        );
    }


    // Process answer
    private static void processWordBuildingAnswer( String userAnswer )
    {
        int score = currentWordLesson.evaluateAnswer( userAnswer );
        updateStudentProgress( score );

        String feedback = generateWordBuildingFeedback( userAnswer );
        showResultMessage( feedback, "Word Building Results" );
    }


    // Generate feedback
    private static String generateWordBuildingFeedback( String userAnswer )
    {
        StringBuilder feedback = new StringBuilder();
        feedback.append( "Word Building Results:\n\n" );
        
        if ( currentWordLesson.evaluateAnswer( userAnswer ) == 100 )
        {
            feedback.append( "✓ Correct! Well done!\n" );
            feedback.append( "The word was: " ).append( currentWordLesson.getTargetWord() );
            feedback.append( "\n\nPerfect score! You formed the word correctly!" );
        }
        else
        {
            feedback.append( "✗ Incorrect answer\n" );
            feedback.append( "You entered: " ).append( userAnswer );
            feedback.append( "\nThe correct word was: " ).append( currentWordLesson.getTargetWord() );
            feedback.append( "\n\nKeep practicing! Try to unscramble the letters again!" );
        }

        return feedback.toString();
    }


    // Show progress
    private static void showProgressWindow()
    {
        if ( currentStudent == null )
        {
            showWarningMessage( mainFrame, "No student profile loaded." );
            return;
        }

        JDialog dialog = createDialog( mainFrame, "Student Progress Report", 600, 500 );
        
        JPanel mainPanel = createMainPanel();
        JPanel titlePanel = createDialogTitle( "Student Progress Report" );
        JPanel contentPanel = createProgressContentPanel();
        JPanel buttonPanel = createProgressButtonPanel( dialog );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( contentPanel, BorderLayout.CENTER );
        mainPanel.add( buttonPanel, BorderLayout.SOUTH );

        dialog.add( mainPanel );
        dialog.setVisible( true );
    }


    // Create content
    private static JPanel createProgressContentPanel()
    {
        JPanel panel = new JPanel( new GridBagLayout() );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets( 8, 10, 8, 10 );
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addProgressField( panel, gbc, "Student Name:", currentStudent.getName(), 0 );
        addProgressField( panel, gbc, "Student ID:", String.valueOf( currentStudent.getStudentId() ), 1 );
        addProgressField( panel, gbc, "Age:", String.valueOf( currentStudent.getAge() ), 2 );
        addProgressField( panel, gbc, "Current Level:", String.valueOf( currentStudent.getCurrentLevel() ), 3 );
        addProgressField( panel, gbc, "Average Score:", String.format( "%.0f%%", currentStudent.getAverageScore() ), 4 );
        addProgressField( panel, gbc, "Lessons Completed:", String.valueOf( currentStudent.getProgressScores().size() ), 5 );

        int perfectScores = calculatePerfectScores();
        addProgressField( panel, gbc, "Perfect Scores:", String.valueOf( perfectScores ), 6 );

        return panel;
    }


    // Calculate perfect
    private static int calculatePerfectScores()
    {
        int perfectScores = 0;
        for ( int score : currentStudent.getProgressScores() )
        {
            if ( score == 100 )
            {
                perfectScores++;
            }
        }
        return perfectScores;
    }


    // Add field
    private static void addProgressField( JPanel panel, GridBagConstraints gbc, String label, String value, int row )
    {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        
        JLabel labelComp = new JLabel( label );
        labelComp.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        labelComp.setForeground( Color.BLACK );
        panel.add( labelComp, gbc );

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel valueComp = new JLabel( value );
        valueComp.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        valueComp.setForeground( Color.DARK_GRAY );
        panel.add( valueComp, gbc );
    }


    // Create panel
    private static JPanel createProgressButtonPanel( JDialog dialog )
    {
        JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        panel.setBackground( Color.WHITE );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 20, 0 ) );

        JButton button = createStyledButton( "Close", new Color( 70, 130, 180 ), 14 );
        button.setPreferredSize( new Dimension( 100, 35 ) );

        button.addActionListener( e -> dialog.dispose() );

        panel.add( button );
        return panel;
    }


    // Export report
    private static void exportReport()
    {
        if ( currentStudent != null )
        {
            FileManager.exportReport( currentStudent );
            showResultMessage( "Report exported to reports/ directory!", "Export Successful" );
        }
        else
        {
            showWarningMessage( mainFrame, "No student profile to export." );
        }
    }


    // Update level
    private static void updateStudentLevel()
    {
        if ( currentStudent != null )
        {
            double averageScore = currentStudent.getAverageScore();
            int level;
            
            if ( averageScore >= 90 )
            {
                level = 3;
            }
            else if ( averageScore >= 60 )
            {
                level = 2;
            }
            else
            {
                level = 1;
            }
            
            currentStudent.setCurrentLevel( level );
            currentStudent.checkAndUpdateLevel();
        }
    }


    // Parse answer
    private static String[] parseUserAnswer( String userAnswer, String[] expectedPattern )
    {
        String cleanAnswer = userAnswer.replaceAll( "[,\\s]+", "" ).toLowerCase();
        
        ArrayList<String> userShapes = new ArrayList<>();
        
        int position = 0;
        while ( position < cleanAnswer.length() )
        {
            boolean foundMatch = false;
            
            for ( String expectedShape : expectedPattern )
            {
                String lowerShape = expectedShape.toLowerCase();
                if ( cleanAnswer.startsWith( lowerShape, position ) )
                {
                    userShapes.add( expectedShape );
                    position += lowerShape.length();
                    foundMatch = true;
                    break;
                }
            }
            
            if ( !foundMatch )
            {
                String remaining = cleanAnswer.substring( position );
                
                if ( remaining.startsWith( "circle" ) )
                {
                    userShapes.add( "Circle" );
                    position += 6;
                }
                else if ( remaining.startsWith( "square" ) )
                {
                    userShapes.add( "Square" );
                    position += 6;
                }
                else if ( remaining.startsWith( "triangle" ) )
                {
                    userShapes.add( "Triangle" );
                    position += 8;
                }
                else if ( remaining.startsWith( "star" ) )
                {
                    userShapes.add( "Star" );
                    position += 4;
                }
                else
                {
                    userShapes.add( cleanAnswer.substring( position, position + 1 ).toUpperCase() );
                    position++;
                }
            }
        }
        
        return userShapes.toArray( new String[0] );
    }
}