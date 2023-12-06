import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    // TODO: declare game variables
    ArrayList<Meteorite> meteors;
    ArrayList<Bullets> bullets;

    Meteorite m ;
    int bulletDelay; // Delay between bullet shots
    int lastBulletTime; // Last time a bullet was fired

    int lastMeteorHeight;
    public void settings() {
        size(600, 600);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        background(0);    // paint screen white
        fill(0,255,0);

        meteors = new ArrayList<Meteorite>();
        bullets = new ArrayList<Bullets>();

        drawMeteorite(true);
        bulletDelay = 300;
        lastBulletTime = 0;
        lastMeteorHeight = 0;
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(0);    // paint screen white

        // Creating spaceship
        SpaceShip myShip = new SpaceShip(50, 50);
        myShip.draw(this);

//      Displaying meteor
        for (int i = 0; i < meteors.size() ; i++) {
            Meteorite meteor = meteors.get(i);
            meteor.update();
            meteor.draw(this, i);

            // Removing meteors that exceed windows height
            if (meteor.getYCoordinate() > height) {
                meteors.remove(i);
                i--;
            }

            // Check for collisions with bullets
            for (int j = bullets.size() - 1; j >= 0; j--) {
                Bullets bullet = bullets.get(j);
                if (meteor.hits(bullet)) {
                    meteors.remove(i);
                    bullets.remove(j);
                    drawMeteorite(false);
                }
            }
        }

//      Displaying bullets
        for (Bullets bullet : bullets) {
            bullet.move();
            fill(255, 255, 0);
            ellipse(bullet.getXCoordinate(), bullet.getYCoordinate(), bullet.getRadius() * 2, bullet.getRadius() * 2);
        }

//      Creating delay between each fire
        int currentTime = millis();
//        if (currentTime - lastBulletTime > bulletDelay) {
//            bullets.add(new Bullets(mouseX, mouseY));
//            lastBulletTime = currentTime;
//        }

    }

    public void keyPressed() {
        if (key == 'S' || key == 's') {
            bullets.add(new Bullets(mouseX, mouseY));
        }
    }

    public void drawMeteorite(boolean init) {
        if (init) {
            for (int x = 20; x < width ; x+=40) {
                m = new Meteorite(x, 0);
                meteors.add(m);
            }
        }
        else {
            Meteorite lastMeteor = meteors.get(meteors.size()-1);
            int lastMeteorYCoordinate = (int) lastMeteor.getYCoordinate();
            for (int x = 20; x < width ; x+=40) {
                // if last meteors grid line has travelled less than 30 steps
                if (lastMeteorYCoordinate < lastMeteor.getRadius()) {
                    m = new Meteorite(x, -(30-lastMeteorYCoordinate));
                }
                else {
                    m = new Meteorite(x, 0);
                }
                meteors.add(m);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
