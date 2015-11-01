package dynutils.collections.sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
/**
 * <p>
 * An ArrayList collection which holds no duplicates. Optionally decide whether
 * duplicates are found byVal or byRef only.</p>
 * <p>
 * NOTE!!! NULL values are not allowed.</p>
 *
 * @param <E> The type of Object kept in this ArraySet.
 */
public class ArraySet<E> extends ArrayList<E> implements Set<E> {
    private final boolean ByValCheck;
    private boolean getByValCheck() {
        return ByValCheck;
    }

    /**
     * <p>
     * Creates a new empty ArraySet which will count value equivalence as a
     * duplicate Object.</p>
     */
    public ArraySet() {
        this.ByValCheck = true;
    }

    /**
     * <p>
     * Creates a new empty ArraySet.</p>
     *
     * @param ByValCheck True means that two different instances which are
     *                   equivalent in value are counted as duplicates.
     */
    public ArraySet(boolean ByValCheck) {
        this.ByValCheck = ByValCheck;
    }

    /**
     * <p>
     * Creates a new ArraySet which starts off with the values of the passed
     * collection.</p>
     *
     * @param c          The collection of initial values.
     * @param ByValCheck True means that two different instances which are
     *                   equivalent in value are counted as duplicates.
     */
    public ArraySet(Collection<? extends E> c, boolean ByValCheck) {
        super(c);
        this.ByValCheck = ByValCheck;
    }

    /**
     * <p>
     * Creates a new ArraySet which starts off with the values of the passed
     * collection and it will consider value equivalency as a duplicate.</p>
     *
     * @param c The collection of initial values.
     */
    public ArraySet(Collection<? extends E> c) {
        super(c);
        this.ByValCheck = true;
    }

    /**
     * <p>
     * Creates a new ArraySet with the specified initial capacity.</p>
     *
     * @param initialCapacity The initial capacity of this ArraySet.
     * @param ByValCheck      True means that two different instances which are
     *                        equivalent in value are counted as duplicates.
     */
    public ArraySet(int initialCapacity, boolean ByValCheck) {
        super(initialCapacity);
        this.ByValCheck = ByValCheck;
    }

    /**
     * <p>
     * Creates a new ArraySet with the specified initial capacity and it will
     * consider value equivalency as a duplicate.</p>
     *
     * @param initialCapacity The initial capacity of this ArraySet.
     */
    public ArraySet(int initialCapacity) {
        super(initialCapacity);
        this.ByValCheck = true;
    }

    /**
     * <p>
     * Compares two Objects and returns true if they are equal. Equality is
     * dependant on whether ByValCheck is true.</p>
     *
     * @param o1 The first object.
     * @param o2 The second object.
     *
     * @return True if the passed Objects are considered equal.
     */
    private boolean isEqual(final Object o1, final Object o2) {
        if (o1 == o2)
            return true;
        if (ByValCheck)
            return o1.equals(o2);
        return false;
    }

    @Override
    public boolean add(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        return super.add(e);
    }

    @Override
    public void add(int index, E element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null)
            throw new NullPointerException("The passed value is null.");
        super.add(index, element); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) throws NullPointerException {
        if (c == null)
            throw new NullPointerException("The passed value is null.");
        return super.addAll(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
            throws IndexOutOfBoundsException, NullPointerException {
        if (c == null)
            throw new NullPointerException("The passed value is null.");
        return super.addAll(index, c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Object o) throws NullPointerException {
        if (o == null)
            throw new NullPointerException("The passed value is null.");
        for (final E item : this)
            if (isEqual(item, o))
                return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof ArraySet) {
            final ArraySet temp = (ArraySet) o;
            return temp.getByValCheck() == ByValCheck && containsAll(temp);
        }
        return false;
    }

    /**
     * <p>
     * Replaces the element at the specified position in this list with
     * the specified element if the passed element is not already a member of
     * the ArraySet.<p>
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     *
     * @return A reference to the element at the specified position.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E set(int index, E element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null)
            throw new NullPointerException("The passed value is null.");
        if (contains(element))
            return get(index);
        return super.set(index, element);
    }

}
