package numberinterpretation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefanos
 */
public class UtilTest {

    public UtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseNums method, of class Util.
     */
    @Test
    public void testParseNums() {
        System.out.println("parseNums");
        String a;
        List<String> expected, result;

        a = "10 0 4333 5";
        expected = Arrays.asList("10", "0", "5");
        result = Util.parseNums(a);
        assertThat(expected, is(result));

        a = "1       33 hello world 599";
        expected = Arrays.asList("1", "33", "599");
        result = Util.parseNums(a);
        assertThat(expected, is(result));
    }

    /**
     * Test of splitAll method, of class Util.
     */
    @Test
    public void testSplitAll() {
        System.out.println("splitAll");
        LinkedList<String> input, expected, result;

        input = new LinkedList(Arrays.asList("15", "0", "554"));
        expected = new LinkedList(Arrays.asList("15", "0", "500", "50", "4"));
        result = new LinkedList(Util.splitAll(input));
        assertThat(result, is(expected));

        input = new LinkedList(Arrays.asList("1", "0", "0"));
        expected = new LinkedList(Arrays.asList("1", "0", "0"));
        result = new LinkedList(Util.splitAll(input));
        assertThat(result, is(expected));

        input = new LinkedList(Arrays.asList("1", "0", "20", "10", "5"));
        expected = new LinkedList(Arrays.asList("1", "0", "20", "10", "5"));
        result = new LinkedList(Util.splitAll(input));
        assertThat(result, is(expected));
    }

    /**
     * Test of getMergerResults method, of class Util.
     */
    @Test
    public void testGetMergerResults() {
        System.out.println("getMergerResults");
        LinkedList<String> input, result;
        String[] expected;

        input = new LinkedList(Arrays.asList("15"));
        expected = new String[]{"15"};
        result = new LinkedList(Util.getMergerResults(input));
        assertThat(result, hasItems(expected));

        input = new LinkedList(Arrays.asList("15", "20"));
        expected = new String[]{"1520"};
        result = new LinkedList(Util.getMergerResults(input));
        assertThat(result, hasItems(expected));

        input = new LinkedList(Arrays.asList("150", "20"));
        expected = new String[]{"15020"};
        result = new LinkedList(Util.getMergerResults(input));
        assertThat(result, hasItems(expected));

        input = new LinkedList(Arrays.asList("100", "20", "5"));
        expected = new String[]{"100205", "1205", "10025", "125"};
        result = new LinkedList(Util.getMergerResults(input));
        assertThat(result, hasItems(expected));
    }

    /**
     * Test of isMergeable method, of class Util.
     */
    @Test
    public void testIsMergeable() {
        System.out.println("isMergeable");
        String a, b;
        boolean result;

        a = "0";
        b = "0";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "0";
        b = "";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "10";
        b = "1";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "99";
        b = "1";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "200";
        b = "1";
        result = Util.isMergeable(a, b);
        assertTrue(result);

        a = "200";
        b = "11";
        result = Util.isMergeable(a, b);
        assertTrue(result);

        a = "200";
        b = "20";
        result = Util.isMergeable(a, b);
        assertTrue(result);

        a = "200";
        b = "02";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "100";
        b = "5";
        result = Util.isMergeable(a, b);
        assertTrue(result);

        a = "110";
        b = "5";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "140";
        b = "0";
        result = Util.isMergeable(a, b);
        assertFalse(result);

        a = "30";
        b = "0";
        result = Util.isMergeable(a, b);
        assertFalse(result);

    }

    /**
     * Test of isSplittable method, of class Util.
     */
    @Test
    public void testIsSplittable() {
        System.out.println("isSplittable");
        String a;
        boolean result;

        a = "0";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "10";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "19";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "20";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "21";
        result = Util.isSplittable(a);
        assertTrue(result);
        
        a = "30";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "99";
        result = Util.isSplittable(a);
        assertTrue(result);

        a = "100";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "200";
        result = Util.isSplittable(a);
        assertFalse(result);

        a = "220";
        result = Util.isSplittable(a);
        assertTrue(result);

        a = "222";
        result = Util.isSplittable(a);
        assertTrue(result);

        a = "202";
        result = Util.isSplittable(a);
        assertTrue(result);
    }

    /**
     * Test of split method, of class Util.
     */
    @Test
    public void testSplit() {
        System.out.println("split");

        String a = "550";
        String[] result = Util.split(a);
        assertArrayEquals(result, new String[]{"500", "50"});

        a = "555";
        result = Util.split(a);
        assertArrayEquals(result, new String[]{"550", "5"});

        a = "55";
        result = Util.split(a);
        assertArrayEquals(result, new String[]{"50", "5"});

        a = "505";
        result = Util.split(a);
        assertArrayEquals(result, new String[]{"500", "5"});

    }

    /**
     * Test of merge method, of class Util.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        String a, b, result;

        a = "100";
        b = "1";
        result = Util.merge(a, b);
        assertEquals("101", result);

        a = "20";
        b = "1";
        result = Util.merge(a, b);
        assertEquals("21", result);

        a = "200";
        b = "20";
        result = Util.merge(a, b);
        assertEquals("220", result);

    }

    /**
     * Test of isGreekNum method, of class Util.
     */
    @Test
    public void testIsGreekNum() {
        System.out.println("isGreekNum");
        String a;
        boolean result;

        a = "2106253286";
        result = Util.isSplittable(a);
        assertTrue(result);

        a = "2124958333";
        result = Util.isSplittable(a);
        assertTrue(result);

        a = "00302124958333";
        result = Util.isSplittable(a);
        assertTrue(result);
    }

    /**
     * Test of findInterpretations method, of class Util.
     */
    @Test
    public void testFindInterpretations() {
        System.out.println("findInterpretations");
        String a;
        List<String> result;
        String[] expected;

        a = "2 10 6 9 30 6 6 4";
        expected = new String[]{"2106930664", "210693664"};
        result = new LinkedList(Util.findInterpretations((LinkedList) Util.parseNums(a)));
        assertThat(result, hasItems(expected));

        a = " 2 10 69 30 6 6 4";
        expected = new String[]{"2106930664", "210693664", "2106093664", "21060930664"};
        result = new LinkedList(Util.findInterpretations((LinkedList) Util.parseNums(a)));
        assertThat(result, hasItems(expected));

        a = "0 0 30 69 700 24 1 3 50 2";
        expected = new String[]{"0030697002413502", "003069700241352", "00306972413502", "00306097241352"};
        result = new LinkedList(Util.findInterpretations((LinkedList) Util.parseNums(a)));
        assertThat(result, hasItems(expected));

    }

}
