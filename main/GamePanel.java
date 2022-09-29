package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //determine screen settings
    final int standardTileSize = 16; //16 by 16 pixels is standard
    final int scale = 3; // 16 pixels small so need to make bigger

    final int tileSize = standardTileSize * scale; // 48x48 pixels is standard scaled size
    final int maxScreenHeight = 16;
    final int maxScreenWidth = 12;
    //16 x 12 or 4 x3

    final int screenWidth = tileSize * maxScreenHeight; //768
    final int screenHeight = tileSize * maxScreenWidth; //576

    int FPS = 60; // update frames per second

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set player positon
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //used to improve rendering
        this.addKeyListener(keyH);
        this.setFocusable(true); // focus on game panel to receive keyboard input

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        //game loop

        double drawInterval = 1000000000/FPS; // one second / 60
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1) update info i.e character position
            update();
            //2) draw new screen due to update
            repaint();
            //fixes problem of player moving too much on one key click
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime /1000000; //convert ns to ms bc of sleep method
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); // pauses game loop
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        //upper left is (0,0) so x increases to the right and y increases down
        // update position due to key input
        if (keyH.up == true){
            playerY -= playerSpeed;
        }
        else if(keyH.down == true){
            playerY+= playerSpeed;
        }
        else if (keyH.left == true){
            playerX-= playerSpeed;
        }
        else if (keyH.right == true){
            playerX+= playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // use as character for now
        g2.dispose(); // saves memory; good practice
    }
}
