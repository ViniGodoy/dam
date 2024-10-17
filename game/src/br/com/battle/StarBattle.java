package br.com.battle;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_ESCAPE;

public class StarBattle implements Steps {
    @Override
    public void load() {
        GameManager.getInstance().add(new Ship());

        for (var y = 0; y < 4; y++) {
            for (var x = 0; x < 5; x++) {
                var xOff = 50 + 50 * (y % 2);
                var alien = new Alien(x * 150 + xOff, y * 75 + 25);
                GameManager.getInstance().add(alien);
            }
        }
    }

    @Override
    public boolean update(double s, InputManager keys) {
        if (keys.isDown(VK_ESCAPE)) {
            return false;
        }

        return true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);
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
