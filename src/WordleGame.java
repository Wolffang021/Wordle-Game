import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WordleGame extends JFrame {
    int curBoxIndex = 0;    // To navigate during typing
    int curBoxRow = 0;      // To navigate during typing
    int wordBoxX = 16;
    int wordBoxY = 10;
    String targetWord;
    String inputWord;
    
    void PlayGame() {
        Random random = new Random();
        Words words = new Words();
        int randomRow = random.nextInt(words.wordsList.length);
        int randomColumn = random.nextInt(words.wordsList[randomRow].length);

        targetWord = words.wordsList[randomRow][randomColumn];
        System.out.println(targetWord);
        inputWord = "";

        JPanel[][] wordBox = new JPanel[6][5];
        JLabel[][] boxText = new JLabel[6][5];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                wordBox[i][j] = new JPanel();
                wordBox[i][j].setBackground(new Color(30, 30, 30));
                wordBox[i][j].setBounds(wordBoxX += 52, wordBoxY, 50, 50);
                this.add(wordBox[i][j]);
        
                boxText[i][j] = new JLabel();
                // System.out.print(boxText[i][j].getFont());
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
        Font messageTextFont = new Font("dialog", Font.BOLD, 24);
        messageText.setFont(messageTextFont);
        messageText.setForeground(new Color(200, 200, 200));
        messageText.setText("");
        messageBox.add(messageText);
        
        this.addKeyListener(new KeyAdapter() {
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
                        if (inputWord.equals(targetWord)) {
                            Font messageTextFont = new Font("dialog", Font.BOLD, 24);
                            messageText.setFont(messageTextFont);
                            messageText.setText("CORRECT!");
                            
                            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                            return;
                        }
                        else {
                            for (int i = 0; i < words.wordsList.length; i++) {
                                for (int j = 0; j < words.wordsList[i].length; j++) {
                                    if (inputWord.equals(words.wordsList[i][j])) {
                                        Font messageTextFont = new Font("dialog", Font.BOLD, 24);
                                        messageText.setFont(messageTextFont);
                                        messageText.setText("INCORRECT!");
                                        
                                        curBoxIndex = 0;
                                        curBoxRow++;
                                        inputWord = "";
                                        
                                        if (curBoxRow > 5) {
                                            messageTextFont = new Font("dialog", Font.BOLD, 16);
                                            messageText.setFont(messageTextFont);
                                            messageText.setText("THE WORD WAS " + targetWord);
                                            
                                            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                                        }
                                        
                                        return;
                                    }    
                                }    
                            }    
                            
                            Font messageTextFont = new Font("dialog", Font.BOLD, 20);
                            messageText.setFont(messageTextFont);
                            messageText.setText("NO SUCH WORD!");
                        }    
                    }    
                    else {
                        Font messageTextFont = new Font("dialog", Font.BOLD, 18);
                        messageText.setFont(messageTextFont);
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
        });
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.revalidate();;
        this.repaint();
        this.setVisible(true);
    }
    
    public WordleGame() {
        this.setTitle("Wordle Game");
        this.setSize(400, 600);
        this.getContentPane().setBackground(new Color(55, 50, 50));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        PlayGame();
    }

    public static void main(String args[]) {
        new WordleGame();
    }
}