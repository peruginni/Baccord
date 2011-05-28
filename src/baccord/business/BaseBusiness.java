
package baccord.business;

import baccord.tools.Observable;
import baccord.tools.Observer;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BaseBusiness implements Observable
{
	private Set<Observer> observers = new HashSet<Observer>();
	
	public void removeObserver(Observer observer)
	{
		observers.remove(observer);
	}

	public void registerObserver(Observer observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers()
	{
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg)
	{
		for (Observer o : observers) {
			o.update(this, arg);
		}
	}
}
