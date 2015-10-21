package dynutils;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
/**
 * <p>
 * SerialChain defines custom serialising and deserialising.</p>
 *
 * @author Dynisious 19/10/2015
 * @version 0.0.1
 */
public interface SerialChain {

    /**
     * @return The size of the SerialChain in bytes post serialisation.
     */
    public int getSerialSize();

    /**
     * <p>
     * Serialises the SerialChain into the passed ByteBuffer.</p>
     *
     * @param buffer The ByteBuffer to serialise into.
     */
    public void serialise(final ByteBuffer buffer)
            throws BufferOverflowException;

    /**
     * <p>
     * Deserialises A SerialChain from the passed ByteBuffer.</p>
     *
     * @param <T>    The Type of SerialChain to return.
     * @param buffer The ByteBuffer to deserialise from.
     *
     * @return The new SerialChain.
     */
    public <T extends SerialChain> T deserialise(final ByteBuffer buffer)
            throws BufferUnderflowException;

}
