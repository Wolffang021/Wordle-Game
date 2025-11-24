import javax.swing.*;

class WordleGame extends JFrame {
    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new WordleGame();
    }
}