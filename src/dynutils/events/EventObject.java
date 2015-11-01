package dynutils.events;

import dynutils.collections.sets.LinkedSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventListener;
import java.util.Objects;
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
public class EventObject<Listener extends EventListener> {
    /**
     * <p>
     * The EventListeners which handle events on this EventObject.</p>
     */
    private transient LinkedSet<Listener> listeners = new LinkedSet<>(false);
    /**
     * <p>
     * Adds the passed EventListener to the collection of EventListeners on this
     * EventObject if it is not already added.</p>
     *
     * @param l The EventListener to add.
     */
    public void addListener(final Listener l) {
        if (l != null) {
            synchronized (listeners) {
                listeners.add(l);
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
        for (final Listener l : ls) {
            synchronized (listeners) {
                if (l != null) {
                    listeners.addAll(ls);
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
     * @return An array containing all EventListener on this EventObject.
     */
    public EventListener[] getListeners() {
        synchronized (listeners) {
            return listeners.toArray(new EventListener[listeners.size()]);
        }
    }

    /**
     * <p>
     * Creates a new EventObject with the passed EventListeners attached.</p>
     *
     * @param listeners The EventListeners to initially begin with.
     */
    public EventObject(final Collection<Listener> listeners) {
        listeners.addAll(listeners);
    }
    public EventObject() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof EventObject) {
            final EventObject temp = (EventObject) obj;
            if (Arrays.equals(temp.getListeners(), getListeners()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + Objects.hashCode(this.listeners);
        return hash;
    }

}
