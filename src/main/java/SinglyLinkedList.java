import java.util.*;

public class SinglyLinkedList<Generic extends Comparable> implements List<Generic>{
    protected SinglyLinkedNode<Generic> start = null;
    protected SinglyLinkedNode<Generic> end = null;
    protected int size = 0;

    protected SinglyLinkedNode<Generic> getNodeAtIndex(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) return start;
        if(index == size-1) return end;
        SinglyLinkedNode<Generic> iterator = start;
        for(int i=0; i<index; i++){
            iterator = iterator.getAfter();
        }
        return iterator;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public boolean add(Generic e){
        if(this.end == null){
            this.start = new SinglyLinkedNode<>(e);
            this.end = this.start;
        }
        else{
            SinglyLinkedNode<Generic> newNode = new SinglyLinkedNode<>(e);
            this.end.setAfter(newNode);
            this.end = newNode;
        }
        this.size++;
        return true;
    }

    @Override
    public void add(int index, Generic element){
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(index);
        SinglyLinkedNode<Generic> next = iterator.getAfter();
        SinglyLinkedNode<Generic> newNode = new SinglyLinkedNode<>(element);
        iterator.setAfter(newNode);
        newNode.setAfter(next);
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
    public boolean addAll(int index, Collection<? extends Generic> c){
        if(index == this.size-1){
            return addAll(c);
        }
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(index);
        SinglyLinkedNode<Generic> next = iterator.getAfter();
        SinglyLinkedNode<Generic> newNode;
        for(Generic gen : c){
            newNode = new SinglyLinkedNode<>(gen);
            iterator.setAfter(newNode);
            iterator = newNode;
        }
        iterator.setAfter(next);
        return true;
    }

    @Override
    public boolean contains(Object o){
        return this.indexOf(o)!=-1;
    }

    @Override
    public Iterator<Generic> iterator(){
        return new SinglyLinkedListIterator<Generic>(start);
    }

    @Override
    public Object[] toArray(){
        Object[] ret = new Object[size];
        int counter = 0;
        SinglyLinkedNode<Generic> iterator = start;
        while(iterator != null){
            ret[counter] = iterator.get();
            counter++;
            iterator = iterator.getAfter();
        }
        return ret;
    }

    @Override
    public <T> T[] toArray(T[] array){
        int counter = 0;
        SinglyLinkedNode<Generic> iterator = start;
        while(iterator != null){
            array[counter] = (T) iterator.get();
            counter++;
            iterator = iterator.getAfter();
        }
        return array;
    }

    @Override
    public Generic remove(int index){
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(index);
        SinglyLinkedNode<Generic> toRemove = iterator.getAfter();
        SinglyLinkedNode<Generic> afterRemove = null;
        if(toRemove != null)
            afterRemove = toRemove.getAfter();
        iterator.setAfter(afterRemove);
        return toRemove.get();
    }

    @Override
    public boolean remove(Object o){
        SinglyLinkedNode<Generic> before = null;
        SinglyLinkedNode<Generic> iterator = start;
        boolean removed = false;
        while(!removed && iterator != null){
            if(iterator.get().equals(o)){
                if(before == null){
                    this.start = iterator.getAfter();
                }
                else {
                    before.setAfter(iterator.getAfter());
                }
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
    public boolean removeAll(Collection<?> c){
        boolean removed = true;
        for(Object o : c){
            removed &= this.remove(o);
        }
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
    public boolean retainAll(Collection<?> c){
        throw new UnsupportedOperationException("not implemented yet, sorry.");
    }

    @Override
    public void clear(){
        this.start = null;
        this.size = 0;
    }

    @Override
    public Generic get(int index){
        return getNodeAtIndex(index).get();
    }

    @Override
    public Generic set(int index, Generic element){
        SinglyLinkedNode<Generic> node = getNodeAtIndex(index);
        Generic ret = node.get();
        node.set(element);
        return ret;
    }

    @Override
    public int indexOf(Object o){
        SinglyLinkedNode<Generic> iterator = start;
        for(int i=0; i < size; i++){
            if(iterator.get().equals(o)){
                return i;
            }
            iterator = iterator.getAfter();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o){
        SinglyLinkedNode<Generic> iterator = start;
        int lastIndex = -1;
        for(int i=0; i < size; i++){
            if(iterator.get().equals(o)){
                lastIndex= i;
            }
            iterator = iterator.getAfter();
        }
        return lastIndex;
    }

    @Override
    public ListIterator<Generic> listIterator(){
        return new SllListIterator<Generic>(this);
    }

    @Override
    public ListIterator<Generic> listIterator(int index){
        return new SllListIterator<Generic>(this, index);
    }

    @Override
    public List<Generic> subList(int fromIndex, int toIndex){
        SinglyLinkedList<Generic> sublist = new SinglyLinkedList<>();
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(fromIndex);
        for (int i=fromIndex; i<=toIndex; i++){
            sublist.add(iterator.get());
            iterator = iterator.getAfter();
        }
        return  sublist;
    }
}

class SinglyLinkedListIterator<Generic> implements Iterator{
    SinglyLinkedNode<Generic> node;

    SinglyLinkedListIterator(SinglyLinkedNode<Generic> start){
        this.node = start;
    }

    @Override
    public boolean hasNext(){
        return node.getAfter() != null;
    }

    @Override
    public Generic next(){
        node = node.getAfter();
        return node.get();
    }
}

class SllListIterator<Generic extends Comparable> implements ListIterator{
    private SinglyLinkedList<Generic> list;
    private int index = 0;
    private SinglyLinkedNode<Generic> iteratorNode;

    SllListIterator(SinglyLinkedList<Generic> list){
        this.list = list;
        this.iteratorNode = this.list.start;
    }

    SllListIterator(SinglyLinkedList<Generic> list, int index){
        this.list = list;
        this.index = index;
        this.iteratorNode = this.list.getNodeAtIndex(index);
    }

    @Override
    public boolean hasNext(){
        return iteratorNode.getAfter() != null;
    }

    @Override
    public Generic next(){
        index++;
        iteratorNode = iteratorNode.getAfter();
        return iteratorNode.get();
    }

    @Override
    public boolean hasPrevious(){
        return index>0;
    }

    @Override
    public Generic previous(){
        if (index == 0) return null;
        iteratorNode = list.getNodeAtIndex(--index);
        return iteratorNode.get();
    }

    @Override
    public int nextIndex(){
        return index+1;
    }

    @Override
    public int previousIndex(){
        return index-1;
    }

    @Override
    public void remove(){
        this.list.remove(index);
    }

    @Override
    public void set(Object o){
        this.iteratorNode.set( (Generic) o);
    }

    @Override
    public void add(Object o){
        this.list.add(index+1, (Generic) o);
    }
}

