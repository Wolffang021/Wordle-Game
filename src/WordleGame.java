import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

class WordleGame extends JFrame {
    int wordBoxX = 10;

    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 600);
        this.getContentPane().setBackground(new Color(55, 50, 50));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel[] wordBox = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            wordBox[i] = new JPanel();
            wordBox[i].setBackground(new Color(30, 30, 30));
            wordBox[i].setBounds(wordBoxX += 55, 10, 50, 50);
            this.add(wordBox[i]);
        }

        this.revalidate();;
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new WordleGame();
    }
}