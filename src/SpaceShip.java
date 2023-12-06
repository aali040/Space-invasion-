import processing.core.PApplet;

import java.util.ArrayList;

public class SpaceShip {
    //Bullets bullets = new Bullets()
    private int width;
    private int height;

    public SpaceShip(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(PApplet window ) {
        window.fill(0, 255, 0);
        window.rect(window.mouseX-20, window.mouseY, 50, 50);
    }
}

