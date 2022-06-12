import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SingleLinkedListTest{

    @Test
    public void testAddSizeGetIntegers(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0, list.size());
        Assertions.assertTrue(list.add(0));
        Assertions.assertEquals(1, list.size());
        Assertions.assertTrue(list.get(0) == 0);
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.get(1) == 1);
    }

    @Nested
    class postAddTests{
        @Test
        public void testIsEmpty(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertTrue(list.isEmpty());
            Assertions.assertTrue(list.add(0));
            Assertions.assertFalse(list.isEmpty());
        }

        @Test
        public void testAddIndex(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertDoesNotThrow(() -> list.add(0, 0));
            Assertions.assertEquals(1, list.size());
            Assertions.assertDoesNotThrow(() -> list.add(1, 2));
            Assertions.assertEquals(2, list.size());
            Assertions.assertDoesNotThrow(() -> list.add(1, 1));
            Assertions.assertEquals(3, list.size());
            Assertions.assertEquals(0, list.get(0));
            Assertions.assertEquals(1, list.get(1));
            Assertions.assertEquals(2, list.get(2));
        }

        @Test
        public void testAddAllStrings(){
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
            ArrayList<String> src = new ArrayList<>(3);
            src.add("firstEntry");
            src.add("secondEntry");
            src.add("thirdEntry");
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.addAll(src));
            Assertions.assertEquals(3,list.size());
        }

        @Test
        public void testAddAllIndexStrings(){
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
            ArrayList<String> src = new ArrayList<>(3);
            src.add("firstEntry");
            src.add("secondEntry");
            src.add("thirdEntry");
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.addAll(0, src));
            Assertions.assertEquals(3,list.size());
            Assertions.assertEquals("firstEntry", list.get(0));
            Assertions.assertEquals("secondEntry", list.get(1));
            Assertions.assertEquals("thirdEntry", list.get(2));
            Assertions.assertTrue(list.addAll(1, src));
            Assertions.assertEquals(6, list.size());
            Assertions.assertEquals("firstEntry", list.get(0));
            Assertions.assertEquals("firstEntry", list.get(1));
            Assertions.assertEquals("secondEntry", list.get(2));
            Assertions.assertEquals("thirdEntry", list.get(3));
            Assertions.assertEquals("secondEntry", list.get(4));
            Assertions.assertEquals("thirdEntry", list.get(5));
            Assertions.assertTrue(list.addAll(6, src));
            Assertions.assertEquals(9, list.size());
            Assertions.assertEquals("firstEntry", list.get(0));
            Assertions.assertEquals("firstEntry", list.get(1));
            Assertions.assertEquals("secondEntry", list.get(2));
            Assertions.assertEquals("thirdEntry", list.get(3));
            Assertions.assertEquals("secondEntry", list.get(4));
            Assertions.assertEquals("thirdEntry", list.get(5));
            Assertions.assertEquals("firstEntry", list.get(6));
            Assertions.assertEquals("secondEntry", list.get(7));
            Assertions.assertEquals("thirdEntry", list.get(8));
        }

        @Test
        public void testRemoveIndexDouble(){
            SinglyLinkedList<Double> list = new SinglyLinkedList<>();
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.add(0.0));
            Assertions.assertEquals(1,list.size());
            Assertions.assertTrue(list.add(1.1));
            Assertions.assertEquals(2,list.size());
            Assertions.assertTrue(list.add(2.2));
            Assertions.assertEquals(3,list.size());
            Assertions.assertTrue(list.add(3.3));
            Assertions.assertEquals(4,list.size());
            Assertions.assertDoesNotThrow( () -> list.remove(0));
            Assertions.assertEquals(-1, list.indexOf(0.0));
            Assertions.assertEquals(3,list.size());
            Assertions.assertDoesNotThrow( () -> list.remove(1));
            Assertions.assertEquals(-1, list.indexOf(2.2));
            Assertions.assertEquals(2,list.size());
            Assertions.assertDoesNotThrow( () -> list.remove(1));
            Assertions.assertEquals(-1, list.indexOf(3.3));
            Assertions.assertEquals(1,list.size());
            Assertions.assertEquals(1.1, list.get(0));
        }

        @Test
        public void testRemoveElementDouble(){
            SinglyLinkedList<Double> list = new SinglyLinkedList<>();
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.add(0.0));
            Assertions.assertEquals(1,list.size());
            Assertions.assertTrue(list.add(1.1));
            Assertions.assertEquals(2,list.size());
            Assertions.assertTrue(list.add(2.2));
            Assertions.assertEquals(3,list.size());
            Assertions.assertTrue(list.remove(0.0));
            Assertions.assertEquals(-1, list.indexOf(0.0));
            Assertions.assertEquals(2,list.size());
            Assertions.assertTrue(list.remove(2.2));
            Assertions.assertEquals(-1, list.indexOf(2.2));
            Assertions.assertEquals(1,list.size());
            Assertions.assertEquals(1.1, list.get(0));
        }

        @Test
        public void testContainsIntegers(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.add(0));
            Assertions.assertEquals(1,list.size());
            Assertions.assertTrue(list.add(1));
            Assertions.assertEquals(2,list.size());
            Assertions.assertTrue(list.contains(0));
            Assertions.assertTrue(list.contains(1));
            Assertions.assertFalse(list.contains(3));
            Assertions.assertFalse(list.contains(-1));
        }

        @Test
        public void testIterator(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertInstanceOf(Iterator.class, list.iterator());
        }

        @Test
        public void testListIterator(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Assertions.assertInstanceOf(ListIterator.class, list.listIterator());
        }

        @Test
        public void testToArray(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Integer[] array = {1,2,3,4,5,6,7};
            list.addAll(Arrays.asList(array));
            Assertions.assertArrayEquals(array, list.toArray());
        }


        @Test
        public void testToArrayT(){
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            Integer[] srcArray = {1,2,3,4,5,6,7};
            Integer[] srcArrayBig = new Integer[srcArray.length+5];
            for(Integer i=1; i <= 7; i++){
                srcArrayBig[i-1]=i;
            }
            for(Integer i=7; i < srcArrayBig.length; i++){
                srcArrayBig[i]=null;
            }
            Integer[] tarraySmall = new Integer[0];
            Integer[] tarrayFit = new Integer[srcArray.length];
            Integer[] tarrayBig = new Integer[srcArrayBig.length];
            list.addAll(Arrays.asList(srcArray));
            Assertions.assertArrayEquals(srcArray, list.toArray(tarraySmall));
            Assertions.assertArrayEquals(srcArray, list.toArray(tarrayFit));
            Assertions.assertArrayEquals(srcArrayBig, list.toArray(tarrayBig));
        }

        @Test
        public void testRemoveAllCharacter(){
            SinglyLinkedList<Character> list = new SinglyLinkedList<>();
            ArrayList<Character> src = new ArrayList<>(3);
            src.add('1');
            src.add('2');
            src.add('3');
            Assertions.assertEquals(0,list.size());
            Assertions.assertTrue(list.add('1'));
            Assertions.assertEquals(1,list.size());
            Assertions.assertTrue(list.add('2'));
            Assertions.assertEquals(2,list.size());
            Assertions.assertTrue(list.add('3'));
            Assertions.assertEquals(3,list.size());
            Assertions.assertTrue(list.add('4'));
            Assertions.assertEquals(4,list.size());
            Assertions.assertTrue(list.add('2'));
            Assertions.assertEquals(5,list.size());
            Assertions.assertTrue(list.add('4'));
            Assertions.assertEquals(6,list.size());
            Assertions.assertTrue(list.removeAll(src));
            Assertions.assertEquals(2,list.size());
        }

        @Test
        public void testContainsAllStrings(){
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
            ArrayList<String> src = new ArrayList<>(3);
            src.add("firstEntry");
            src.add("secondEntry");
            src.add("thirdEntry");
            Assertions.assertEquals(0, list.size());
            Assertions.assertTrue(list.add("firstEntry"));
            Assertions.assertEquals(1, list.size());
            Assertions.assertTrue(list.add("secondEntry"));
            Assertions.assertEquals(2, list.size());
            Assertions.assertTrue(list.add("firstEntry"));
            Assertions.assertEquals(3, list.size());
            Assertions.assertTrue(list.add("thirdEntry"));
            Assertions.assertEquals(4, list.size());
            Assertions.assertTrue(list.containsAll(src));
        }
    }
    
    @Test
    public void testClear(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0,list.size());
        Assertions.assertTrue(list.add("firstEntry"));
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.add("secondEntry"));
        Assertions.assertEquals(2,list.size());
        Assertions.assertTrue(list.add("firstEntry"));
        Assertions.assertEquals(3,list.size());
        Assertions.assertTrue(list.add("thirdEntry"));
        Assertions.assertEquals(4,list.size());
        Assertions.assertDoesNotThrow(() -> list.clear());
        Assertions.assertFalse(list.contains("firstEntry"));
        Assertions.assertFalse(list.contains("secondEntry"));
        Assertions.assertFalse(list.contains("thirdEntry"));
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testSetCharacter(){
        SinglyLinkedList<Character> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0,list.size());
        Assertions.assertTrue(list.add('1'));
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.add('2'));
        Assertions.assertEquals(2,list.size());
        Assertions.assertTrue(list.add('3'));
        Assertions.assertEquals(3,list.size());
        Assertions.assertTrue(list.add('4'));
        Assertions.assertEquals(4,list.size());
        Assertions.assertTrue(list.add('2'));
        Assertions.assertEquals(5,list.size());
        Assertions.assertTrue(list.add('4'));
        Assertions.assertEquals(6,list.size());
        Assertions.assertEquals('1', list.set(0, '#'));
        Assertions.assertEquals('#', list.get(0));
        Assertions.assertEquals('4', list.set(5, '#'));
        Assertions.assertEquals('#', list.get(5));
        Assertions.assertEquals('4', list.set(3, '#'));
        Assertions.assertEquals('#', list.get(3));
        Assertions.assertEquals(6,list.size());
    }

    @Test
    public void testIndexOfIntegers(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0,list.size());
        Assertions.assertTrue(list.add(0));
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(2,list.size());
        Assertions.assertEquals(0, list.indexOf(0));
        Assertions.assertEquals(1, list.indexOf(1));
        Assertions.assertEquals(-1, list.indexOf(2));
        Assertions.assertEquals(-1, list.indexOf(-1));
    }

    @Test
    public void testLastIndexOfIntegers(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0,list.size());
        Assertions.assertTrue(list.add(0));
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(2,list.size());
        Assertions.assertTrue(list.add(1));
        Assertions.assertEquals(3,list.size());
        Assertions.assertTrue(list.add(0));
        Assertions.assertEquals(4,list.size());
        Assertions.assertEquals(0, list.indexOf(0));
        Assertions.assertEquals(1, list.indexOf(1));
        Assertions.assertEquals(3, list.lastIndexOf(0));
        Assertions.assertEquals(2, list.lastIndexOf(1));
    }

    @Test
    public void testRemoveIndexDouble(){
        class TestDummy implements Comparable{
            int val = 0;
            TestDummy(){}
            TestDummy(int val){this.val = val;}

            @Override
            public int compareTo(Object o){
                if (o instanceof TestDummy){
                    TestDummy other = (TestDummy) o;
                    return Integer.compare(this.val, other.val);
                }
                return 0;
            }
        }

        SinglyLinkedList<TestDummy> list = new SinglyLinkedList<>();
        Assertions.assertEquals(0,list.size());
        Assertions.assertTrue(list.add(new TestDummy()));
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.add(new TestDummy(1)));
        Assertions.assertEquals(2,list.size());
        Assertions.assertTrue(list.add(new TestDummy(2)));
        Assertions.assertEquals(3,list.size());
        Assertions.assertTrue(list.add(new TestDummy(3)));
        Assertions.assertEquals(4,list.size());
        List<TestDummy> sublist = list.subList(1, 3);
        Assertions.assertEquals(2, sublist.size());
        Assertions.assertEquals(1, sublist.get(0).val);
        Assertions.assertEquals(2, sublist.get(1).val);
        Assertions.assertEquals(1, sublist.set(0, new TestDummy(9)).val);
        Assertions.assertEquals(1, list.get(1).val);
        Assertions.assertDoesNotThrow(() -> {TestDummy testDummy = sublist.get(1);
                                             testDummy.val = 7;});
        Assertions.assertEquals(7, list.get(2).val);
    }

    @Test
    public void testRetainAll(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.retainAll(null));
    }

    /**
     * these tests verify that this list behaves as the java util lists.
     */
    @Nested
    class InterfaceTests{
        private SinglyLinkedList<Integer> singlyLinkedList;
        private LinkedList<Integer> linkedList;

        @BeforeEach
        public void init(){
            singlyLinkedList = new SinglyLinkedList<>();
            linkedList = new LinkedList<>();
        }

        @Test
        public void testAddSizeGet(){
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.get(0), singlyLinkedList.get(0));
            Assertions.assertEquals(linkedList.get(1), singlyLinkedList.get(1));
            Assertions.assertEquals(linkedList.get(2), singlyLinkedList.get(2));
        }

        @Test
        public void testIsEmpty(){
            Assertions.assertEquals(linkedList.isEmpty(), singlyLinkedList.isEmpty());
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.isEmpty(), singlyLinkedList.isEmpty());
        }

        @Test
        public void testAddIndexToArray(){
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            linkedList.add(0,2);
            singlyLinkedList.add(0,2);
            Assertions.assertEquals(linkedList.get(0), singlyLinkedList.get(0));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            linkedList.add(2,2);
            singlyLinkedList.add(2,2);
            Assertions.assertEquals(linkedList.get(0), singlyLinkedList.get(0));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            linkedList.add(1,3);
            singlyLinkedList.add(1,3);
            Assertions.assertEquals(linkedList.get(0), singlyLinkedList.get(0));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertArrayEquals(linkedList.toArray(new Integer[0]), singlyLinkedList.toArray(new Integer[0]));
        }

        @Test
        public void testAddAlls(){
            ArrayList<Integer> src = new ArrayList<>(4);
            src.add(1); src.add(2); src.add(3); src.add(4);
            Assertions.assertEquals(linkedList.addAll(src), singlyLinkedList.addAll(src));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.addAll(2, src), singlyLinkedList.addAll(2, src));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
        }

        @Test
        public void testContains(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.contains(1), singlyLinkedList.contains(1));
            Assertions.assertEquals(linkedList.contains(2), singlyLinkedList.contains(2));
        }

        @Test
        public void testRemoves(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.remove(0), singlyLinkedList.remove(0));
            Assertions.assertEquals(linkedList.remove(2), singlyLinkedList.remove(2));
            Assertions.assertEquals(linkedList.remove(3), singlyLinkedList.remove(3));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.remove((Integer) 1), singlyLinkedList.remove((Integer) 1));
            Assertions.assertEquals(linkedList.remove((Integer) 2), singlyLinkedList.remove((Integer) 2));
            Assertions.assertEquals(linkedList.remove((Integer) 3), singlyLinkedList.remove((Integer) 3));
            Assertions.assertEquals(linkedList.remove((Integer) 3), singlyLinkedList.remove((Integer) 3));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
        }

        @Test
        public void testRemoveAll(){
            ArrayList<Integer> src = new ArrayList<>(4);
            src.add(1); src.add(2); src.add(3); src.add(4);
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.removeAll(src), singlyLinkedList.removeAll(src));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.add(5), singlyLinkedList.add(5));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.removeAll(src), singlyLinkedList.removeAll(src));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
        }

        @Test
        public void testContainsAll(){
            ArrayList<Integer> src = new ArrayList<>(4);
            src.add(1); src.add(2); src.add(3); src.add(4);
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.containsAll(src), singlyLinkedList.containsAll(src));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.containsAll(src), singlyLinkedList.containsAll(src));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.add(5), singlyLinkedList.add(5));
            Assertions.assertEquals(linkedList.add(6), singlyLinkedList.add(6));
            Assertions.assertEquals(linkedList.containsAll(src), singlyLinkedList.containsAll(src));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
        }

        @Test
        public void testClear(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            linkedList.clear();
            singlyLinkedList.clear();
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
        }

        @Test
        public void testSet(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedList.size(), singlyLinkedList.size());
            Assertions.assertEquals(linkedList.set(2, 4), singlyLinkedList.set(2, 4));
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
        }

        @Test
        public void testIndexOf(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.indexOf(1), singlyLinkedList.indexOf(1));
            Assertions.assertEquals(linkedList.indexOf(2), singlyLinkedList.indexOf(2));
            Assertions.assertEquals(linkedList.indexOf(3), singlyLinkedList.indexOf(3));
            Assertions.assertEquals(linkedList.indexOf(4), singlyLinkedList.indexOf(4));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.indexOf(1), singlyLinkedList.indexOf(1));
            Assertions.assertEquals(linkedList.indexOf(2), singlyLinkedList.indexOf(2));
            Assertions.assertEquals(linkedList.indexOf(3), singlyLinkedList.indexOf(3));
        }

        @Test
        public void testLastIndexOf(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.lastIndexOf(1), singlyLinkedList.lastIndexOf(1));
            Assertions.assertEquals(linkedList.lastIndexOf(2), singlyLinkedList.lastIndexOf(2));
            Assertions.assertEquals(linkedList.lastIndexOf(3), singlyLinkedList.lastIndexOf(3));
            Assertions.assertEquals(linkedList.lastIndexOf(4), singlyLinkedList.lastIndexOf(4));
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.indexOf(1), singlyLinkedList.indexOf(1));
            Assertions.assertEquals(linkedList.indexOf(2), singlyLinkedList.indexOf(2));
            Assertions.assertEquals(linkedList.indexOf(3), singlyLinkedList.indexOf(3));
            Assertions.assertEquals(linkedList.lastIndexOf(1), singlyLinkedList.lastIndexOf(1));
            Assertions.assertEquals(linkedList.lastIndexOf(2), singlyLinkedList.lastIndexOf(2));
            Assertions.assertEquals(linkedList.lastIndexOf(3), singlyLinkedList.lastIndexOf(3));
        }

        @Test
        public void testSubList(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.add(5), singlyLinkedList.add(5));
            Assertions.assertEquals(linkedList.add(6), singlyLinkedList.add(6));
            Assertions.assertArrayEquals(linkedList.subList(0,5).toArray(), singlyLinkedList.subList(0,5).toArray());
            Assertions.assertArrayEquals(linkedList.subList(0,2).toArray(), singlyLinkedList.subList(0,2).toArray());
            Assertions.assertArrayEquals(linkedList.subList(2,5).toArray(), singlyLinkedList.subList(2,5).toArray());
            Assertions.assertArrayEquals(linkedList.subList(2,3).toArray(), singlyLinkedList.subList(2,3).toArray());
            Assertions.assertArrayEquals(linkedList.subList(3,3).toArray(), singlyLinkedList.subList(3,3).toArray());
        }


        @Test
        public void testIterator(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.add(5), singlyLinkedList.add(5));
            Assertions.assertEquals(linkedList.add(6), singlyLinkedList.add(6));
            Iterator<Integer> linkedListIterator = linkedList.iterator();
            Iterator<Integer> singlyLinkedListIterator = singlyLinkedList.iterator();
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertThrows(NoSuchElementException.class, () -> linkedListIterator.next());
            Assertions.assertThrows(NoSuchElementException.class, () -> singlyLinkedListIterator.next());
        }


        @Test
        public void testListIterator(){
            Assertions.assertEquals(linkedList.add(1), singlyLinkedList.add(1));
            Assertions.assertEquals(linkedList.add(2), singlyLinkedList.add(2));
            Assertions.assertEquals(linkedList.add(3), singlyLinkedList.add(3));
            Assertions.assertEquals(linkedList.add(4), singlyLinkedList.add(4));
            Assertions.assertEquals(linkedList.add(5), singlyLinkedList.add(5));
            Assertions.assertEquals(linkedList.add(6), singlyLinkedList.add(6));
            ListIterator<Integer> linkedListIterator = linkedList.listIterator();
            ListIterator<Integer> singlyLinkedListIterator = singlyLinkedList.listIterator();
            Assertions.assertEquals(linkedListIterator.hasPrevious(), singlyLinkedListIterator.hasPrevious());
            Assertions.assertThrows(NoSuchElementException.class, () -> linkedListIterator.previous());
            Assertions.assertThrows(NoSuchElementException.class, () -> singlyLinkedListIterator.previous());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.nextIndex(), singlyLinkedListIterator.nextIndex());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            linkedListIterator.remove();
            singlyLinkedListIterator.remove();
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.nextIndex(), singlyLinkedListIterator.nextIndex());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            Assertions.assertThrows(NoSuchElementException.class, () -> linkedListIterator.next());
            Assertions.assertThrows(NoSuchElementException.class, () -> singlyLinkedListIterator.next());
            Assertions.assertEquals(linkedListIterator.previous(), singlyLinkedListIterator.previous());
            Assertions.assertEquals(linkedListIterator.previousIndex(), singlyLinkedListIterator.previousIndex());
            Assertions.assertEquals(linkedListIterator.previous(), singlyLinkedListIterator.previous());
            Assertions.assertEquals(linkedListIterator.previous(), singlyLinkedListIterator.previous());
            Assertions.assertEquals(linkedListIterator.previous(), singlyLinkedListIterator.previous());
            linkedListIterator.add(7);
            singlyLinkedListIterator.add(7);
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
            linkedListIterator.add(9);
            singlyLinkedListIterator.add(9);
            Assertions.assertArrayEquals(linkedList.toArray(), singlyLinkedList.toArray());
            Assertions.assertEquals(linkedListIterator.previous(), singlyLinkedListIterator.previous());
            Assertions.assertEquals(linkedListIterator.hasNext(), singlyLinkedListIterator.hasNext());
            Assertions.assertEquals(linkedListIterator.nextIndex(), singlyLinkedListIterator.nextIndex());
            Assertions.assertEquals(linkedListIterator.next(), singlyLinkedListIterator.next());
        }
    }
}


