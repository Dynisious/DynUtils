package dynutils.collections.sets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
/**
 * <p>
 * A Linked collection which holds no duplicates. Optionally decide whether
 * duplicates are found byVal or byRef only.</p>
 * <p>
 * NOTE!!! NULL values are not allowed.</p>
 *
 * @author Dynisious 23/10/2015
 * @version 0.0.1
 * @param <E> The type of Objects stored in this LinkedSet.
 */
public class LinkedSet<E> extends LinkedList<E> implements Set<E> {
    private final boolean ByValCheck;
    public boolean getByValCheck() {
        return ByValCheck;
    }

    /**
     * <p>
     * Creates a new empty LinkedSet which will count value equivalence as a
     * duplicate Object.</p>
     */
    public LinkedSet() {
        this.ByValCheck = true;
    }

    /**
     * <p>
     * Creates a new empty LinkedSet.</p>
     *
     * @param ByValCheck True means that two different instances which are
     *                   equivalent in value are counted as duplicates.
     */
    public LinkedSet(boolean ByValCheck) {
        this.ByValCheck = ByValCheck;
    }

    /**
     * <p>
     * Creates a new LinkedSet which starts off with the values of the passed
     * collection.</p>
     *
     * @param c          The collection of initial values.
     * @param ByValCheck True means that two different instances which are
     *                   equivalent in value are counted as duplicates.
     */
    public LinkedSet(final Collection<E> c, boolean ByValCheck) {
        this.ByValCheck = ByValCheck;
        addAll(c);
    }

    /**
     * <p>
     * Creates a new LinkedSet which starts off with the values of the passed
     * collection which will count value equivalence as a duplicate.</p>
     *
     * @param c The collection of initial values.
     */
    public LinkedSet(final Collection<E> c) {
        this.ByValCheck = true;
        addAll(c);
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
    public boolean contains(final Object o) throws NullPointerException {
        if (o == null)
            throw new NullPointerException("The passed value is null.");
        for (final E item : this)
            if (isEqual(item, o))
                return true;
        return false;
    }

    @Override
    public void addFirst(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        if (!contains(e))
            super.addFirst(e);
    }

    @Override
    public void addLast(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        if (!contains(e))
            add(e);
    }

    @Override
    public boolean offerFirst(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        if (contains(e))
            return false;
        return super.offerFirst(e);
    }

    @Override
    public boolean offerLast(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        if (contains(e))
            return false;
        return super.offerLast(e);
    }

    @Override
    public boolean offer(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        if (contains(e))
            return false;
        return super.offer(e);
    }

    @Override
    public void push(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is a null value.");
        if (!contains(e))
            super.push(e);
    }

    @Override
    public boolean add(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("The passed value is null.");
        boolean ret = false;
        if (!contains(e))
            if (super.add(e))
                ret = true;
        return ret;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) throws NullPointerException {
        if (c == null)
            throw new NullPointerException("The passed value is null.");
        boolean ret = false;
        for (final E e : c)
            if (add(e))
                ret = true;
        return ret;
    }

    @Override
    public boolean equals(Object obj) throws NullPointerException {
        if (obj == null)
            throw new NullPointerException("The passed value is null.");
        if (obj == this)
            return true;
        if (obj instanceof LinkedSet) {
            final LinkedSet temp = (LinkedSet) obj;
            if (temp.containsAll(this))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + (this.ByValCheck ? 1 : 0);
        return hash;
    }

}
