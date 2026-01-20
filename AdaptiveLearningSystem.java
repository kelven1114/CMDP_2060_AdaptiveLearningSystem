import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptiveLearningSystem
{
    private static JFrame mainFrame;
    

    public static void main ( String[] args )
    {
        createMainDashboard ();
    }
    

    public static void createMainDashboard ()
    {
        mainFrame = new JFrame ( "Adaptive Learning System" );
        mainFrame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        mainFrame.setSize ( 900, 700 );
        mainFrame.getContentPane ().setBackground ( Color.LIGHT_GRAY );
        mainFrame.setLocationRelativeTo ( null );
        mainFrame.setLayout ( new BorderLayout () );

        JLabel titleLabel = new JLabel ( "Adaptive Learning System", SwingConstants.CENTER );
        titleLabel.setFont ( new Font ( "Arial", Font.BOLD, 28 ) );
        titleLabel.setForeground ( Color.DARK_GRAY );
        titleLabel.setBorder ( BorderFactory.createEmptyBorder ( 20, 0, 30, 0 ) );

        JPanel mainButtonPanel = new JPanel ( new GridLayout ( 2, 2, 30, 30 ) );
        mainButtonPanel.setBackground ( Color.LIGHT_GRAY );
        mainButtonPanel.setBorder ( BorderFactory.createEmptyBorder ( 20, 50, 20, 50 ) );

        JButton patternMatchingButton = new JButton ( "Pattern Matching" );
        JButton wordBuildingButton = new JButton ( "Word Building" );
        JButton viewProgressButton = new JButton ( "View Progress" );
        JButton exportReportButton = new JButton ( "Export Report" );

        Font buttonFont = new Font ( "Arial", Font.BOLD, 18 );
        Color buttonBackgroundColor = new Color ( 240, 240, 240 );
        Color buttonBorderColor = new Color ( 70, 130, 180 );

        JButton[] applicationButtons = { patternMatchingButton, wordBuildingButton, viewProgressButton, exportReportButton };
        
        for ( JButton currentButton : applicationButtons )
        {
            currentButton.setFont ( buttonFont );
            currentButton.setForeground ( Color.DARK_GRAY );
            currentButton.setBackground ( buttonBackgroundColor );
            currentButton.setFocusPainted ( false );
            
            currentButton.setBorder ( BorderFactory.createCompoundBorder (
                BorderFactory.createLineBorder ( buttonBorderColor, 2 ),
                BorderFactory.createEmptyBorder ( 80, 40, 80, 40 )
            ) );
            
            currentButton.addMouseListener ( new java.awt.event.MouseAdapter ()
            {
                public void mouseEntered ( java.awt.event.MouseEvent mouseEvent )
                {
                    currentButton.setBackground ( new Color ( 220, 220, 220 ) );
                }
                
                public void mouseExited ( java.awt.event.MouseEvent mouseEvent )
                {
                    currentButton.setBackground ( buttonBackgroundColor );
                }
            } );
        }

        patternMatchingButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                createPatternMatchingWindow ();
            }
        } );
        
        wordBuildingButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                JOptionPane.showMessageDialog ( mainFrame, "Word Building feature in progress, Lim" );
            }
        } );
        
        viewProgressButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                JOptionPane.showMessageDialog ( mainFrame, "View Progress feature in progress, kelven" );
            }
        } );
        
        exportReportButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                JOptionPane.showMessageDialog ( mainFrame, "Export Report feature in progress, kelven" );
            }
        } );

        mainButtonPanel.add ( patternMatchingButton );
        mainButtonPanel.add ( wordBuildingButton );
        mainButtonPanel.add ( viewProgressButton );
        mainButtonPanel.add ( exportReportButton );

        mainFrame.add ( titleLabel, BorderLayout.NORTH );
        mainFrame.add ( mainButtonPanel, BorderLayout.CENTER );
        
        mainFrame.setVisible ( true );
    }
    

    private static void createPatternMatchingWindow ()
    {
        JDialog patternMatchingDialog = new JDialog ( mainFrame, "Pattern Matching", true );
        patternMatchingDialog.setSize ( 500, 400 );
        patternMatchingDialog.setLocationRelativeTo ( mainFrame );
        patternMatchingDialog.setLayout ( new BorderLayout () );
        
        JPanel mainContentPanel = new JPanel ();
        mainContentPanel.setLayout ( new BorderLayout () );
        mainContentPanel.setBackground ( new Color ( 250, 250, 250 ) );
        
        JPanel patternPanel = new JPanel ();
        patternPanel.setLayout ( new BorderLayout () );
        patternPanel.setBackground ( new Color ( 250, 250, 250 ) );
        patternPanel.setBorder ( BorderFactory.createEmptyBorder ( 80, 20, 80, 20 ) );
        
        JPanel bottomPanel = new JPanel ();
        bottomPanel.setLayout ( new BorderLayout () );
        bottomPanel.setBorder ( BorderFactory.createEmptyBorder ( 10, 20, 20, 20 ) );
        bottomPanel.setBackground ( new Color ( 250, 250, 250 ) );
        
        JButton readyButton = new JButton ( "I'm Ready!" );
        readyButton.setFont ( new Font ( "Arial", Font.BOLD, 16 ) );
        readyButton.setPreferredSize ( new Dimension ( 0, 40 ) );
        readyButton.setBackground ( new Color ( 70, 130, 180 ) );
        readyButton.setForeground ( Color.WHITE );
        readyButton.setFocusPainted ( false );
        
        readyButton.addMouseListener ( new java.awt.event.MouseAdapter ()
        {
            public void mouseEntered ( java.awt.event.MouseEvent mouseEvent )
            {
                readyButton.setBackground ( new Color ( 50, 110, 160 ) );
            }
            
            public void mouseExited ( java.awt.event.MouseEvent mouseEvent )
            {
                readyButton.setBackground ( new Color ( 70, 130, 180 ) );
            }
        } );
        
        readyButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                patternMatchingDialog.dispose ();
            }
        } );
        
        bottomPanel.add ( readyButton, BorderLayout.CENTER );
        
        mainContentPanel.add ( patternPanel, BorderLayout.CENTER );
        
        patternMatchingDialog.add ( mainContentPanel, BorderLayout.CENTER );
        patternMatchingDialog.add ( bottomPanel, BorderLayout.SOUTH );
        patternMatchingDialog.setVisible ( true );
    }
}