import java.util.*;

public class LetterInventory {
   
   public final int ALPHABET = 26;
   private char letter = '\0';
   private int[] count;
   private int inventory = 0;
   
   public LetterInventory(String data) {
      count = new int[ALPHABET];
      data = data.toUpperCase();
      for (int x = 0; x < data.length(); x++) {
         inventory++;
         count[data.charAt(x)-65]++;
      }
   }
   
   //Takes a char as a parameter and returns a count of how many of this letter are
   //in the inventory. Throws an exception if the letter is a special character.
   public int get (char letter) throws IllegalArgumentException {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("No Special Characters Allowed");
      }
      return count[Character.toUpperCase(letter)-65];
   }
   
   //Sets the count for the given letter to the given value. Throws an exception if 
   //given leter is a special character.
   public void set(char letter, int value) throws IllegalArgumentException {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("No Special Characters Allowed");
      } else {
         int originalValue = count[(int)Character.toUpperCase(letter)-65];
         letter = Character.toUpperCase(letter);
         count[letter-65] = value;
         inventory += originalValue - value;
      }
   }
   
   //Returns the sum of all of the counts in this inventory
   public int size() {
      return inventory;
   }
   
   //Returns true if count[] is empty, false if it has one or more elements
   public boolean isEmpty() {
      return inventory == 0;
   }
   
   public String toString() {
      String inventory = "[";
      for (int x = 0; x < count.length; x++) {
         for (int i = 0; i < count[x]; i++) {
            inventory += (char) (x + 'a');
         }
      }
      return inventory += "]";
   }
   
   public LetterInventory add(LetterInventory other) {
      LetterInventory add = new LetterInventory(null);
      for (int i = 0; i < count.length; i++) {
         add.count[i] = this.count[i] + other.count[i];
         i++;
      }
      inventory = this.inventory + other.size();
      return add;
   }
   
   public LetterInventory subtract(LetterInventory other) {
      int otherInventory = 0;
      String subtracted = "";
      for (int x = 0; x < other.inventory; x++) {
         otherInventory++;      
      }
      if (otherInventory > inventory) {
         return null;
      } else {
         for (int y = 0; y < inventory - otherInventory; y++) {
            subtracted += "a";
         }
         LetterInventory sum = new LetterInventory(subtracted);
         return sum;
      }     
   }
   
}