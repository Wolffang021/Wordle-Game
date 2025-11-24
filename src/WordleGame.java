import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WordleGame extends JFrame {
    int curBoxIndex = 0;    // To navigate during typing
    int wordBoxX = 10;

    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 600);
        this.getContentPane().setBackground(new Color(55, 50, 50));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel[] wordBox = new JPanel[5];
        JLabel[] boxText = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            wordBox[i] = new JPanel();
            wordBox[i].setBackground(new Color(30, 30, 30));
            wordBox[i].setBounds(wordBoxX += 55, 10, 50, 50);
            this.add(wordBox[i]);

            boxText[i] = new JLabel();
            Font tempFont = boxText[i].getFont();
            Font boxTextFont = tempFont.deriveFont(32f);
            boxText[i].setFont(boxTextFont);
            boxText[i].setForeground(new Color(200, 200, 200));
            boxText[i].setText("");
            wordBox[i].add(boxText[i]);
        }

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && curBoxIndex > 0) {
                    boxText[--curBoxIndex].setText("");
                }

                char tempChar = e.getKeyChar();
                
                if (curBoxIndex < 5 && Character.isAlphabetic(tempChar)) {
                    boxText[curBoxIndex++].setText(Character.toUpperCase(tempChar) + "");
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