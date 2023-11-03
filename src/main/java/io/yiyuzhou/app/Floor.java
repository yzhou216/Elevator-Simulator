package io.yiyuzhou.app;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class Floor {
	final public int floorNum;
	private Deque<Person> passengersUp;
	private Deque<Person> passengersDown;

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
}
