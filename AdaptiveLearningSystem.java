import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class AdaptiveLearningSystem
{
    private static JFrame mainFrame;
    private static List<PatternMatchingLesson> patternMatchingLessons;
    private static PatternMatchingLesson currentPatternLesson;
    private static Student currentStudent;

    public static void main( String[] args )
    {
        showStudentRegistration();
    }

        private static void showStudentRegistration()
    {
        mainFrame = new JFrame( "Adaptive Learning System - Welcome" );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setSize( 500, 400 );
        mainFrame.getContentPane().setBackground( Color.WHITE );
        mainFrame.setLocationRelativeTo( null );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BorderLayout() );
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout( new BoxLayout( titlePanel, BoxLayout.Y_AXIS ) );
        titlePanel.setBackground( Color.WHITE );
        titlePanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 30, 0 ) );

        // First title line
        JPanel titleLine1Panel = new JPanel( new BorderLayout() );
        titleLine1Panel.setBackground( new Color( 70, 130, 180 ) );
        titleLine1Panel.setBorder( BorderFactory.createEmptyBorder( 12, 0, 12, 0 ) );
        titleLine1Panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel titleLine1 = new JLabel( "Welcome to", SwingConstants.CENTER );
        titleLine1.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        titleLine1.setForeground( Color.WHITE );
        titleLine1Panel.add( titleLine1, BorderLayout.CENTER );

        // Second title line - with reduced top border to remove the white line
        JPanel titleLine2Panel = new JPanel( new BorderLayout() );
        titleLine2Panel.setBackground( new Color( 70, 130, 180 ) );
        titleLine2Panel.setBorder( BorderFactory.createEmptyBorder( 1, 0, 12, 0 ) ); // Reduced top padding
        titleLine2Panel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel titleLine2 = new JLabel( "Adaptive Learning System", SwingConstants.CENTER );
        titleLine2.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        titleLine2.setForeground( Color.WHITE );
        titleLine2Panel.add( titleLine2, BorderLayout.CENTER );

        titlePanel.add( titleLine1Panel );
        titlePanel.add( titleLine2Panel );

        JPanel centerPanel = new JPanel( new GridLayout( 1, 2, 30, 0 ) );
        centerPanel.setBackground( Color.WHITE );
        centerPanel.setBorder( BorderFactory.createEmptyBorder( 40, 20, 40, 20 ) );

        JButton newStudentButton = new JButton( "New Student" );
        newStudentButton.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        newStudentButton.setBackground( new Color( 60, 179, 113 ) );
        newStudentButton.setForeground( Color.WHITE );
        newStudentButton.setFocusPainted( false );
        newStudentButton.setBorder( BorderFactory.createEmptyBorder( 80, 20, 80, 20 ) );

        JButton loadStudentButton = new JButton( "Load Student" );
        loadStudentButton.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        loadStudentButton.setBackground( new Color( 70, 130, 180 ) );
        loadStudentButton.setForeground( Color.WHITE );
        loadStudentButton.setFocusPainted( false );
        loadStudentButton.setBorder( BorderFactory.createEmptyBorder( 80, 20, 80, 20 ) );

        newStudentButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                newStudentButton.setBackground( new Color( 50, 159, 103 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                newStudentButton.setBackground( new Color( 60, 179, 113 ) );
            }
        } );

        loadStudentButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                loadStudentButton.setBackground( new Color( 50, 110, 160 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                loadStudentButton.setBackground( new Color( 70, 130, 180 ) );
            }
        } );

        newStudentButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                showNewStudentForm();
            }
        } );

        loadStudentButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                mainFrame.dispose();
                showLoadStudentWindow();
            }
        } );

        centerPanel.add( newStudentButton );
        centerPanel.add( loadStudentButton );

        JPanel bottomPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        bottomPanel.setBackground( Color.WHITE );
        
        JButton exitButton = new JButton( "Exit" );
        exitButton.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        exitButton.setPreferredSize( new Dimension( 100, 35 ) );
        
        exitButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Exit application?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );

                if( option == JOptionPane.YES_OPTION )
                {
                    System.exit( 0 );
                }
            }
        } );
        
        bottomPanel.add( exitButton );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( centerPanel, BorderLayout.CENTER );
        mainPanel.add( bottomPanel, BorderLayout.SOUTH );

        mainFrame.add( mainPanel );
        mainFrame.setVisible( true );
    }

        private static void showNewStudentForm()
    {
        JDialog studentFormDialog = new JDialog( mainFrame, "Create New Student", true );
        studentFormDialog.setSize( 500, 400 );
        studentFormDialog.setLocationRelativeTo( mainFrame );
        studentFormDialog.getContentPane().setBackground( Color.WHITE );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BorderLayout() );
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );

        // Title with blue box
        JPanel titlePanel = new JPanel( new BorderLayout() );
        titlePanel.setBackground( new Color( 70, 130, 180 ) );
        titlePanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );

        JLabel titleLabel = new JLabel( "Create Student Profile", SwingConstants.CENTER );
        titleLabel.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        titleLabel.setForeground( Color.WHITE );
        titlePanel.add( titleLabel, BorderLayout.CENTER );

        JPanel formPanel = new JPanel( new GridBagLayout() );
        formPanel.setBackground( Color.WHITE );
        formPanel.setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20 ) );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets( 10, 10, 10, 10 );
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Student ID Label (no blue box - just regular text)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel idLabel = new JLabel( "Student ID:" );
        idLabel.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        idLabel.setForeground( Color.BLACK );
        formPanel.add( idLabel, gbc );

        // Student ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        JTextField idField = new JTextField( 20 );
        idField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        formPanel.add( idField, gbc );

        // Name Label (no blue box)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        JLabel nameLabel = new JLabel( "Name:" );
        nameLabel.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        nameLabel.setForeground( Color.BLACK );
        formPanel.add( nameLabel, gbc );

        // Name Text Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        JTextField nameField = new JTextField( 20 );
        nameField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        formPanel.add( nameField, gbc );

        // Age Label (no blue box)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        JLabel ageLabel = new JLabel( "Age:" );
        ageLabel.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        ageLabel.setForeground( Color.BLACK );
        formPanel.add( ageLabel, gbc );

        // Age Text Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        JTextField ageField = new JTextField( 20 );
        ageField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        formPanel.add( ageField, gbc );

        // Error Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        JLabel errorLabel = new JLabel( "", SwingConstants.CENTER );
        errorLabel.setFont( new Font( "Arial", Font.PLAIN, 12 ) );
        errorLabel.setForeground( Color.RED );
        formPanel.add( errorLabel, gbc );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 0 ) );
        buttonPanel.setBackground( Color.WHITE );
        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 0, 0 ) );

        JButton saveButton = new JButton( "Save Student" );
        saveButton.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        saveButton.setPreferredSize( new Dimension( 150, 40 ) );
        saveButton.setBackground( new Color( 60, 179, 113 ) );
        saveButton.setForeground( Color.WHITE );
        saveButton.setFocusPainted( false );

        JButton cancelButton = new JButton( "Cancel" );
        cancelButton.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        cancelButton.setPreferredSize( new Dimension( 150, 40 ) );

        saveButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                saveButton.setBackground( new Color( 50, 159, 103 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                saveButton.setBackground( new Color( 60, 179, 113 ) );
            }
        } );

        saveButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                String idText = idField.getText().trim();
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();

                if( idText.isEmpty() || name.isEmpty() || ageText.isEmpty() )
                {
                    errorLabel.setText( "Please fill in all fields." );
                    return;
                }

                try
                {
                    int studentId = Integer.parseInt( idText );
                    int age = Integer.parseInt( ageText );

                    if( age <= 0 || age > 120 )
                    {
                        errorLabel.setText( "Please enter a valid age (1-120)." );
                        return;
                    }

                    Student existingStudent = FileManager.loadStudent( idText );
                    if( existingStudent != null )
                    {
                        int option = JOptionPane.showConfirmDialog(
                            studentFormDialog,
                            "Student ID already exists. Load existing profile?",
                            "Existing Student",
                            JOptionPane.YES_NO_OPTION
                        );

                        if( option == JOptionPane.YES_OPTION )
                        {
                            currentStudent = existingStudent;
                            studentFormDialog.dispose();
                            mainFrame.dispose();
                            createMainDashboard();
                            return;
                        }
                        else
                        {
                            errorLabel.setText( "Please use a different Student ID." );
                            return;
                        }
                    }

                    currentStudent = new Student( studentId, name, age, 1, new ArrayList<Integer>() );

                    FileManager.saveStudent( currentStudent );

                    JOptionPane.showMessageDialog(
                        studentFormDialog,
                        "Student profile created successfully!\nName: " + name + "\nID: " + studentId,
                        "Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    studentFormDialog.dispose();
                    mainFrame.dispose();
                    createMainDashboard();
                }
                catch( NumberFormatException exception )
                {
                    errorLabel.setText( "Student ID and Age must be numbers." );
                }
            }
        } );

        cancelButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                studentFormDialog.dispose();
            }
        } );

        buttonPanel.add( saveButton );
        buttonPanel.add( cancelButton );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( formPanel, BorderLayout.CENTER );
        mainPanel.add( buttonPanel, BorderLayout.SOUTH );

        studentFormDialog.add( mainPanel );
        studentFormDialog.setVisible( true );
    }

    private static void showLoadStudentWindow()
    {
        JFrame loadFrame = new JFrame( "Load Existing Student" );
        loadFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        loadFrame.setSize( 500, 400 );
        loadFrame.getContentPane().setBackground( Color.WHITE );
        loadFrame.setLocationRelativeTo( null );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BorderLayout() );
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout( new BorderLayout() );
        titlePanel.setBackground( new Color( 70, 130, 180 ) );
        titlePanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );

        JLabel titleLabel = new JLabel( "Select a Student Profile", SwingConstants.CENTER );
        titleLabel.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        titleLabel.setForeground( Color.WHITE );
        titlePanel.add( titleLabel, BorderLayout.CENTER );

        String[] studentList = FileManager.listStudents();
        
        JPanel listPanel = new JPanel();
        listPanel.setLayout( new BoxLayout( listPanel, BoxLayout.Y_AXIS ) );
        listPanel.setBackground( Color.WHITE );
        
        JScrollPane scrollPane = new JScrollPane( listPanel );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        scrollPane.setPreferredSize( new Dimension( 400, 250 ) );
        
        if( studentList.length == 0 )
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
            for( String studentId : studentList )
            {
                JPanel studentPanel = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
                studentPanel.setBackground( Color.WHITE );
                studentPanel.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 10 ) );
                
                JButton studentButton = new JButton( "Student ID: " + studentId );
                studentButton.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
                studentButton.setPreferredSize( new Dimension( 350, 40 ) );
                studentButton.setBackground( new Color( 70, 130, 180 ) );
                studentButton.setForeground( Color.WHITE );
                studentButton.setFocusPainted( false );
                studentButton.setBorder( BorderFactory.createLineBorder( Color.WHITE, 2 ) );
                
                studentButton.addMouseListener( new java.awt.event.MouseAdapter()
                {
                    public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
                    {
                        studentButton.setBackground( new Color( 50, 110, 160 ) );
                    }

                    public void mouseExited( java.awt.event.MouseEvent mouseEvent )
                    {
                        studentButton.setBackground( new Color( 70, 130, 180 ) );
                    }
                } );
                
                studentButton.addActionListener( new ActionListener()
                {
                    public void actionPerformed( ActionEvent actionEvent )
                    {
                        currentStudent = FileManager.loadStudent( studentId );
                        if( currentStudent != null )
                        {
                            loadFrame.dispose();
                            createMainDashboard();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(
                                loadFrame,
                                "Error loading student profile.",
                                "Load Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } );
                
                studentPanel.add( studentButton );
                listPanel.add( studentPanel );
                listPanel.add( Box.createVerticalStrut( 5 ) );
            }
        }
        
        JPanel bottomPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        bottomPanel.setBackground( Color.WHITE );
        
        JButton backButton = new JButton( "Back" );
        backButton.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        backButton.setPreferredSize( new Dimension( 100, 35 ) );
        
        backButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                loadFrame.dispose();
                showStudentRegistration();
            }
        } );
        
        bottomPanel.add( backButton );

        mainPanel.add( titlePanel, BorderLayout.NORTH );
        mainPanel.add( scrollPane, BorderLayout.CENTER );
        mainPanel.add( bottomPanel, BorderLayout.SOUTH );

        loadFrame.add( mainPanel );
        loadFrame.setVisible( true );
    }

    public static void createMainDashboard()
    {
        patternMatchingLessons = FileManager.loadPatternLessons();

        if( patternMatchingLessons == null || patternMatchingLessons.isEmpty() )
        {
            JOptionPane.showMessageDialog(
                null,
                "No pattern matching lessons found. Please check the lessons directory.",
                "Lesson Error",
                JOptionPane.WARNING_MESSAGE
            );
        }

        if( mainFrame != null )
        {
            mainFrame.dispose();
        }

        mainFrame = new JFrame( "Adaptive Learning System" );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setSize( 900, 700 );
        mainFrame.getContentPane().setBackground( Color.WHITE );
        mainFrame.setLocationRelativeTo( null );
        mainFrame.setLayout( new BorderLayout() );

        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.Y_AXIS ) );
        topPanel.setBackground( Color.WHITE );
        topPanel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 20, 0 ) );

        JPanel titlePanel = new JPanel( new BorderLayout() );
        titlePanel.setBackground( new Color( 70, 130, 180 ) );
        titlePanel.setBorder( BorderFactory.createEmptyBorder( 15, 0, 15, 0 ) );
        titlePanel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 60 ) );

        JLabel titleLabel = new JLabel( "Adaptive Learning System", SwingConstants.CENTER );
        titleLabel.setFont( new Font( "Arial", Font.BOLD, 28 ) );
        titleLabel.setForeground( Color.WHITE );
        titlePanel.add( titleLabel, BorderLayout.CENTER );

        topPanel.add( titlePanel );
        topPanel.add( Box.createVerticalStrut( 20 ) );

        if( currentStudent != null )
        {
            JPanel welcomePanel = new JPanel( new BorderLayout() );
            welcomePanel.setBackground( new Color( 70, 130, 180 ) );
            welcomePanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
            welcomePanel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

            JLabel welcomeLabel = new JLabel(
                "Welcome, " + currentStudent.getName() + "! (Level: " + currentStudent.getCurrentLevel() + ")",
                SwingConstants.CENTER
            );
            welcomeLabel.setFont( new Font( "Arial", Font.ITALIC, 18 ) );
            welcomeLabel.setForeground( Color.WHITE );
            welcomePanel.add( welcomeLabel, BorderLayout.CENTER );

            topPanel.add( welcomePanel );
            topPanel.add( Box.createVerticalStrut( 20 ) );
        }

        JPanel mainButtonPanel = new JPanel( new GridLayout( 2, 2, 30, 30 ) );
        mainButtonPanel.setBackground( Color.WHITE );
        mainButtonPanel.setBorder( BorderFactory.createEmptyBorder( 20, 50, 20, 50 ) );

        JButton patternMatchingButton = new JButton( "Pattern Matching" );
        JButton wordBuildingButton = new JButton( "Word Building" );
        JButton viewProgressButton = new JButton( "View Progress" );
        JButton exportReportButton = new JButton( "Export Report" );

        Font buttonFont = new Font( "Arial", Font.BOLD, 18 );
        Color buttonBackgroundColor = new Color( 70, 130, 180 );
        Color buttonBorderColor = Color.WHITE;

        JButton[] applicationButtons = { patternMatchingButton, wordBuildingButton, viewProgressButton, exportReportButton };

        for( JButton currentButton : applicationButtons )
        {
            currentButton.setFont( buttonFont );
            currentButton.setForeground( Color.WHITE );
            currentButton.setBackground( buttonBackgroundColor );
            currentButton.setFocusPainted( false );

            currentButton.setBorder( BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder( buttonBorderColor, 2 ),
                BorderFactory.createEmptyBorder( 80, 40, 80, 40 )
            ) );

            currentButton.addMouseListener( new java.awt.event.MouseAdapter()
            {
                public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
                {
                    currentButton.setBackground( new Color( 50, 110, 160 ) );
                }

                public void mouseExited( java.awt.event.MouseEvent mouseEvent )
                {
                    currentButton.setBackground( buttonBackgroundColor );
                }
            } );
        }

        patternMatchingButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                if( patternMatchingLessons == null || patternMatchingLessons.isEmpty() )
                {
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        "No pattern matching lessons available. Please check the lessons file.",
                        "No Lessons",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                selectPatternMatchingLesson();
                createPatternMatchingWindow();
            }
        } );

        wordBuildingButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                JOptionPane.showMessageDialog( mainFrame, "Word Building feature in progress, Lim" );
            }
        } );

        viewProgressButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                if( currentStudent != null )
                {
                    String statistics = currentStudent.getStatistics();
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        statistics,
                        "Progress Report",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else
                {
                    JOptionPane.showMessageDialog( mainFrame, "No student profile loaded." );
                }
            }
        } );

        exportReportButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                if( currentStudent != null )
                {
                    FileManager.exportReport( currentStudent );
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        "Report exported to reports/ directory!",
                        "Export Successful",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else
                {
                    JOptionPane.showMessageDialog( mainFrame, "No student profile to export." );
                }
            }
        } );

        mainButtonPanel.add( patternMatchingButton );
        mainButtonPanel.add( wordBuildingButton );
        mainButtonPanel.add( viewProgressButton );
        mainButtonPanel.add( exportReportButton );

        mainFrame.add( topPanel, BorderLayout.NORTH );
        mainFrame.add( mainButtonPanel, BorderLayout.CENTER );

        mainFrame.setVisible( true );
    }

    private static void selectPatternMatchingLesson()
    {
        if( patternMatchingLessons == null || patternMatchingLessons.isEmpty() )
        {
            currentPatternLesson = null;
            return;
        }

        currentPatternLesson = patternMatchingLessons.get( 0 );
    }

    private static void createPatternMatchingWindow()
    {
        if( currentPatternLesson == null )
        {
            JOptionPane.showMessageDialog( mainFrame, "Error loading lesson." );
            return;
        }

        JDialog patternMatchingDialog = new JDialog( mainFrame, "Pattern Matching", true );
        patternMatchingDialog.setSize( 500, 450 );
        patternMatchingDialog.setLocationRelativeTo( mainFrame );
        patternMatchingDialog.getContentPane().setBackground( Color.WHITE );
        patternMatchingDialog.setLayout( new BorderLayout() );

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout( new BoxLayout( mainContentPanel, BoxLayout.Y_AXIS ) );
        mainContentPanel.setBackground( Color.WHITE );
        mainContentPanel.setBorder( BorderFactory.createEmptyBorder( 30, 20, 20, 20 ) );

        JPanel instructionPanel = new JPanel( new BorderLayout() );
        instructionPanel.setBackground( new Color( 70, 130, 180 ) );
        instructionPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
        instructionPanel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel instructionLabel = new JLabel( "Memorize this pattern:", SwingConstants.CENTER );
        instructionLabel.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        instructionLabel.setForeground( Color.WHITE );
        instructionPanel.add( instructionLabel, BorderLayout.CENTER );

        JPanel patternPanel = new JPanel();
        patternPanel.setLayout( new FlowLayout( FlowLayout.CENTER, 30, 0 ) );
        patternPanel.setBackground( Color.WHITE );
        patternPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        patternPanel.setMaximumSize( new Dimension( 450, 150 ) );

        String[] pattern = currentPatternLesson.getPattern();

        for( String shape : pattern )
        {
            JLabel shapeLabel = createShapeLabel( shape );
            patternPanel.add( shapeLabel );
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new BorderLayout() );
        buttonPanel.setBackground( Color.WHITE );
        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 100, 20, 50, 20 ) );

        JButton readyButton = new JButton( "I'm Ready!" );
        readyButton.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        readyButton.setPreferredSize( new Dimension( 0, 40 ) );
        readyButton.setBackground( new Color( 70, 130, 180 ) );
        readyButton.setForeground( Color.WHITE );
        readyButton.setFocusPainted( false );

        readyButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                readyButton.setBackground( new Color( 50, 110, 160 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                readyButton.setBackground( new Color( 70, 130, 180 ) );
            }
        } );

        readyButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                patternMatchingDialog.dispose();
                showPatternMatchingTest();
            }
        } );

        buttonPanel.add( readyButton, BorderLayout.CENTER );

        mainContentPanel.add( instructionPanel );
        mainContentPanel.add( Box.createVerticalStrut( 30 ) );
        mainContentPanel.add( patternPanel );
        mainContentPanel.add( Box.createVerticalGlue() );
        mainContentPanel.add( buttonPanel );

        patternMatchingDialog.add( mainContentPanel, BorderLayout.CENTER );
        patternMatchingDialog.setVisible( true );
    }

    private static JLabel createShapeLabel( String shape )
    {
        JLabel shapeLabel = new JLabel( shape, SwingConstants.CENTER );
        shapeLabel.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        shapeLabel.setPreferredSize( new Dimension( 100, 80 ) );
        shapeLabel.setBorder( BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder( Color.BLACK, 1 ),
            BorderFactory.createEmptyBorder( 10, 10, 10, 10 )
        ) );
        shapeLabel.setOpaque( true );
        shapeLabel.setBackground( new Color( 70, 130, 180 ) );
        shapeLabel.setForeground( Color.WHITE );

        return shapeLabel;
    }

    private static void showPatternMatchingTest()
    {
        JDialog testDialog = new JDialog( mainFrame, "Pattern Matching Test", true );
        testDialog.setSize( 500, 300 );
        testDialog.setLocationRelativeTo( mainFrame );
        testDialog.getContentPane().setBackground( Color.WHITE );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 20, 30, 20, 30 ) );

        JPanel instructionPanel = new JPanel( new BorderLayout() );
        instructionPanel.setBackground( new Color( 70, 130, 180 ) );
        instructionPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 0 ) );
        instructionPanel.setMaximumSize( new Dimension( Integer.MAX_VALUE, 50 ) );

        JLabel instructionLabel = new JLabel( "Recreate the pattern (e.g., CircleSquareCircle):", SwingConstants.CENTER );
        instructionLabel.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        instructionLabel.setForeground( Color.WHITE );
        instructionPanel.add( instructionLabel, BorderLayout.CENTER );

        JTextField answerField = new JTextField();
        answerField.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        answerField.setMaximumSize( new Dimension( 400, 30 ) );
        answerField.setAlignmentX( Component.CENTER_ALIGNMENT );
        answerField.setBackground( Color.WHITE );
        answerField.setBorder( BorderFactory.createLineBorder( new Color( 70, 130, 180 ), 2 ) );

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout( new BoxLayout( inputPanel, BoxLayout.Y_AXIS ) );
        inputPanel.setBackground( Color.WHITE );
        inputPanel.setBorder( BorderFactory.createEmptyBorder( 20, 0, 20, 0 ) );

        inputPanel.add( instructionPanel );
        inputPanel.add( Box.createVerticalStrut( 20 ) );
        inputPanel.add( answerField );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 0 ) );
        buttonPanel.setBackground( Color.WHITE );
        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );

        JButton submitButton = new JButton( "Submit" );
        submitButton.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        submitButton.setPreferredSize( new Dimension( 100, 35 ) );
        submitButton.setBackground( new Color( 70, 130, 180 ) );
        submitButton.setForeground( Color.WHITE );
        submitButton.setFocusPainted( false );

        JButton cancelButton = new JButton( "Cancel" );
        cancelButton.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
        cancelButton.setPreferredSize( new Dimension( 100, 35 ) );

        submitButton.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseEntered( java.awt.event.MouseEvent mouseEvent )
            {
                submitButton.setBackground( new Color( 50, 110, 160 ) );
            }

            public void mouseExited( java.awt.event.MouseEvent mouseEvent )
            {
                submitButton.setBackground( new Color( 70, 130, 180 ) );
            }
        } );

        submitButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                String userAnswer = answerField.getText().trim();

                if( userAnswer.isEmpty() )
                {
                    JOptionPane.showMessageDialog(
                        testDialog,
                        "Please enter an answer.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                testDialog.dispose();
                processPatternMatchingAnswer( userAnswer );
            }
        } );

        cancelButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                testDialog.dispose();
            }
        } );

        buttonPanel.add( submitButton );
        buttonPanel.add( cancelButton );

        mainPanel.add( inputPanel );
        mainPanel.add( buttonPanel );

        testDialog.add( mainPanel );
        testDialog.setVisible( true );
    }

    private static void processPatternMatchingAnswer( String userAnswer )
    {
        int score = currentPatternLesson.evaluateAnswer( userAnswer );

        if( currentStudent != null )
        {
            currentStudent.updateProgress( score );
            FileManager.saveStudent( currentStudent );
        }

        String[] pattern = currentPatternLesson.getPattern();
        
        String[] userShapes = parseUserAnswer( userAnswer, pattern );
        
        int correctCount = 0;
        int totalShapes = pattern.length;
        
        StringBuilder feedback = new StringBuilder();
        feedback.append( "Pattern Matching Results:\n\n" );
        
        for( int i = 0; i < totalShapes; i++ )
        {
            if( i < userShapes.length )
            {
                if( userShapes[i].equalsIgnoreCase( pattern[i] ) )
                {
                    correctCount++;
                    feedback.append( "✓ " ).append( pattern[i] ).append( " - Correct!\n" );
                }
                else
                {
                    feedback.append( "✗ " ).append( pattern[i] )
                           .append( " (You entered: " ).append( userShapes[i] ).append( ")\n" );
                }
            }
            else
            {
                feedback.append( "✗ " ).append( pattern[i] ).append( " - Missing\n" );
            }
        }

        double scorePercentage = ( ( double ) correctCount / totalShapes ) * 100;
        double pointsPerCorrect = 100.0 / totalShapes;
        
        feedback.append( "\nScore Breakdown:\n" );
        feedback.append( "Correct: " ).append( correctCount ).append( "/" ).append( totalShapes ).append( "\n" );
        feedback.append( "Points per correct shape: " ).append( String.format( "%.1f", pointsPerCorrect ) ).append( "\n" );
        feedback.append( "This lesson score: " ).append( String.format( "%.0f", scorePercentage ) ).append( "%\n" );
        
        feedback.append( "\nOverall Progress:\n" );
        feedback.append( "Current Average Score: " ).append( String.format( "%.0f", currentStudent.getAverageScore() ) ).append( "%\n" );
        feedback.append( "Current Level: " ).append( currentStudent.getCurrentLevel() );
        
        if( scorePercentage == 100 )
        {
            feedback.append( "\n\nPerfect score! Well done!" );
        }
        else if( scorePercentage >= 60 )
        {
            feedback.append( "\n\nGood work! You're progressing well!" );
        }
        else
        {
            feedback.append( "\n\nKeep practicing! Every attempt helps!" );
        }

        JOptionPane.showMessageDialog(
            mainFrame,
            feedback.toString(),
            "Pattern Matching Results",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static String[] parseUserAnswer( String userAnswer, String[] expectedPattern )
    {
        String cleanAnswer = userAnswer.replaceAll( "[,\\s]+", "" ).toLowerCase();
        
        ArrayList<String> userShapes = new ArrayList<>();
        
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
                    userShapes.add( cleanAnswer.substring( position, position + 1 ).toUpperCase() );
                    position++;
                }
            }
        }
        
        return userShapes.toArray( new String[0] );
    }
}