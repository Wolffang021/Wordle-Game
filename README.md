# Wordle-Game

A desktop implementation of the classic **Wordle** game built using **Java Swing**. Guess the hidden word within a limited number of attempts and receive color-coded feedback for every guess.

This project focuses on core game logic, clean structure, and a simple GUI — no browser, no distractions, just words vs. you.

## ✨ Features

* Classic **Wordle gameplay**
* Graphical user interface built with **Java Swing**
* Randomly selected target words
* Color feedback for each guessed letter:
  * Correct letter & correct position
  * Correct letter but wrong position
  * Incorrect letter
* Validation against predefined word lists
* Runnable **JAR file** for playing without an IDE

## ▶️ How to Use

### Option 1: Run the JAR (Recommended)

1. Navigate to the `build/` folder
2. Download the `.jar` file
3. Run it using:

   ```bash
   java -jar Wordle-Game.jar
   ```

Make sure Java is installed on your system.

### Option 2: Run from Source

1. Clone the repository
2. Open the project in a Java IDE (IntelliJ, Eclipse, NetBeans, etc.)
3. Go to the `src/` directory
4. Run the `WordleGame.java` file

## 🛠️ Requirements

* **Java JDK 8 or higher**
* Any OS capable of running Java applications
* (Optional) Java IDE for running from source

## 📁 What’s Inside

```
Wordle-Game/
│
├── src/
│   ├── WordleGame.java              # Main game logic and Swing GUI
│   ├── Words.java                   # Base word handling
│   ├── TargetableWords.java         # Valid answer words
│   ├── NonTargetableWords_A.java    # Valid guess words (set A)
│   └── NonTargetableWords_B.java    # Valid guess words (set B)
│
├── img/
│   └── icon.png                     # Application icon
│
├── build/
│   └── *.jar                        # Runnable JAR file
│
├── README.md
├── .gitignore
└── .gitattributes
```

## 💬 Feedback

Feedback, bug reports, and improvements are always welcome.

* Open an **issue** on GitHub
* Fork the project and submit a **pull request**
* Or star the repo if it made Wordle slightly more addictive ⭐
