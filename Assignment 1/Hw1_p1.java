package assignment1;

import java.util.Arrays;

public class Hw1_p1 {
	
	public static void find(int[] a, int x) {
		// implement this method: linear search for each element in a
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				System.out.printf("%d is in a[%d]\n", a[i], i);
				count++;
			}
		}
		// if x is not found in array a
		if (count == 0) System.out.printf("%d does not exist\n", x);
	}
	
	public static boolean isPrefix(String s1, String s2) {
		// implement this method: compare the character in each position for string s1 and s2
		// int i cannot exceed s1.length() because s1 is shorter than or equal to s2
		for (int i = 0; i < s1.length(); i++){
			if (s1.charAt(i) != s2. charAt(i)) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		
		int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};
		
		find(a, 5);
		find(a, 10);
		System.out.println();
		
		String s1 = "abc";
		String s2 = "abcde";
		String s3 = "abdef";
		
		if (isPrefix(s1,s2)) {
			System.out.println(s1 + " is a prefix of " + s2);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s2);
		}
		
		if (isPrefix(s1,s3)) {
			System.out.println(s1 + " is a prefix of " + s3);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s3);
		}
	}
}
