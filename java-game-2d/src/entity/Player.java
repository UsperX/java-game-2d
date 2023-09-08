package entity;

import main.KeyHandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {

    GamePanel gp; 
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(new File("java-game-2d\\res\\player\\up1.png"));
            up2 = ImageIO.read(new File("java-game-2d\\res\\player\\up2.png"));
            down1 = ImageIO.read(new File("java-game-2d\\res\\player\\down1.png"));
            down2 = ImageIO.read(new File("java-game-2d\\res\\player\\down2.png"));
            left1 = ImageIO.read(new File("java-game-2d\\res\\player\\left1.png"));
            left2 = ImageIO.read(new File("java-game-2d\\res\\player\\left2.png"));
            right1 = ImageIO.read(new File("java-game-2d\\res\\player\\right1.png"));
            right2 = ImageIO.read(new File("java-game-2d\\res\\player\\right2.png"));

            //Alternative 
            //up1 = ImageIO.read(new File("src/player/up1.png"));


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading player images");
        }
    }

    //TODO: Make speed consistent in all directions
    public void update() {

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            } 

            spriteCounter++;
            if(spriteCounter == 10) {
                spriteNum = 2;
            } else if(spriteCounter == 20) {
                spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(java.awt.Graphics2D g2d) {
 //       g2d.setColor(java.awt.Color.WHITE);
 //       g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                } 
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                } 
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                } 
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
    
}
