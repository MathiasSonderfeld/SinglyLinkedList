/**
 * Simple Node class for the SinglyLinkedList
 * @author Mathias Sonderfeld
 * @version 0.1-SNAPSHOT
 * @param <Generic> inherits the datatype from SinglyLinkedList
 */
class SinglyLinkedNode<Generic>{
    private SinglyLinkedNode after;
    private Generic content;

    public SinglyLinkedNode(){
        after=null;
        content=null;
    }
    public SinglyLinkedNode(Generic content){
        this();
        this.content=content;
    }

    public SinglyLinkedNode<Generic> getAfter(){
        return after;
    }

    public void setAfter(SinglyLinkedNode<Generic> node){
        this.after = node;
    }

    public Generic get(){
        return content;
    }

    public void set(Generic content){
        this.content = content;
    }

}
