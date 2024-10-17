package br.pucpr.jge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
            var manager = GameManager.getInstance();
            game.load();
            var time = 1L;
            while (true) {
                var before = System.currentTimeMillis();
                var s = time / 1000.0;
                if (!game.update(s, input)) break;
                manager.update(s, input);

                var g2d = (Graphics2D) getBufferStrategy().getDrawGraphics();
                game.draw(g2d);
                manager.draw(g2d);

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
