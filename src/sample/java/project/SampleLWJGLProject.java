package sample.java.project;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * The main class.
 *
 * This is the main class of the application. It contains the main()
 * method, the first method called.
 */
public class SampleLWJGLProject implements Runnable {

    /** The delay between frames. */
    private static final long DELAY = 16L;

    /** Width of the display. */
    private static final int WIDTH = 800;

    /** Height of the display. */
    private static final int HEIGHT = 600;

    /**
     * The main class.
     *
     * Print the "Hello, world!" string.
     *
     * @param args application input arguments
     */
    public static void main(final String[] args) {
        new SampleLWJGLProject().run();
    }

    @Override
    public final void run() {
        try {
            com.nullprogram.lwjgl.Lwjgl.setup();
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } catch (java.io.IOException e) {
            System.out.println("error: could not prepare libraries: " + e);
            System.exit(0);
        } catch (LWJGLException e) {
            System.out.println("error: could not prepare display: " + e);
            System.exit(0);
        }
        while (!Display.isCloseRequested()) {
            repaint();
            Display.update();
        }
        Display.destroy();
    }

    /**
     * Draw the OpenGL display.
     */
    private void repaint() {

    }

    /**
     * Sleep the current thread for a given interval.
     * @param ms  the number of milliseconds to sleep
     */
    private void sleep(final long ms) {
        try {
            Thread.sleep(DELAY);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Add two integers together.
     *
     * This is a dumb method that is here for the purposed of unit
     * testing.
     *
     * @param  a first number
     * @param  b second number
     * @return sum of the numbers
     */
    public final int add(final int a, final int b) {
        return a + b;
    }
}
