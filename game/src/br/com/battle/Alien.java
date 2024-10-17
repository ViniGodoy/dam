package br.com.battle;

import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Alien extends GameObject {
    private double initialX;
    private double t;

    public Alien(double x, double y) {
        super("/image/destroyer.png", x, y);
        this.initialX = x;
    }

    public void update(double s, InputManager keys) {
        x = initialX + Math.sin(t) * 50;
        t += s;
    }
}
