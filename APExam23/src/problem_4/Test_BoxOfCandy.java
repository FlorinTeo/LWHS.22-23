package problem_4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_BoxOfCandy {

    @Test
    void test_moveCandyToFirstRow() {
        Candy[][] candies = {
            { null,   new Candy("lime"),    null                },
            { null,   new Candy("orange"),  null                },
            { null,   null,                 new Candy("cherry") },
            { null,   new Candy("lemon"),   new Candy("grape")  }
        };
        
        BoxOfCandy box = new BoxOfCandy(candies);
        assertFalse(box.moveCandyToFirstRow(0));
        assertArrayEquals(candies, box.getCandies());
        assertTrue(box.moveCandyToFirstRow(1));
        assertArrayEquals(candies, box.getCandies());
        assertTrue(box.moveCandyToFirstRow(2));
        Candy[][] newCandies = box.getCandies();
        assertNotNull(newCandies[0][2]);
        assertTrue(newCandies[2][2] == null && newCandies[3][2] == candies[3][2]);
    }
    
    @Test
    void test_removeNextByFlavor() {
        Candy[][] candies = {
                { new Candy("lime"),   new Candy("lime"), null,               new Candy("lemon"), null },
                { new Candy("orange"), null,              null,               new Candy("lime"),  new Candy("lime") },
                { new Candy("cherry"),  null,              new Candy("lemon"), null,               new Candy("orange") }
            };
        
        BoxOfCandy box = new BoxOfCandy(candies);

        box.removeNextByFlavor("cherry");
        candies[2][0] = null;
        assertArrayEquals(candies, box.getCandies());
        
        box.removeNextByFlavor("lime");
        candies[1][3] = null;
        assertArrayEquals(candies, box.getCandies());
    }
}
