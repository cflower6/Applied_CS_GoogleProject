package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<String>();
    HashSet<String> wordSet = new HashSet<String>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            String key = sortLetters(word);
            wordSet.add(word);

            if(lettersToWord.containsKey(key)){
                ArrayList<String> wordList2 = lettersToWord.get(key);
                wordList2.add(word);
            }
            else{
                ArrayList<String> wordList2 = new ArrayList<String>();
                wordList2.add(word);
                lettersToWord.put(key, wordList2);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(!wordSet.contains(word) && wordSet.contains(base)){
            return false;
        }

        return true;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        String sWord = sortLetters(targetWord);

        for(int i = 0; i < wordList.size();i++) {
            String tList = sortLetters(wordList.get(i));

            if(sWord.equals(tList)){
                result.add(wordList.get(i));
            }
        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for (char alpha = 'a'; alpha <= 'z'; alpha++) {
            String modWord = word;
            modWord += alpha;
            String sWord2 = sortLetters(word);

            for (int i = 0; i < wordList.size(); i++) {
                String tList2 = sortLetters(wordList.get(i));

                if (sWord2.equals(tList2)) {
                    result.add(wordList.get(i));
                }


            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }

    public String sortLetters(String wordToSolve) {
        //Takes the word splits the word into characters places characters into an Array
        char[] chars = wordToSolve.toCharArray();
        //This sorts the characters in alphabetical order
        Arrays.sort(chars);
        //places chars into a string
        String newSortedWord = new String(chars);
        return newSortedWord;
        //rearranging the word into alphabetical order, use the word length to compare aetks
    }
}