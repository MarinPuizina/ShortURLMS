package com.marin.urlshortenerms.shared;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IDConverter {

    private IDConverter() {
        initializeCharToNumberTable();
        initializeNumberToCharTable();
    }

    private static HashMap<Character, Integer> charToNumberTable;
    private static List<Character> numberToCharTable;

    /**
     * Mapping chars to numbers.
     *
     * 0 -> a ... 25 -> z
     * 26 -> A ... 51 -> Z
     * 52 -> 0 ... 61 -> 9
     */
    private void initializeCharToNumberTable() {

        charToNumberTable = new HashMap<>();

        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            charToNumberTable.put(c, i);
        }

        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            charToNumberTable.put(c, i);
        }

        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            charToNumberTable.put(c, i);
        }

    }

    /**
     * Mapping numbers to chars.
     *
     * 0 -> a ... 25 -> z
     * 26 -> A ... 51 -> Z
     * 52 -> 0 ... 61 -> 9
     */
    private void initializeNumberToCharTable() {

        numberToCharTable = new ArrayList<>();

        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            numberToCharTable.add(c);
        }

        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            numberToCharTable.add(c);
        }

        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            numberToCharTable.add(c);
        }

    }

    /**
     * Converting ID base10 to base62.
     *
     * @param dividend ID.
     * @return Base62 ID.
     */
    public List<Integer> convertIDToBase62(Optional<Integer> dividend) {

        List<Integer> remainder = new LinkedList<>();

        while (dividend.orElse(0) > 0) {

            remainder.add(dividend.orElse(0) % 62);
            dividend = Optional.of(dividend.orElse(0) / 62);

        }

        return remainder;
    }

    public String createUniqueID(Optional<Integer> id) {

        List<Integer> base62ID = convertIDToBase62(id);
        StringBuilder uniqueURLID = new StringBuilder();

        if(!base62ID.isEmpty()) {

            for (int digit: base62ID) {
                uniqueURLID.append(numberToCharTable.get(digit));
            }

        }

        return uniqueURLID.reverse().toString();
    }

}
