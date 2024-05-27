import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

class R_P_S extends JFrame {
    JLabel computerchoice, finalResult;
    JLabel PlayerScore, ComputerScore;

    int player_score = 0, computer_score = 0;
    public String result = "";

    ImageIcon image_rock, image_paper, image_scissor;

    R_P_S() {
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        finalResult = new JLabel();
        finalResult.setBounds(270, 400, 200, 100);
        finalResult.setFont(new Font("Serif", Font.BOLD, 20));
        finalResult.setForeground(Color.GREEN);
        add(finalResult);
    }

    public void setup() {
        // Initialize the icons
        image_rock = new ImageIcon(new ImageIcon("rock.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        image_paper = new ImageIcon(new ImageIcon("paper.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        image_scissor = new ImageIcon(new ImageIcon("scissor.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

        // // Set up components

        ComputerScore = new JLabel("Computer : 0");
        ComputerScore.setBounds(100, 15, 200, 100);
        ComputerScore.setFont(new Font("Serif", Font.BOLD, 15));
        ComputerScore.setForeground(Color.GREEN);
        ComputerScore.setBorder(new EmptyBorder(10, 0, 10, 0)); 

        PlayerScore = new JLabel("Player : 0");
        PlayerScore.setBounds(100, 200, 100, 100);
        PlayerScore.setFont(new Font("Serif", Font.BOLD, 15));
        PlayerScore.setForeground(Color.GREEN);
        PlayerScore.setBorder(new EmptyBorder(10, 0, 10, 0)); 


        computerchoice = new JLabel("Start Game");
        computerchoice.setHorizontalTextPosition(JLabel.CENTER);
        computerchoice.setVerticalTextPosition(JLabel.BOTTOM);
        computerchoice.setBounds(250, 90, 200, 100);
        computerchoice.setFont(new Font("Serif", Font.BOLD, 24));
        computerchoice.setForeground(Color.GREEN);

        add(ComputerScore);
        add(PlayerScore);
        add(computerchoice);
        addGameOptions();
        add(finalResult);

    }

    private void addGameOptions() {
        JLabel label_rock = new JLabel("Rock");
        label_rock.setIcon(image_rock);
        label_rock.setHorizontalTextPosition(JLabel.CENTER);
        label_rock.setVerticalTextPosition(JLabel.BOTTOM);
        label_rock.setBounds(150, 300, 100, 100);
        label_rock.setForeground(Color.GREEN);

        JLabel label_paper = new JLabel("Paper");
        label_paper.setIcon(image_paper);
        label_paper.setHorizontalTextPosition(JLabel.CENTER);
        label_paper.setVerticalTextPosition(JLabel.BOTTOM);
        label_paper.setBounds(250, 300, 100, 100);
        label_paper.setForeground(Color.GREEN);

        JLabel label_scissor = new JLabel("Scissor");
        label_scissor.setIcon(image_scissor);
        label_scissor.setHorizontalTextPosition(JLabel.CENTER);
        label_scissor.setVerticalTextPosition(JLabel.BOTTOM);
        label_scissor.setBounds(350, 300, 100, 100);
        label_scissor.setForeground(Color.GREEN);

        // Event listeners
        label_rock.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                calculate(label_rock.getText());
            }
        });

        label_paper.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                calculate(label_paper.getText());
            }
        });

        label_scissor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                calculate(label_scissor.getText());
            }
        });

        add(label_rock);
        add(label_paper);
        add(label_scissor);
    }

    public void calculate(String player) {

        String[] list = {"Rock", "Paper", "Scissor"};
        int random_choice = (int) ((Math.random() * 10) % 3);
        String computer = list[random_choice];

        computerchoice.setIcon(new ImageIcon(new ImageIcon(computer + ".png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
        computerchoice.setText(computer);
        computerchoice.setForeground(Color.green);
        computerchoice.setFont(new Font("Serif", Font.BOLD, 16));


        // Main logic
        if (player.equals(computer)) {
            result = "Draw";
        } else if (player.equals("Rock")) {
            if (computer.equals("Paper")) {
                result = "Computer Won";
                computer_score += 1;
            } else {
                result = "You Won";
                player_score += 1;
            }
        } else if (player.equals("Paper")) {
            if (computer.equals("Scissor")) {
                result = "Computer Won";
                computer_score += 1;
            } else {
                result = "You Won";
                player_score += 1;
            }
        } else {
            if (computer.equals("Rock")) {
                result = "Computer Won";
                computer_score += 1;
            } else {
                result = "You Won";
                player_score += 1;
            }
        }

        // Show the result panel
        if(!result.equals("Draw")) {
            Result_Panel rp = new Result_Panel(result);
            rp.setTitle("Result Panel") ;
            rp.setVisible(true);
        }

        else{
            finalResult.setText(result);
        }
        PlayerScore.setText("Player : " + player_score);
        ComputerScore.setText("Computer : " + computer_score);

    }

    public static void main(String[] args) {
        R_P_S g = new R_P_S();
        g.setTitle("ROCK PAPER SCISSORS");
        g.setBounds(200, 200, 600, 600);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.getContentPane().setBackground(Color.BLACK);
        g.setup();
        g.setLocationRelativeTo(null);

        g.setResizable(false);
        g.setVisible(true);
    }
}
