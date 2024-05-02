package project.flashcardapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for CardList and CategoryList
 *
 * @author Bartlomiej Gladys
 * @Date 03/11/2018
 * @version 1.0
 */

public class Base<A> {
    /**
     * Container for elements
     */
    private List<A> elements = new ArrayList<>();

    /**
     * Giving all elements
     *
     * @return all elements
     */
    public List<A> getAll() {
        return elements;
    }

    /**
     * Remove one object from container
     *
     * @param obj to remove
     */
    public void remove(A obj) {
        elements.remove(obj);
    }

    /**
     * Add one object to container
     *
     * @param obj to add
     */
    public void add(A obj) {
        elements.add(obj);
    }
}
