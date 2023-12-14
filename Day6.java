import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        try{
            //Code For Part 1
            // ArrayList<Long> time = new ArrayList<Long>();
            // ArrayList<Long> dist = new ArrayList<Long>();
            // Scanner sc = new Scanner(new File("input6.txt"));
            
            // String[] input1 = sc.nextLine().split(":")[1].split(" ");
            // for(String input: input1){
            //     if(input.equals(""))   continue;
            //     time.add(Long.parseLong(input));
            // }
            // String[] input2 = sc.nextLine().split(":")[1].split(" ");
            // for(String input: input2){
            //     if(input.equals(""))   continue;
            //     dist.add(Long.parseLong(input));
            // }
            
            // long ans = 1;
            // for(int i = 0; i < time.size(); i++){
            //     ans *= noOfWaysToWin(time.get(i), dist.get(i));
            // }
            // System.out.println(ans);

            //Code for Part 2
            String time = "";
            String dist = "";
            Scanner sc = new Scanner(new File("input6.txt"));
            String input1 = sc.nextLine().split(":")[1];
            for(int i = 0; i < input1.length(); i++){
                char c = input1.charAt(i);
                if(Character.isDigit(c)){
                    time += c;
                }
            }
            String input2 = sc.nextLine().split(":")[1];
            for(int i = 0; i < input2.length(); i++){
                char c = input2.charAt(i);
                if(Character.isDigit(c)){
                    dist += c;
                }
            }
            System.out.println(noOfWaysToWin(Long.parseLong(time), Long.parseLong(dist)));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long noOfWaysToWin(long time, long dist){
        long ans = 0;
        for(int speed = 1; speed < time; speed++){
            long distTravelled = speed * (time-speed);
            if(distTravelled > dist)    ans++;
        }
        return ans;
    }
}
