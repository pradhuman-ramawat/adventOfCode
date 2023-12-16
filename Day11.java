import java.util.*;
import java.io.*;

public class Day11 {
    static class Pair{
        int row;
        int col;
        Pair(int first, int second){
            this.row = first;
            this.col = second;
        }

        @Override
        public String toString() {
            return "[ " + this.row + " " + this.col + " ]";
        }
    }
    static class Triplet{
        int row;
        int col;
        int dist;
        Triplet(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("input11.txt"));
            ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
            while(sc.hasNextLine()){
                String input = sc.nextLine();
                ArrayList<Character> line = new ArrayList<Character>();
                for(char c: input.toCharArray()){
                    line.add(c);
                }
                matrix.add(line);
            }

            // matrix = expandMatrix(matrix);
            
            ArrayList<Pair> galaxies = new ArrayList<Pair>();
            for(int i = 0; i < matrix.size(); i++){
                for(int j = 0; j < matrix.get(i).size(); j++){
                    if(matrix.get(i).get(j) == '#'){
                        galaxies.add(new Pair(i, j));
                    }
                }
            }

            ArrayList<Integer> emptyRows = new ArrayList<>();
            ArrayList<Integer> emptyCols = new ArrayList<>();
            for(int row = 0; row < matrix.size(); row++){
                if(isEmpty(matrix, true, row)){
                    emptyRows.add(row);
                }
            }
            for(int col = 0; col < matrix.get(0).size(); col++){
                if(isEmpty(matrix, false, col)){
                    emptyCols.add(col);
                }
            }
            
            long ans = 0;
            for(int i = 0; i < galaxies.size(); i++){
                for(int j = i+1; j < galaxies.size(); j++){
                    //Part 1
                    //long dist = findDistance(matrix, emptyRows, emptyCols, galaxies.get(i), galaxies.get(j), 2);
                    //Part 2
                    long dist = findDistance(matrix, emptyRows, emptyCols, galaxies.get(i), galaxies.get(j), 1000000);
                    ans += dist;
                }
            }
            System.out.println(ans);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long findDistance(ArrayList<ArrayList<Character>> matrix, ArrayList<Integer> emptyRows, ArrayList<Integer> emptyCols, Pair p1, Pair p2, long gap){
        long ans = 0;
        //Add Rows
        ans += Math.abs(p1.row - p2.row);
        int rowLow = Math.min(p1.row, p2.row);
        int rowHigh = Math.max(p1.row, p2.row);
        for(int row: emptyRows){
            if(row > rowLow && row < rowHigh)    ans += gap-1;
        }

        //Add Cols
        ans += Math.abs(p1.col - p2.col);
        int colLow = Math.min(p1.col, p2.col);
        int colHigh = Math.max(p1.col, p2.col);
        for(int col: emptyCols){
            if(col > colLow && col < colHigh)    ans += gap-1;
        }
        
        return ans;

        // int rows = matrix.size();
        // int cols = matrix.get(0).size();
        // int[][] visited = new int[rows][cols];
        // visited[p1.first][p1.second] = 1;
        // Queue<Triplet> queue = new LinkedList<>();
        // queue.add(new Triplet(p1.first, p1.second, 0));
        
        // int[] dRow = {1, 0, -1, 0};
        // int[] dCol = {0, -1, 0, 1};
        // while(!queue.isEmpty()){
        //     Triplet t = queue.poll();
        //     if(t.row == p2.first && t.col == p2.second) return t.dist;
            
        //     for(int loop = 0; loop < 4; loop++){
        //         int newRow = t.row + dRow[loop];
        //         int newCol = t.col + dCol[loop];
        //         if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && visited[newRow][newCol] == 0){
        //             visited[newRow][newCol] = 1;
        //             queue.add(new Triplet(newRow, newCol, t.dist+1));
        //         }
        //     }
        // }
        // return 0;
    }

    // public static ArrayList<ArrayList<Character>> expandMatrix(ArrayList<ArrayList<Character>> matrix){
    //     ArrayList<ArrayList<Character>> matrixExpansion = new ArrayList<ArrayList<Character>>();
    //         for(int row = 0; row < matrix.size(); row++){
    //             matrixExpansion.add(new ArrayList<>(matrix.get(row)));
    //             if(isEmpty(matrix, true, row)){
    //                 ArrayList<Character> line = new ArrayList<>(matrix.get(row));
    //                 matrixExpansion.add(line);
    //             }
    //         }

    //         int offSet = 0;
    //         for(int col = 0; col < matrix.get(0).size(); col++){
    //             if(isEmpty(matrix, false, col)){
    //                 for(int row = 0; row < matrixExpansion.size(); row++){
    //                     matrixExpansion.get(row).add(col+offSet, '.');
    //                 }
    //                 offSet++;
    //             }
    //         }
    //     return matrixExpansion;
    // }

    public static boolean isEmpty(ArrayList<ArrayList<Character>> matrix, boolean isRow, int idx){
        if(isRow){
            ArrayList<Character> row = matrix.get(idx);
            for(char c: row){
                if(c != '.')    return false;
            }
        }
        else{
            for(int i = 0; i < matrix.size(); i++){
                if(matrix.get(i).get(idx) != '.')   return false;
            }
        }
        return true;
    }
}
