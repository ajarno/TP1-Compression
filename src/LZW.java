import java.util.*;

public class LZW {

    /**
     * Compress the given text according the LZW compression method
     * @param uncompressed the text uncompressed which is inside the txt file
     * @param sizeOfSymbol the size the user wants to allocate to each symbol
     * @return the list of symbol constituting the compressed text
     */
    public static String compress(String uncompressed, int sizeOfSymbol) {

        /******** Build the primary dictionary ********/

        int sizeOfFile = 0;

        // Indicates the size of an initial dictionnary
        int dictSize = 257;

        // Use a HashMap since each symbol correspond to a code and appears only once
        Map<String,Integer> dictionary = new HashMap<String,Integer>();

        // Fill up the dictionnary with the already known symbols
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(Character.toString((char)i), i);
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

         if (!latentCode.equals("")) {
             result.add(dictionary.get(latentCode));
         }

        System.out.println(dictionary);

        /** Add the size of the file at the end of the list ***/
        result.add(sizeOfFile);

        /******* Return the list comprising the compressed text *********/
        return turnResultIntoString(result);
    }

    private static String turnResultIntoString(List<Integer> result) {
        StringBuilder compressedText = new StringBuilder();
        int i=0;
        for (i=0; i < result.size()-1; i++) {
            compressedText.append(result.get(i).toString());
        }
        compressedText.append("\nTaille du fichier compressé : ").append(result.get(i).toString());
        return compressedText.toString();
    }
}