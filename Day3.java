import java.io.*;
import java.util.*;

public class Day3 {
    static class Triplet{
        int first, second, third;
        Triplet(int f, int s, int t){
            this.first = f;
            this.second = s;
            this.third = t;
        }

        @Override
        public boolean equals(Object other){
            if(this.first == ((Triplet)other).first && this.second == ((Triplet)other).second && this.third == ((Triplet)other).third)   return true;
            return false;
        }
    }

    public static void main(String[] args) {
        try{
            ArrayList<String> arr = new ArrayList<>();
            Scanner sc = new Scanner(new File("input3.txt"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                arr.add(s);
            }

            System.out.println(partSum(arr));
            System.out.println(gearRatioSum(arr));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long gearRatioSum(ArrayList<String> schematics){
        long ans = 0;
        
        for(int x = 0; x < schematics.size(); x++){
            String line = schematics.get(x);
            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == '*'){
                    ans += sumGear(schematics, x, i);
                }
            }
        }
        return ans;
    }

    public static long sumGear(ArrayList<String> schematics, int line, int idx){
        ArrayList<Triplet> list = new ArrayList<Triplet>();
        
        //Check All Neigbours
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        for(int loop = 0; loop < 8; loop++){
            int x = line + dx[loop];
            int y = idx + dy[loop];
            if(x >= 0 && x < schematics.size() && y >= 0 && y < schematics.get(x).length()){
                if(Character.isDigit(schematics.get(x).charAt(y))){
                    int start = y, end = y;
                    while(start >= 0 && Character.isDigit(schematics.get(x).charAt(start))) start--;
                    start++;
                    while(end < schematics.get(x).length() && Character.isDigit(schematics.get(x).charAt(end))) end++;
                    end--;
                    Triplet t = new Triplet(x, start, end);
                    
                    boolean match = false;
                    for(Triplet trip: list){
                        if(trip.equals(t)){
                            match = true;
                            break;
                        }
                    }
                    if(!match)  list.add(t);
                }
            }
        }
        if(list.size() == 2){
            long ans = 1;
            for(Triplet t: list){
                ans *= Integer.parseInt(schematics.get(t.first).substring(t.second, t.third+1));
            }
            return ans;
        }

        return 0;
    }

    public static long partSum(ArrayList<String> schematics){
        long ans = 0;
        for(int x = 0; x < schematics.size(); x++){
            String line = schematics.get(x);
            int i = 0;
            while(i < line.length()){
                if(Character.isDigit(line.charAt(i))){
                    int j = i;
                    while(j < line.length() && Character.isDigit(line.charAt(j))){
                        j++;
                    }
                    j--;

                    //Check if part
                    if(isPart(schematics, x, i, j)){
                        ans += Integer.parseInt(line.substring(i, j+1));
                    }
                    i = j+1;
                    continue;
                }
                i++;
            }
        }

        return ans;
    }

    public static boolean isPart(ArrayList<String> schematics, int line, int start, int end){
        //Check Neighbours on All Sides
        //Top Neighbours
        if(line-1 > 0){
            for(int i = Math.max(start-1, 0); i <= Math.min(end+1, schematics.get(line-1).length()-1); i++){
                if(schematics.get(line-1).charAt(i) != '.') return true;
            }
        }
        //Bottom Neighbour
        if(line+1 < schematics.size()){
            for(int i = Math.max(start-1, 0); i <= Math.min(end+1, schematics.get(line+1).length()-1); i++){
                if(schematics.get(line+1).charAt(i) != '.')    return true;
            }
        }

        //Left and Right Neighbour
        if((start-1 > 0 && schematics.get(line).charAt(start-1) != '.') || 
            (end+1 < schematics.get(line).length() && schematics.get(line).charAt(end+1) != '.')){
                return true;
            }
        
        return false;
    }
}

