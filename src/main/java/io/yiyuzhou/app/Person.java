package io.yiyuzhou.app;

import java.util.Random;

class Person implements Comparable<Person> {
	private int start;
	private int dest;
	private static final Random random = new Random();

	private long startTime;
	public static int totalArrived = 0;
	public static int totalTime = 0;
	public static long maxTime = Long.MIN_VALUE;

	/* creates a Person object has the dest of a random floor */
	public Person(int start, int maxFloor) {
		/* time recording */
		this.startTime = System.currentTimeMillis();

		this.start = start;

		/* maxFloor is the floor number of the top floor, not the number of floors */
		do {
			this.dest = random.nextInt(maxFloor + 1); /* between 0 and maxFloor inclusive */
		} while (this.dest == this.start); /* person can't start and go to the same floor */
	}

	public int compareTo(Person person) {
		return (Integer.compare(this.dest, person.dest));
	}

	public int getStart() {
		return this.start;
	}

	public int getDest() {
		return this.dest;
	}

	public long getStartTime() {
		return this.startTime;
	}
}
