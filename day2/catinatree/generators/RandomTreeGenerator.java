import java.util.*;

public class RandomTreeGenerator {

	Random rn;
	RandomTreeGenerator() {rn = new Random();}
	
	// turns a sequence with integers [T0, T1, ...., Tn] with Ti <= i into a tree.
	// parents are always labeled lower than children. 
	ArrayList<ArrayList<Integer> > sequenceToTree(ArrayList<Integer> T) {
		int n = T.size();
		int N = n+1;
		
		ArrayList<ArrayList<Integer> > ans = new ArrayList<ArrayList<Integer> > ();
		for(int i = 0; i < N; ++i) ans.add(new ArrayList<Integer> ());
		
		for(int i = 0; i < n; ++i) {
			int j = T.get(i);
			assert j <= i : "Bad Sequence";
			ans.get(i+1).add(j);
			ans.get(j).add(i+1);
		}
		
		return ans;
	}
	
	// makes a random tree with root 0 and vertices [0.....n]
	ArrayList<Integer> randomTreeSequence(int n) {
		ArrayList<Integer> T = new ArrayList<Integer> ();
		for(int i = 0; i < n; ++i) T.add(rn.nextInt(i+1));
		return T;
	}
	
	// creates a caterpillar with main path [0,1,...,pathLength-1]
	ArrayList<Integer> randomCaterpillarSequence(int pathLength, int numLeaves) {
		ArrayList<Integer> T = new ArrayList<Integer> ();
		
		// create main path.
		for(int i = 0; i < pathLength-1; ++i) {T.add(i);}
		
		// add leaves
		for(int i = 0; i < numLeaves; ++i) T.add(rn.nextInt(pathLength));
		
		return T;
	}
	
	// creates a star with root 0 and numLeaves leaves
	ArrayList<Integer> starSequence(int numLeaves) {
		ArrayList<Integer> T = new ArrayList<Integer> ();
		for(int i = 0; i < numLeaves; ++i) T.add(0);
		return T;
	}
	
	ArrayList<Integer> randomRootedShallowTreeSequence(int internal, int numLeaves) {
		ArrayList<Integer> seq = randomTreeSequence(internal - 1);
		for(int i = 0; i < numLeaves; ++i) seq.add(rn.nextInt(internal));
		return seq;
	}
	
	ArrayList<ArrayList<Integer> > rootedStar(int N) {
		return sequenceToTree(starSequence(N));
	}
	
	ArrayList<ArrayList<Integer> > randomRootedTree(int N) {
		return sequenceToTree(randomTreeSequence(N-1));
	}
	
	ArrayList<ArrayList<Integer> > randomRootedCaterpillar(int pathLength, int numLeaves) {
		return sequenceToTree(randomCaterpillarSequence(pathLength, numLeaves));
	}
	
	ArrayList<ArrayList<Integer> > randomRootedShallowTree(int internal, int numLeaves) {
		return sequenceToTree(randomRootedShallowTreeSequence(internal, numLeaves));
	}
	
	public static void main(String[] args) {
		System.out.println((new RandomTreeGenerator ()).randomRootedShallowTree(6,10));
	}
	
	
	
	
	
	
	
}
