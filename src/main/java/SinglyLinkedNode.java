public class SinglyLinkedNode<Generic>{
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

    public SinglyLinkedNode getAfter(){
        return after;
    }

    public void setAfter(SinglyLinkedNode node){
        this.after = node;
    }

    public Generic get(){
        return content;
    }

}
