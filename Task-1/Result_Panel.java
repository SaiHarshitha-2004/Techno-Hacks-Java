import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Result_Panel extends JFrame implements ActionListener { 
    Button playAgain;
    Label resultLabel;

    Result_Panel(String result) {
        this.setLayout(new BorderLayout());

        // Background panel for fireworks effect
        FireworksPanel fireworksPanel = new FireworksPanel();
        fireworksPanel.setLayout(new GridBagLayout());

        // Configure GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        gbc.anchor = GridBagConstraints.CENTER;  
        gbc.fill = GridBagConstraints.NONE;  

        resultLabel = new Label(result, Label.CENTER);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 24));
        resultLabel.setForeground(Color.GREEN);
        fireworksPanel.add(resultLabel, gbc);

        playAgain = new Button("Play Again");
        playAgain.addActionListener(this);
        playAgain.setForeground(Color.GREEN);
        playAgain.setBackground(Color.BLACK) ;
        gbc.gridy = 1;  // Move to row 1
        fireworksPanel.add(playAgain, gbc);

        this.add(fireworksPanel, BorderLayout.CENTER);

        // Frame properties
        this.setTitle("Result Panel");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);  // Center the window on the screen
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgain) {
            this.dispose();
        }
    }
}
