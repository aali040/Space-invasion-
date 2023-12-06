import processing.core.PApplet;

public class Meteorite extends PApplet {
    private int xCoordinate;
    private int yCoordinate;
    private int speed;
    private int radius;
    public Meteorite(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed=1;
        this.radius=30;
    }

    public void setXCoordinate(int x) {
        this.xCoordinate = x;
    }

    public void setYCoordinate(int y) {
        this.yCoordinate = y;
    }

    public float getXCoordinate() {
        return this.xCoordinate;
    }

    public float getYCoordinate() {
        return this.yCoordinate;
    }

    public float getRadius() {
        return this.radius;
    }
    public void update() {
        yCoordinate = yCoordinate + speed;
//        xCoordinate= xCoordinate + speed;
//        if (xCoordinate == 0 || xCoordinate == 800){
//            xCoordinate-= speed;
//        }
    }

    public void draw(PApplet window, int i) {
        if (i % 2 == 0) {
            window.fill(255, 255, 0);
        }
        else {
            window.fill(255, 0, 255);
        }
        window.ellipse(xCoordinate, yCoordinate, radius, radius);
    }

    public boolean hits(Bullets bullet) {
        double deltaX = bullet.getXCoordinate() - xCoordinate;
        double deltaY = bullet.getYCoordinate() - yCoordinate;

        double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return d < radius + bullet.getRadius();
    }


}
