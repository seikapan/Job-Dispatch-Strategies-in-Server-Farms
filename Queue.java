/**
 * A queue interface.
 * 
 * File name: Queue.java
 * Author: Seika Oelschig
 * Created on: 03/12/24
 */

public interface Queue<T> {

    /**
     * Adds the given {@code item} to the end of this queue.
     * 
     * @param item the item to add to the queue.
     */
    public boolean offer(T item);

    /**
     * Returns the number of items in the queue.
     * @return the number of items in the queue.
     */
    public int size();

    /**
     * Returns the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T peek();

    /**
     * Returns and removes the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T poll();
}
