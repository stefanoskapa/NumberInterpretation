package numberinterpretation;

import java.util.LinkedList;
import java.util.List;

public class Util {

    private static final List<String> interpretations = new LinkedList<>();

    /*
    Inserts valid numbers into a List of Strings, 
    irrelevant characters are ignored
     */
    static List<String> parseNums(String userInput) {
        List<String> numbers = new LinkedList();
        String[] words = userInput.split("\\s+");

        for (String i : words) {
            if (i.matches("[0-9]{1,3}")) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private static String concatenate(List<String> numbers) {
        String concat = "";
        for (String i : numbers) {
            concat += i;
        }
        return concat;
    }

    /*
    Performs all possible split operations and returns a
    List which can't be splitted further
     */
    static List<String> splitAll(LinkedList<String> words) {
        List<String> temp = (LinkedList) words.clone();
        int counter = 0;
        while (counter < temp.size()) {
            if (Util.isSplittable(temp.get(counter))) {
                String[] result = split(temp.get(counter));
                temp.set(counter, result[0]);
                temp.add(counter + 1, result[1]);
            } else {
                counter++;
            }
        }
        return temp;
    }

    /*
        The method splits out the list completely, then tries
        all possible merge combinations recursively
     */
    static List<String> findInterpretations(LinkedList<String> words) {
        List<String> splittedNums = splitAll(words);
        return getMergerResults((LinkedList) splittedNums);
    }

    static List<String> getMergerResults(LinkedList<String> words) {
        interpretations.clear();
        Util.merger(words, 0);
        return interpretations;
    }

    /*
    Performs all possible merge operations starting from the specified
    offset. Every merge operation results in a unique interpretation.
     */
    private static void merger(LinkedList<String> words, int offset) {
        interpretations.add(concatenate(words));

        while (offset < words.size() - 1) {

            if (Util.isMergeable(words.get(offset), words.get(offset + 1))) {
                LinkedList<String> temp = (LinkedList) words.clone();
                temp.set(offset, merge(words.get(offset), words.get(offset + 1)));
                temp.remove(offset + 1);
                merger(temp, offset);
            }
            offset++;
        }
    }

    static void showInterpretations() {

        for (int i = 0; i < interpretations.size(); i++) {
            System.out.print("Interpretation " + (i + 1) + ": " + interpretations.get(i));
            String result = isGreekNum(interpretations.get(i)) ? "VALID" : "INVALID";
            System.out.print(" [phone number: " + result + "]\n");
        }

    }

    /*
    Checks whether two numbers can be merged or not
    (visible for testing)
     */
    static boolean isMergeable(String a, String b) {
   
        return (b.length() == 1 && !b.equals("0") && (a.matches("[2-9]0|[1-9]([2-9]|[0])0"))
                || (b.length() == 2 && b.charAt(0) != '0' && a.matches("[1-9]00")));

    }

    /*
    Checks if a number can be splitted 
    (visible for testing)
     */
    static boolean isSplittable(String a) {
        return (!a.matches("[0-1][0-9]|.00|.0|."));
    }

    /*
    Splits a number 
    It is assumed that it has been positively evaluated
    with isSplittable
    (visible for testing)
     */
    static String[] split(String a) {
        String[] result = new String[2];
        if (a.matches("..")) {
            result[0] = a.charAt(0) + "0";
            result[1] = a.charAt(1) + "";
        }

        if (a.matches("...")) {
            if (a.matches("[1-9]{3}")) {
                result[0] = a.charAt(0) + "" + a.charAt(1) + "0";
                result[1] = a.charAt(2) + "";
            } else {
                result[0] = a.charAt(0) + "00";
                result[1] = a.charAt(1) == '0' ? a.charAt(2) + "" : a.charAt(1) + "" + a.charAt(2);
            }
        }
        return result;
    }

    /*
    Merges two numbers
    It is assumed that they have been positively evaluated
    with isMergeable
    (visible for testing)
     */
    static String merge(String a, String b) {
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        return (aInt + bInt) + "";
    }

    /*
    Check if it is a greek phone number
    (visible for testing)
     */
    static boolean isGreekNum(String numbers) {
        return ((numbers.length() == 10 && numbers.matches("^(2|69).+"))
                || (numbers.length() == 14 && numbers.matches("^(00302|003069).+")));

    }

}
