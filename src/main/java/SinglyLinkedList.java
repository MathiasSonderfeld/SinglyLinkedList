import java.lang.reflect.Array;
import java.util.*;

/**
 * A Singly-linked implementation of List-Interface.
 * Note that this implementation is not synchronized.
 * @author Mathias Sonderfeld
 * @version 0.1-SNAPSHOT
 * @param <Generic> The datatype to be stored in this list
 */
public class SinglyLinkedList<Generic extends Comparable> implements List<Generic>{
    protected SinglyLinkedNode<Generic> start = null;
    protected SinglyLinkedNode<Generic> end = null;
    protected int size = 0;

    /**
     * retrieves the node object at the given index
     * @param index the index for the node object. Is checked for boundries.
     * @return the node object at the given index for further processing
     */
    protected SinglyLinkedNode<Generic> getNodeAtIndex(int index){
        if(index < 0) throw new IndexOutOfBoundsException("index must not be negative.");
        if(index >= size){
            throw new IndexOutOfBoundsException("index is larger than size of the list");
        }
        if(index == 0) return start;
        if(index == size-1) return end;
        SinglyLinkedNode<Generic> iterator = start;
        for(int i=0; i<index; i++){
            iterator = iterator.getAfter();
        }
        return iterator;
    }

    /**
     * @return the number of Elements in the list.
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * @return whether there are any elements in the list.
     */
    @Override
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Add an Object to the end of the List.
     * @param element the new object, that gets stored in the list
     * @return true if this list changed as a result of the call.
     */
    @Override
    public boolean add(Generic element){
        if(this.end == null){
            this.start = new SinglyLinkedNode<>(element);
            this.end = this.start;
        }
        else{
            SinglyLinkedNode<Generic> newNode = new SinglyLinkedNode<>(element);
            this.end.setAfter(newNode);
            this.end = newNode;
        }
        this.size++;
        return true;
    }

    /**
     * Add an Object to the List at the index given. Shifts every following values further.
     * Therefore their indices increase by one.
     * @param index the position in the list, where the new object is placed
     * @param element the new object, that gets stored in the list
     */
    @Override
    public void add(int index, Generic element){
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(index-1);
        SinglyLinkedNode<Generic> next = iterator.getAfter();
        SinglyLinkedNode<Generic> newNode = new SinglyLinkedNode<>(element);
        iterator.setAfter(newNode);
        newNode.setAfter(next);
    }

    /**
     * Add several objects to the end of the list in the given order.
     * @param collection collection containing elements to be added to the list
     * @return whether the addition of all elements was successful or not.
     * If returned false, its unclear how many objects have been stored.
     *      Or in other words: true if this list changed as a result of the call.
     */
    @Override
    public boolean addAll(Collection<? extends Generic> collection){
        boolean added = true;
        for(Generic o : collection){
            added &= this.add(o);
        }
        return added;
    }

    /**
     *Add several objects to the list at the given index in the given order.
     * @param index index at which to insert the first element
     * @param collection collection containing elements to be added to the list
     * @return whether the addition of all elements was successful or not.
     *      If returned false, its unclear how many objects have been stored.
     *      Or in other words: true if this list changed as a result of the call.
     */
    @Override
    public boolean addAll(int index, Collection<? extends Generic> collection){
        if(index == this.size){
            return addAll(collection);
        }
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(index-1);
        SinglyLinkedNode<Generic> next = iterator.getAfter();
        SinglyLinkedNode<Generic> newNode;
        for(Generic gen : collection){
            newNode = new SinglyLinkedNode<>(gen);
            iterator.setAfter(newNode);
            iterator = newNode;
        }
        iterator.setAfter(next);
        return true;
    }

    /**
     * Returns true, if this list contains the given element at least once.
     * The comparison is made with Object.equals.
     * @param o element whose presence in this list is to be tested
     * @return whether an equal object is present in this list at least once.
     */
    @Override
    public boolean contains(Object o){
        return this.indexOf(o)!=-1;
    }

    /**
     * Returns an iterator over the elements in this list in the stored order.
     * @return an iterator over the elements in this list in the stored order.
     */
    @Override
    public Iterator<Generic> iterator(){
        return new SinglyLinkedListIterator<Generic>(start);
    }

    /**
     * Returns an array of all objects stored in this list in correct order.
     * The returned array is safe, changes to the array do not affect the list.
     * Changes to the object in that array will affect this list though.
     * It neither possible nor wanted for that behaviour to change.
     * @return an Object array with all objects in this list.
     */
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

    /**
     * Returns an array containing all of the elements in this list in that order;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
     *
     * If this collection fits in the specified array with room to spare (i.e., the array has more elements than this collection),
     * the element in the array immediately following the end of the collection is set to null.
     *
     * @param array the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array cantaining all elements of this list.
     * @param <T> the element class for the new array. May result in an IllegalCastException if the elements can not be cast into the given class.
     */
    @Override
    public <T> T[] toArray(T[] array){
        int counter = 0;
        if(array.length < this.size){
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        }
        SinglyLinkedNode<Generic> iterator = start;
        while(iterator != null){
            array[counter] = (T) iterator.get();
            counter++;
            iterator = iterator.getAfter();
        }
        if(array.length > size){
            for (int i = size-1; i < array.length; i++){
                array[i] = null;
            }
        }
        return array;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
      * @param index the index of the element to be removed
     * @return the element that was removed from the list.
     */
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

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If this list does not contain the element, it is unchanged.
     * @param toRemove element to be removed from this list, if present
     * @return true if the list contained the specified element
     */
    @Override
    public boolean remove(Object toRemove){
        SinglyLinkedNode<Generic> before = null;
        SinglyLinkedNode<Generic> iterator = start;
        boolean removed = false;
        while(!removed && iterator != null){
            if(iterator.get().equals(toRemove)){
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

    /**
     * Removes all occurances of the elements that are contained in the given collection from this list.
     * @param c collection containing elements to be removed
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c){
        int index;
        boolean changed = false;
        for(Object o : c){
            while((index = indexOf(o)) > -1){
                this.remove(index);
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Returns true if this list contains all elements of the specified collection.
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> c){
        boolean found = true;
        for(Object o : c){
            found &= this.contains(o);
        }
        return found;
    }

    /**
     * This is not implemented yet!
     * Throws UnsupportedOperationException.
     */
    @Override
    public boolean retainAll(Collection<?> c){
        throw new UnsupportedOperationException("not implemented yet, sorry.");
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear(){
        this.start = null;
        this.size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element stored at the given index
     */
    @Override
    public Generic get(int index){
        return getNodeAtIndex(index).get();
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the old element, that was stored at the index before
     */
    @Override
    public Generic set(int index, Generic element){
        SinglyLinkedNode<Generic> node = getNodeAtIndex(index);
        Generic ret = node.get();
        node.set(element);
        return ret;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param toFind element to search for
     * @return the index of the first occurrence of the specified element in this list
     */
    @Override
    public int indexOf(Object toFind){
        SinglyLinkedNode<Generic> iterator = start;
        for(int i=0; i < size; i++){
            if(iterator.get().equals(toFind)){
                return i;
            }
            iterator = iterator.getAfter();
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param toFind element to search for
     * @return the index of the last occurrence of -1 if the element is not in this list
     */
    @Override
    public int lastIndexOf(Object toFind){
        SinglyLinkedNode<Generic> iterator = start;
        int lastIndex = -1;
        for(int i=0; i < size; i++){
            if(iterator.get().equals(toFind)){
                lastIndex= i;
            }
            iterator = iterator.getAfter();
        }
        return lastIndex;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    @Override
    public ListIterator<Generic> listIterator(){
        return new SllListIterator<Generic>(this);
    }

    /**
     * eturns a list iterator over the elements in this list (in proper sequence),
     * starting at the specified position in the list.
     * The specified index indicates the first element that would be returned by an initial call to next.
     * An initial call to previous would return the element with the specified index minus one.
     * @param index index of the first element to be returned from the
     *        list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper sequence),
     * starting at the specified position in the list
     */
    @Override
    public ListIterator<Generic> listIterator(int index){
        return new SllListIterator<Generic>(this, index);
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.)
     * The returned list is backed by this list,
     * so non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported by this list.
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     */
    @Override
    public List<Generic> subList(int fromIndex, int toIndex){
        SinglyLinkedList<Generic> sublist = new SinglyLinkedList<>();
        SinglyLinkedNode<Generic> iterator = getNodeAtIndex(fromIndex);
        for (int i=fromIndex; i<toIndex; i++){
            sublist.add(iterator.get());
            iterator = iterator.getAfter();
        }
        return  sublist;
    }
}

/*
these are my first iterators so there sure is room for improvement.
At the moment Im really unsure if the implement behaviour is consistent with the java list iterators.
But they are better than returning null. :)
 */
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

