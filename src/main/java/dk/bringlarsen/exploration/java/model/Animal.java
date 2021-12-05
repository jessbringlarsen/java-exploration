package dk.bringlarsen.exploration.java.model;

import dk.bringlarsen.exploration.java.JDK;

/**
 * The main motivation behind sealed interfaces/classes is to have the possibility for a superclass to be
 * widely accessible but not widely extensible.
 * <p>
 * A sealed class imposes three important constraints on its permitted subclasses:
 *<ul>
 * <li>All permitted subclasses must belong to the same module as the sealed class.
 * <li>Every permitted subclass must explicitly extend the sealed class.
 * <li>Every permitted subclass must define a modifier: final, sealed, or non-sealed.
 * </ul>
 */
@JDK(version = 12, description = "sealed classes")
public sealed interface Animal permits Cat {

    void walk();
}
