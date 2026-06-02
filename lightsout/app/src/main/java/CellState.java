public enum CellState {
    ON('*'),
    OFF('.');

    private final char display;

    CellState(char display){
        this.display = display;
    }

    public char getDisplay(){
        return display;
    }

    /**
     * Return the given CellState to a char of '*' or '.'
     * @throws IllegalArgumentException
     *  @param display
     * @return {@link CellState}
     */
    public static CellState fromDisplay(char display){
        CellState res = null;
        if(display == '*'){
            res = CellState.ON;
        }
        else if(display == '.'){
            res = CellState.OFF;
        }
        else{
            throw new IllegalArgumentException("CellState doesnt exist!");
        }
        return res;
    }

    /**
     * Returns the opposite CellState
     * @return {@link CellState}
     */
    public CellState toggle(){
        CellState res = null;
        if(getDisplay() == '*'){
            res = CellState.OFF;
        }
        else if(getDisplay() == '.'){
            res = CellState.ON;
        }
        return res;
    }
}
