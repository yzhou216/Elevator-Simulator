package io.yiyuzhou.app;

import java.util.Random;

class Person implements Comparable<Person> {
	private int start;
	private int dest;
	private static final Random random = new Random();

	/* creates a Person object has the dest of a random floor */
	public Person(int start, int maxFloor) {
		this.start = start;

		do {
			this.dest = random.nextInt(maxFloor - 1) + 1;
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
}
