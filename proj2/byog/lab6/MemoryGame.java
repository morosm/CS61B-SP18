package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < n) {
            sb.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }

        return sb.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear();
        Font font = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(this.width/2, this.height/2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(750);
            drawFrame(" ");
            StdDraw.pause(750);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);

        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            if(StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
                drawFrame(sb.toString());
            }
        }

        StdDraw.pause(750);
        return sb.toString();
    }

    public void startGame() {
        gameOver = false;
        playerTurn = false;
        round = 1;

        while(!gameOver){
            playerTurn = false;
            drawFrame("Round " + round + "! Good luck!");
            StdDraw.pause(1500);

            String s = generateRandomString(round);
            flashSequence(s);

            playerTurn = true;
            String typedS = solicitNCharsInput(round);

            if(!typedS.equals(s)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            }else {
                drawFrame("Correct, well done!");
                StdDraw.pause(1500);
                round += 1;
            }
        }
    }
}
