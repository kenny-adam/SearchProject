import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

// stand-alone file, can be run on its own
// initial test file for implementing a phrase search
public class PhraseSearch {
    public static void main (String[] args) {
        // create test map containing sample index data
        // format is word, position
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("the", 1);
        testMap.put("quick", 2);
        testMap.put("brown", 3);
        testMap.put("fox", 4);
        testMap.put("jumps", 5);
        testMap.put("over", 6);
        testMap.put("a", 7);
        testMap.put("lazy", 8);
        testMap.put("dog", 9);

        // print test map
        System.out.println("\nPrinting entire test Map...\n" + testMap);

        // create empty set to hold found elements
        Set<String> foundSet = new HashSet<>();

        // print out found set to make sure it is empty at the beginning
        System.out.println("\nFound Set at start of search (should be empty)...\n" + foundSet + "\n");

        // search for phrase and add to foundset if they are found in exact order
        // these phrases SHOULD be found
        String searchPhraseOne = "the! quick# $brown";
        String searchPhraseTwo = "%over &a (lazy) dog";
        String searchPhraseThree = "brown* +fox, -jumps.";

        // these phrases should NOT be found
        String searchPhraseFour = "over: a; <lazy> hog";        // no 'hog'
        String searchPhraseFive = "a? quick@ [brown] fox";      // wrong order
        String searchPhraseSix = "jumps` ^over {my} ~lazy|";    // no 'my'

        // clean input, remove special characters from all strings
        // replaceAll("[^\\w\\s]","") removes all except [a-zA-Z_0-9]
        System.out.println("Cleaning all input of special characters...\n");
        searchPhraseOne = searchPhraseOne.replaceAll("[^\\w\\s]","");
        searchPhraseTwo = searchPhraseTwo.replaceAll("[^\\w\\s]","");
        searchPhraseThree = searchPhraseThree.replaceAll("[^\\w\\s]","");
        searchPhraseFour = searchPhraseFour.replaceAll("[^\\w\\s]","");
        searchPhraseFive = searchPhraseFive.replaceAll("[^\\w\\s]","");
        searchPhraseSix = searchPhraseSix.replaceAll("[^\\w\\s]","");

        // do the actual searching
        FindPhrase(searchPhraseOne, testMap, foundSet);
        FindPhrase(searchPhraseTwo, testMap, foundSet);
        FindPhrase(searchPhraseThree, testMap, foundSet);
        FindPhrase(searchPhraseFour, testMap, foundSet);
        FindPhrase(searchPhraseFive, testMap, foundSet);
        FindPhrase(searchPhraseSix, testMap, foundSet);

        // print out found set at end of search
        System.out.println("Found Set at end of search (should contain 3 matches)...\n" + foundSet);

    }

    // method to search tMap for phrase and add to fSet if found
    static void FindPhrase(String phrase, Map<String, Integer> tMap, Set<String> fSet) {
        // first, break up phrase into individual words
        String[] words = phrase.split(" ");

        // boolean to keep track of whether word was found
        // assume false at beginning
        boolean isFound = false;

        // int to keep track of position, so we can check one word ahead
        int pos = 0;

        // print out phrase we are looking for
        System.out.println("Searching for phrase '" + phrase + "'...");

        // see if first word is in tMap
        if (tMap.containsKey(words[0])) {
            System.out.println("found '" + words[0] + "' at " + tMap.get(words[0]));
            pos = (int)tMap.get(words[0]);

            // now, search tMap for rest of words
            for (int i = 1; i < words.length; ++i) {
                if (tMap.containsKey(words[i])) {
                    // word was found, is it next word?
                    if (tMap.get(words[i]) == (pos + 1)) {
                        // increment new position
                        System.out.println("found '" + words[i] + "' at " + tMap.get(words[i]));
                        ++pos;
                    } else {
                        // word found, but at wrong position
                        System.out.println("'" + words[i] + "' not found at expected position. aborting search.\n");
                        isFound = false;
                        break;
                    }
                } else {
                    // word not found
                    System.out.println("'" + words[i] + "' not found. aborting search.\n");
                    isFound = false;
                    break;
                }
                // found all words in order
                isFound = true;
            }
        }

        // if all words were found in order, add phrase to fSet
        if (isFound) {
            System.out.println("'" + phrase + "' was found! adding to found Set.\n");
            fSet.add(phrase);
        }
    }
}