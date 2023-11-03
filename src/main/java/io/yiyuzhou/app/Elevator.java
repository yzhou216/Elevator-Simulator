package io.yiyuzhou.app;

import java.util.PriorityQueue;
import java.util.Collections;

class Elevator {
	private final int capacity;
	private int curFloor;
	private boolean up;
	PriorityQueue<Person> minHeap = new PriorityQueue<>(); /* elevator going up */
	PriorityQueue<Person> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); /* elevator going down */

	public Elevator(int capacity) {
		this.capacity = capacity;
		this.curFloor= 0;
		this.up = true;
	}

	/* returns true if the person is added, false otherwise */
	public boolean addPerson(Person person) {
		if (minHeap.size() < capacity && maxHeap.size() < capacity) {
			if (this.up)
				minHeap.add(person);
			else
				maxHeap.add(person);

			return true;
		} else {
			return false; /* elevator full */
		}
	}

	public boolean unload(int floor) {
		PriorityQueue<Person> passengers;

		if (up)
			passengers = minHeap;
		else
			passengers = maxHeap;

		if (passengers.peek() == null)
			return false;
			
		while (passengers.peek().getDest() == floor)
			passengers.poll();

		return true;
	}

	public boolean load(Person passenger) {
		if (!(minHeap.size() < capacity) || !(maxHeap.size() < capacity))
			return false; /* elevator full */

		if (up)
			this.minHeap.add(passenger);
		else
			this.maxHeap.add(passenger);

		return true;
	}

	public int getCurFloor() {
		return curFloor;
	}
}
