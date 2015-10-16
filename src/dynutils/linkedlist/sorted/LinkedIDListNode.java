package dynutils.linkedlist.sorted;

import dynutils.linkedlist.LinkedListNode;
import java.lang.ref.WeakReference;
/**
 * <p>
 * LinkedIDListNodes extend the function of LinkedListNodes to use the
 * TaggedByID interface.</p>
 *
 * @author Dynisious 16/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of object which is stored in this
 *                     LinkedIDListNode.
 */
public abstract class LinkedIDListNode<ObjectType extends TaggedByID>
        extends LinkedListNode<ObjectType, LinkedIDListNode<ObjectType>> {
    private final Object listObjectLock = new Object();
    private WeakReference<LinkedIDList<ObjectType>> listObject; //The LinkedIDList this is attached to.
    public final LinkedIDList<ObjectType> getListObject() {
        synchronized (listObjectLock) {
            if (listObject != null) {
                return listObject.get();
            }
        }
        return null;
    }
    public void setListObject(final LinkedIDList<ObjectType> listObject) {
        synchronized (listObjectLock) {
            this.listObject = new WeakReference<>(listObject);
        }
    }

    /**
     * @return The ID of the value in this LinkedIDListNode or -1.
     */
    public abstract long getValueID();

    /**
     * <p>
     * Inserts this LinkedIDListNode ahead of the passed node.</p>
     *
     * @param <T>  The return type of the LinkedIDListNode.
     * @param node The node to be ahead of.
     *
     * @return This LinkedIDListNode.
     */
    @Override
    public <T extends LinkedIDListNode<ObjectType>> T insertAhead(
            final LinkedIDListNode<ObjectType> node) {
        synchronized (listObjectLock) {
            listObject = new WeakReference<>(node.getListObject());
        }
        if (listObject.get() == null) { //This node is independant of a LinkedIDList.
            super.insertAhead(node);
        } else {
            listObject.get().add(this);
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
    @Override
    public <T extends LinkedIDListNode<ObjectType>> T removeFromList() {
        synchronized (listObjectLock) {
            if (listObject == null) { //This LinkedIDListNode is independent of a LinkedIDList.
                super.removeFromList();
            } else {
                listObject.get().remove(getValueID());
            }
        }
        return (T) this;
    }

}
