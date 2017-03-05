package src;

/**
 * Class Maze - class for representing search space as a bi-dimensional maze
 */
public class Maze {

    public static short OBSTACLE = -1;
    public static short START_POS_VALUE = -2;
    public static short GOAL_POS_VALUE = -3;
    private int width = 0;
    private int height = 0;
    public Position startPos = new Position();
    public Position goalPos = new Position();
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first search can use the array to store search
     * depth. A value of -1 indicates an obstacle in the maze.
     */
    private short[][] maze;

    public Maze(int width, int height) {
        System.out.println("New maze of size " + width + " by " + height);
        this.width = width;
        this.height = height;
        maze = new short[width + 2][height + 2];
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                maze[i][j] = 0;
            }
        }
        for (int i = 0; i < height + 2; i++) {
            maze[0][i] = maze[width + 1][i] = OBSTACLE;
        }
        for (int i = 0; i < width + 2; i++) {
            maze[i][0] = maze[i][height + 1] = OBSTACLE;
        }
        /**
         * Randomize the maze by putting up arbitrary obstacles
         */
        int max_obstacles = (width * height) / 3;
        for (int i = 0; i < max_obstacles; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            setValue(x, y, OBSTACLE);
        }
        /**
         * Specify the starting position
         */
        startPos.x = 0;
        startPos.y = 0;
        setValue(0, 0, START_POS_VALUE);
        /**
         * Specify the goal position
         */
        goalPos.x = width - 1;
        goalPos.y = height - 1;
        setValue(width - 1, height - 1, GOAL_POS_VALUE);
    }

    synchronized public short getValue(int x, int y) {
        return maze[x + 1][y + 1];
    }

    synchronized public void setValue(int x, int y, short value) {
        maze[x + 1][y + 1] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
