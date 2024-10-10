package br.pucpr.jge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class GameFrame extends JFrame {
    private Steps game;
    private InputManager input;

    public GameFrame(String title, int w, int h, Steps game) {
        super(title);
        this.game = game;
        this.input = new InputManager();

        setSize(w, h);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        setUndecorated(true);
        setIgnoreRepaint(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                createBufferStrategy(2);
                new Thread(() -> gameLoop()).start();
            }
        });

        addKeyListener(input.getListener());
    }

    private void gameLoop() {
        try {
            game.load();
            var time = 1L;
            while (true) {
                var before = System.currentTimeMillis();
                if (!game.update(time / 1000.0, input)) break;

                game.draw((Graphics2D) getBufferStrategy().getDrawGraphics());
                if (!getBufferStrategy().contentsLost()) {
                    getBufferStrategy().show();
                    Thread.sleep(1);
                    time = System.currentTimeMillis() - before;
                }
                game.unload();
            }
        } catch (InterruptedException e) {
            System.err.println("Game interrupted!");
        } catch (Exception e) {
            System.err.println("Game aborted due to exception");
            e.printStackTrace();
        } finally {
            EventQueue.invokeLater(this::dispose);
        }
    }
}
