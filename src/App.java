public class App {

    static class Cell {
        int value;
        boolean fixed;

        Cell(int value, boolean fixed) {
            this.value = value;
            this.fixed = fixed;
        }
    }

    public static void main(String[] args) {
        Cell[][] board = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell(0, false);
            }
        }

        for (String arg : args) {
            try {
                String[] parts = arg.split(";");
                if (parts.length != 2) continue;

                String[] coords = parts[0].split(",");
                int x = Integer.parseInt(coords[0]); // coluna
                int y = Integer.parseInt(coords[1]); // linha

                String[] valFixed = parts[1].split(",");
                int val = Integer.parseInt(valFixed[0]);
                boolean fixed = Boolean.parseBoolean(valFixed[1]);

                if (x >= 0 && x < 9 && y >= 0 && y < 9) {
                    board[y][x] = new Cell(val, fixed);
                }
            } catch (Exception e) {
                System.err.println("Erro ao analisar argumento: " + arg);
            }
        }

        printBoard(board);
    }

    private static void printBoard(Cell[][] board) {
        System.out.println("Tabuleiro de Sudoku (* = fixo):");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                
                int val = board[i][j].value;
                boolean fixed = board[i][j].fixed;
                
                if (val == 0) {
                    System.out.print(". ");
                } else {
                    if (fixed) {
                        System.out.print(val + "*");
                    } else {
                        System.out.print(val + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
