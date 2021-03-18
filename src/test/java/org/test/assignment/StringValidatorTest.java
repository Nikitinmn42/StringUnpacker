package org.test.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringValidatorTest {

    @Test
    public void isValidInputFalse() {
        assertFalse(new StringValidator("xyz[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("10[xyz").validate().isStringValid());
        assertFalse(new StringValidator("10[xyz10[xyz]10[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("10]xyz[").validate().isStringValid());
        assertFalse(new StringValidator("10xyz[]").validate().isStringValid());
        assertFalse(new StringValidator("10xyz").validate().isStringValid());
        assertFalse(new StringValidator("10[xyz]10").validate().isStringValid());
        assertFalse(new StringValidator("0[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("10[]").validate().isStringValid());
        assertFalse(new StringValidator("10[ ]").validate().isStringValid());
        assertFalse(new StringValidator("10[//]").validate().isStringValid());
        assertFalse(new StringValidator("10[x10yz]").validate().isStringValid());
        assertFalse(new StringValidator("001[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("9[]99[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("9xyz99[xyz]").validate().isStringValid());
        assertFalse(new StringValidator("9[xyz9[xyz]z").validate().isStringValid());
        assertFalse(new StringValidator("").validate().isStringValid());
        assertFalse(new StringValidator(" ").validate().isStringValid());
        assertFalse(new StringValidator("*").validate().isStringValid());
        assertFalse(new StringValidator("\\\\").validate().isStringValid());
        assertFalse(new StringValidator("10").validate().isStringValid());
        assertFalse(new StringValidator("[]").validate().isStringValid());
        assertFalse(new StringValidator("i[ii]").validate().isStringValid());
    }

    @Test
    public void isValidInputTrue() {
        assertTrue(new StringValidator("10[xyz]").validate().isStringValid());
        assertTrue(new StringValidator("10[XYZ]").validate().isStringValid());
        assertTrue(new StringValidator("2[3[x]y]").validate().isStringValid());
        assertTrue(new StringValidator("2[3[XY]y]").validate().isStringValid());
        assertTrue(new StringValidator("10[10[x]y]").validate().isStringValid());
        assertTrue(new StringValidator("10[xyz]10[xyz]").validate().isStringValid());
        assertTrue(new StringValidator("3[xyz]4[xy]z").validate().isStringValid());
        assertTrue(new StringValidator("xyz3[xyz]xyz4[xy]z").validate().isStringValid());
        assertTrue(new StringValidator("9[xyz9[xyz]z]").validate().isStringValid());
        assertTrue(new StringValidator("xyz").validate().isStringValid());
        assertTrue(new StringValidator("2[abc2[oo]]").validate().isStringValid());
    }

}
