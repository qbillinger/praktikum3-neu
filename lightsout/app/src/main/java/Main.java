import java.util.Scanner;

public class Main {
    public static void main(String[] Args) {
        CellState[][] board = LightsOut.getExample();
        Scanner s = new Scanner(System.in);
        int zuege = 0;

        while (!LightsOut.isSolved(board)) {
            LightsOut.printBoard(board);
            System.out.println("Anzahl der eingeschalteten Lichter: " + LightsOut.countLightsOn(board));

            System.out.println("Enter row: ");
            if (!s.hasNextInt()) {
                System.out.println("keine gültige Zahl!");
                s.next();
                continue;
            }
            int row = s.nextInt();

            System.out.println("Enter col: ");
            if (!s.hasNextInt()) {
                System.out.println("keine gültige Zahl!");
                s.next();
                continue;
            }
            int col = s.nextInt();

            try {
                LightsOut.toggle(board, row, col);
                zuege++;
                System.out.println("Aktuelle Züge: " + zuege);
            } catch (IllegalArgumentException e) {
                System.out.println("Ungültige Koordinate: " + e.getMessage());
            }
        }

        System.out.println("Gewonnen! Anzahl Züge: " + zuege);
        s.close();
    }
}