public class LightsOut {

    public static CellState[][] getExample(){
        char [][] ex = {{'.', '*', '.', '*', '.'},
                        {'*', '.', '*', '.', '*'},
                        {'.', '*', '*', '*', '.'},
                        {'*', '.', '*', '.', '*'},
                        {'.', '*', '.', '*', '.'}};
        //Einen Array erstellen der CellState als Wert akzeptiert
        CellState[][] result = new CellState[ex.length][ex[0].length];
        //Verschachtelte schleife, um durch den 2 dimensionalen Array zu iterieren
        for(int i = 0; i < ex.length; i++){
            for(int x = 0; x < ex[i].length; x++){
                result[i][x] = CellState.fromDisplay(ex[i][x]);
            }
        }
        return result;
    }

    /**
     * Checks if a Board has valid Values und the valid length
     * @param board The board u want to check
     * @throws IllegalArgumentException
     */
    public static void checkBoard(CellState[][] board){
        boolean checker = false;
        //Check if board is null
        if(board != null){
            //Check if board has five Rows
            if(board.length == 5){
                checker = true;
                for (CellState[] cellStates : board) {
                    //cehck every row to see if it has null values
                    for (CellState cellState : cellStates) {
                        if (cellState == null) {
                            throw new IllegalArgumentException("Value cant be null!");
                        }
                    }
                    //check if length of a row is 5 when not checker is false
                    if (cellStates.length != 5) {
                        checker = false;
                        break;
                    }
                }
            }
        }
        //when checker is false it throws a exception
        if(!checker){
            throw new IllegalArgumentException("Board has not the matching size!");
        }
    }

    public static void printBoard(CellState[][] board){
        checkBoard(board);
        int count = 0;
        System.out.println("   0 1 2 3 4");
        System.out.println("  +-+-+-+-+-+");
        for (CellState[] cellStates : board) {
            System.out.print(count + " |");
            for (CellState cellState : cellStates) {
                System.out.print(cellState.getDisplay() + "|");
            }
            System.out.println("\n  +-+-+-+-+-+");
            count++;
        }
    }

    public static int countLightsOn(CellState[][] board){
        checkBoard(board);
        int count = 0;
        for(CellState[] cellStates : board){
            for(CellState cellState : cellStates){
                if(cellState == CellState.ON){
                    count++;
                }
            }
        }
        return count;
    }
    public static void toggle(CellState[][] board, int row, int col){
        checkBoard(board);
        if(!(row >= 0 && row <= 4 && col >= 0 && col <= 4)){
            throw new IllegalArgumentException("Index muss zwischen 0 und 4 sein");
        }

        // Die Zelle selbst toggeln
        board[row][col] = board[row][col].toggle();

        // Nachbar oben (nur wenn nicht in oberster Zeile)
        if(row > 0){
            board[row - 1][col] = board[row - 1][col].toggle();
        }
        // Nachbar unten (nur wenn nicht in unterster Zeile)
        if(row < 4){
            board[row + 1][col] = board[row + 1][col].toggle();
        }
        // Nachbar links (nur wenn nicht in erster Spalte)
        if(col > 0){
            board[row][col - 1] = board[row][col - 1].toggle();
        }
        // Nachbar rechts (nur wenn nicht in letzter Spalte)
        if(col < 4){
            board[row][col + 1] = board[row][col + 1].toggle();
        }
    }

    public static boolean isSolved(CellState[][] board){
        checkBoard(board);
        boolean res = false;
        int count = countLightsOn(board);
        if(count == 0){
            res = true;
        }
        return res;
    }

}
