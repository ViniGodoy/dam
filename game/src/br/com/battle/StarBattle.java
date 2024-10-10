package br.com.battle;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

import java.awt.*;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_SPACE;

public class StarBattle implements Steps {
    private Ship ship = new Ship();
    private Shot shot = null;

    @Override
    public void load() {
        ship.load();
    }

    @Override
    public boolean update(double s, InputManager keys) {
        if (keys.isDown(VK_ESCAPE)) {
            return false;
        }

        ship.update(s, keys);

        //Lógica de atirar
        if (keys.isDown(VK_SPACE) && shot == null) {
            shot = new Shot(ship.getX(), ship.getY());
        }

        //Faz o tiro voar
        if (shot != null) {
            shot.update(s, keys);
            if (shot.getY() < -50) shot = null;
        }
        return true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        ship.draw(g2d);
        if (shot != null) shot.draw(g2d);
    }

    @Override
    public void unload() {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
            new GameFrame("Star Battle",
                800, 600,
                new StarBattle()
            ).setVisible(true)
        );
    }
}
