package dynutils.linkedlist.sorted;

import java.lang.ref.WeakReference;
/**
 * <p>
 * A LinkedIDListNode which contains a weak reference to it's value.</p>
 *
 * @author Dynisious 16/10/2015
 * @version 0.0.1
 * @param <ValueType> The type of object to store in this StrongLinkedListNode.
 */
public class WeakLinkedIDListNode<ValueType extends TaggedByID> extends LinkedIDListNode<ValueType> {
    private WeakReference<ValueType> value; //The value stored in this WeakLinkedList.

    public WeakLinkedIDListNode(final ValueType value) {
        this.value = new WeakReference<>(value);
    }

    @Override
    public <T extends ValueType> T getValue() throws ClassCastException {
        return (T) value.get();
    }

    @Override
    public <T extends ValueType> T setValue(final ValueType value)
            throws ClassCastException {
        final T val = (T) this.value.get();
        this.value = new WeakReference(value);
        return val;
    }

    @Override
    public long getValueID() {
        if (value.get() != null) {
            return value.get().getID();
        }
        return -1;
    }

}
