package dynutils.linkedlist.sorted;
/**
 * <p>
 A LinkedIDListNode which contains a strong reference to it's value.</p>
 *
 * @author Dynisious 16/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of object to store in this StrongLinkedIDListNode.
 */
public class StrongLinkedIDListNode<ObjectType extends TaggedByID> extends LinkedIDListNode<ObjectType> {
    private ObjectType value; //The value stored in this StrongLinkedList.

    public StrongLinkedIDListNode(final ObjectType value) {
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

    @Override
    public long getValueID() {
        if (value != null) {
            return value.getID();
        }
        return -1;
    }

}
