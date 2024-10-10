package br.pucpr.jge;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager {
    private Set<Integer> keys = new HashSet<>();

    public boolean isDown(int code) {
        return keys.contains(code);
    }

    KeyListener getListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys.remove(e.getKeyCode());
            }
        };
    }
}
