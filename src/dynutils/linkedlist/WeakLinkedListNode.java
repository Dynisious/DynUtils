package dynutils.linkedlist;

import java.lang.ref.WeakReference;
/**
 * <p>
 * A LinkedIDListNode which contains a weak reference to it's value.</p>
 *
 * @author Dynisious 16/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of object to store in this StrongLinkedListNode.
 */
public class WeakLinkedListNode<ObjectType>
        extends LinkedListNode<ObjectType, WeakLinkedListNode<ObjectType>> {
    private WeakReference<ObjectType> value; //The value stored in this WeakLinkedList.

    public WeakLinkedListNode(final ObjectType value) {
        this.value = new WeakReference<>(value);
    }

    @Override
    public <T extends ObjectType> T getValue() throws ClassCastException {
        return (T) value.get();
    }

    @Override
    public <T extends ObjectType> T setValue(final ObjectType value)
            throws ClassCastException {
        final T val = (T) this.value.get();
        this.value = new WeakReference(value);
        return val;
    }

}
