import java.util.ArrayList;

class Sudoku {
// Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    public boolean isValidSudoku(char[][] board) {
        //loop through the board
        //check if the row is valid
        for(int i = 0; i < 9; i++){
            //loop through the row
            ArrayList<Character> row = new ArrayList<Character>();
            for(int ii = 0; ii < 9; ii++){
                char current = board[i][ii];
                if(current != '.'){
                    if(row.contains(current)){
                        return false;
                    }
                    else{
                        row.add(current);
                    }
                        
                }
                
            }
        }

        //check if the column is valid
        for(int i = 0; i < 9; i++){
            //loop through the row
            ArrayList<Character> col = new ArrayList<Character>();
            for(int ii = 0; ii < 9; ii++){
                char current = board[ii][i];
                if(current != '.'){
                    if(col.contains(current)){
                        return false;
                    }
                    else{
                        col.add(current);
                    }
                        
                }
                
            }
        }

        //check if the 3x3 is valid
        for(int i = 0; i < 3; i++){
            for(int ii = 0; ii < 3; ii++){
                ArrayList<Character> box = new ArrayList<Character>();
                for(int iii = 0; iii < 3; iii++){
                    for(int iiii = 0; iiii < 3; iiii++){
                        char current = board[iii + (3 * i)][iiii + (3 * ii)];
                        if(current != '.'){
                            if(box.contains(current)){
                                return false;
                            }
                            else{
                                box.add(current);
                            }
                                
                        }
                    }
                }
                
            }
        }   
        return true; 
    }

    int newboardcounter = 0;

    public void solveSudoku(char[][] board){
// Write a program to solve a Sudoku puzzle by filling the empty cells.

// A sudoku solution must satisfy all of the following rules:

// Each of the digits 1-9 must occur exactly once in each row.
// Each of the digits 1-9 must occur exactly once in each column.
// Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
// The '.' character indicates empty cells.
        solve(board);
    }
    
    public boolean solve(char[][] board) {
        //solve the sudoku
        for( int i = 0; i < 9; i++){
            for(int ii = 0; ii < 9; ii++){
                char current = board[i][ii];
                if(current == '.'){
                    for(char num='1';num<='9';num++){
                        if(isPossible(board,i,ii,num)){
                            board[i][ii]=num;
                            newboardcounter++;
                            if(solve(board)){
                                return true;
                            }
                            else{
                                board[i][ii]='.';
                            }
                        }
                    }
                    return false;
                }
                
            }
        }
        return true;
    }

    public boolean isPossible(char[][] board, int row, int col, char c){
        for(int i=0;i<9;i++){
            if(board[i][col]==c) return false;
            if(board[row][i]==c) return false;
            if(board[3*(row/3)+i/3][3*(col/3)+i%3]==c) return false;//checking in each 3 x 3 matrix
        }
        return true;
    }

    public static void main(String[] args) {
        //test isValidSudoku
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
        Sudoku s = new Sudoku();
        System.out.println(s.isValidSudoku(board));
        s.solveSudoku(board);
        //print the board
        for(int i = 0; i < 9; i++){
            for(int ii = 0; ii < 9; ii++){
                System.out.print(board[i][ii] + " ");
            }
            System.out.println();
        }
        System.out.println(s.newboardcounter);
    }
}