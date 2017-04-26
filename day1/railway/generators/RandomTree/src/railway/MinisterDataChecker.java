package railway;


import java.io.*;
import java.util.StringTokenizer;

public class MinisterDataChecker {
	
	static String mainDir = RailwayGenerator.mainDir; 
	
	// ------------ INPUT HANDLING
	static BufferedReader stdin;
    static StringTokenizer st = new StringTokenizer("");
	
    // Read next input-token as integer.
    static int readInt() throws Exception {
        return Integer.parseInt(readString());
    }
    
    // Read next input-token as string.
    static String readString() throws Exception {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(stdin.readLine());
        }
        return st.nextToken();
    }
    
    // ------------ END INPUT HANDLING
	
    MinisterDataChecker(String fileName) throws Exception {
    	 stdin = new BufferedReader(new FileReader(new File(fileName)));
    }
	
	public static void main(String[] args) throws Exception{
		summarizeG1();
		summarizeG2();
		summarizeG3();
		summarizeG4();
		summarizeG5();
		summarizeG6();
	}
	
	
	static void summarizeG1() throws Exception {
		summarize(mainDir + "g1\\railway.01.in");
		summarize(mainDir + "g1\\railway.02.in");
		summarize(mainDir + "g1\\railway.03.in");
		summarize(mainDir + "g1\\railway.04.in");
		summarize(mainDir + "g1\\railway.05.in");
		summarize(mainDir + "g1\\railway.06.in");
		summarize(mainDir + "g1\\railway.07.in");
		summarize(mainDir + "g1\\railway.08.in");
	}
	
	static void summarizeG2() throws Exception {
		summarize(mainDir + "g2\\railway.01.in");
		summarize(mainDir + "g2\\railway.02.in");
		summarize(mainDir + "g2\\railway.03.in");
		summarize(mainDir + "g2\\railway.04.in");
		summarize(mainDir + "g2\\railway.05.in");
		summarize(mainDir + "g2\\railway.06.in");
		summarize(mainDir + "g2\\railway.07.in");
	}
	
	static void summarizeG3() throws Exception {
		summarize(mainDir + "g3\\railway.01.in");
		summarize(mainDir + "g3\\railway.02.in");
		summarize(mainDir + "g3\\railway.03.in");
		summarize(mainDir + "g3\\railway.04.in");
		summarize(mainDir + "g3\\railway.05.in");
		summarize(mainDir + "g3\\railway.06.in");
	}
	
	
	static void summarizeG4() throws Exception {
		summarize(mainDir + "g4\\railway.01.in");
		summarize(mainDir + "g4\\railway.02.in");
		summarize(mainDir + "g4\\railway.03.in");
		summarize(mainDir + "g4\\railway.04.in");
		summarize(mainDir + "g4\\railway.05.in");
		summarize(mainDir + "g4\\railway.06.in");
		summarize(mainDir + "g4\\railway.07.in");
	}	
	
	static void summarizeG5() throws Exception {
		summarize(mainDir + "g5\\railway.01.in");
		summarize(mainDir + "g5\\railway.02.in");
		summarize(mainDir + "g5\\railway.03.in");
		summarize(mainDir + "g5\\railway.04.in");
		summarize(mainDir + "g5\\railway.05.in");
		summarize(mainDir + "g5\\railway.06.in");
		summarize(mainDir + "g5\\railway.07.in");
		summarize(mainDir + "g5\\railway.08.in");
	}
	
	static void summarizeG6() throws Exception {
		summarize(mainDir + "g6\\railway.01.in");
		summarize(mainDir + "g6\\railway.02.in");
		summarize(mainDir + "g6\\railway.03.in");
		summarize(mainDir + "g6\\railway.04.in");
		summarize(mainDir + "g6\\railway.05.in");
		summarize(mainDir + "g6\\railway.06.in");
		summarize(mainDir + "g6\\railway.07.in");
		summarize(mainDir + "g6\\railway.08.in");
	}
		
	
	
	
	static void summarize(String fileName) throws Exception {
		MinisterDataChecker chk = new MinisterDataChecker(fileName);
		System.out.println(chk);
	}
	
	public String toString() {
		int n=0, m=0, k=0, s=0, a=0;
		try{
			n = readInt();
			m = readInt();
			k = readInt();
			for(int i = 0; i < n-1; ++i) {a= readInt(); a = a + readInt() - a;}
		
			s = 0;
			for(int i = 0; i < m; ++i) {
				int ss = readInt();
				s += ss;
				while(ss-->0) {a = readInt();}
			}
		} catch(Exception e) {
				assert false : "Exception in toString";
		}
		
		return "n: " + n + "  m:  " + m + "  k: " + k + "  s: " + s;
	}
	




}
