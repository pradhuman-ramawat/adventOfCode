import java.io.*;
import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("input8.txt"));
            String instructions = sc.nextLine();
            sc.nextLine();
            HashMap<String, ArrayList<String>> adjList = new HashMap<>();
            while(sc.hasNextLine()){
                String[] s = sc.nextLine().split("=");
                String key = s[0].split(" ")[0];
                adjList.put(key, new ArrayList<String>());
                String[] vals = s[1].substring(2, s[1].length()-1).split(",");
                for(String val: vals){
                    adjList.get(key).add(val.trim());
                }
            }

            String curr = "AAA";
            long ans = 0;
            int idx = 0;
            while(!curr.equals("ZZZ")){
                if(instructions.charAt(idx) == 'L'){
                    curr = adjList.get(curr).get(0);
                }
                else{
                    curr = adjList.get(curr).get(1);
                }
                ans++;
                idx = (idx+1) % instructions.length();
            }
            System.out.println(ans);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
