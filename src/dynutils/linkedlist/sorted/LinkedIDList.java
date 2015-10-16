package dynutils.linkedlist.sorted;

import dynutils.linkedlist.sorted.TaggedByID;
/**
 * <p>
 * EntityLists are lists specifically designed for storing, sorting and
 * searching for EntityObjects. They are always ordered by the EntityObjects
 * IDs and cannot contain duplicates.</p>
 * <p>
 * NOTE!!! All operations are thread safe.</p>
 *
 * @author Dynisious 06/10/2015
 * @version 0.0.1
 * @param <ObjectType> The type of TaggedByID contained in this LinkedIDList.
 */
public final class LinkedIDList<ObjectType extends TaggedByID> {
    private final Object opperationLock = new Object();
    private static final int blockSize = 15; //The size of a searchable block.
    private int containedElements = 0; //The number of elements in the linked list.
    private int quickReferencesCount = 0; //The number of elements in quickReferences.
    private LinkedIDListNode<ObjectType>[] quickReferences = new LinkedIDListNode[blockSize];
    //A collection of references to every blockSize LinkedIDListNode to be able to
    //quickly search through a larged linked list.

    /**
     * <p>
     * Creates a new LinkedIDList.</p>
     */
    public LinkedIDList() {
    }

    /**
     * <p>
     * Creates a new LinkedIDList based off the linked list the passed
     * LinkedIDListNode is a part of.</p>
     *
     * @param node The collection of initial values.
     */
    public LinkedIDList(LinkedIDListNode<ObjectType> node) {
        while (node.getPreviousNode() != null) { //Find the start of the linked list.
            node = node.getPreviousNode();
        }
        do { //Add every LinkedIDListNode in this linked list.
            add(node);
            node = node.getNextNode();
        } while (node != null);
    }

    /**
     * @param ID The ID to search for.
     *
     * @return The quick reference which is closest to the passed ID, preferably
     *         lower.
     */
    private int getQuickReferenceIndex(final long ID) {
        int low = 0;
        int high = quickReferencesCount;
        int search = high >>> 1;
        while (low != high && low != search) { //Find the best position in quickReferences.
            if (quickReferences[search].getValueID() < ID) {
                high = search;
            } else {
                low = search;
            }
            search = low + ((high - low) >>> 1);
        }
        return search;
    }

    /**
     * <p>
     * Adds the passed LinkedIDListNode to the linked list reference by this
     * LinkedIDList.</p>
     *
     * @param node The LinkedIDListNode to add.
     */
    public void add(final LinkedIDListNode<ObjectType> node) {
        synchronized (opperationLock) {
            node.setListObject(this);
            if (++containedElements % blockSize == quickReferences.length) { //quickLocations is too small.
                final LinkedIDListNode<ObjectType>[] temp = new LinkedIDListNode[quickReferences.length + blockSize];
                System.arraycopy(quickReferences, 0, temp, 0,
                        quickReferences.length);
                quickReferences = temp;
            }
            if (quickReferencesCount != 0) {
                int index = getQuickReferenceIndex(node.getValueID());
                LinkedIDListNode<ObjectType> positionNode = quickReferences[index]; //The LinkedIDListNode to insert ahead of.
                if (positionNode.getValueID() > node.getValueID() && positionNode.getNextNode() != null) {
                    while (positionNode.getValueID() > node.getValueID() && positionNode.getNextNode() != null) {
                        positionNode = positionNode.getNextNode();
                    }
                } else {
                    quickReferences[index++] = node;
                    for (; index < quickReferences.length; index++) {
                        quickReferences[index] = quickReferences[index].getPreviousNode();
                    }
                    LinkedIDListNode lastNode = quickReferences[quickReferencesCount - 1];
                    for (int i = blockSize; lastNode.getNextNode() != null; i--) {
                        lastNode = (LinkedIDListNode) lastNode.getNextNode();
                        if (i == 0) {
                            quickReferences[quickReferencesCount++] = lastNode;
                        }
                    }
                }
                node.setPreviousNode(positionNode);
                node.setNextNode(positionNode.setNextNode(node));
                if (node.getNextNode() != null) {
                    node.getNextNode().setPreviousNode(node);
                }
            } else {
                quickReferences[quickReferencesCount++] = node;
            }
        }
    }

    /**
     * @param <T> The return type of the LinkedIDListNode.
     * @param ID  The ID of the LinkedIDListNode to remove.
     *
     * @return The removed LinkedIDListNode or null.
     */
    public <T extends LinkedIDListNode<ObjectType>> T remove(final long ID) {
        synchronized (opperationLock) {
            if (containedElements-- != 0) {
                int index = getQuickReferenceIndex(ID);
                LinkedIDListNode node = quickReferences[index];
                while (node.getValueID() < ID) {
                    node = (LinkedIDListNode) node.getNextNode();
                }
                if (node.getValueID() == ID) {
                    if (node.getPreviousNode() != null) {
                        node.getPreviousNode().setNextNode(
                                node.setNextNode(null));
                    }
                    if (node.getNextNode() != null) {
                        node.getNextNode().setPreviousNode(
                                node.setPreviousNode(null));
                    }
                    node.setListObject(null);
                    for (; index < quickReferencesCount;) {
                        quickReferences[index] = quickReferences[index].getNextNode();
                        if (quickReferences[index++] == null) {
                            quickReferencesCount--;
                        }
                    }
                    return (T) node;
                }
            }
            return null;
        }
    }

    /**
     * @param <T> The return type of the LinkedIDListNode.
     * @param ID  The ID of the LinkedIDListNode to get.
     *
     * @return The LinkedIDListNode with the passed ID.
     */
    public <T extends LinkedIDListNode> T get(final long ID) {
        synchronized (opperationLock) {
            if (containedElements != 0) {
                int index = getQuickReferenceIndex(ID);
                LinkedIDListNode node = quickReferences[index];
                while (node.getValueID() < ID) {
                    node = (LinkedIDListNode) node.getNextNode();
                }
                if (node.getValueID() == ID) {
                    return (T) node;
                }
            }
            return null;
        }
    }

    /**
     * @param <T> The return type of the LinkedIDListNode.
     *
     * @return The first item in the linked list.
     */
    public <T extends LinkedIDListNode> T getFirst() {
        return (T) quickReferences[0];
    }

    /**
     * @param <T> The return type of the LinkedIDListNode.
     *
     * @return The last item in the linked list.
     */
    public <T extends LinkedIDListNode> T getLast() {
        LinkedIDListNode node = quickReferences[quickReferencesCount - 1];
        while (node.getNextNode() != null) {
            node = (LinkedIDListNode) node.getNextNode();
        }
        return (T) node;
    }

}
