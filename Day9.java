import java.util.*;
import java.io.*;

public class Day9 {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("input9.txt"));
            long ans1 = 0;
            long ans2 = 0;
            while(sc.hasNextLine()){
                String[] input = sc.nextLine().split(" ");
                ArrayList<Long> vals = new ArrayList<Long>();
                for(String str: input){
                    vals.add(Long.parseLong(str));
                }
                //Part 1
                fillNextValue(vals);
                ans1 += vals.get(vals.size()-1);

                //Part 2
                fillPrevValue(vals);
                ans2 += vals.get(0);
            }
            System.out.println(ans1);
            System.out.println(ans2);
        }
        catch(Exception e){

        }
    }

    public static void fillPrevValue(ArrayList<Long> vals){
        //Base Case
        boolean allZeroes = true;
        for(long val: vals){
            if(val != 0){
                allZeroes = false;
                break;
            }
        }
        if(allZeroes){
            vals.add(0, 0L);
            return;
        }

        ArrayList<Long> newVals = new ArrayList<Long>();
        for(int i = 0; i < vals.size()-1; i++){
            newVals.add(vals.get(i+1) - vals.get(i));
        }
        fillPrevValue(newVals);
        vals.add(0, vals.get(0) - newVals.get(0));
    }

    public static void fillNextValue(ArrayList<Long> vals){
        //Base Case
        boolean allZeroes = true;
        for(long val: vals){
            if(val != 0){
                allZeroes = false;
                break;
            }
        }
        if(allZeroes){
            vals.add(0L);
            return;
        }

        ArrayList<Long> newVals = new ArrayList<Long>();
        for(int i = 0; i < vals.size()-1; i++){
            newVals.add(vals.get(i+1) - vals.get(i));
        }
        fillNextValue(newVals);
        vals.add(vals.get(vals.size()-1) + newVals.get(newVals.size()-1));
    }
}
