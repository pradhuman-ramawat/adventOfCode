import java.io.*;
import java.util.*;

public class Day4 {
    public static void main(String[] args) {
        try{
            ArrayList<String> arr = new ArrayList<>();
            Scanner sc = new Scanner(new File("input4.txt"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                arr.add(s);
            }

            System.out.println(sumPoints(arr));
            System.out.println(totalScratchCardsWon(arr));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long totalScratchCardsWon(ArrayList<String> cards){
        long ans = 0;
        long[] dp = new long[cards.size()];
        Arrays.fill(dp, -1);
        for(int i = 0; i < cards.size(); i++)
            ans += recursion(dp, cards, i);
        return ans;
    }

    public static long recursion(long[] dp, ArrayList<String> cards, int idx){
        if(idx > cards.size())  return 0;

        if(dp[idx] != -1)   return dp[idx];
        long ans = 1;
        String card = cards.get(idx);
        int winNums = findWinningNumbers(card);
        for(int i = idx+1; i <= idx+winNums; i++){
            ans += recursion(dp, cards, i);
        }
        return dp[idx] = ans;
    }

    public static int findWinningNumbers(String card){
        int ans = 0;
        String[] winningNumbers = card.split(":")[1].split("\\|")[0].split(" ");
        String[] myNumbers = card.split(":")[1].split("\\|")[1].split(" ");
        Set<Integer> winNums = new HashSet<Integer>();
        for(String s: winningNumbers){
            if(s.equals(""))    continue;
            winNums.add(Integer.parseInt(s));
        }
        for(String s: myNumbers){
            if(s.equals(""))    continue;
            if(winNums.contains(Integer.parseInt(s)))   ans++;
        }
        return ans;
    }

    public static long sumPoints(ArrayList<String> cards){
        long ans = 0;
        for(String card: cards){
            int matchingNumbers = 0;
            String[] winningNumbers = card.split(":")[1].split("\\|")[0].split(" ");
            String[] myNumbers = card.split(":")[1].split("\\|")[1].split(" ");
            Set<Integer> winNums = new HashSet<Integer>();
            for(String s: winningNumbers){
                if(s.equals(""))    continue;
                winNums.add(Integer.parseInt(s));
            }
            for(String s: myNumbers){
                if(s.equals(""))    continue;
                if(winNums.contains(Integer.parseInt(s)))   matchingNumbers++;
            }
            ans += (int) Math.pow(2, matchingNumbers-1);
        }
        return ans;
    }
}
