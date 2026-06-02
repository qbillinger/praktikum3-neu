import org.junit.*;
import static org.junit.Assert.*;

public class CellStateTest {


    @Test
    public void testValues() {
        assertEquals("ON.getDisplay must return '*'",
                '*', CellState.ON.getDisplay());
        assertEquals("OFF.getDisplay must return '.'",
                '.', CellState.OFF.getDisplay());
        assertEquals("enum CellState must contain exactly 2 entries",
                2, CellState.values().length);
    }



    @Test
    public void testFromDisplay() {
        assertEquals("fromDisplay('*') must return ON",
                CellState.ON, CellState.fromDisplay('*'));
        assertEquals("fromDisplay('.') must return OFF",
                CellState.OFF, CellState.fromDisplay('.'));
        assertThrows("fromDisplay(' ') must throw IllegalArgumentException",
                IllegalArgumentException.class,
                () -> CellState.fromDisplay(' '));
        assertThrows("fromDisplay('x') must throw IllegalArgumentException",
                IllegalArgumentException.class,
                () -> CellState.fromDisplay('x'));
        assertThrows("fromDisplay('0') must throw IllegalArgumentException",
                IllegalArgumentException.class,
                () -> CellState.fromDisplay('0'));
    }



    @Test
    public void testToggle() {
        assertEquals("ON.toggle() must return OFF",
                CellState.OFF, CellState.ON.toggle());
        assertEquals("OFF.toggle() must return ON",
                CellState.ON, CellState.OFF.toggle());
        // Double toggle returns original
        assertEquals("ON.toggle().toggle() must return ON",
                CellState.ON, CellState.ON.toggle().toggle());
        assertEquals("OFF.toggle().toggle() must return OFF",
                CellState.OFF, CellState.OFF.toggle().toggle());
    }

}
