package br.com.battle;

import br.pucpr.jge.InputManager;
import br.pucpr.jge.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

public class Ship {
    private BufferedImage img;
    private double x;
    private double y = 400;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void load() {
        var loader = new Loader();
        img = loader.loadImage("/ship.png");
    }

    public void update(double s, InputManager keys) {
        if (keys.isDown(VK_RIGHT)) {
            x += 400 * s;
        } else if (keys.isDown(VK_LEFT)) {
            x -= 400 * s;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawImage(img,
            (int) x, (int)y,
            img.getWidth() / 2, img.getHeight() / 2,
            null
        );
    }
}
