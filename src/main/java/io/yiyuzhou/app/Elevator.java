package io.yiyuzhou.app;

import java.util.PriorityQueue;
import java.util.Collections;

class Elevator {
	private final int capacity;
	private int curFloor;
	private boolean goingUp;
	private final PriorityQueue<Person> minHeap = new PriorityQueue<>(); /* elevator going up */
	private final PriorityQueue<Person> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); /* elevator going down */

	public Elevator(int capacity) {
		this.capacity = capacity;
		this.curFloor= 0;
		this.goingUp = true;
	}

	/* returns true if the person is added, false otherwise */
	public boolean addPerson(Person person) {
		if (minHeap.size() < capacity && maxHeap.size() < capacity) {
			if (this.goingUp)
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

		if (goingUp)
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

		if (goingUp)
			this.minHeap.add(passenger);
		else
			this.maxHeap.add(passenger);

		return true;
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

	public void setGoingUp(boolean up) {
		this.goingUp = up;
	}

	public PriorityQueue<Person> getMinHeap() {
		return this.minHeap;
	}

	public PriorityQueue<Person> getMaxHeap() {
		return this.maxHeap;
	}
}
