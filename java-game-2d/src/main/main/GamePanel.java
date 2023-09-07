package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public  class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int orginalTileSize = 16;
    final int scale = 3;
    final int tileSize = orginalTileSize * scale;
    
    final int maxScreenCol = 32; 
    final int maxScreenRow = 18;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
    }
}
