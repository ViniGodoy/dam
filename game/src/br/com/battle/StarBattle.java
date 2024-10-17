package br.com.battle;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class StarBattle implements Steps {
    private Ship ship = new Ship();
    private List<Alien> aliens = new ArrayList<>();
    private List<Shot> shots = new ArrayList<>();
    private double shotInterval = 1;

    @Override
    public void load() {
        ship.load();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                var xOff = 50 * (y % 2) + 50;
                var alien = new Alien(x * 150 + xOff, y * 75 + 25);
                alien.load();
                aliens.add(alien);
            }
        }
    }

    @Override
    public boolean update(double s, InputManager keys) {
        shotInterval += s;
        if (keys.isDown(VK_ESCAPE)) {
            return false;
        }

        if (keys.isDown(VK_SPACE) && shotInterval > 0.3) {
            var shot = new Shot(ship.getX() + 25, ship.getY());
            shot.load();
            shots.add(shot);
            shotInterval = 0;
        }

        ship.update(s, keys);
        for (var alien : aliens) {
            alien.update(s, keys);
        }
        var it = shots.iterator();
        while (it.hasNext()) {
            var shot = it.next();
            shot.update(s, keys);
            if (shot.getY() < 10) {
                it.remove();
            }
        }

        return true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        ship.draw(g2d);
        for (var alien : aliens) {
            alien.draw(g2d);
        }
        for (var shot : shots) {
            shot.draw(g2d);
        }
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
