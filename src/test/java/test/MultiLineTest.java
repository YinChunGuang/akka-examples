package test;

public class MultiLineTest {
public static void main(String[] args) {
	String lines = ""+/**~{*/""
		    + "SELECT * "
		    + "\r\n    FROM user"
		    + "\r\n    WHERE name=\"zzg\""
		+ "\r\n"/**}*/;
		System.out.println(lines);
}
}
