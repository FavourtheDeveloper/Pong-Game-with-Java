import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JPanel implements KeyListener, ActionListener {
    private int ballX = 200;
    private int ballY = 200;
    private int ballDiameter = 20;
    private int ballXSpeed = 3;
    private int ballYSpeed = 3;

    private int paddle1Y = 150;
    private int paddle2Y = 150;
    private int paddleWidth = 10;
    private int paddleHeight = 80;

    private boolean upPressed1 = false;
    private boolean downPressed1 = false;
    private boolean upPressed2 = false;
    private boolean downPressed2 = false;

    private Timer timer;

    public Pong() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(50, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(740, paddle2Y, paddleWidth, paddleHeight);

        // Draw ball
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    }

    public void actionPerformed(ActionEvent e) {
        moveBall();
        movePaddles();
        checkCollisions();
        repaint();
    }

    public void moveBall() {
        ballX += ballXSpeed;
        ballY += ballYSpeed;
    }

    public void movePaddles() {
        if (upPressed1 && paddle1Y > 0) {
            paddle1Y -= 5;
        }
        if (downPressed1 && paddle1Y < getHeight() - paddleHeight) {
            paddle1Y += 5;
        }
        if (upPressed2 && paddle2Y > 0) {
            paddle2Y -= 5;
        }
        if (downPressed2 && paddle2Y < getHeight() - paddleHeight) {
            paddle2Y += 5;
        }
    }

    public void checkCollisions() {
        // Ball and wall collisions
        if (ballY <= 0 || ballY + ballDiameter >= getHeight()) {
            ballYSpeed = -ballYSpeed;
        }
        // Ball and paddle collisions
        if (ballX <= 50 + paddleWidth && ballY + ballDiameter >= paddle1Y && ballY <= paddle1Y + paddleHeight) {
            ballXSpeed = -ballXSpeed;
        }
        if (ballX + ballDiameter >= 740 && ballY + ballDiameter >= paddle2Y && ballY <= paddle2Y + paddleHeight) {
            ballXSpeed = -ballXSpeed;
        }
        // Ball out of bounds
        if (ballX <= 0 || ballX + ballDiameter >= getWidth()) {
            // Reset ball position
            ballX = 200;
            ballY = 200;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed1 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed1 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed2 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed2 = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed1 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed1 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed2 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed2 = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new Pong());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
