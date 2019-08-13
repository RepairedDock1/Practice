package string;

import java.util.HashMap;

public class LongestSubstring {
  public static void main(String[] args) {
    System.out.println(longestSubstring("abcabcbb"));
  }

  private static Integer longestSubstring(String string){
    HashMap<Character, Integer> charMap = new HashMap<>();
    Integer max = 0;

    for(int substringStart=0,  current = 0; current<string.length(); current++){
      Character character = string.charAt(current);
      if(charMap.containsKey(character) && charMap.get(character)>=substringStart){
        substringStart = charMap.get(character)+1;
        charMap.put(character, current);
        continue;
      }

      charMap.put(character, current);
      if((current+1)-substringStart>max){
        max = (current+1)-substringStart;
      }
    }

    return max;
  }
}
