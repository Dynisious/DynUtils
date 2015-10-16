package dynutils.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
/**
 * <p>
 * EventObjects are any objects which fire events. Facilitates the adding and
 * removing of EventListeners.</p>
 * <p>
 * NOTE: All methods are Thread safe.</p>
 *
 * @author Dynisious 05/10/2015
 * @version 0.0.1
 * @param <Listener> The type of EventListers stored inside this EventObject.
 */
public abstract class EventObject<Listener extends EventListener> {
    private ArrayList<Listener> listeners; //The EventListeners which handle
    //events on this EventObject.
    /**
     * <p>
     * Checks whether listeners has been initialised and initialises it if it
     * has been.</p>
     */
    private synchronized void checkListeners() {
        if (listeners == null) {
            listeners = new ArrayList<>(0);
        }
    }
    /**
     * <p>
     * Adds the passed EventListener to the collection of EventListeners on this
     * EventObject if it is not already added.</p>
     *
     * @param l The EventListener to add.
     */
    public void addListener(final Listener l) {
        if (l != null) {
            checkListeners();
            synchronized (listeners) {
                if (!listeners.contains(l)) {
                    listeners.add(l);
                }
            }
        }
    }
    /**
     * <p>
     * Adds the collection of EventListeners to the collection of EventListeners
     * on this EventObject if they are not already added.</p>
     *
     * @param ls The collection of EventListeners to add.
     */
    public void addAllListeners(final Collection<Listener> ls) {
        checkListeners();
        for (final Listener l : ls) {
            synchronized (listeners) {
                if (l != null) {
                    if (!listeners.contains(l)) {
                        listeners.addAll(ls);
                    }
                }
            }
        }
    }
    /**
     * <p>
     * Removes the passed EventListener from the collection of EventListeners on
     * this EventObject.</p>
     *
     * @param l The EventListener to remove.
     *
     * @return True if the EventListener was removed.
     */
    public boolean removeListener(final Listener l) {
        synchronized (listeners) {
            return listeners.remove(l);
        }
    }
    /**
     * <p>
     * Removes the EventListener at the specified index.</p>
     *
     * @param index The index of the EventListener to remove.
     *
     * @return The EventListener to remove.
     */
    public Listener removeListener(final int index) {
        checkListeners();
        synchronized (listeners) {
            return listeners.remove(index);
        }
    }
    /**
     ** @return An array containing all EventListener on this EventObject.
     */
    public EventListener[] getListeners() {
        if (listeners != null) {
            synchronized (listeners) {
                return listeners.toArray(new EventListener[listeners.size()]);
            }
        }
        return new EventListener[0];
    }

    /**
     * <p>
     * Creates a new EventObject with the passed EventListeners attached.</p>
     *
     * @param listeners The EventListeners to initially begin with.
     */
    protected EventObject(final Collection<Listener> listeners) {
        this.listeners = new ArrayList<>(listeners);
    }
    protected EventObject() {
    }

}
