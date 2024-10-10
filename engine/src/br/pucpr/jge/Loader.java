package br.pucpr.jge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Loader {
    private String root;

    public Loader(String root) {
        this.root = root;
    }

    public Loader() {
        this("/assets");
    }

    BufferedImage createErrorImage() {
        var img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        var g2d = img.getGraphics();
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(0, 0, 100, 100);
        g2d.dispose();

        return img;
    }

    public BufferedImage loadImage(String path) {
        var name = root + path;
        try {
            return ImageIO.read(getClass().getResourceAsStream(name));
        } catch (Exception e) {
            System.err.println("Unable to load " + name);
            return createErrorImage();
        }
    }
}
