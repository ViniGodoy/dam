package br.pucpr.jge;

import java.awt.*;

public interface Steps {
    void load();
    boolean update(double s, InputManager keys);
    void draw(Graphics2D g2d);
    void unload();
}
