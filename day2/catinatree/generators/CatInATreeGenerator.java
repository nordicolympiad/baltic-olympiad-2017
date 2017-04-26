import java.util.*;
import java.io.*;

public class CatInATreeGenerator {
	
	static String mainDir = "..\\data\\secret\\";
	static String taskname = "catinatree";
	static String group1Dir = "g1\\";
	static String group2Dir = "g2\\";
	static String group3Dir = "g3\\";
	
	public static void main(String[] args) {
		genGroup1();
		genGroup2();
		genGroup3();
	}
	
	public static void genGroup1() {
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.01.in", 10, 2);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.02.in", 10, 3);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.03.in", 12, 1);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.04.in", 12, 4);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.05.in", 14, 2);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.06.in", 14, 4);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.07.in", 16, 2);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.08.in", 16, 3);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.09.in", 18, 1);
		genRandomTreeInstance(mainDir + group1Dir + taskname  + ".g1.10.in", 18, 5);
		
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.11.in", 1, 17, 1);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.12.in", 1, 17, 2);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.13.in", 3, 14, 1);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.14.in", 3, 14, 2);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.15.in", 3, 15, 5);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.16.in", 5, 13, 1);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.17.in", 5, 13, 2);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.18.in", 5, 13, 3);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.19.in", 7, 11, 6);
		genShallowTreeInstance(mainDir + group1Dir + taskname  + ".g1.20.in", 7, 11, 7);
	}

	
	public static void genGroup2() {
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.01.in", 500, 1);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.02.in", 500, 5);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.03.in", 500, 10);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.04.in", 1000, 1);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.05.in", 1000, 5);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.06.in", 1000, 10);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.07.in", 1500, 12);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.08.in", 1500, 5);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.09.in", 1500, 15);
		genRandomTreeInstance(mainDir + group2Dir + taskname  + ".g2.10.in", 1500, 40);
		
		genShallowTreeInstance(mainDir + group2Dir + taskname  + ".g2.11.in", 20, 1450, 4);
		genShallowTreeInstance(mainDir + group2Dir + taskname  + ".g2.12.in", 20, 1450, 10);
		genShallowTreeInstance(mainDir + group2Dir + taskname  + ".g2.13.in", 100, 1300, 10);
		genShallowTreeInstance(mainDir + group2Dir + taskname  + ".g2.14.in", 100, 1300, 12);
		genShallowTreeInstance(mainDir + group2Dir + taskname  + ".g2.15.in", 100, 1400, 10);
		
		genCaterpillarInstance(mainDir + group2Dir + taskname  + ".g2.16.in", 100, 1400, 4);
		genCaterpillarInstance(mainDir + group2Dir + taskname  + ".g2.17.in", 300, 1200, 50);
		genCaterpillarInstance(mainDir + group2Dir + taskname  + ".g2.18.in", 500, 1000, 70);
		genCaterpillarInstance(mainDir + group2Dir + taskname  + ".g2.19.in", 1499, 1, 1000);
		genCaterpillarInstance(mainDir + group2Dir + taskname  + ".g2.20.in", 1499, 1, 5);
	}	
	
	public static void genGroup3() {
		genRandomTreeInstance(mainDir + group3Dir + taskname  + ".g3.01.in", 100000, 1);
		genRandomTreeInstance(mainDir + group3Dir + taskname  + ".g3.02.in", 100000, 20);
		genRandomTreeInstance(mainDir + group3Dir + taskname  + ".g3.03.in", 100000, 50);
		genRandomTreeInstance(mainDir + group3Dir + taskname  + ".g3.04.in", 100000, 100);
		
		genShallowTreeInstance(mainDir + group3Dir + taskname  + ".g3.05.in", 1000, 99000, 3);
		genShallowTreeInstance(mainDir + group3Dir + taskname  + ".g3.06.in", 1000, 99000, 10);
		genShallowTreeInstance(mainDir + group3Dir + taskname  + ".g3.07.in", 1000, 99000, 15);
		
		genCaterpillarInstance(mainDir + group3Dir + taskname  + ".g3.08.in", 1000, 1400, 4);
		genCaterpillarInstance(mainDir + group3Dir + taskname  + ".g3.09.in", 20000, 1200, 2000);
		genCaterpillarInstance(mainDir + group3Dir + taskname  + ".g3.10.in", 90000, 10000, 10000);
	}
	
	// --------------------------------------------------------------------------
	
	
	public static void genRandomTreeInstance(String fileName, int n, int d) {
		RandomTreeGenerator gen = new RandomTreeGenerator();

		
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(n + " " + d);
		
		    ArrayList<Integer> seq = gen.randomTreeSequence(n-1);
			for(int a : seq) writer.println(a);		
			
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public static void genShallowTreeInstance(String fileName, int inner, int leaves, int d) {
		RandomTreeGenerator gen = new RandomTreeGenerator();
		int n = inner + leaves; 
		
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(n + " " + d);
		
		    ArrayList<Integer> seq = gen.randomRootedShallowTreeSequence(inner, leaves);
			for(int a : seq) writer.println(a);		
			
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public static void genCaterpillarInstance(String fileName, int inner, int leaves, int d) {
		RandomTreeGenerator gen = new RandomTreeGenerator();
		int n = inner + leaves; 
		
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(n + " " + d);
		
		    ArrayList<Integer> seq = gen.randomCaterpillarSequence(inner, leaves);
			for(int a : seq) writer.println(a);		
			
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	
	
	
	
}
