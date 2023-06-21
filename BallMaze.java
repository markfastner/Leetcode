public class BallMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
        //The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
        // When the ball stops, it could choose the next direction.
        //Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] 
        //and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
        //You may assume that the borders of the maze are all walls (see examples).

        //we craete an empty boolean array to keep track of visited cells
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);//recursively call the dfs function
    }

    public boolean dfs(int[][]maze, int[]start, int[]destination, boolean[][] visited){
        //base case
        if (visited[start[0]][start[1]]){//if we have already visited this cell then return false
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]){//if we have reached the destination then return true
            return true;
        }

        visited[start[0]][start[1]] = true;//set that we have visited this cell

        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;//get the next cells in each of the 4 directions

        //for each direction we find how far the ball can go in that direction
        //then we recursively call the dfs function on that cell
        while (r < maze[0].length && maze[start[0]][r] == 0) {// right
            r++;
        }
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited)){
            return true;
        }
        while (l >= 0 && maze[start[0]][l] == 0){ //left
            l--;
        }
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited)){
            return true;
        }
        while (u >= 0 && maze[u][start[1]] == 0){ //up
            u--;
        }
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited)){
            return true;
        }
        while (d < maze.length && maze[d][start[1]] == 0){ //down
            d++;
        }
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        //test cases
        BallMaze bm = new BallMaze();
        int[][] maze = {{0,0,1,0,0},
                        {0,0,0,0,0},
                        {0,0,0,1,0},
                        {1,1,0,1,1},
                        {0,0,0,0,0}};
        int[] start = {0,4};
        int[] destination = {4,4};
        System.out.println(bm.hasPath(maze, start, destination));

    }
}
