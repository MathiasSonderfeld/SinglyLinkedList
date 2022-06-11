import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SinglyLinkedList<Generic extends Comparable> implements Collection<Generic>{
    private SinglyLinkedNode start = null;
    private SinglyLinkedNode end = null;
    private int size = 0;

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public boolean contains(Object o){
        SinglyLinkedNode iterator = start;
        boolean found = false;
        while(!found && iterator != null){
            if(iterator.get().equals(o)){
                found = true;
            }
            else{
                iterator = iterator.getAfter();
            }
        }
        return found;
    }

    @Override
    public Iterator<Generic> iterator(){
        return null;
    }

    @Override
    public Object[] toArray(){
        Object[] ret = new Object[size];
        int counter = 0;
        SinglyLinkedNode iterator = start;
        while(iterator != null){
            ret[counter] = iterator.get();
            counter++;
            iterator = iterator.getAfter();
        }
        return ret;
    }

    @Override
    public <T> T[] toArray(T[] a){
        return null;
    }

    @Override
    public boolean remove(Object o){
        SinglyLinkedNode before = null;
        SinglyLinkedNode iterator = start;
        boolean removed = false;
        while(!removed && iterator != null){
            if(iterator.get().equals(o)){
                before.setAfter(iterator.getAfter());
                removed = true;
            }
            else{
                before=iterator;
                iterator = iterator.getAfter();
            }
        }
        if(removed) this.size--;
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c){
        boolean found = true;
        for(Object o : c){
            found &= this.contains(o);
        }
        return found;
    }

    @Override
    public boolean removeAll(Collection<?> c){
        boolean removed = true;
        for(Object o : c){
            removed &= this.remove(o);
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c){
        throw new UnsupportedOperationException("not implemented yet, sorry.");
    }

    @Override
    public void clear(){
        this.start = null;
        this.size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends Generic> c){
        boolean added = true;
        for(Generic o : c){
            added &= this.add(o);
        }
        return added;
    }

    @Override
    public boolean add(Generic e){
        boolean added = false;
        if(this.end == null){
            this.start = new SinglyLinkedNode<>(e);
            this.end = this.start;
            added = true;
        }
        else{
            SinglyLinkedNode<Generic> newNode = new SinglyLinkedNode<>(e);
            this.end.setAfter(newNode);
            this.end = newNode;
            added=true;
        }

        if(added) this.size++;
        return added;
    }
}

