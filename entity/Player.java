package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel g, KeyHandler k){
        this.gp = g;
        this.keyH = k;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("../res/src/player/boy_up_1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("..res/src/player/boy_up_2.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("..res/src/player/boy_down_1.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/src/player/boy_down_2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/src/player/boy_left_1.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/src/player/boy_left_2.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("..res/src/player/boy_right_1.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/src/player/boy_right_2.png.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.up == true){
            direction = "up";
            y -= speed;
        }
        else if(keyH.down == true){
            direction = "down";
            y+= speed;
        }
        else if (keyH.left == true){
            direction = "left";
            x-= speed;
        }
        else if (keyH.right == true){
            direction = "right";
            x+= speed;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize)
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
