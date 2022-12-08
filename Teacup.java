public class Teacup {
    private int[][] table = new int[12][24];

    public Teacup() {
        for (int i=0; i < table.length; i++) {
            for (int j=0; j < table[i].length; j++) {
                table[i][j] = 0;
            }
        }
    }

    public void allCombos() {
        allCombos(10, "", 1);
    }

    private void allCombos(int amt, String soFar, int currentSize) {
        StringBuilder sb = new StringBuilder(soFar);
        if (amt != 10) {sb.append(currentSize);}
        if (amt == 0) {
            System.out.println(sb.toString());
            return;
        }
        for (int i=currentSize; i <= amt; i++) {
            allCombos(amt - i, sb.toString(), i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < table.length; i++) {
            sb.append(i);
            for (int k=0; k < 3 - i / 10; k++) { sb.append(" "); }
            for (int j=0; j < table[i].length; j++) { sb.append(table[i][j] + " ");}
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Teacup tc = new Teacup();
        tc.allCombos();
    }
}