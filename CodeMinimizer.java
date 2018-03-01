/*
  Assumptions:
    1. I have assumed that the input will be a string and will not contain any New Line characters.
  Data Structures Used:
    1.HashMap: I used a HashMap to store the Identifiers and their position in the string in a <String, Integer> key value pair.

  Regular Expression:
    1.(?=[\\P{Alpha}+]) : Splits over all characters that are not alphabets (including numbers, whitespace and special characters).
    2.(?<=[\\P{Alpha}+]) : Splits over all alphabetical characters.
    3.^[a-zA-Z]+: To check that the identifier only contains alphabets.


  Approach:
  Take the string and split it over special characters as well as over alphabets, this allows us to have an array of words that contains
  both the identifiers and the characters that they were split over:
  Example: "I am4a boy" ====> ["I","am","4","a","boy"] (after split)
  After splitting we save all the valid identifiers (checked using a pattern matcher) in a HashMap. At the same time we initialise a
  StringBuilder object (sb) and append to it to form the new changed string, For the first occurrence of the identifier we append it directly to
  sb and put it in the HashMap, from then whenever an identifier is encountered which is already present in the HashMap, we replace it by the
  value of that identifier from the HashMap and append the $(integer value of identifier) to sb. At the end of the for loop we return the New String
  which contains the modified string.

  Functions:
  1. main: driver function.
  2. minimize(String): Function to take in the original string and return the modified string.   
  
 */
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CodeMinimizer {
    public static void main(String[] args) {
        String program ="/*" +
                "* Function to chop a string in half. */" +
                "public static string Chop(string input) { if (input == null || input.isEmpty()) {" +
                "return input; }" +
                "if (input.length() % 2 == 1) {" +
                "return \"cannot chop an odd-length string in half\";" +
                "}" +
                "return input.substring(input.length() / 2); }";
        System.out.println(minimize(program));
    }
    static String minimize(String program){
        int counter = 0;
        Map<String, Integer> cache = new HashMap<String,Integer>();
        String[] a = program.split("(?=[\\P{Alpha}+])|(?<=[\\P{Alpha}+])");
        StringBuilder sb = new StringBuilder();
        Pattern alphabets  = Pattern.compile("^[a-zA-Z]+");

        for (String str : a) {
            if (alphabets.matcher(str).matches()) {
                if (cache.containsKey(str)) {
                    sb.append("$" + Integer.toString(cache.get(str)));
                } else {
                    cache.put(str, counter);
                    sb.append(str);
                }
                counter++;
            } else {
                sb.append(str);
            }
        }
        String minimizedString = sb.toString();
        return minimizedString;
    }

}
