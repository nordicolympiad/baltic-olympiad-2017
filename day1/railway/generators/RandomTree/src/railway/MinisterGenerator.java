package railway;
import java.util.*;

public class MinisterGenerator {

	ArrayList<ArrayList<Integer> > T;
	Random rn;
	
	public ArrayList<ArrayList<Integer> > ministers;
	
	
	MinisterGenerator (	ArrayList<ArrayList<Integer> > myT) {
		T = myT;
		rn = new Random();
		ministers = new ArrayList<ArrayList<Integer> > ();
		//System.out.println("Minister Generator for Tree of size " + T.size());
	}
	
	public int randomVertex() {return rn.nextInt(T.size());}

	public ArrayList<Integer> randomVertexGroup (int n) {
		TreeSet<Integer> ans = new TreeSet<Integer> ();
		while(ans.size() < n) ans.add(randomVertex());
		return new ArrayList<Integer>(ans);
	}
	
	public int closeRandomVertex(int start, int distance) {
		int v = start;
		while(distance --> 0) {
			int deg = T.get(v).size();
			v = T.get(v).get(rn.nextInt(deg));
		}
		return v;
	}
	
	public ArrayList<Integer> closeRandomVertexGroup(int start, int distance, int n) {
		TreeSet<Integer> ans = new TreeSet<Integer> ();
		while(ans.size() < n) {ans.add(closeRandomVertex(start, distance));}
		return new ArrayList<Integer>(ans);
	}

	
	public void addRandomMinister(int numVertices) {
		ArrayList<Integer> centers = randomVertexGroup(numVertices);
		addMinister(centers, 0, 1);
	}
			
		
	
	
	public void addMinister(ArrayList<Integer> centers, int distance, int groupSize) {
		TreeSet<Integer> ans = new TreeSet<Integer> ();
		
		for(int i : centers) {
			ans.addAll(closeRandomVertexGroup(i, distance, groupSize));	
		}
		ministers.add(new ArrayList<Integer> (ans));
	}
	
	public void addMinisterGroup(int numMinisters, int numCenters, int distance, int groupSize) {
		ArrayList<Integer> centers = randomVertexGroup(numCenters);
		for(int i = 0; i < numMinisters; ++i) addMinister(centers, distance, groupSize);
	}
}
