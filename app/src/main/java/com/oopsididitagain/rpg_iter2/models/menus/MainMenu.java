package com.oopsididitagain.rpg_iter2.models.menus;

import java.util.LinkedList;
import java.util.Observable;

/**
 * void	            addObserver(Observer o)     Adds an observer to the set of observers for this object, provided that it is not the same as some observer already in the set.
 *
 * protected void	clearChanged()              Indicates that this object has no longer changed, or that it has already notified all of its observers of its most recent change,
 *                                              so that the hasChanged method will now return false.
 *
 * int	            countObservers()            Returns the number of observers of this Observable object.
 *
 * void	            deleteObserver(Observer o)  Deletes an observer from the set of observers of this object.
 *
 * void	            deleteObservers()           Clears the observer list so that this object no longer has any observers.
 *
 * boolean	        hasChanged()                Tests if this object has changed.
 *
 * void	            notifyObservers()           If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged
 *                                              method to indicate that this object has no longer changed.
 *
 * void	            notifyObservers(Object arg) If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged
 *                                              method to indicate that this object has no longer changed.
 *
 * protected void	setChanged()                Marks this Observable object as having been changed; the hasChanged method will now return true.
 *
 */


public class MainMenu extends Observable {
	LinkedList<String> options;
	int size;
	int currentOption;


}
