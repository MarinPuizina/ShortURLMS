package com.marin.urlshortenerms.shared;

import com.marin.urlshortenerms.service.URLShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class IDConverterTest {

    @Autowired
    IDConverter idConverter;

    @Test
    void shouldBeAbleToDivideTwoNumbers() {

        Optional<Long> number = Optional.of(62L);

        List<Integer> returnValue = idConverter.convertIDToBase62(number);

        assertNotNull(returnValue);

    }

    @Test
    void shouldStoreReminderIntoArrayList() {

        List<Integer> testList = new ArrayList<>();
        Optional<Long> number = Optional.of(125L);

        List<Integer> returnValue = idConverter.convertIDToBase62(number);

        assertNotNull(testList);
        assertEquals(1, returnValue.get(0));
        assertEquals(2, returnValue.get(1));

    }


    /*@Test
    void shouldMapCharsToNumbers() {

        idConverter.initializeCharToNumberTable();

        assertNotNull(idConverter.charToNumberTable);
        assertEquals(26 , idConverter.charToNumberTable.get('A'));

    }*/

    /*@Test
    void shouldMapNumbersToChars() {

        idConverter.initializeNumberToCharTable();

        assertNotNull(idConverter.numberToCharTable);
        assertEquals('a' , idConverter.charToNumberTable.get(0));

    }*/

    @Test
    void shouldCreateUniqueURLID() {

        String returnValue = idConverter.createUniqueID(Optional.of(125L));

        assertNotNull("cb", returnValue);

    }

    @Test
    void uniqueURLIDShouldBeReversed() {

        String returnValue = idConverter.createUniqueID(Optional.of(125L));

        assertNotNull("cb", returnValue);

    }

}