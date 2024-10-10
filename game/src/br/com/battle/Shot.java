package br.com.battle;

import br.pucpr.jge.InputManager;
import br.pucpr.jge.Loader;

import java.awt.*;

public class Shot {
    private double x;
    private double y;

    public Shot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(double s, InputManager keys) {
        y -= 800 * s;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval((int) x, (int) y, 15, 15);
    }
}
