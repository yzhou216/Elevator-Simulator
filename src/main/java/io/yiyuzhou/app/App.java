package io.yiyuzhou.app;

public class App {
	private static void tick(Elevator[] elevators, Floor[] floors, int numOfFloors, float passengersProbablity) {
		/* random generate passengers on different floors */
		for (int i = 0; i < floors.length; i++) {
			if (Math.random() < passengersProbablity)
				floors[i].addPerson(new Person(floors[i].floorNum, numOfFloors - 1));
		}

		for (int i = 0; i < elevators.length; i++) {
			/* send elevator down or up if it's on the top floor or the bottom floor */
			if (elevators[i].getCurFloor() == numOfFloors - 1) {
				if (elevators[i].isGoingUp())
					elevators[i].setGoingUp(false);
				else
					elevators[i].setGoingUp(true);
			} else if (elevators[i].getCurFloor() == 0) {
				if (!elevators[i].isGoingUp())
					elevators[i].setGoingUp(true);
				else
					elevators[i].setGoingUp(true);
			}

			if (elevators[i].isGoingUp()) {
				/* case when the elevator is empty */
				//Person root = elevators[i].getMinHeap().peek();
				if (elevators[i].getMinHeap().isEmpty()) {
					if (elevators[i].getCurFloor() + 5 <= numOfFloors - 1) {
						elevators[i].setCurFloor(elevators[i].getCurFloor() + 5);
					} else {
						elevators[i].setCurFloor(numOfFloors - 1);
					}
				} else {
					/* elevator can move up no more than 5 floors each time */
					Person root = elevators[i].getMinHeap().peek();
					if (root.getDest() - elevators[i].getCurFloor() > 5)
						elevators[i].setCurFloor(elevators[i].getCurFloor() + 5);
					else
						elevators[i].setCurFloor(root.getDest());
				}

			} else if (!elevators[i].isGoingUp()) {
				if (elevators[i].getMaxHeap().isEmpty()) {
					if (elevators[i].getCurFloor() - 5 >= 0) {
						elevators[i].setCurFloor(elevators[i].getCurFloor() - 5);
					} else {
						elevators[i].setCurFloor(0);
					}
				} else {
					/* elevator can move down no more than 5 floors each time */
					Person root = elevators[i].getMaxHeap().peek();
					if (elevators[i].getCurFloor() - root.getDest() > 5)
						elevators[i].setCurFloor(elevators[i].getCurFloor() - 5);
					else
						elevators[i].setCurFloor(root.getDest());
				}
			}

			/* debug */
			if (elevators[i].isGoingUp())
				System.out.println("going up");
			else
				System.out.println("going down");
			System.out.printf("num of min heap passengers: %d\n", elevators[i].getMinHeap().size());
			System.out.printf("num of max heap passengers: %d\n", elevators[i].getMaxHeap().size());
			System.out.printf("current floor: %d\n\n", elevators[i].getCurFloor()); /* debug */

			elevators[i].unload(elevators[i].getCurFloor());

			if (elevators[i].isGoingUp()) {
				elevators[i].load(floors[elevators[i].getCurFloor()].getPassengersUp());
			} else {
				elevators[i].load(floors[elevators[i].getCurFloor()].getPassengersDown());
			}
		}
	}

	public static void main(String[] args) {
		Env env = new Env(args[0]);
		final String structure = env.getProperty("structures");
		final int numOfFloors = Integer.parseInt(env.getProperty("floors"));
		final float passengersProbability = Float.parseFloat(env.getProperty("passengers"));
		final int numOfElevators = Integer.parseInt(env.getProperty("elevators"));
		final int elevatorCapacity = Integer.parseInt(env.getProperty("elevatorCapacity"));
		final int duration = Integer.parseInt(env.getProperty("duration"));

		Elevator[] elevators = new Elevator[numOfElevators];
		for (int i = 0; i < numOfElevators; i++)
			elevators[i] = new Elevator(elevatorCapacity);

		Floor[] floors = new Floor[numOfFloors];
		for (int i = 0; i < numOfFloors; i++)
			floors[i] = new Floor(i, structure);

		for (int i = 0; i < duration; i++) {
			tick(elevators, floors, numOfFloors, passengersProbability);
		}
	}
}
