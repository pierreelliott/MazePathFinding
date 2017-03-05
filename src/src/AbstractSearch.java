package src;

public class AbstractSearch {

    public AbstractSearch(int width, int height) {
        maze = new Maze(width, height);
        initSearch();
    }

    public Maze getMaze() {
        return maze;
    }
    protected Maze maze;
    /*
     * Java type Position (with width and height encoding x and y directions) is used for path finding
     */
    protected Position[] searchPath = null;
    protected int pathCount;
    protected int maxDepth;
    protected Position startPos, goalPos, currentPos;
    protected boolean isSearching = true;

    protected void initSearch() {
        if (searchPath == null) {
            searchPath = new Position[1000];
            for (int i = 0; i < 1000; i++) {
                searchPath[i] = new Position();
            }
        }
        pathCount = 0;
        startPos = maze.startPos;
        currentPos = startPos;
        goalPos = maze.goalPos;
        searchPath[pathCount++] = currentPos;
    }

    protected boolean equals(Position d1, Position d2) {
        return d1.x == d2.x && d1.y == d2.y;
    }

    public Position[] getPath() {
        Position[] ret = new Position[maxDepth];
        for (int i = 0; i < maxDepth; i++) {
            ret[i] = searchPath[i];
        }
        return ret;
    }

    protected Position[] getPossibleMoves(Position Pos) {
        Position tempMoves[] = new Position[4];
        tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;
        int x = Pos.x;
        int y = Pos.y;
        int num = 0;
        if (maze.getValue(x - 1, y) == 0 || maze.getValue(x - 1, y) == Maze.GOAL_POS_VALUE) {
            tempMoves[num++] = new Position(x - 1, y);
        }
        if (maze.getValue(x + 1, y) == 0 || maze.getValue(x + 1, y) == Maze.GOAL_POS_VALUE) {
            tempMoves[num++] = new Position(x + 1, y);
        }
        if (maze.getValue(x, y - 1) == 0 || maze.getValue(x, y - 1) == Maze.GOAL_POS_VALUE) {
            tempMoves[num++] = new Position(x, y - 1);
        }
        if (maze.getValue(x, y + 1) == 0 || maze.getValue(x, y + 1) == Maze.GOAL_POS_VALUE) {
            tempMoves[num++] = new Position(x, y + 1);
        }
        return tempMoves;
    }
}
