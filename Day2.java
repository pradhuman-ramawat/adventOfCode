import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        try{
            ArrayList<String> arr = new ArrayList<>();
            Scanner sc = new Scanner(new File("input2.txt"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                arr.add(s);
            }

            System.out.println(possibleGames(arr));
            System.out.println(sumOfPowerSet(arr));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static long sumOfPowerSet(ArrayList<String> games){
        long ans = 0;
        for(String game: games){
            String[] sets = game.split(":")[1].split(";");
            int minRed = 0, minGreen = 0, minBlue = 0;
            for(String set: sets){
                int red = 0, green = 0, blue = 0;
                String[] vals = set.split(",");
                for(String val: vals){
                    String[] ball = val.split(" ");
                    if(ball[2].equals("red")){
                        red += Integer.parseInt(ball[1]);
                        minRed = Math.max(red, minRed);
                    }   
                    else if(ball[2].equals("green")){
                        green += Integer.parseInt(ball[1]);
                        minGreen = Math.max(green, minGreen);
                    }
                    else if(ball[2].equals("blue")){
                        blue += Integer.parseInt(ball[1]);
                        minBlue = Math.max(blue, minBlue);
                    }  
                }
            }

            ans += minRed * minGreen * minBlue;
        }
        return ans;
    }

    public static long possibleGames(ArrayList<String> games){
        long ans = 0;
        for(String game: games){
            if(validateGame(game)){
                ans += Integer.parseInt(game.split(":")[0].split(" ")[1]);
            }
        }
        return ans;
    }

    public static boolean validateGame(String game){
        String[] sets = game.split(":")[1].split(";");
        for(String set: sets){
            int red = 0;
            int green = 0;
            int blue = 0;
            String[] vals = set.split(",");
            for(String val: vals){
                String[] ball = val.split(" ");
                if(ball[2].equals("red"))   red += Integer.parseInt(ball[1]);
                else if(ball[2].equals("green"))   green += Integer.parseInt(ball[1]);
                else if(ball[2].equals("blue"))   blue += Integer.parseInt(ball[1]);

                if(red > 12 || green > 13 || blue > 14) return false;
            }
        }
        return true;
    }
}
