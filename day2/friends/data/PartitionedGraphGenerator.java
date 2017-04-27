
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class PartitionedGraphGenerator 
{
	public static int MAX_PQ = 15; 
	public static int MAX_N = 2500; 
	public static int MAX_E = 15000; 
	public static int MAX_SUM_DEG = 30000; 
	
	public static void main(String[] args) throws Exception
	{
	  // generate("test1_true_smalln", 5, 1, 2, 0.85, true);
  //   generate("test2_true_smalln", 10, 2, 2, 0.95, true);
  //   generate("test3_true_smalln", 15, 2, 3, 1.00, true);
  //   generate("test4_true_smalln", 16, 3, 3, 0.85, true);
  //   generate("test5_false_smalln", 16, 4, 4, 0.95, false);
  //   generate("test6_false_smalln", 16, 3, 3, 0.85, false);
  //   generate("test7_false_stupid_case", 16, 5, 5, 0.85, true);
  //
  //   generate("test8_true_smalln_smallq", 100, 5, 0, 0.85, true);
  //   generate("test9_true_smalln_smallq", 250, 10, 1, 1.00, true);
  //   generate("test10_true_smalln_smallq", 200, 8, 2, 0.85, true);
  //   generate("test11_true_smalln_smallq", 150, 5, 2, 0.95, true);
  //   generate("test12_false_smalln_smallq", 250, 6, 2, 0.85, false);
  //   generate("test13_false_smalln_smallq", 250, 7, 2, 0.95, false);
  //   generate("test14_false_stupid_case", 250, 5, 2, 0.85, true);
  //
  //   generate("test15_true_smallq", 1000, 10, 0, 0.95, true);
  //   generate("test16_true_smallq", 1250, 8, 1, 1.00, true);
  //   generate("test17_true_smallq", 1200, 9, 2, 0.85, true);
  //   generate("test18_true_smallq", 1250, 9, 2, 0.85, true);
  //   generate("test19_false_smallq", 1800, 6, 2, 0.95, false);
  //   generate("test20_false_smallq", 1800, 7, 2, 0.80, false);
  //   generate("test21_false_stupid_case", 2000, 5, 2, 0.85, true);
  //
  //   generate("test22_true", 1900, 6, 5, 0.80, true);
  //   generate("test23_true", 1800, 5, 5, 1.00, true);
  //   generate("test24_true", 1650, 6, 5, 0.95, true);
  //   generate("test25_false", 1050, 10, 5, 0.85, false);
  //   generate("test26_false", 1300, 7, 7, 0.95, false);
  //   generate("test27_false", 1300, 9, 4, 0.85, false);
  //   generate("test28_false_stupid_case", 2000, 5, 5, 0.85, true);
  // generate("test31_true", 2500, 9, 6, 0.8, true);
  generate("test32_false", 2500, 9, 6, 0.8, false);
	}
	
	public static void generate(String file, int n, int p, int q, double prob, boolean type) throws Exception
	{
		if(n > MAX_N)
			throw new Exception("black magic");
		
		if(p <= 0)
			throw new Exception("black magic");
		
		if(p + q > MAX_PQ)
			throw new Exception("black magic");

		Random random = new Random();
		int used_edges = 0;
		int used_vertices = 0; 
		ArrayList<Integer> group_sizes = new ArrayList<Integer>();
		ArrayList<Integer> group_edges = new ArrayList<Integer>();
		
		while(used_vertices < n)
		{
			int group_size = 1 + random.nextInt(p);
			if(Math.random() < 0.75)
				group_size = p; 
			
			if(used_vertices + group_size <= n)
			{
				used_vertices += group_size; 
				group_sizes.add(group_size); 
				group_edges.add(0);
			}
		}
		
		int[][] edges = new int[group_sizes.size()][group_sizes.size()];
		boolean stop = false;
		
		for(int i = 0; i < group_sizes.size(); i++)
		{
			if(stop)
				break; 
			
			for(int j = i; j < group_sizes.size(); j++)
			{
				if(i == j)
					continue;
				
				int max_possible = Math.min((group_sizes.get(i) * group_sizes.get(j)) + 1, 
						Math.min(q - group_edges.get(i) + 1, q - group_edges.get(j) + 1));
				int temp = random.nextInt(max_possible);

				edges[i][j] = temp;
				edges[j][i] = edges[i][j];
				
				group_edges.set(i, group_edges.get(i) + edges[j][i]);
				group_edges.set(j, group_edges.get(j) + edges[j][i]);
			}
		}
		
		for(int i = 0; i < group_edges.size(); i++)
			if(group_edges.get(i) > q)
				throw new Exception("black magic");

		int[][] graph = new int[used_vertices][used_vertices];
		int[] components = new int[used_vertices]; 
		ArrayList<ArrayList<Integer>> comp_lists = new ArrayList<ArrayList<Integer>>();
		
		int counter = 0; 

		for(int i = 0; i < group_sizes.size(); i++)
		{
			int group_size = group_sizes.get(i);
			ArrayList<Integer> comp = new ArrayList<Integer>();
			
			for(int j = counter; j < counter + group_size; j++)
			{
				components[j] = i; 
				comp.add(j);
			}
			
			comp_lists.add(comp);
			counter += group_size;
		}
		
		for(int i = 0; i < graph.length; i++)
		{
			for(int j = i; j < graph.length; j++)
			{
				if(i == j)
					continue;

				if(components[i] == components[j])
				{
					if(Math.random() < prob)
					{
						graph[i][j] = 1;
						graph[j][i] = 1;
						used_edges++;
					}
					else
					{
						graph[i][j] = 0;
						graph[j][i] = 0;
					}
				}
			}
		}
		
		for(int i = 0; i < group_sizes.size(); i++)
		{
			for(int j = i; j < group_sizes.size(); j++)
			{
				if(i == j)
					continue;
				
				int edges_between_groups = edges[i][j];

				while(edges_between_groups > 0)
				{
					int vertex_in_group_i = comp_lists.get(i).get(random.nextInt(comp_lists.get(i).size()));
					int vertex_in_group_j = comp_lists.get(j).get(random.nextInt(comp_lists.get(j).size()));

					if(graph[vertex_in_group_i][vertex_in_group_j] == 0) 
					{
						graph[vertex_in_group_i][vertex_in_group_j] = 1;
						graph[vertex_in_group_j][vertex_in_group_i] = 1; 
						edges_between_groups--;
						used_edges++;
					}
				}
			}
		}

		if(type == false)
		{
			int bad_group = random.nextInt(group_sizes.size());
			for(int i = 0; i < group_sizes.size(); i++)
			{
				if(group_sizes.get(i) > group_sizes.get(bad_group))
					bad_group = i;
			}
			
			if(group_sizes.get(bad_group) != p)
				throw new Exception("could not find a group to make bad");
			
			for(int i = 0; i < graph.length; i++)
			{
				for(int j = i; j < graph.length; j++)
				{
					if(i == j)
						continue;

					if(components[i] == components[j] && components[i] == bad_group)
					{
						if(graph[i][j] == 0)
						{
							graph[i][j] = 1;
							graph[j][i] = 1;
							used_edges++;
						}
					}
				}
			}
			
			int group_out_edges = 0; 
			
			for(int i = 0; i < graph.length; i++)
			{
				for(int j = i; j < graph.length; j++)
				{
					if(i == j)
						continue;

					if(components[i] == components[j])
						continue;
					
					if(graph[i][j] != 1)
						continue; 
					
					if(components[i] == bad_group && components[j] != bad_group)
						group_out_edges++; 
					
					if(components[j] == bad_group && components[i] != bad_group)
						group_out_edges++; 
				}
			}
			
			while(group_out_edges <= q)
			{
				int vertex_in_bad_group = comp_lists.get(bad_group).get(random.nextInt(comp_lists.get(bad_group).size()));
				int random_group = random.nextInt(comp_lists.size()); 
				int some_other_vertex = comp_lists.get(random_group).get(random.nextInt(comp_lists.get(random_group).size()));
				
				if(random_group != bad_group && graph[vertex_in_bad_group][some_other_vertex] == 0)
				{
					graph[vertex_in_bad_group][some_other_vertex] = 1;
					graph[some_other_vertex][vertex_in_bad_group] = 1;
					used_edges++;
					group_out_edges++; 
				}
			}
			
			group_out_edges = 0; 
			
			for(int i = 0; i < graph.length; i++)
			{
				for(int j = i; j < graph.length; j++)
				{
					if(i == j)
						continue;

					if(components[i] == components[j])
						continue;
					
					if(graph[i][j] != 1)
						continue; 
					
					if(components[i] == bad_group && components[j] != bad_group)
						group_out_edges++; 
					
					if(components[j] == bad_group && components[i] != bad_group)
						group_out_edges++; 
				}
			}
			
			System.out.println("Bad group: " + bad_group);
			System.out.println("Bad group size: " + group_sizes.get(bad_group));
			System.out.println("Bad group edges: " + group_out_edges);
		}
		
		int checksum = 0;
		
		try
		{
		    PrintWriter writer = new PrintWriter(file + ".in", "UTF-8");
		    writer.println(used_vertices + " " + p + " " + q);

			for(int i = 0; i < graph.length; i++)
			{
				int friends = 0; 
				StringBuilder list_of_friends = new StringBuilder();
				
				for(int j = 0; j < graph.length; j++)
				{
					if(i == j)
						continue;
					
					if(graph[i][j] == 1)
					{
						friends++; 
						if(friends == 1)
							list_of_friends.append(j);
						else
							list_of_friends.append(" " + j);
					}
				}
				
				checksum += friends;
				if(friends == 0)
					writer.println(friends);
				else
					writer.println(friends + " " + list_of_friends.toString());
			}
			
		    writer.close();
		} 
		catch (Exception e) 
		{
		   e.printStackTrace();
		}
		
		try
		{
		    PrintWriter writer = new PrintWriter(file + ".ans", "UTF-8");
		    if(type == true)
		    	writer.println("home");
		    else
		    	writer.println("detention");
		    writer.close();
		} 
		catch (Exception e) 
		{
		   e.printStackTrace();
		}

		if(used_edges > MAX_E || checksum > MAX_SUM_DEG)
			throw new Exception("black magic");
		
		System.out.println("N: " + n);
		System.out.println("P: " + p);
		System.out.println("Q: " + q);
		System.out.println("groups: " + group_sizes.size());
		System.out.println("used_vertices: " + used_vertices);
		System.out.println("used_edges: " + used_edges);
		System.out.println("checksum: " + checksum);
	}
}
