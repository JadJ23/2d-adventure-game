package entity;

import java.awt.image.BufferedImage;

// super to store vars used in player, monster, npc classes
public class Entity {

    public int x;
    public int y;
    public int speed;

    public BufferedImage bI, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
}
