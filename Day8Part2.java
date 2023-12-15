import java.io.*;
import java.util.*;

public class Day8Part2 {
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

            ArrayList<Long> steps = new ArrayList<Long>();
            for(String key: adjList.keySet()){
                if(key.charAt(key.length()-1) == 'A'){
                    steps.add(findSteps(key, instructions, adjList));
                }
            }
            System.out.println(lcm_of_array_elements(steps));
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long findSteps(String curr, String instructions, HashMap<String, ArrayList<String>> adjList){
        long ans = 0;
        int idx = 0;
        while(curr.charAt(curr.length()-1) != 'Z'){
            if(instructions.charAt(idx) == 'L'){
                curr = adjList.get(curr).get(0);
            }
            else{
                curr = adjList.get(curr).get(1);
            }
            ans++;
            idx = (idx+1) % instructions.length();
        }

        return ans;
    }

    //Logic To Calculate LCM of N numbers.
    public static long lcm_of_array_elements(ArrayList<Long> element_array)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;
         
        while (true) {
            int counter = 0;
            boolean divisible = false;
             
            for (int i = 0; i < element_array.size(); i++) {
 
                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.
 
                if (element_array.get(i) == 0) {
                    return 0;
                }
                else if (element_array.get(i) < 0) {
                    element_array.set(i, element_array.get(i) * (-1));
                }
                if (element_array.get(i) == 1) {
                    counter++;
                }
 
                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array.get(i) % divisor == 0) {
                    divisible = true;
                    element_array.set(i, element_array.get(i) / divisor);
                }
            }
 
            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
 
            // Check if all element_array is 1 indicate 
            // we found all factors and terminate while loop.
            if (counter == element_array.size()) {
                return lcm_of_array_elements;
            }
        }
    }
}
