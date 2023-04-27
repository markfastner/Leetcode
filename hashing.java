import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class hashing {
    public boolean checkIfPangram(String sentence) {
        //A pangram is a sentence where every letter of the English alphabet appears at least once.
        //Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
        
        // We iterate over 'sentence' for 26 times, one for each letter 'currChar'.
        for (int i = 0; i < 26; ++i) {
            char currChar = (char)('a' + i);

            // If 'sentence' doesn't contain currChar, it is not a pangram.
            if (sentence.indexOf(currChar) == -1)
                return false;
        }
        
        // If we manage to find all 26 letters, it is a pangram.
        return true;
    }

    public int countElements(int[] arr) {
        //Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
        // If there are duplicates in arr, count them separately.
        
        Set<Integer> hashSet = new HashSet<>();
        for (int x : arr) {
            hashSet.add(x);
        }
        int count = 0;
        for (int x : arr) {
            if (hashSet.contains(x + 1)) {
                count++;
            }
        }
        return count;
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        //You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the 
        //player winneri defeated player loseri in a match.
        //Return a list answer of size 2 where:
        //answer[0] is a list of all players that have not lost any matches.
        //answer[1] is a list of all players that have lost exactly one match.
        //The values in the two lists should be returned in increasing order.

        //create hashet for players and their losses
        HashSet<Integer> players = new HashSet<>();
        HashMap<Integer, Integer> losses = new HashMap<>();

        //loop through the matches and keep track of the players and their losses
        for(int[] match: matches){
            int winner = match[0];
            int loser = match[1];
            //if the player is not in the hashmap then add them and set their losses to 0
            if(!losses.containsKey(loser)){
                losses.put(loser, 1);
            }
            //else we jsut want to increment their losses
            else{
                losses.put(loser, losses.get(loser) + 1);
            }

            //add both the winner and loser to the players hashset if they arent in it already
            if(!players.contains(winner)){
                players.add(winner);
            }
            if(!players.contains(loser)){
                players.add(loser);
            }

        }

        //print out the hashmap
        System.out.println(losses);


        //now we can solve the problem using the hahmap we created. anser[0] will be the players who are not in the hashmap since they received no losses
        //asnwer[1] will be the players who are in the hashmap and have a value of 1 since they lost one game
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        answer.add(new ArrayList<>());
        //iterate through players and add them to the correct list
        for(int player: players){
            if(!losses.containsKey(player)){
                answer.get(0).add(player);
            }
            else if(losses.get(player) == 1){
                answer.get(1).add(player);
            }
        }

        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));
        return answer;
    }

    public static void main(String[] args) {
        hashing h = new hashing();
        System.out.println(h.checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));

        int[] arr = {1,2,3};
        System.out.println(h.countElements(arr));

        //test find winners
        int[][] matches = {{1,2},{2,3},{1,7},{4,5},{3,6}};
        List<List<Integer>> answer = h.findWinners(matches);
        System.out.println(answer);
    }
}
