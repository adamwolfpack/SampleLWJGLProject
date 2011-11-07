package sample.java.project;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

/**
 * The main class.
 *
 * This is the main class of the application. It contains the main()
 * method, the first method called.
 */
public class SampleLWJGLProject implements Runnable {

    /** The delay between frames. */
    private static final int FPS = 60;

    /** Width of the display. */
    private static final int WIDTH = 600;

    /** Height of the display. */
    private static final int HEIGHT = 600;

    /** The length of one second in milliseconds. */
    private static final double SECOND = 1000d;

    /** Rate of rotation oscillation. */
    private static final float ROT_RATE = 4f;

    /** Range of rotation oscillation. */
    private static final float ROT_RANGE = 36;

    /* Red diffuse light. */
    float[] light_diffuse = {1f, 0f, 0f, 1f};
    /* Infinite light location. */
    float[] light_position = {1f, 1f, 1f, 0f};
    /* Normals for the 6 faces of a cube. */
    float[][] n = {
        {-1f, 0f, 0f}, {0f, 1f, 0f}, {1f, 0f, 0f},
        {0f, -1f, 0f}, {0f, 0f, 1f}, {0f, 0f, -1f}
    };
    /* Vertex indices for the 6 faces of a cube. */
    int[][] faces = {
        {0, 1, 2, 3}, {3, 2, 6, 7}, {7, 6, 5, 4},
        {4, 5, 1, 0}, {5, 6, 2, 1}, {7, 4, 0, 3}
    };
    /* Will be filled in with X,Y,Z vertexes. */
    float[][] v = new float[8][3];

    float[][] colors = {
        {1f, 0f, 0f}, {0f, 1f, 0f}, {0f, 0f, 1f},
        {1f, 1f, 0f}, {0f, 1f, 1f}, {1f, 0f, 1f}
    };

    /**
     * The main class.
     *
     * Print the "Hello, world!" string.
     *
     * @param args application input arguments
     */
    public static void main(final String[] args) {
        try {
            com.nullprogram.lwjgl.Lwjgl.setup();
        } catch (java.io.IOException e) {
            System.out.println("error: could not prepare libraries: " + e);
            System.exit(0);
        }
        new SampleLWJGLProject().run();
    }

    @Override
    public final void run() {
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
            init();
        } catch (LWJGLException e) {
            System.out.println("error: could not prepare display: " + e);
            return;
        }

        while (!Display.isCloseRequested()) {
            repaint();
            Display.update();
            Display.sync(FPS);
        }
        Display.destroy();
    }

    private FloatBuffer wrap(float[] in) {
        FloatBuffer buffer;
        buffer = BufferUtils.createFloatBuffer(in.length * 4);
        return buffer.put(in);
    }

    /**
     * Initial display configuration.
     */
    private void init() {
        /* Setup cube vertex data. */
        v[0][0] = v[1][0] = v[2][0] = v[3][0] = -1;
        v[4][0] = v[5][0] = v[6][0] = v[7][0] = 1;
        v[0][1] = v[1][1] = v[4][1] = v[5][1] = -1;
        v[2][1] = v[3][1] = v[6][1] = v[7][1] = 1;
        v[0][2] = v[3][2] = v[4][2] = v[7][2] = 1;
        v[1][2] = v[2][2] = v[5][2] = v[6][2] = -1;

        /* Enable a single OpenGL light. */
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, wrap(light_diffuse));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, wrap(light_position));
        GL11.glEnable(GL11.GL_LIGHT0);
        //GL11.glEnable(GL11.GL_LIGHTING);

        /* Use depth buffering for hidden surface elimination. */
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        /* Setup the view of the cube. */
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        Project.gluPerspective(40f, 1f, 1f, 10f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        Project.gluLookAt(0f, 0f, 5f,
                          0f, 0f, 0f,
                          0f, 1f, 0f);

        /* Adjust cube position to be asthetic angle. */
        GL11.glTranslatef(0f, 0f, -1f);
        GL11.glRotatef(60f, 1f, 0f, 0f);
        GL11.glRotatef(-20f, 0f, 0f, 1f);
    }

    /**
     * Draw the OpenGL display.
     */
    private void repaint() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        double time = System.currentTimeMillis() / SECOND;
        GL11.glPushMatrix();
        float r = (float) (Math.sin(time * ROT_RATE) * ROT_RANGE + ROT_RANGE);
        GL11.glRotatef(r, 0f, 0f, 1f);

        for (int i = 0; i < 6; i++) {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(colors[i][0], colors[i][1], colors[i][2]);
            GL11.glNormal3f(n[i][0], n[i][1], n[i][2]);
            GL11.glVertex3f(v[faces[i][0]][0],
                            v[faces[i][0]][1],
                            v[faces[i][0]][2]);
            GL11.glVertex3f(v[faces[i][1]][0],
                            v[faces[i][1]][1],
                            v[faces[i][1]][2]);
            GL11.glVertex3f(v[faces[i][2]][0],
                            v[faces[i][2]][1],
                            v[faces[i][2]][2]);
            GL11.glVertex3f(v[faces[i][3]][0],
                            v[faces[i][3]][1],
                            v[faces[i][3]][2]);
            GL11.glEnd();
        }

        GL11.glPopMatrix();
    }
}
