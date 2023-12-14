import java.io.*;
import java.util.*;

public class Day7Part2 {
    public static void main(String[] args) {
        try{
            HashMap<String, Long> handVal = new HashMap<String, Long>();
            ArrayList<String> fiveOfKind = new ArrayList<>();
            ArrayList<String> fourOfKind = new ArrayList<>();
            ArrayList<String> fullHouse = new ArrayList<>();
            ArrayList<String> threeOfKind = new ArrayList<>();
            ArrayList<String> twoPair = new ArrayList<>();
            ArrayList<String> onePair = new ArrayList<>();
            ArrayList<String> highCard = new ArrayList<>();
            Scanner sc = new Scanner(new File("input7.txt"));

            long hands = 0;
            while(sc.hasNextLine()){
                hands++;
                String[] hand = sc.nextLine().split(" ");
                handVal.put(hand[0], Long.parseLong(hand[1]));

                //Place hand in respected list.
                HashMap<Character, Integer> map = new HashMap<>();
                for(int i = 0; i < 5; i++){
                    char c = hand[0].charAt(i);
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
                if(map.containsKey('J')){
                    int jVal = map.get('J');
                    map.remove('J');

                    //Find Max of Vals
                    int maxOcc = 1;
                    char maxChar = 'A';
                    for(char key: map.keySet()){
                        int val = map.get(key);
                        if(val >= maxOcc){
                            maxOcc = val;
                            maxChar = key;
                        }
                    }
                    map.put(maxChar, maxOcc + jVal);
                }


                if(map.size() == 1) fiveOfKind.add(hand[0]);
                else if(map.size() == 2){
                    for(int val: map.values()){
                        if(val == 1 || val == 4){
                            fourOfKind.add(hand[0]);
                            break;
                        }
                        else if(val == 2 || val == 3){
                            fullHouse.add(hand[0]);
                            break;
                        }
                    }
                }
                else if(map.size() == 3){
                    for(int val: map.values()){
                        if(val == 3){
                            threeOfKind.add(hand[0]);
                            break;
                        }
                        else if(val == 2){
                            twoPair.add(hand[0]);
                            break;
                        }
                    }
                }
                else if(map.size() == 4){
                    onePair.add(hand[0]);
                }
                else if(map.size() == 5){
                    highCard.add(hand[0]);
                }
            } 
            
            sortList(fiveOfKind);
            sortList(fourOfKind);
            sortList(fullHouse);
            sortList(threeOfKind);
            sortList(twoPair);
            sortList(onePair);
            sortList(highCard);

            long ans = 0;
            for(String str: fiveOfKind){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: fourOfKind){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: fullHouse){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: threeOfKind){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: twoPair){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: onePair){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            for(String str: highCard){
                ans += (handVal.get(str) * hands);
                hands--;
            }
            System.out.println(ans);

            //Print List
            // System.out.println("Five Of Kind");
            // for(String str: fiveOfKind){
            //     System.out.println(str);
            // }
            // System.out.println("Four Of Kind");
            // for(String str: fourOfKind){
            //     System.out.println(str);
            // }
            // System.out.println("Full House");
            // for(String str: fullHouse){
            //     System.out.println(str);
            // }
            // System.out.println("Three Of Kind");
            // for(String str: threeOfKind){
            //     System.out.println(str);
            // }
            // System.out.println("Two Pair");
            // for(String str: twoPair){
            //     System.out.println(str);
            // }
            // System.out.println("One Pair");
            // for(String str: onePair){
            //     System.out.println(str);
            // }
            // System.out.println("High Card");
            // for(String str: highCard){
            //     System.out.println(str);
            // }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void sortList(ArrayList<String> list){
        ArrayList<Character> strength = new ArrayList<>();
            strength.add('A'); strength.add('K'); strength.add('Q');
            strength.add('T'); strength.add('9'); strength.add('8');
            strength.add('7'); strength.add('6'); strength.add('5');
            strength.add('4'); strength.add('3'); strength.add('2');
            strength.add('J');
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2){
                    for(int i = 0; i < 5; i++){
                        char c1 = s1.charAt(i);
                        char c2 = s2.charAt(i);
                        if(strength.indexOf(c1) < strength.indexOf(c2)) return -1;
                        else if(strength.indexOf(c1) > strength.indexOf(c2)) return 1;
                    }
                    return 0;
                }
            });
    }

}
