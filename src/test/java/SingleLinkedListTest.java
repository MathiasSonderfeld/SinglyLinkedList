import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class SingleLinkedListTest{

    @Test
    public void testAddIntegers(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assertions.assertEquals(list.size(),0);
        Assertions.assertTrue(list.add(0));
        Assertions.assertEquals(list.size(),1);
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(list.size(),2);
    }

    @Nested
    class postAddTests{
@Test
        public void testAddAllStrings(){
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
            ArrayList<String> src = new ArrayList<>(3);
            src.add("firstEntry");
            src.add("secondEntry");
            src.add("thirdEntry");
            Assertions.assertEquals(list.size(),0);
            Assertions.assertTrue(list.addAll(src));
            Assertions.assertEquals(list.size(),3);
        }

        @Test
        public void testRemoveDouble(){
            SinglyLinkedList<Double> list = new SinglyLinkedList<>();
            Assertions.assertEquals(list.size(),0);
            Assertions.assertTrue(list.add(0.0));
            Assertions.assertEquals(list.size(),1);
            Assertions.assertTrue(list.add(1.1));
            Assertions.assertEquals(list.size(),2);
            Assertions.assertTrue(list.add(2.2));
            Assertions.assertEquals(list.size(),3);
            Assertions.assertTrue(list.remove(0.0));
            Assertions.assertEquals(list.size(),2);
            Assertions.assertTrue(list.remove(2.2));
            Assertions.assertEquals(list.size(),1);
        }


        @Test
        public void testRemoveAllCharacter(){
            SinglyLinkedList<Character> list = new SinglyLinkedList<>();
            ArrayList<Character> src = new ArrayList<>(3);
            src.add('1');
            src.add('2');
            src.add('3');
            Assertions.assertEquals(list.size(),0);
            Assertions.assertTrue(list.add('1'));
            Assertions.assertEquals(list.size(),1);
            Assertions.assertTrue(list.add('2'));
            Assertions.assertEquals(list.size(),2);
            Assertions.assertTrue(list.add('3'));
            Assertions.assertEquals(list.size(),3);
            Assertions.assertTrue(list.add('4'));
            Assertions.assertEquals(list.size(),4);
            Assertions.assertTrue(list.removeAll(src));
            Assertions.assertEquals(list.size(),1);
        }

        @Test
        public void testContainsIntegers(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertEquals(list.size(),0);
            Assertions.assertTrue(list.add(0));
            Assertions.assertEquals(list.size(),1);
            Assertions.assertTrue(list.add(1));
            Assertions.assertEquals(list.size(),2);
            Assertions.assertTrue(list.contains(0));
            Assertions.assertTrue(list.contains(1));
        }

        @Test
        public void testContainsAllStrings(){
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
            ArrayList<String> src = new ArrayList<>(3);
            src.add("firstEntry");
            src.add("secondEntry");
            src.add("thirdEntry");
            Assertions.assertEquals(list.size(),0);
            Assertions.assertTrue(list.add("firstEntry"));
            Assertions.assertEquals(list.size(),1);
            Assertions.assertTrue(list.add("secondEntry"));
            Assertions.assertEquals(list.size(),2);
            Assertions.assertTrue(list.add("thirdEntry"));
            Assertions.assertEquals(list.size(),3);
            Assertions.assertTrue(list.containsAll(src));
        }
    }
}
