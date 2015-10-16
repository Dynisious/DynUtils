package dynutils.linkedlist;
/**
 * <p>
 * A LinkedIDListNode which contains a strong reference to it's value.</p>
 *
 * @author Dynisious 16/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of object to store in this StrongLinkedListNode.
 */
public class StrongLinkedListNode<ObjectType>
        extends LinkedListNode<ObjectType, StrongLinkedListNode<ObjectType>> {
    private ObjectType value; //The value stored in this StrongLinkedList.

    public StrongLinkedListNode(final ObjectType value) {
        this.value = value;
    }

    @Override
    public <T extends ObjectType> T getValue() throws ClassCastException {
        return (T) value;
    }

    @Override
    public <T extends ObjectType> T setValue(final ObjectType value)
            throws ClassCastException {
        final T val = (T) value;
        this.value = value;
        return val;
    }

}
