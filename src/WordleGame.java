import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WordleGame extends JFrame {
    int curBoxIndex = 0;    // To navigate during typing
    int curBoxRow = 0;    // To navigate during typing
    int wordBoxX = 16;
    int wordBoxY = 10;

    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 600);
        this.getContentPane().setBackground(new Color(55, 50, 50));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel[][] wordBox = new JPanel[6][5];
        JLabel[][] boxText = new JLabel[6][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                wordBox[i][j] = new JPanel();
                wordBox[i][j].setBackground(new Color(30, 30, 30));
                wordBox[i][j].setBounds(wordBoxX += 52, wordBoxY, 50, 50);
                this.add(wordBox[i][j]);
    
                boxText[i][j] = new JLabel();
                Font tempFont = boxText[i][j].getFont();
                Font boxTextFont = tempFont.deriveFont(32f);
                boxText[i][j].setFont(boxTextFont);
                boxText[i][j].setForeground(new Color(200, 200, 200));
                boxText[i][j].setText("");
                wordBox[i][j].add(boxText[i][j]);
            }

            wordBoxX = 16;
            wordBoxY += 52;
        }

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && curBoxIndex > 0) {
                    boxText[curBoxRow][--curBoxIndex].setText("");
                }

                char tempChar = e.getKeyChar();
                
                if (curBoxIndex < 5 && Character.isAlphabetic(tempChar)) {
                    boxText[curBoxRow][curBoxIndex++].setText(Character.toUpperCase(tempChar) + "");
                }
            }
        });

        this.setFocusable(true);
        this.requestFocus();

        this.revalidate();;
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new WordleGame();
    }
}