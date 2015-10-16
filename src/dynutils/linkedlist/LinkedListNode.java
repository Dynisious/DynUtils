package dynutils.linkedlist;
/**
 * <p>
 * A LinkedListNode is an object with a single value and a reference to the
 * previous LinkedListNode and the next LinkedListNode.</p>
 *
 * @author Dynisious 17/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of objects stored in this LinkedListNode.
 * @param <ReturnType> The type of LinkedListNode to return from functions.
 */
public abstract class LinkedListNode<ObjectType, ReturnType extends LinkedListNode> {
    private ReturnType previousNode; //The previous node in the LinkedIDList.
    /**
     * @param previousNode The new value for previousNode.
     *
     * @return The previous value for previousNode.
     */
    public ReturnType setPreviousNode(final ReturnType previousNode) {
        final ReturnType val = this.previousNode;
        this.previousNode = previousNode;
        return val;
    }
    public <T extends ReturnType> T getPreviousNode() {
        return (T) previousNode;
    }
    private ReturnType nextNode; //The next node in the linkedIDList.
    /**
     * @param nextNode The new value for nextNode.
     *
     * @return The previous value for nextNode.
     */
    public ReturnType setNextNode(final ReturnType nextNode) {
        final ReturnType val = this.nextNode;
        this.nextNode = nextNode;
        return val;
    }
    public <T extends ReturnType> T getNextNode() {
        return (T) nextNode;
    }

    /**
     * @param <T> The type to cast the returned value to.
     *
     * @return The value contained in this LinkedIDListNode or null.
     */
    public abstract <T extends ObjectType> T getValue()
            throws ClassCastException;

    /**
     * <p>
     * Sets the value stored in this LinkedIDListNode.</p>
     *
     * @param <T>   The type for the return value.
     * @param value The new value to store in this LinkedIDListNode.
     *
     * @return The previous value or null.
     */
    public abstract <T extends ObjectType> T setValue(final ObjectType value)
            throws ClassCastException;

    /**
     * <p>
     * Inserts this LinkedIDListNode ahead of the passed node.</p>
     *
     * @param <T>  The return type of the LinkedIDListNode.
     * @param node The node to be ahead of.
     *
     * @return This LinkedIDListNode.
     */
    public <T extends ReturnType> T insertAhead(final ReturnType node) {
        previousNode = node;
        nextNode = (ReturnType) node.setNextNode(this);
        if (nextNode != null) {
            nextNode.setPreviousNode(this);
        }
        return (T) this;
    }

    /**
     * <p>
     * Removes this LinkedIDListNode from the LinkedIDList it's currently in.
     *
     * @param <T> The return type of the LinkedIDListNode.
     *
     * @return This LinkedIDListNode.
     */
    public <T extends ReturnType> T removeFromList() {
        if (nextNode != null) {
            nextNode.setPreviousNode(setPreviousNode(null));
        }
        if (previousNode != null) {
            previousNode.setNextNode(setNextNode(null));
        }
        return (T) this;
    }

}
