package br.pucpr.jge;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> newObjects = new ArrayList<>();

    private GameManager() {
    }

    public static GameManager getInstance() {
        return instance;
    }

    public GameManager clear() {
        objects.clear();
        return this;
    }

    public GameManager add(GameObject obj) {
        obj.load();
        newObjects.add(obj);
        return this;
    }

    void update(double s, InputManager keys) {
        var it = objects.iterator();
        while (it.hasNext()) {
            var go = it.next();
            go.update(s, keys);
            if (!go.isInGame()) {
                it.remove();
            }
        }
        objects.addAll(newObjects);
        newObjects.clear();
    }

    void draw(Graphics2D g2d) {
        for (var obj : objects) {
            obj.draw(g2d);
        }
    }
}
