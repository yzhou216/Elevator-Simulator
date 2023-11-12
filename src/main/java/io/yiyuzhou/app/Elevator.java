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

	public Elevator(int capacity) {
		this.capacity = capacity;
		this.curFloor = 0;
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
		}

		return false; /* elevator full */
	}

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

	/* takes a deque, load as much as posssible on to the elevator then return the rest as a deque */
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
