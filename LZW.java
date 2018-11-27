import java.util.*;

public class LZW {

    int sizeOfFile = 0;
    
    /**
     * Compress the given text according the LZW compression method
     * @param uncompressed the text uncompressed which is inside the txt file
     * @param sizeOfSymbol the size the user wants to allocate to each symbol
     * @return the list of symbol constituting the compressed text 
     */
    public List<Integer> compress(String uncompressed, int sizeOfSymbol) {
        
        /******** Build the primary dictionary ********/

        // Indicates the size of an initial dictionnary
        int dictSize = 257;

        // Use a HashMap since each symbol correspond to a code and appears only once
        Map<String,Integer> dictionary = new HashMap<String,Integer>();

        // Fill up the dictionnary with the already known symbols
        for (int i = 0; i < dictSize; i++) {
            dictionary.put("" + (char)i, i);
        }

        /********* Complete the dictionary ***********/
 
        // Init the latent code to nothing
        String latentCode = "";

        // Build the list of symbol which will constitute the compressed text 
        List<Integer> result = new ArrayList<Integer>();

        // Read character by character the given text
        for (char readCode : uncompressed.toCharArray()) {

            // Add the read character to the latent code
            String potentialAdd = latentCode + readCode;

            /**** Verify if the new word exists in the dictionnary ****/
            if (dictionary.containsKey(potentialAdd)) {

                /* If the word is already in the dictionnary
                it become the latent code for the next loop */
                latentCode = potentialAdd;

            } else {

                /* If the word isn't in the dictionnary, we : */

                // Add the code wich correspond to the previous latent code to the list which constitutes the compressed text
                // Correspond to the emitted code
                result.add(dictionary.get(latentCode));

                // Increase the size of our file since a symbol was added in the result
                sizeOfFile += sizeOfSymbol;

                // Add the new word to the dictionary with the affected code corresponding to the first free code
                dictionary.put(potentialAdd, ++dictSize);

                // Init the latent code for the next loop as the last read code
                latentCode = Character.toString(readCode);
            }
        }
 
        // if (!latentCode.equals("")) {
        //     result.add(dictionary.get(latentCode));
        // }
    
        /******* Return the list comprising the compressed text *********/
        return result;
    }

    public int getSizeOfFile() { 

        /***** Return the size of the file according the given size for one symbol ******/
        return sizeOfFile ; 
    }

    
    
    
    //Pour nous !!
    public static String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result.append(entry);
 
            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result.toString();
    }
}