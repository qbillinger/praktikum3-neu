import org.junit.*;
import static org.junit.Assert.*;

public class LightsOutTest {


    // Helper: create a board where all lights are OFF
    private CellState[][] allOff() {
        CellState[][] board = new CellState[5][5];
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                board[row][col] = CellState.OFF;
        return board;
    }

    // Helper: create a board where all lights are ON
    private CellState[][] allOn() {
        CellState[][] board = new CellState[5][5];
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                board[row][col] = CellState.ON;
        return board;
    }

    @Test
    public void testGetExample() {
        CellState[][] example = LightsOut.getExample();
        assertNotNull("getExample must not return null", example);
        assertEquals("example must have exactly 5 rows", 5, example.length);
        for (int i = 0; i < 5; i++) {
            assertNotNull("row " + i + " must not be null", example[i]);
            assertEquals("row " + i + " must have exactly 5 columns", 5, example[i].length);
        }
        // Check specific cells of the example
        // Row 0: . * . * .
        assertEquals("example[0][0] must be OFF", CellState.OFF, example[0][0]);
        assertEquals("example[0][1] must be ON", CellState.ON, example[0][1]);
        assertEquals("example[0][2] must be OFF", CellState.OFF, example[0][2]);
        assertEquals("example[0][3] must be ON", CellState.ON, example[0][3]);
        assertEquals("example[0][4] must be OFF", CellState.OFF, example[0][4]);
        // Row 1: * . * . *
        assertEquals("example[1][0] must be ON", CellState.ON, example[1][0]);
        assertEquals("example[1][1] must be OFF", CellState.OFF, example[1][1]);
        assertEquals("example[1][2] must be ON", CellState.ON, example[1][2]);
        assertEquals("example[1][3] must be OFF", CellState.OFF, example[1][3]);
        assertEquals("example[1][4] must be ON", CellState.ON, example[1][4]);
        // Row 2: . * * * .
        assertEquals("example[2][0] must be OFF", CellState.OFF, example[2][0]);
        assertEquals("example[2][1] must be ON", CellState.ON, example[2][1]);
        assertEquals("example[2][2] must be ON", CellState.ON, example[2][2]);
        assertEquals("example[2][3] must be ON", CellState.ON, example[2][3]);
        assertEquals("example[2][4] must be OFF", CellState.OFF, example[2][4]);
        // Row 3: * . * . *
        assertEquals("example[3][0] must be ON", CellState.ON, example[3][0]);
        assertEquals("example[3][1] must be OFF", CellState.OFF, example[3][1]);
        assertEquals("example[3][2] must be ON", CellState.ON, example[3][2]);
        assertEquals("example[3][3] must be OFF", CellState.OFF, example[3][3]);
        assertEquals("example[3][4] must be ON", CellState.ON, example[3][4]);
        // Row 4: . * . * .
        assertEquals("example[4][0] must be OFF", CellState.OFF, example[4][0]);
        assertEquals("example[4][1] must be ON", CellState.ON, example[4][1]);
        assertEquals("example[4][2] must be OFF", CellState.OFF, example[4][2]);
        assertEquals("example[4][3] must be ON", CellState.ON, example[4][3]);
        assertEquals("example[4][4] must be OFF", CellState.OFF, example[4][4]);
    }



    @Test
    public void testCheckBoard() {
        // null board
        assertThrows("must throw IllegalArgumentException on null board",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(null));
        // wrong number of rows
        assertThrows("must throw IllegalArgumentException on board with 3 rows",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(new CellState[3][5]));
        assertThrows("must throw IllegalArgumentException on board with 0 rows",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(new CellState[0][0]));
        assertThrows("must throw IllegalArgumentException on board with 6 rows",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(new CellState[6][5]));
        // null row
        CellState[][] boardWithNullRow = new CellState[5][];
        boardWithNullRow[0] = new CellState[5];
        boardWithNullRow[1] = new CellState[5];
        boardWithNullRow[2] = null;
        boardWithNullRow[3] = new CellState[5];
        boardWithNullRow[4] = new CellState[5];
        assertThrows("must throw IllegalArgumentException on board with null row",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(boardWithNullRow));
        // wrong number of columns
        assertThrows("must throw IllegalArgumentException on board with 4 columns",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(new CellState[5][4]));
        assertThrows("must throw IllegalArgumentException on board with 6 columns",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(new CellState[5][6]));
        // non-uniform row lengths
        CellState[][] nonUniform = new CellState[5][];
        nonUniform[0] = new CellState[] { CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF };
        nonUniform[1] = new CellState[] { CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF };
        nonUniform[2] = new CellState[] { CellState.OFF, CellState.OFF, CellState.OFF };
        nonUniform[3] = new CellState[] { CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF };
        nonUniform[4] = new CellState[] { CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF, CellState.OFF };
        assertThrows("must throw IllegalArgumentException on board with non-uniform row lengths",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(nonUniform));
        // null entry
        CellState[][] boardWithNullEntry = allOff();
        boardWithNullEntry[2][3] = null;
        assertThrows("must throw IllegalArgumentException on board with null entry",
                IllegalArgumentException.class,
                () -> LightsOut.checkBoard(boardWithNullEntry));
        // valid board should not throw
        LightsOut.checkBoard(allOff());
        LightsOut.checkBoard(allOn());
    }



    @Test
    public void testCountLightsOn() {
        assertEquals("all OFF board must have 0 lights on",
                0, LightsOut.countLightsOn(allOff()));
        assertEquals("all ON board must have 25 lights on",
                25, LightsOut.countLightsOn(allOn()));

        CellState[][] board = allOff();
        board[0][0] = CellState.ON;
        assertEquals("one ON cell must have 1 light on",
                1, LightsOut.countLightsOn(board));

        board[2][3] = CellState.ON;
        board[4][4] = CellState.ON;
        assertEquals("three ON cells must have 3 lights on",
                3, LightsOut.countLightsOn(board));
    }



    @Test
    public void testToggleCenter() {
        // Toggle center (2,2): should toggle (2,2), (1,2), (3,2), (2,1), (2,3)
        CellState[][] board = allOff();
        LightsOut.toggle(board, 2, 2);

        assertEquals("(2,2) must be ON after toggle center",
                CellState.ON, board[2][2]);
        assertEquals("(1,2) must be ON after toggle center",
                CellState.ON, board[1][2]);
        assertEquals("(3,2) must be ON after toggle center",
                CellState.ON, board[3][2]);
        assertEquals("(2,1) must be ON after toggle center",
                CellState.ON, board[2][1]);
        assertEquals("(2,3) must be ON after toggle center",
                CellState.ON, board[2][3]);

        // Cells not adjacent should remain OFF
        assertEquals("(0,0) must remain OFF", CellState.OFF, board[0][0]);
        assertEquals("(1,1) must remain OFF", CellState.OFF, board[1][1]);
        assertEquals("(3,3) must remain OFF", CellState.OFF, board[3][3]);

        assertEquals("exactly 5 lights must be on after center toggle",
                5, LightsOut.countLightsOn(board));
    }

    @Test
    public void testToggleCorner() {
        // Toggle top-left corner (0,0): should toggle (0,0), (0,1), (1,0)
        CellState[][] board = allOff();
        LightsOut.toggle(board, 0, 0);

        assertEquals("(0,0) must be ON after toggle corner",
                CellState.ON, board[0][0]);
        assertEquals("(0,1) must be ON after toggle corner",
                CellState.ON, board[0][1]);
        assertEquals("(1,0) must be ON after toggle corner",
                CellState.ON, board[1][0]);

        assertEquals("exactly 3 lights must be on after corner toggle",
                3, LightsOut.countLightsOn(board));

        // Toggle bottom-right corner (4,4): should toggle (4,4), (4,3), (3,4)
        CellState[][] board2 = allOff();
        LightsOut.toggle(board2, 4, 4);

        assertEquals("(4,4) must be ON after toggle corner",
                CellState.ON, board2[4][4]);
        assertEquals("(4,3) must be ON after toggle corner",
                CellState.ON, board2[4][3]);
        assertEquals("(3,4) must be ON after toggle corner",
                CellState.ON, board2[3][4]);

        assertEquals("exactly 3 lights must be on after corner toggle",
                3, LightsOut.countLightsOn(board2));
    }

    @Test
    public void testToggleEdge() {
        // Toggle top edge (0,2): should toggle (0,2), (0,1), (0,3), (1,2)
        CellState[][] board = allOff();
        LightsOut.toggle(board, 0, 2);

        assertEquals("(0,2) must be ON after toggle edge",
                CellState.ON, board[0][2]);
        assertEquals("(0,1) must be ON after toggle edge",
                CellState.ON, board[0][1]);
        assertEquals("(0,3) must be ON after toggle edge",
                CellState.ON, board[0][3]);
        assertEquals("(1,2) must be ON after toggle edge",
                CellState.ON, board[1][2]);

        assertEquals("exactly 4 lights must be on after edge toggle",
                4, LightsOut.countLightsOn(board));
    }

    @Test
    public void testToggleDoubleRevert() {
        // Toggling the same cell twice should restore the original state
        CellState[][] board = allOff();
        board[1][3] = CellState.ON;
        board[4][0] = CellState.ON;

        // Save original state
        CellState[][] original = new CellState[5][5];
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 5; c++)
                original[r][c] = board[r][c];

        LightsOut.toggle(board, 2, 2);
        LightsOut.toggle(board, 2, 2);

        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 5; c++)
                assertEquals("board[" + r + "][" + c + "] must be restored after double toggle",
                        original[r][c], board[r][c]);
    }

    @Test
    public void testToggleInvalidArguments() {
        CellState[][] board = allOff();
        assertThrows("toggle must throw IllegalArgumentException when row is -1",
                IllegalArgumentException.class,
                () -> LightsOut.toggle(board, -1, 2));
        assertThrows("toggle must throw IllegalArgumentException when row is 5",
                IllegalArgumentException.class,
                () -> LightsOut.toggle(board, 5, 2));
        assertThrows("toggle must throw IllegalArgumentException when col is -1",
                IllegalArgumentException.class,
                () -> LightsOut.toggle(board, 2, -1));
        assertThrows("toggle must throw IllegalArgumentException when col is 5",
                IllegalArgumentException.class,
                () -> LightsOut.toggle(board, 2, 5));
        assertThrows("toggle must throw IllegalArgumentException on null board",
                IllegalArgumentException.class,
                () -> LightsOut.toggle(null, 2, 2));
    }



    @Test
    public void testIsSolved() {
        assertTrue("all OFF board must be solved",
                LightsOut.isSolved(allOff()));
        assertFalse("all ON board must not be solved",
                LightsOut.isSolved(allOn()));

        CellState[][] board = allOff();
        assertTrue("empty board must be solved", LightsOut.isSolved(board));

        board[3][3] = CellState.ON;
        assertFalse("board with one ON cell must not be solved",
                LightsOut.isSolved(board));

        board[3][3] = CellState.OFF;
        assertTrue("board must be solved after turning last light off",
                LightsOut.isSolved(board));
    }

    @Test
    public void testIsSolvedAfterToggles() {
        // Start from all OFF, toggle a cell, then toggle it again -> should be solved
        CellState[][] board = allOff();
        LightsOut.toggle(board, 3, 1);
        assertFalse("board must not be solved after single toggle",
                LightsOut.isSolved(board));
        LightsOut.toggle(board, 3, 1);
        assertTrue("board must be solved after toggling same cell twice",
                LightsOut.isSolved(board));
    }

}
