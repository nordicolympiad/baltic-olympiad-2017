import java.util.*;
import java.io.*;

public class PlusMinusGenerator {
	
	static String mainDir = "../data/secret/";
	static String taskname = "plusminus";
	static String group1Dir = "g1/";
	static String group2Dir = "g2/";
	static String group3Dir = "g3/";
	
	static Random rn;
	
	public static void main(String[] args) {
		rn = new Random();
		genGroup1();
		genGroup2();
		genGroup3();
	}
	
	public static void genGroup1() {
		String prefix = mainDir + group1Dir + taskname;
		genRandomInstance(prefix + ".g1.01.in", 5, 5, 0, false, false);
		genRandomInstance(prefix + ".g1.02.in", 5, 5, 7, true, false);
		genRandomInstance(prefix + ".g1.03.in", 5, 5, 7, false, false);
		genRandomInstance(prefix + ".g1.04.in", 5, 5, 10, false, true);
		genRandomInstance(prefix + ".g1.05.in", 5, 5, 10, false, false);
		genRandomInstance(prefix + ".g1.06.in", 4, 4, 1, false, false);
		genRandomInstance(prefix + ".g1.07.in", 4, 4, 4, true, false);
		genRandomInstance(prefix + ".g1.08.in", 5, 5, 20, true, false);
		genRandomInstance(prefix + ".g1.09.in", 5, 5, 20, true, true);
		genRandomInstance(prefix + ".g1.10.in", 2, 2, 1, true, true);
	}

	
	public static void genGroup2() {
		String prefix = mainDir + group2Dir + taskname;
		genRandomInstance(prefix + ".g2.01.in", 990, 10, 0, false, false);
		genRandomInstance(prefix + ".g2.02.in", 990, 10, 15, false, true);
		genRandomInstance(prefix + ".g2.03.in", 999, 888, 1001, false, false);
		genRandomInstance(prefix + ".g2.04.in", 999, 888, 1001, true, false);
		genRandomInstance(prefix + ".g2.05.in", 999, 999, 1001, true, true);
		genRandomInstance(prefix + ".g2.06.in", 1000, 1000, 100000, false, true);
		genRandomInstance(prefix + ".g2.07.in", 1000, 1000, 100000, true, false);
		genRandomInstance(prefix + ".g2.08.in", 1000, 1000, 100000, true, false);
		genRandomInstance(prefix + ".g2.09.in", 999, 858, 100000, true, true);
		genRandomInstance(prefix + ".g2.10.in", 994, 907, 100000, true, true);
	}	
	
	public static void genGroup3() {
		String prefix = mainDir + group3Dir + taskname;
		genRandomInstance(prefix + ".g3.01.in", 999999999, 999999777, 0, false, false);
		genRandomInstance(prefix + ".g3.02.in", 90000, 90000, 98989, false, false);
		genRandomInstance(prefix + ".g3.03.in", 90000, 90000, 98989, false, true);
		genRandomInstance(prefix + ".g3.04.in", 90000, 90000, 98989, true, false);
		genRandomInstance(prefix + ".g3.05.in", 90000, 90000, 98989, true, true);
		genRandomInstance(prefix + ".g3.06.in", 899997992, 999299776, 83542, false, false);
		genRandomInstance(prefix + ".g3.07.in", 899997992, 999299776, 83542, false, true);
		genRandomInstance(prefix + ".g3.08.in", 899997992, 999299776, 83542, true, false);
		genRandomInstance(prefix + ".g3.09.in", 899997992, 999299776, 83542, true, true);
		genRandomInstance(prefix + ".g3.10.in", 1000000000, 1000000000, 100000, true, true);
	}	

	
	// --------------------------------------------------------------------------
	
	
	// Generates random instance with dimension n*m,  
	// 
	
	
	public static void genRandomInstance(String fileName, int n, int m, int k, boolean forceHSuccess, boolean forceVSuccess) {
		System.out.println("Generating " + fileName);
		
		if(forceHSuccess && forceVSuccess) {
			genPrettyInstance(fileName, n, m, k);
			return;
		}
		
		TreeMap<Point, Character> pmap = new TreeMap<Point, Character> ();
		TreeMap<Integer, Character> hmap = new TreeMap<Integer, Character> ();
		TreeMap<Integer, Character> vmap = new TreeMap<Integer, Character> ();
		
		while(pmap.size() < k) {
			int x = 1 + rn.nextInt(m);
			int y = 1 + rn.nextInt(n);
			char c = rn.nextBoolean() ? '+' : '-';
			
			if(forceHSuccess) {
				if(hmap.containsKey(y)) {c = x%2==0 ? hmap.get(y) : oppositeSign(hmap.get(y));}
				hmap.put(y, c);
			} else if(forceVSuccess) {
				if(vmap.containsKey(x)) {c = y%2==0 ? vmap.get(x) : oppositeSign(vmap.get(x));}
				vmap.put(x, c);
			}
			pmap.put(new Point(x,y), c);
			//if(pmap.size() % 10000 == 1) System.out.println(pmap.size());
		}
		
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(n + " " + m + " " + k);
		    
		    outputmap(writer, pmap, k);
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	// either all pluses are on even and all minuses on odd or vice verca.
	public static void genPrettyInstance(String fileName, int n, int m, int k) {
		try{
		    
		    boolean evenIsPlus = rn.nextBoolean();
		    TreeMap<Point, Character> pmap = new TreeMap<Point, Character> ();
		    
		    while(pmap.size() < k) {
		    	int y = 1 + rn.nextInt(n);
		    	int x = 1 + rn.nextInt(m);
		    	
		    	char c = '+';
		    	if(evenIsPlus) {
		    		if((x+y)%2 == 0) c = '+'; else c = '-';
		    	} else {
		    		if((x+y)%2 == 0) c = '-'; else c = '+';
		    	}

		    	pmap.put(new Point(x, y), c);
		    }
		    
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(n + " " + m + " " + k);
		    
		    outputmap(writer, pmap, k);		    
	    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}		
	}
	
	public static char oppositeSign(char c) {return c=='+' ? '-' : '+';}
	
	public static void outputmap(PrintWriter writer, TreeMap<Point, Character> pmap, int k) {
		RandomPermutationGenerator rpg = new RandomPermutationGenerator();
	    ArrayList<Integer> perm = rpg.randomPermutaion(k);
	    
	    Point[] coord = new Point[k];
	    char[] sign = new char[k];
	    
	    int i = 0;
	    for(Point p : pmap.keySet()) {
	    	coord[perm.get(i)] = p;
	    	sign[perm.get(i)] = pmap.get(p);
	    	i++;
	    }
		
	    for(int t = 0; t < k; ++t) {
	    	writer.println(sign[t] + " " + coord[t].y + " " + coord[t].x);
	    }
	}
}

class Point implements Comparable<Point> {
	int y,x;
	
	Point(int x, int y){this.x=x; this.y=y;}
	
	public int compareTo(Point p) {
		if(y != p.y) return y < p.y ? -1 : 1;
		if(x != p.x) return x < p.x ? -1 : 1;
		return 0;
	}
}




