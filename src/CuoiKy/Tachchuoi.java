package CuoiKy;

import java.util.Scanner;

public class Tachchuoi {
	public Tachchuoi() {
		String s = "nguyen,thi" ;
		String s1 = "";
		String s2 = "" ;

		int index = 0;
		int size = s.length();
		for(int i = 0; i<size; i++) {
			if(s.charAt(i) == ',') {
				index = index + 1 ;
				continue;
			}
			if(index == 0) {	
				s1 = s1 + s.charAt(i);
			}
			if(index == 1) {
				s2 = s2 + s.charAt(i);
			}
		}
		System.out.println(s1);
		System.out.println(s2);
	}
	
	public static void main(String[] args) {
		Tachchuoi tc = new Tachchuoi();
	}
}
