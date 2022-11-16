package assignment1;

import assignment1.Car;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Hw1_p2 {

	public static void findByMake(Car[] cars, String make) {

		// implement this method: for each cars[i], get the make parameter and compare with the make input
		// there maybe multiple cars satisfy the condition, therefore, create a count variable to make sure if the target appears
		int count = 0;
		for (int i = 0; i < cars.length; i++) {
			if (cars[i].getMake().equals(make)){
				System.out.println(cars[i]);
				count++;
			}
		}
		if (count == 0) System.out.println("No car found.");

	}
	public static void olderThan(Car[] cars, int year) {

		// implement this method: for each cars[i], get the year parameter and compare with the year input
		int count = 0;
		for (int i = 0; i < cars.length; i++){
			if (cars[i].getYear() <= year){
				System.out.println(cars[i]);
				count++;
			}
		}
		if (count == 0) System.out.println("No car found.");
	}

	public static void main(String[] args) throws IOException {

		// complete this part
		// create an array of assignment1.Car objects, cars, of size 10
		Car[] cars = new Car[10];

		// read input file and store 10 car Objects in the array
		// I used the absolute path, but the relative path is also ok
		//D:\0 Hallie Jin\0 Hallie\0 CS\0 BU CS526 Data Stucture and Algorithms\Assignments\Assignment 1\src\input.txt
		//相对路径需要定位到该模块的前一个文件夹？？
		File file = new File("Assignments\\Assignment 1\\src\\input.txt");
		Scanner inputScanner = new Scanner (file);

		// set three variables to receive the value we scanned and split
		String make0;
		int year0;
		int price0;
		while (inputScanner.hasNextLine()){
			for (int i = 0; i < cars.length; i++) {
				String lineInput = inputScanner.nextLine();
				String[] splitLines = lineInput.split(",");
				make0 = splitLines[0];

				// trim the strings after split them and then cast the variable type
				price0 = Integer.parseInt(splitLines[1].trim());
				year0 = Integer.parseInt(splitLines[2].trim());

				// pass the three values to the new Car object we create
				cars[i] = new Car(make0, year0, price0);
			}
		}
		// close the scanner when there is no line to scan
		inputScanner.close();


		System.out.println("\nAll cars:");
		for (int i=0; i<cars.length; i++) {
			System.out.println(cars[i]);
		}

		String make = "Honda";
		int year = 2017;

		System.out.println("\nAll cars made by " + make);
		findByMake(cars, make);
		System.out.println("\nAll cars made before " + year);
		olderThan(cars, year);
	}

}
