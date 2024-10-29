package se233.asteroidproject.characters;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class PlayerShipTest {
    private PlayerShip playerShip;
    private List<Bullet> bullets;
    private List<Asteroids> asteroids;

    @Before
    public void setUp() {
        // Initialize JavaFX runtime
        new JFXPanel();
        playerShip = new PlayerShip();
        bullets = new ArrayList<>();
        asteroids = new ArrayList<>();
    }

    @Test
    public void testTurnLeft() {
        double initialAngle = playerShip.getEntityShape().getRotate();
        playerShip.turnLeft();
        assertEquals(initialAngle - 5, playerShip.getEntityShape().getRotate(), 0.01);
    }

    @Test
    public void testTurnRight() {
        double initialAngle = playerShip.getEntityShape().getRotate();
        playerShip.turnRight();
        assertEquals(initialAngle + 5, playerShip.getEntityShape().getRotate(), 0.01);
    }

    @Test
    public void testApplyThrust() {
        Point2D initialMovement = playerShip.getMovement();
        playerShip.applyThrust();
        assertNotEquals(initialMovement, playerShip.getMovement());
    }

    @Test
    public void testShootBullet() {
        int initialBulletCount = bullets.size();
        playerShip.shootBullet(bullets);
        assertEquals(initialBulletCount + 1, bullets.size());
    }

    @Test
    public void testScorePoints() {
        int initialScore = 0;
        Asteroids asteroid = new AsteroidsVar.SmallAsteroids(); // Use a concrete subclass
        asteroids.add(asteroid);
        playerShip.shootBullet(bullets);
        Bullet bullet = bullets.get(0);

        // Simulate bullet movement to collide with the asteroid
        bullet.getEntityShape().setTranslateX(asteroid.getEntityShape().getTranslateX());
        bullet.getEntityShape().setTranslateY(asteroid.getEntityShape().getTranslateY());

        if (bullet.hasCollided(asteroid)) {
            initialScore += 100; // Assuming 100 points per asteroid
        }
        assertEquals(100, initialScore);
    }
}