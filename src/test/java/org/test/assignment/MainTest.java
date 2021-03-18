package org.test.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {

    @Test
    void unpackStringTest() {
        assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyz", Main.unpackString("10[xyz]"));
        assertEquals("xxxyxxxy", Main.unpackString("2[3[x]y]"));
        assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyz",
                Main.unpackString("10[xyz]10[xyz]"));
        assertEquals("xyzxyzxyzxyxyxyxyz", Main.unpackString("3[xyz]4[xy]z"));
        assertEquals("xyzxyzxyzxyzxyzxyxyxyxyz", Main.unpackString("xyz3[xyz]xyz4[xy]z"));
        assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzxyzxy" +
                        "zxyzxyzzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzxyz" +
                        "xyzxyzxyzzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzzxyzxyzxyzxyzxyzxyzx" +
                        "yzxyzxyzxyzz",
                Main.unpackString("9[xyz9[xyz]z]"));
        assertEquals("xyz", Main.unpackString("xyz"));
        assertEquals("abcooooabcoooo", Main.unpackString("2[abc2[oo]]"));
    }
}