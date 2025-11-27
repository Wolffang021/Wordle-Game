import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WordleGame extends JFrame {
    int curBoxIndex;    // To navigate during typing
    int curBoxRow;      // To navigate during typing
    int wordBoxX;
    int wordBoxY;
    String targetWord;
    String inputWord;

    void MainMenu() {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        
        JButton playButton = new JButton("PLAY");
        playButton.setBounds(90, 180, 215, 50);
        playButton.setBackground(new Color(30, 30, 30));
        playButton.setForeground(new Color(200, 200, 200));
        playButton.setFont(new Font("dialog", Font.BOLD, 24));
        playButton.setEnabled(true);
        this.add(playButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(90, 235, 215, 50);
        exitButton.setBackground(new Color(30, 30, 30));
        exitButton.setForeground(new Color(200, 200, 200));
        exitButton.setFont(new Font("dialog", Font.BOLD, 24));
        exitButton.setEnabled(true);
        this.add(exitButton);
        
        ActionListener exitActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        
        exitButton.addActionListener(exitActionListener);
        
        ActionListener playActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.removeActionListener(this);
                exitButton.removeActionListener(exitActionListener);
                PlayGame();
            }
        };
        
        playButton.addActionListener(playActionListener);
        
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    
    void PlayGame() {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();

        Random random = new Random();
        Words words = new Words();
        int randomRow = 0;
        int randomColumn = random.nextInt(words.wordsList[randomRow].length);
        
        targetWord = words.wordsList[randomRow][randomColumn];
        // System.out.println(targetWord);
        inputWord = "";
        
        curBoxIndex = 0;
        curBoxRow = 0;
        wordBoxX = 16;
        wordBoxY = 10;
        int[] wordCount = {0, 0, 0, 0, 0};

        JPanel[][] wordBox = new JPanel[6][5];
        JLabel[][] boxText = new JLabel[6][5];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                wordBox[i][j] = new JPanel();
                wordBox[i][j].setBackground(new Color(30, 30, 30));
                wordBox[i][j].setBounds(wordBoxX += 52, wordBoxY, 50, 50);
                this.add(wordBox[i][j]);
        
                boxText[i][j] = new JLabel();
                Font boxTextFont = new Font("dialog", Font.BOLD, 32);
                boxText[i][j].setFont(boxTextFont);
                boxText[i][j].setForeground(new Color(200, 200, 200));
                boxText[i][j].setText("");
                wordBox[i][j].add(boxText[i][j]);
            }
            
            wordBoxX = 16;
            wordBoxY += 52;
        }
        
        JPanel messageBox = new JPanel();
        messageBox.setBackground(new Color(30, 30, 30));
        messageBox.setBounds(90, 325, 215, 50);
        messageBox.setLayout(new GridBagLayout());
        this.add(messageBox);
        
        JLabel messageText = new JLabel();
        messageText.setFont(new Font("dialog", Font.BOLD, 24));
        messageText.setForeground(new Color(200, 200, 200));
        messageText.setText("");
        messageBox.add(messageText);
        
        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setBounds(90, 380, 215, 50);
        playAgainButton.setBackground(new Color(30, 30, 30));
        playAgainButton.setForeground(new Color(200, 200, 200));
        playAgainButton.setFont(new Font("dialog", Font.BOLD, 24));
        playAgainButton.setEnabled(false);
        this.add(playAgainButton);

        JButton menuButton = new JButton("MAIN MENU");
        menuButton.setBounds(90, 435, 215, 50);
        menuButton.setBackground(new Color(30, 30, 30));
        menuButton.setForeground(new Color(200, 200, 200));
        menuButton.setFont(new Font("dialog", Font.BOLD, 24));
        menuButton.setEnabled(true);
        this.add(menuButton);

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (curBoxIndex > 0) {
                        boxText[curBoxRow][--curBoxIndex].setText("");
                    
                        inputWord = inputWord.substring(0, inputWord.length() - 1);
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (inputWord.length() == 5) {
                        for (int i = 0; i < wordCount.length; i++) {
                            wordCount[i] = 0;
                        }
                        
                        for (int i = 0; i < targetWord.length(); i++) {
                            wordCount[targetWord.indexOf(targetWord.charAt(i))]++;
                        }
                        
                        // for (int i : wordCount) {
                        //     System.out.print(i + " ");
                        // }
                            
                        if (inputWord.equals(targetWord)) {
                                messageText.setFont(new Font("dialog", Font.BOLD, 24));
                                messageText.setText("CORRECT!");
                                
                                for (JPanel box : wordBox[curBoxRow]) {
                                    box.setBackground(new Color(50, 150, 50));
                                }

                                playAgainButton.setEnabled(true);
                                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                            }
                            else {
                                for (int i = 0; i < words.wordsList.length; i++) {
                                    for (int j = 0; j < words.wordsList[i].length; j++) {
                                        if (inputWord.equals(words.wordsList[i][j])) {
                                            messageText.setFont(new Font("dialog", Font.BOLD, 24));
                                            messageText.setText("INCORRECT!");
                                        
                                        for (int k = 0; k < wordBox[curBoxRow].length; k++) {
                                            if (targetWord.contains(boxText[curBoxRow][k].getText())) {
                                                if (boxText[curBoxRow][k].getText().charAt(0) == targetWord.charAt(k)) {
                                                    wordBox[curBoxRow][k].setBackground(new Color(50, 150, 50));
                                                    
                                                    wordCount[targetWord.indexOf(boxText[curBoxRow][k].getText().charAt(0))]--;
                                                }
                                                else if (wordCount[targetWord.indexOf(boxText[curBoxRow][k].getText().charAt(0))]-- > 0) {
                                                    wordBox[curBoxRow][k].setBackground(new Color(160, 150, 40));
                                                }
                                            }
                                        }
                                        
                                        curBoxIndex = 0;
                                        curBoxRow++;
                                        inputWord = "";
                                        
                                        if (curBoxRow > 5) {
                                            messageText.setFont(new Font("dialog", Font.BOLD, 16));
                                            messageText.setText("THE WORD WAS " + targetWord);
                                            
                                            playAgainButton.setEnabled(true);
                                            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                                        }
                                        
                                        return;
                                    }    
                                }    
                            }    
                            
                            messageText.setFont(new Font("dialog", Font.BOLD, 20));
                            messageText.setText("NO SUCH WORD!");
                        }    
                    }    
                    else {
                        messageText.setFont(new Font("dialog", Font.BOLD, 18));
                        messageText.setText("INCOMPLETE WORD!");
                    }    
                    
                    return;
                }
        
                char tempChar = e.getKeyChar();
                
                if (curBoxIndex < 5 && Character.isAlphabetic(tempChar)) {
                    boxText[curBoxRow][curBoxIndex++].setText(Character.toUpperCase(tempChar) + "");

                    inputWord += Character.toUpperCase(tempChar);
                }
                // System.out.println(inputWord + " Size: " + inputWord.length());
            }
        };
        
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocusInWindow();

        ActionListener playAgainActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordleGame.this.removeKeyListener(keyListener);
                playAgainButton.removeActionListener(this);
                PlayGame();
            }
        };

        playAgainButton.addActionListener(playAgainActionListener);

        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordleGame.this.removeKeyListener(keyListener);
                playAgainButton.removeActionListener(playAgainActionListener);
                menuButton.removeActionListener(this);
                MainMenu();
            }
        };

        menuButton.addActionListener(menuActionListener);
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    
    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 525);
        this.getContentPane().setBackground(new Color(55, 50, 50));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        MainMenu();
    }

    public static void main(String args[]) {
        new WordleGame();
    }
}