import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class FireworksPanel extends JPanel {
    private final List<Firework> fireworks;
    private final Random random;
    private final Timer timer;

    public FireworksPanel() {
        fireworks = new ArrayList<>();
        random = new Random();
        timer = new Timer(50, e -> updateFireworks());

        // Start the animation timer
        timer.start();
        setBackground(Color.BLACK);
    }

    private void updateFireworks() {
        // Add new fireworks periodically
        if (random.nextInt(10) > 7) {
            fireworks.add(new Firework(getWidth(), getHeight()));
        }

        // Update existing fireworks
        for (Firework firework : fireworks) {
            firework.update();
        }

        // Remove finished fireworks
        fireworks.removeIf(firework -> !firework.isAlive());

        // Repaint the panel to show the updated fireworks
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFireworks(g);
    }

    private void drawFireworks(Graphics g) {
        for (Firework firework : fireworks) {
            firework.draw(g);
        }
    }

    private static class Firework {
        private static final int MAX_PARTICLES = 50;
        private final List<Particle> particles;
        private final Random random;

        public Firework(int width, int height) {
            particles = new ArrayList<>();
            random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height / 2);

            for (int i = 0; i < MAX_PARTICLES; i++) {
                particles.add(new Particle(x, y, random));
            }
        }

        public void update() {
            for (Particle particle : particles) {
                particle.update();
            }
        }

        public void draw(Graphics g) {
            for (Particle particle : particles) {
                particle.draw(g);
            }
        }

        public boolean isAlive() {
            return particles.stream().anyMatch(Particle::isAlive);
        }
    }

    private static class Particle {
        private static final int MAX_LIFETIME = 100;
        private final int xStart, yStart;
        private final double angle, speed;
        private final Color color;
        private int lifetime;

        public Particle(int x, int y, Random random) {
            xStart = x;
            yStart = y;
            angle = Math.toRadians(random.nextInt(360));
            speed = random.nextDouble() * 2 + 1;
            color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            lifetime = MAX_LIFETIME;
        }

        public void update() {
            if (lifetime > 0) {
                lifetime--;
            }
        }

        public void draw(Graphics g) {
            if (lifetime > 0) {
                int x = xStart + (int) (speed * (MAX_LIFETIME - lifetime) * Math.cos(angle));
                int y = yStart + (int) (speed * (MAX_LIFETIME - lifetime) * Math.sin(angle));
                g.setColor(color);
                g.fillOval(x, y, 5, 5);
            }
        }

        public boolean isAlive() {
            return lifetime > 0;
        }
    }
}
