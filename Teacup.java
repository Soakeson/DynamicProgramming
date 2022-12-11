import java.util.Hashtable;

public class Teacup {
    private int[][] table = new int[12][24];
    private Hashtable<Integer, Integer> prices = new Hashtable<>();

    public Teacup() {
        for (int i=0; i < table.length; i++) {
            for (int j=0; j < table[1].length; j++) {
                table[i][j] = 0;
            }
        }

        prices.put(1,1);
        prices.put(2,3);
        prices.put(3,5);
        prices.put(4,9);
        prices.put(5, 10);
        prices.put(6, 15);
        prices.put(7, 17);
        prices.put(8, 18);
        prices.put(9, 19);
        prices.put(10, 22);
        prices.put(11, 25);
        prices.put(12, 27);
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

    /**
     * ITERATIVE SOLUTION
     */
    public void buildTable() {
        for (int i=0; i < table.length; i++) {
            for (int j=0; j < table[1].length; j++) {
                int setSize = i+1;
                int numCups = j+1;
                int priceSetIncl = 0;

                if (numCups >= setSize){
                    numCups -= setSize;
                    priceSetIncl += prices.get(setSize);
                }
                if (numCups > 0) {
                    priceSetIncl += table[i][numCups-1];
                }
                if (i > 0 && priceSetIncl <= table[i-1][j]) {
                    table[i][j] = table[i-1][j];
                } else {
                    table[i][j] = priceSetIncl;
                }
            }
        }
    }

    public String bestSum(int numCups) {
        StringBuilder sb = new StringBuilder();
        numCups -= 1;
        int setSize = 11;
        sb.append(table[setSize][numCups] + "$ ");
        while(numCups > 0) {
            if (table[setSize][numCups] > table[setSize-1][numCups]) {
                numCups -= setSize+1;
                sb.append(setSize+1 + " ");
            } else {
                setSize -= 1;
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i=1; i <= table[1].length; i++) {
            sb.append(i);
            for (int l=0; l < 3 - String.valueOf(i).length(); l++) { sb.append(" "); }
        }
        sb.append("\n");
        for (int i=1; i <= table.length; i++) {
            sb.append(i);
            for (int l=0; l < 3 - String.valueOf(i).length(); l++) { sb.append(" "); }
            for (int j=0; j < table[1].length; j++) { 
                sb.append(table[i-1][j]);
                for (int l=0; l < 3 - String.valueOf(table[i-1][j]).length(); l++) { sb.append(" "); }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Teacup tc = new Teacup();
        tc.buildTable();
        System.out.println(tc);
        for (int i = 1; i <= 24; i++) {
            System.out.println(tc.bestSum(i));
        }
    }
}