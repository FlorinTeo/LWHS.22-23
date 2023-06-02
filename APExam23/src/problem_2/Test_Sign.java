package problem_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_Sign {

    @Test
    void test_Sign_methods() {
        String str;
        int x;
        
        // The message for sign 1 contains 8 characters,
        // and the sign has lines of width 3.
        Sign sign1 = new Sign("ABC222DE", 3);
        x = sign1.numberOfLines();
        // The sign needs three lines to display the
        // 8-character message on a sign with lines of width 3.
        assertEquals(3, x);
        // Semicolons separate the text displayed on the
        // first, second, and third lines of the sign.
        str = sign1.getLines();
        assertEquals("ABC;222;DE", str);
        // Successive calls to getLines return the same value.
        str = sign1.getLines();
        assertEquals("ABC;222;DE", str);
        
        // The message for sign2 contains 4
        // characters and the sign has lines of width 10.
        Sign sign2 = new Sign("ABCD", 10);
        // The sign needs one line to display
        // the 4-character message on a sign with lines of
        // width 10.
        x = sign2.numberOfLines();
        assertEquals(1, x);
        // No semicolon appears, since the text to be
        // displayed fits on the first line of the sign.
        str = sign2.getLines();
        assertEquals("ABCD", str);
        
        // The message for sign3 contains 6
        // characters, and the sign has lines of width 6.
        Sign sign3 = new Sign("ABCDEF", 6);
        // The sign needs one line to display the
        // 6-character message on a sign with lines of
        // width 6.
        x = sign3.numberOfLines();
        assertEquals(1, x);
        // No semicolon appears, since the text to be
        // displayed fits on the first line of the sign.
        str = sign3.getLines();
        assertEquals("ABCDEF", str);
        
        // The message for sign4 is an empty string.
        Sign sign4 = new Sign("", 4);
        // There is no text to display.
        x = sign4.numberOfLines();
        assertEquals(0, x);
        // There is no text to display.
        str = sign4.getLines();
        assertNull(str);
        
        // The message for sign5 contains 8
        // characters, and the sign has lines of width 2.
        Sign sign5 = new Sign("AB_CD_EF", 2);
        // The sign needs four lines to display the
        // 8-character message on a sign with lines of width 2.
        x = sign5.numberOfLines();
        assertEquals(4, x);
        // Semicolons separate the text displayed on the
        // four lines of the sign.
        str = sign5.getLines();
        assertEquals("AB;_C;D_;EF", str);
    }

}
