package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class Graph {

	int sizeOfArray;
	List<Integer> adjListArray[];
	public static final ArrayList<Integer> counter = new ArrayList<Integer>();
	public static int time = 0;

	public Graph(int sizeOfArray) {
		this.sizeOfArray = sizeOfArray;

		adjListArray = new LinkedList[sizeOfArray];

		for (int i = 0; i < sizeOfArray; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	public void addEdge(Graph graph, int src, int dest) {

		graph.adjListArray[src].add(dest);

	}

	public void readLinkedList(int n, Graph graph, int temples) {

		if (n == 0) {
			System.out.println("Please enter a proper value");
		}
		int index = 0;
		int[] in = new int[2];
		if (counter.size() == 0) {

			counter.add(n);
			//index = smallest(n, graph);
			in = smallest(n, graph);
			counter.add(in[0] + 1);
			time = time + in[1];
			readLinkedList((in[0] + 1), graph,temples);

		} else if (counter.size() >= temples) {

			System.out.println();
			System.out.println("Time take" + time);
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(new GraphData(counter));
			f.setSize(1000, 1000);
			f.setLocation(500, 500);
			f.setVisible(true);

		} else {

			if (counter.contains(n)) {
				//index = smallest(n, graph);
				in = smallest(n,graph);
				if (!counter.contains(in[0] + 1)) {
					time = time + in[1];
					counter.add(in[0] + 1);
					readLinkedList(in[0] + 1, graph,temples);

				} else {

					readLinkedList((n), graph,temples);
				}
			}
		}
	}

	public void counter() {

		// create an iterator
		Iterator<Integer> iterator = counter.iterator();
		// check values
		while (iterator.hasNext()) {
			System.out.print(" " + iterator.next() + " ");
		}

	}

	public int[] smallest(int n, Graph graph) {
		int index = 0;
		int[] array = new int[2];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < adjListArray[n].size(); i++) {
			if (adjListArray[n].get(i) > 0) {
				if (min > adjListArray[n].get(i)) {
					min = adjListArray[n].get(i);
					index = i;
					array[0] = i;
				}
			}
		}
		array[1] = adjListArray[n].get(index);
		graph.adjListArray[n].set(index, -1);
		return array;
	}

	// A utility function to print the adjacency list
	// representation of graph
	public void printGraph(Graph graph) {
		for (int v = 0; v < graph.sizeOfArray; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.adjListArray[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

}
