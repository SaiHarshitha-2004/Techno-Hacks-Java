import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    JFrame frame = new JFrame() ;
    JLabel textlabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3] ;
    String playerX = "X" ;
    String playerO= "O" ;
    String currPlayer = playerX;

    boolean gameOver = false;
    int turns = 0 ;

    TicTacToe(){
        frame.setVisible(true) ;
        frame.setSize(600 , 600) ;
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Tic Tac Toe");
        textlabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textlabel );
        frame.add(textPanel , BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3 , 3 ) );
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);


        for(int r =  0 ; r < 3 ; r++){
            for(int c = 0 ; c < 3 ; c ++) {
                JButton tile=  new JButton();
                board[r][c] = tile ;
                boardPanel.add(tile) ;

                tile.setBackground(Color.darkGray) ;
                tile.setForeground(Color.white) ;
                tile.setFont(new Font("Arial" , Font.BOLD , 45));
                tile.setFocusable(false);
                //tile.setText(currPlayer) ;

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return ;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currPlayer) ;
                            turns += 1 ;
                            checkWinner() ;
                            if (!gameOver){
                                currPlayer = currPlayer == playerX ? playerO : playerX ;
                                textlabel.setText(currPlayer + "'s turn") ;
                            }
                        
                        }
                        

                    }
                }) ;
            }

            
        }
        

    }
    void checkWinner() {
        for(int r = 0 ; r < 3 ; r ++) {
            
            if(board[r][0].getText() == "") continue; 

            if(board[r][0].getText() == board[r][1].getText() &&  
               board[r][1].getText() == board[r][2].getText()){

                for(int i = 0; i < 3 ; i ++ ){
                    setWinner(board[r][i]);
                }

                gameOver = true ;
                return ;
            }
        }

        for(int c = 0; c < 3 ; c ++) {
            if(board[0][c].getText() == "" ) continue ;

            if(board[0][c].getText() == board[1][c].getText() && 
            board[1][c].getText() == board[2][c].getText() ) {

                for (int i =0 ; i < 3 ; i ++ ){ 
                    setWinner(board[i][c]) ;
                }

                gameOver = true ;
                return ;
            }
        }

        if(board[0][0].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][2].getText()  && 
         board[0][0].getText() != "") { 

            for(int i = 0 ; i < 3 ; i ++) {
                setWinner(board[i][i]) ;
            }

            gameOver = true ; 
            return;


        } 

        if(board[0][2].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "" ) {
            int j = 2 ;
            for(int i = 0 ; i < 3 ; i ++) {
                setWinner(board[i][j]) ;
                j -= 1;
            }

            gameOver = true ; 
            return;

        }

        if(turns == 9  ) {
            for(int r =0 ; r < 3 ; r ++ ) { 
                for (int c =0 ;  c < 3 ; c ++) { 
                    setTie(board[r][c]) ;
                }
            }

            gameOver = true ;

        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green) ;
        tile.setBackground(Color.gray) ;
        textlabel.setText(currPlayer + " is the winner!") ;
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.BLACK) ;
        tile.setBackground(Color.gray) ;
        tile.setFont(new Font("Arila" , Font.BOLD , 16));
        textlabel.setText("Tie") ;
    }
}
