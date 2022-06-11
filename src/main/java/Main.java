import java.util.LinkedList;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        System.out.println("Init");
        LinkedList<Integer> javaList = new LinkedList<>();
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        Random random = new Random();
        int size = 1000000;
        Integer[] src = new Integer[size];
        System.out.println("fill array");
        for(int i=0; i<size; i++){
            src[i] = random.nextInt();
        }
        System.out.println("start benchmark");
        long start = System.currentTimeMillis();
        for(int i=0; i<size; i++){
            javaList.add(src[i]);
        }
        long mid = System.currentTimeMillis();
        for(int i=0; i<size; i++){
            singlyLinkedList.add(src[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("Java List: " + (mid-start));
        System.out.println("My List: " + (end-mid));
    }

}
