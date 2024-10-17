package br.com.battle;

import br.pucpr.jge.InputManager;
import br.pucpr.jge.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Alien {
    private double x;
    private double y;
    private double t = 0;
    private double initialX;
    private BufferedImage sprite;

    public Alien(double x, double y) {
        this.initialX = x;
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
        sprite = new Loader().loadImage("/image/destroyer.png");
    }

    public void update(double s, InputManager keys) {
        t += s;
        x = initialX + Math.sin(t) * 50;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprite, (int)x, (int)y, null);
    }
}
