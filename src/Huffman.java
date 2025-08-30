/**
 * Author @ Sachit Singh Chawla
 * Banner ID : B00865842
 * This is the implementation of Huffman Coding. It takes a table with Alphabets and their probabilities pairs them,
 * Then creates a huffman table using the probabilities and then takes an input for a string and encodes the string in the
 * form of 1's and 0's and then decodes the 0's and 1's String back to the String provided by the user.
 */

import java.io.*;
import java.util.*;

public class Huffman {
    public static void main ( String[] args ) throws FileNotFoundException {
        Scanner in = new Scanner ( (System.in) );
        System.out.println ( "Huffman Coding" );
        System.out.print ( "Enter the name of the file with letters and probability: " );
        String fileName = in.nextLine ( );
        File file = new File ( fileName );

        Scanner scannedFile = new Scanner ( file );
        StringTokenizer token;
        ArrayList<BinaryTree<Pair>> allPairs = new ArrayList<> ( );

        while (scannedFile.hasNext ( )) {
            String inputs = scannedFile.nextLine ( );
            // converts the inputs into tokens and removes the tab
            token = new StringTokenizer ( inputs, "\t" );

            //separates the alphabet and the probability
            String string = token.nextToken ( );
            char alphabet = string.charAt ( 0 );

            double prob = Double.parseDouble ( token.nextToken ( ) );

            //pairs them together to form a binary tree and add the binary tree to the set of pairs
            Pair pair = new Pair ( alphabet, prob );
            BinaryTree<Pair> binaryTree = new BinaryTree<> ( );
            binaryTree.setData ( pair );
            allPairs.add ( binaryTree );
        }
        scannedFile.close ( );

        System.out.println ( );
        System.out.println ( "Building the Huffman tree ...." );

        // Helper queues S and T where S contains all the pairs binary trees
        ArrayList<BinaryTree<Pair>> S = new ArrayList<> ( );
        ArrayList<BinaryTree<Pair>> T = new ArrayList<> ( );

        //Enqueue S
        for (BinaryTree<Pair> allPair : allPairs) {
            S.add ( allPair );
        }
        while (S.size ( ) > 0) {// This loops happens until queue S is empty

            BinaryTree<Pair> A = new BinaryTree<> ( );
            BinaryTree<Pair> B = new BinaryTree<> ( );

            if ( T.size ( ) < 1 ) {
                // A and B are right and left subtrees respectively
                A = S.remove ( 0 );
                B = S.remove ( 0 );
            } else if ( T.size ( ) > 0 ) {
                // Sets A depending on the lesser probabilities
                if ( S.get ( 0 ).getData ( ).getProb ( ) < T.get ( 0 ).getData ( ).getProb ( ) ) {
                    A = S.remove ( 0 );
                } else {
                    A = T.remove ( 0 );
                }
                //Sets B by finding next less probability in the stack when A is already set
                if ( T.size ( ) < 1 || (S.get ( 0 ).getData ( ).getProb ( ) < T.get ( 0 ).getData ( ).getProb ( )) ) {
                    B = S.remove ( 0 );
                } else {
                    B = T.remove ( 0 );
                }
            }
            //Creates a root by the addition A and B probabilities  and sets the root as parent of A and B
            // and A and B as children by attaching them to the left and right
            //and then adds the created binary tree to the queue T
            BinaryTree<Pair> parentBinaryTree = new BinaryTree<> ( );

            Pair root = new Pair ( '!', A.getData ( ).getProb ( ) + B.getData ( ).getProb ( ) );

            parentBinaryTree.setData ( root );
            parentBinaryTree.attachLeft ( A );
            parentBinaryTree.attachRight ( B );

            T.add ( parentBinaryTree );
        }
        // This does the same as step 2
        //If the number of elements in queue T is greater than 1, dequeues two nodes at a time, combines
        //them (see strep 2) and enqueue the combined tree until queue T's size is 1. The last node remaining in
        //the queue T will be the final Huffman tree
        while (T.size ( ) > 1) {
            BinaryTree<Pair> A;
            BinaryTree<Pair> B;

            A = T.remove ( 0 );
            B = T.remove ( 0 );
            BinaryTree<Pair> parentBinaryTree = new BinaryTree<> ( );

            Pair root = new Pair ( '!', A.getData ( ).getProb ( ) + B.getData ( ).getProb ( ) );

            parentBinaryTree.setData ( root );
            parentBinaryTree.attachLeft ( A );
            parentBinaryTree.attachRight ( B );

            T.add ( parentBinaryTree );
        }
        //Find the huffman codes through huffman tree
        String[] huffmanCodes = findEncoding ( T.remove ( 0 ) );

        System.out.println ( "Huffman coding completed." );
        System.out.println ( );

        System.out.print ( "Enter a line of text (uppercase letters only): " );
        String userText = in.nextLine ( );
        // Encodes the text given by user into 0's and 1's using the huffman codes and then prints the coding
        String encodedText = encode ( huffmanCodes, userText );
        System.out.println ( "Here’s the encoded line: " + encodedText );
        //decodes the encoded text into human-readable format
        System.out.println ( "The decoded line is: " + decode ( huffmanCodes, encodedText ) );
    }
    private static String[] findEncoding ( BinaryTree<Pair> bt ) {
        String[] result = new String[26];
        findEncoding ( bt, result, "" );
        return result;
    }

    private static void findEncoding ( BinaryTree<Pair> bt, String[] a, String prefix ) {
        // test is node/tree is a leaf
        if ( bt.getLeft ( ) == null && bt.getRight ( ) == null ) {
            a[bt.getData ( ).getValue ( ) - 65] = prefix;
        }
        // recursive calls
        else {
            findEncoding ( bt.getLeft ( ), a, prefix + "0" );
            findEncoding ( bt.getRight ( ), a, prefix + "1" );
        }
    }

    /**
     * This method takes huffman codes and text  and encodes the text  into 0's and 1's using huffman codes
     * @param huffmanCodes The array of Strings which would be used to convert for eg A into 00
     * @param userText The text given by the user to be encoded
     * @return The encoded text
     */
    public static String encode ( String[] huffmanCodes, String userText ) {
        //This method goes by creating a 0's and 1's coding by first rejecting a space(" ") and
        // Then checks the huffman codes for the character coding
        // for eg if we have "A" and we subtract 65 we would get 0
        // Then we check 0 in huffman code which would correspond to A.
        String encodedText = "";
        for (int i = 0; i < userText.length ( ); i++) {
            if ( userText.charAt ( i ) ==  ' '  ) {
                encodedText += " ";
            }
            else {
                encodedText += huffmanCodes[userText.charAt ( i ) - 65];
            }
        }
        return encodedText;
    }

    /**
     * This method takes in encoding of 0's and 1's and then converts it to human-readable format
     * @param huffmanCodes codes which would be used to convert it back to the text
     * @param encodedText The String of 1's and 0's which has to be converted back ro readable format
     * @return The decoded text
     */
    private static String decode(String[] huffmanCodes,String encodedText) {
        //This method goes by first by starting from encoded text (which is given) first character and sees
        //if matches with any huffman code. If it is found, it associated an alphabet with it
        //and does this until the whole phrase is formed.
        // So for eg we have A which has a huffman code of 0011
        //it starts with 0 compares with huffman codes. It is not found then it takes 00 compares it. Nothing is found
        //adds 1 to the string (001) and checks with huffman code, nothing is found then adds 1 to the string (0011)
        //then checks with the huffman code, sees it matches with A. Then takes the A adds it the decoded String
        //it goes with every alphabet
        String decodedText = "";
        String[] code = encodedText.split(" ");
        String codeToString = "";

        for (String word : code) {
            for (int i = 0; i < word.length(); i++) {
                codeToString += word.charAt(i);
                for (int j = 0; j < huffmanCodes.length; j++) {
                    if (codeToString.equals(huffmanCodes[j])){
                        //Converting to an alphabet
                        decodedText += (char)(j+65);
                        codeToString = "";
                    }
                }
            }
            decodedText += " ";
        }
        return decodedText;
    }
}

