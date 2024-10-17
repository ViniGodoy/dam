package br.pucpr.jge;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected BufferedImage sprite;
    private String spriteName;

    public GameObject(String spriteName, double x, double y) {
        this.spriteName = spriteName;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void load() {
        sprite = new Loader().loadImage(spriteName);
    }

    public void update(double s, InputManager keys) {
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprite, (int)x, (int)y, null);
    }

    public boolean isInGame() {
        return true;
    }
}
