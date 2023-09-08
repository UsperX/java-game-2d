package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public  class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int orginalTileSize = 16;
    final int scale = 3;
    final int tileSize = orginalTileSize * scale;
    
    final int maxScreenCol = 32; 
    final int maxScreenRow = 18;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    final int UPS = 60;
    int maxFPS = Integer.MAX_VALUE;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //Player start position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //TODO Seperate update and draw to different threads
    @Override
    public void run() {
        double drawInterval = 1000000000/maxFPS;
        double updateInterval = 1000000000/UPS;
        double deltaUpdate = 0; 
        double deltaDraw = 0;
        long lastTime = System.nanoTime();
        long currentTime; 
        long timer = 0; 
        int drawCount = 0;
        int updateCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            deltaUpdate += (currentTime - lastTime) / updateInterval;
            deltaDraw += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(deltaUpdate >= 1) {
                update();
                deltaUpdate--;
                updateCount++;
            }
            if(deltaDraw >= 1) {
                repaint();
                deltaDraw--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount + " | Updates: " + updateCount);
                drawCount = 0;
                updateCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        if(keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        if(keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        if(keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
