package io.yiyuzhou.app;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.Iterator;

public class Floor {
	final public int floorNum;
	private Deque<Person> passengersUp;
	private Deque<Person> passengersDown;

	/**
	 * Constructs a Floor instance with a specified floor number and structure type.
	 * Initializes the queues for passengers going up and down based on the specified structure.
	 *
	 * @param floorNum The floor number of this floor.
	 * @param structure The type of data structure to use for storing passengers.
	 *                  "linked" for LinkedList, any other value for ArrayDeque.
	 */
	public Floor(final int floorNum, String structure) {
		this.floorNum = floorNum;
		if (structure == "linked") {
			passengersUp = new LinkedList<Person>();
			passengersDown = new LinkedList<Person>();
		} else {
			passengersUp = new ArrayDeque<Person>();
			passengersDown = new ArrayDeque<Person>();
		}
	}

	/**
	 * Adds a person to the appropriate queue based on their destination relative to the current floor.
	 * If the person's destination is above their start, they are added to the passengers going up;
	 * otherwise, they are added to the passengers going down.
	 *
	 * @param person The Person object to be added to the queue.
	 */
	public void addPerson(Person person) {
		if (person.getStart() > person.getDest())
			passengersDown.add(person);
		else /* person.getStart() < person.getDest */
			passengersUp.add(person);
	}

	public Person getPassenger(boolean up) {
		if (up)
			return passengersUp.poll();
		else
			return passengersDown.poll();
	}

	public int getFloorNum() {
		return floorNum;
	}

	public Deque<Person> getPassengersDown() {
		return passengersDown;
	}

	public Deque<Person> getPassengersUp() {
		return passengersUp;
	}

	/**
	 * Increments the tick count for each person waiting on this floor.
	 * This method should be called in each tick of the simulation to update the waiting time
	 * of each person in the queues.
	 */
	public void incrementTickCount() {
		Iterator<Person> upIterator;
		Iterator<Person> downIterator;
		upIterator = passengersUp.iterator();
		downIterator = passengersDown.iterator();

		while (upIterator.hasNext()) {
			Person person = upIterator.next();
			person.setTicksTraveled(person.getTicksTraveled() + 1);
		}

		while (downIterator.hasNext()) {
			Person person = downIterator.next();
			person.setTicksTraveled(person.getTicksTraveled() + 1);
		}
	}
}
