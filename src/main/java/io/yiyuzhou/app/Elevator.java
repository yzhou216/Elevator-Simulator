package io.yiyuzhou.app;

import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;

class Elevator {
	private final int capacity;
	private int curFloor;
	private boolean goingUp;
	private final PriorityQueue<Person> minHeap = new PriorityQueue<>(); /* elevator going up */
	private final PriorityQueue<Person> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); /* elevator going down */

	/**
	 * Constructs an Elevator instance with a specified capacity.
	 * Initializes the elevator at the ground floor and sets its initial direction to going up.
	 *
	 * @param capacity The maximum number of passengers the elevator can hold.
	 */
	public Elevator(int capacity) {
		this.capacity = capacity;
		this.curFloor = 0;
		this.goingUp = true;
	}

	/**
	 * Adds a person to the appropriate queue based on the current direction of the elevator.
	 * Only adds the person if there is capacity in the elevator.
	 *
	 * @param person The Person object to be added.
	 * @return true if the person is successfully added, false if the elevator is full.
	 */
	public boolean addPerson(Person person) {
		if (minHeap.size() < capacity && maxHeap.size() < capacity) {
			if (this.goingUp)
				minHeap.add(person);
			else
				maxHeap.add(person);

			return true;
		}

		return false; /* elevator full */
	}

	/**
	 * Unloads passengers whose destination is the current floor.
	 * Updates statistics for each person unloaded, such as total ticks traveled and max ticks.
	 *
	 * @param floor The current floor of the elevator.
	 * @return true if any passengers were unloaded, false if the queue is empty.
	 */
	public boolean unload(int floor) {
		PriorityQueue<Person> passengers;

		if (goingUp)
			passengers = minHeap;
		else
			passengers = maxHeap;

		if (passengers.peek() == null)
			return false;
			
		while (passengers.peek() != null && passengers.peek().getDest() == floor) {
			Person person = passengers.poll();
			int ticksTraveled = person.getTicksTraveled();
			if (ticksTraveled > Person.maxTicks)
				Person.maxTicks = ticksTraveled;

			Person.totalTicks += ticksTraveled;
			Person.totalArrived++;
		}

		return true;
	}

	/**
	 * Loads passengers onto the elevator from a queue of waiting passengers.
	 * The method continues loading until either the elevator reaches its capacity
	 * or there are no more passengers waiting.
	 *
	 * @param passengers A Deque of Person objects representing passengers waiting to board.
	 */
	public void load(Deque<Person> passengers) {
		if (goingUp)
			while (minHeap.size() < capacity && !passengers.isEmpty())
				this.minHeap.add(passengers.poll());
		else
			while (maxHeap.size() < capacity && !passengers.isEmpty())
				this.maxHeap.add(passengers.poll());
	}

	public int getCurFloor() {
		return curFloor;
	}

	public void setCurFloor(int curFloor) {
		this.curFloor = curFloor;
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public PriorityQueue<Person> getMinHeap() {
		return this.minHeap;
	}

	public PriorityQueue<Person> getMaxHeap() {
		return this.maxHeap;
	}

	/**
	 * Increments the tick count for each person inside the elevator.
	 * This method should be called in each tick of the simulation to update the travel time
	 * of each person in the elevator.
	 */
	public void incrementTickCount() {
		Iterator<Person> iterator;
		if (goingUp)
			iterator = minHeap.iterator();
		else
			iterator = maxHeap.iterator();

		while (iterator.hasNext()) {
			Person person = iterator.next();
			person.setTicksTraveled(person.getTicksTraveled() + 1);
		}
	}
}
