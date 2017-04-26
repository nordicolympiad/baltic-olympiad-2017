package railway;
import java.util.*;
import java.io.*;

public class RailwayGenerator {
	
	static final boolean permute = false;
	
	static String mainDir = "C:\\Users\\Daniel\\Dropbox\\BOI2017\\tasks\\kattisversions\\railway\\data\\secret\\";
	static String taskname = "railway";
	
	final static int randomTree = 0, shallowTree = 1, deepTree = 2;

	static ArrayList<Integer> groupSizes;
	
	static Random rn;
	
	public static void main(String[] args) {
		rn = new Random();
		groupSizes = new ArrayList<Integer> ();
		genGroup1();
		genGroup2();
		genGroup3();
		genGroup4();
		genGroup5();
		genGroup6();
		System.out.println("GROUP SIZES: " + groupSizes);
	}
	

	public static void genGroup1() {
		System.out.println("Group 1");
		ArrayList<InstanceSpecifier> isl = group1ISL();
		genGroup(isl, "g1\\");
		groupSizes.add(isl.size());
	}
	
	public static void genGroup2() {
		System.out.println("Group 2");
		ArrayList<InstanceSpecifier> isl = group2ISL();
		isl.addAll(group1ISL());
		genGroup(isl, "g2\\");
		groupSizes.add(isl.size());
	}
	
	public static void genGroup3() {
		System.out.println("Group 3");
		ArrayList<InstanceSpecifier> isl = group3ISL();
		genGroup(isl, "g3\\");
		groupSizes.add(isl.size());
	}	
	
	public static void genGroup4() {
		System.out.println("Group 4");
		ArrayList<InstanceSpecifier> isl = group4ISL();
		genGroup(isl, "g4\\");
		groupSizes.add(isl.size());
	}
	
	public static void genGroup5() {
		System.out.println("Group 5");
		ArrayList<InstanceSpecifier> isl = group5ISL();
		isl.addAll(group4ISL());
		genGroup(isl, "g5\\");
		groupSizes.add(isl.size());
	}
		
	public static void genGroup6() {
		System.out.println("Group 6");
		ArrayList<InstanceSpecifier> isl = group6ISL();
		isl.addAll(group4ISL());
		isl.addAll(group5ISL());
		genGroup(isl, "g6\\");
		groupSizes.add(isl.size());
	}
		
	
	// N <= 10000, S <= 2000
	public static ArrayList<InstanceSpecifier> group1ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10,					0,						10,						2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						3));	
				
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10000,				0,						50,						4,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  3,					50,					3,					5,						4,						20));	
				
		
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10000,				0,						50,						4,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  3,					50,					3,					5,						4,						55));
		
		// PATH
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			20,					20,						10,						2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						3));
				
		
		// PATH
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			20,					20,						0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					5,					2,					2,						2,						5));
		
		
		// Almost PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			10000,				5000,					250,					4,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  50,					4,					2,					30,						2,						12));		
		
		// Almost PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			10000,				1000,					1,						1000,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  100,					1,					1,					10,						10,						2));		
		
		// STAR
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		10000,				10,						1000,					2,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						2));
		
		// STARLIKE
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		10000,				200,					0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  100,					10,					2,					5,						1,						1000));
	
	
		return isl;
	
	}

	
	
	// N <= 10000, m <= 2000
	public static ArrayList<InstanceSpecifier> group2ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10000,				0,						1000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  100,					10,					30,					10,						5,						50));	
				
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10000,				0,						1000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  100,					10,					30,					10,						5,						30));	
		
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			10000,				0,						1000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  100,					10,					30,					10,						5,						10));	
		

		// Almost PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			10000,				5000,					1,						1,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  10,					199,				2,					5,						1,						399));		
		
		
		// Almost PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			10000,				1000,					1000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						200));		
		
		
		// STAR
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		10000,				1,						1000,					200,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						20));
		

		// STARLIKE
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		10000,				3,						1000,					200,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,						0,						200));
		
		return isl;
	}	

	public static ArrayList<InstanceSpecifier> group3ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();		
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			20,					20,						5,						3,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					10,				    2,					3,						2,						12));		
		
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					100000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,				    0,					0,						0,						10000));			
		
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					100000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,				    0,					0,						0,						20000));			
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					100000,					2,					
		
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,				    0,					0,						0,						30000));	
		
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					10000,					20,					
			
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,				    0,					0,						0,						9000));	
			
			
		
		//  PATH 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					1000,					100,					
			
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					1000,			    2,					1000,					50,						1900));		
		
		return isl;
	}	
		
	
		

	public static ArrayList<InstanceSpecifier> group4ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					100000,				2,					1000,					1,						100000));	
		// 
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					100000,				2,					1000,					1,						100000));	

		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						1,						2,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					99999,				2,					1000,					1,						100000));	
		
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				10000,					0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					100000,				2,					100,					1,						100000));		
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				100000,					0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					50000,				2,					1000,					1,						100000));			
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				100000,					0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					50000,				2,					1000,					1,						100000));			

		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				100000,					0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					50000,				2,					1000,					1,						100000));			

		return isl;
	}

	
	
	
	
	public static ArrayList<InstanceSpecifier> group5ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				100000,					100,					1000,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					50000,				2,					1000,					1,						50100));	
		// 

		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				100000,					100,					1000,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  1,					50000,				2,					1000,					1,						50100));	
		// 
		
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					100,					1000,					
						
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  10,					100,				100,				1000,					1,						1100));	
		//
		
		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					deepTree,			200000,				200000,					100,					1000,					
						
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  10,					100,				100,				1000,					1,						1100));	

		
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		200000,				10,						200,					1000,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,					    0,						200));	
		
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					shallowTree,		200000,				10,						200,					1000,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  0,					0,					0,					0,					    0,						200));
		

		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					5000,				10,					50,						2,						100000));	
		

		// 
		//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
		isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						0,						0,					
				
		//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
									  2,					5000,				10,					50,						2,						100000));	
		
		
		return isl;
	}
	
	
	public static ArrayList<InstanceSpecifier> group6ISL() {
		ArrayList<InstanceSpecifier> isl = new ArrayList<InstanceSpecifier> ();
	
	// 
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						100000,					2,					
			
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  0,					0,					0,					0,						0,						1000));	
	//
	
	
	// 
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						10000,					20,					
			
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  0,					0,					0,					0,						0,						1000));	
	
	// 
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					randomTree,			200000,				0,						10000,					10,					
			
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  100,					100,				4,					30,						5,						1000));		
	
	
	// 
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					shallowTree,		200000,				100,					100,					1000,					
			
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  10,					100,				5,					5,						2,						400));	
	
	// 
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					shallowTree,		200000,				100,					100,					1000,					
				
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  10,					100,				5,					5,						2,						400));	
		
	
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					deepTree,			200000,				50000,					33000,					2,					
				
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  2,					33000,				2,					5,						1,						40000));	
	
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					deepTree,			200000,				50000,					33000,					2,					
				
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  2,					33000,				2,					5,						1,						20000));	
	
	
	//             				  fileName; 			treeType; 			numVertices; 		numSpecialVertices; 	numRandomMinisters; 	sizeRandomMinisters; 
	isl.add(new InstanceSpecifier("", 					deepTree,			200000,				50000,					1000,					100,					
				
	//             				  numMinisterGroups; 	sizeMinisterGroups; numClusterCenters; 	clusterDistance; 		sizeClusters; 			k;		
								  1,					50000,				2,					100,						1,					20000));	
	
	return isl;
	
		
	
}



	
	
	
	
	public static void genGroup(ArrayList<InstanceSpecifier> isl, String groupDir) {
		String prefix = mainDir + groupDir + taskname + ".";
		String postfix = ".in";		

		for(int i = 0; i < isl.size(); ++i) { 
			InstanceSpecifier is = new InstanceSpecifier(isl.get(i));
			is.fileName = prefix + (i+1 < 10 ? "0" : "") + (i+1) + postfix;
			System.out.println("starting " + i);
			genInstance(is);
			System.out.println("done with " + i);
		}
	}
	
	
	
	
	// --------------------------------------------------------------------------
	
	
	
	
	
	public static void genInstance(InstanceSpecifier is) {
									
		RandomTreeGenerator rt = new RandomTreeGenerator();
		ArrayList<Integer> seq;
		
		if(is.treeType == randomTree) {
			seq = rt.randomTreeSequence(is.numVertices-1);
		} else if(is.treeType == shallowTree) {
			seq = rt.randomRootedShallowTreeSequence(is.numSpecialVertices, is.numVertices - is.numSpecialVertices);
		} else {
			seq = rt.randomDeepTreeSequence(is.numSpecialVertices, is.numVertices - is.numSpecialVertices);
		}
		
		ArrayList<ArrayList<Integer> > T = rt.sequenceToTree(seq);

		//System.out.println("made tree");
		
		
		MinisterGenerator mg = new MinisterGenerator(T);
		for(int i = 0; i < is.numRandomMinisters; ++i) mg.addRandomMinister(is.sizeRandomMinisters);
	
		for(int i = 0; i < is.numMinisterGroups; ++i) {
			mg.addMinisterGroup(is.sizeMinisterGroups, is.numClusterCenters, is.clusterDistance, is.sizeClusters);
		}
				
		//System.out.println("made ministers");
		
		
		ArrayList<ArrayList<Integer> > edgeListTree = rt.sequenceToTreeNoParent(seq);
		
		// TODO: ADD VERTEX NAME PERMUTATION
		
		
				
		try{
		    PrintWriter writer = new PrintWriter(is.fileName, "UTF-8");
		    writer.println(T.size() + " " + mg.ministers.size() + " " + is.k);
		    
		    // print edges
		    for(int i = 0; i < edgeListTree.size(); ++i) {
		    	for(int j = 0; j < edgeListTree.get(i).size(); ++j) {
		    		writer.println((i+1) + " " + (edgeListTree.get(i).get(j)+1));
		    	}
		    }
		    
		    // print ministers
		    for(ArrayList<Integer> cityList : mg.ministers) {
		    	writer.print(cityList.size());
		    	for(int i : cityList) {
		    		writer.print(" " + (i+1));
		    	}
		    	writer.println();
		    }

		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}

class InstanceSpecifier {
	public String fileName; 
	public int treeType;
	public int numVertices;
	public int numSpecialVertices;
	public int numRandomMinisters;
	public int sizeRandomMinisters;
	public int numMinisterGroups;
	public int sizeMinisterGroups;
	public int numClusterCenters;
	public int clusterDistance;
	public int sizeClusters; 
	public int k;
	
	public InstanceSpecifier(String fileName, 
							 int treeType,
							 int numVertices,
							 int numSpecialVertices,
							 int numRandomMinisters,
							 int sizeRandomMinisters,
							 int numMinisterGroups,
							 int sizeMinisterGroups,
							 int numClusterCenters,
							 int clusterDistance,
							 int sizeClusters, 
							 int k) {
		
				this.fileName = fileName; 
				this.treeType = treeType;
				this.numVertices = numVertices;
				this.numSpecialVertices = numSpecialVertices;
				this.numRandomMinisters = numRandomMinisters;
				this.sizeRandomMinisters = sizeRandomMinisters;
				this.numMinisterGroups = numMinisterGroups;
				this.sizeMinisterGroups = sizeMinisterGroups;
				this.numClusterCenters = numClusterCenters;
				this.clusterDistance = clusterDistance;
				this.sizeClusters = sizeClusters;
				this.k = k;
	}
	
	public InstanceSpecifier(InstanceSpecifier is) {
		this(	is.fileName, 
				is.treeType,
				is.numVertices,
				is.numSpecialVertices,
				is.numRandomMinisters,
				is.sizeRandomMinisters,
				is.numMinisterGroups,
				is.sizeMinisterGroups,
				is.numClusterCenters,
				is.clusterDistance,
				is.sizeClusters, 
				is.k);
	}
	
	
	
}


