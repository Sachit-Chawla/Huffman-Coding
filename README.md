# Huffman Coding

Author: **Sachit Singh Chawla**  
Banner ID: **B00865842**

This project is an implementation of the **Huffman Coding algorithm**, a widely used compression technique.  

The program:  
- Takes a table of **alphabets and their probabilities**.  
- Builds a **Huffman Tree** based on the probabilities.  
- Generates a **Huffman Code Table** (mapping characters to binary strings).  
- Accepts a user-provided string input, encodes it into a binary sequence of `0`s and `1`s, and then decodes it back to the original string.  

---

## Features

- Builds a Huffman Tree from a set of probability values.  
- Generates prefix-free binary codes for each character.  
- Encodes user input into compressed binary form.  
- Decodes the binary sequence back to the original text.  
- Demonstrates efficient compression and decompression.  

---

## Example

**Input probability table**: LettersProbability.txt



//OUTPUT 1
Huffman Coding
Enter the name of the file with letters and probability: LettersProbability.txt

Building the Huffman tree ....
Huffman coding completed.

Enter a line of text (uppercase letters only): GOD BLESS EVERYONE
Here’s the encoded line: 111100110111111 1011001011101110011001 01100101001110000010011011010011
The decoded line is: GOD BLESS EVERYONE 

//OUTPUT 2
Huffman Coding
Enter the name of the file with letters and probability:LettersProbability.txt

Building the Huffman tree ....
Huffman coding completed.

Enter a line of text (uppercase letters only): HELLO THERE
Here’s the encoded line: 010101110111101111101 00001010111000011
The decoded line is: HELLO THERE 

//OUTPUT 3
Huffman Coding
Enter the name of the file with letters and probability:LettersProbability.txt

Building the Huffman tree ....
Huffman coding completed.

Enter a line of text (uppercase letters only): MY NAME IS SACHIT 
Here’s the encoded line: 0011100100 1010111000111011 11001001 100111100100001011100000 
The decoded line is: MY NAME IS SACHIT 


