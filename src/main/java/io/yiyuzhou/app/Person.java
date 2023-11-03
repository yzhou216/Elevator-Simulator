package io.yiyuzhou.app;

class Person implements Comparable<Person>{
	private int start;
	private int dest;

	public Person(int maxFloor) {
		/* TODO: random start and dest */
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
