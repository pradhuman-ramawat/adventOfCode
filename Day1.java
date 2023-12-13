import java.util.*;
import java.io.*;
public class Day1 {
    public static void main(String args[]){
        try{
            ArrayList<String> arr = new ArrayList<>();
            Scanner sc = new Scanner(new File("input1.txt"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                arr.add(s);
            }
            System.out.println(findCalibrationValue(arr));
            System.out.println(findCalibrationValue1(arr));
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    public static long findCalibrationValue(ArrayList<String> arr){
        long ans = 0;
        for(String str: arr){
            ArrayList<Integer> vals = new ArrayList<>();
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(c >= '0' && c <= '9'){
                    vals.add(c - '0');
                }
            }
            if(vals.size() == 0)    continue;
            ans += vals.get(0)*10 + vals.get(vals.size()-1);
        }
        return ans;
    }

    public static long findCalibrationValue1(ArrayList<String> arr){
        long ans = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        for(String str: arr){
            ArrayList<Integer> vals = new ArrayList<>();
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(c >= '0' && c <= '9'){
                    vals.add(c - '0');
                }
                else{
                    int j = i+3;
                    while(j <= str.length() && j-i <= 5){
                        String substr = str.substring(i, j);
                        if(map.containsKey(substr)){
                            vals.add(map.get(substr));
                        }
                        j++;
                    }
                }
            }
            if(vals.size() == 0)    continue;
            ans += vals.get(0)*10 + vals.get(vals.size()-1);
        }
        return ans; 
    }
}