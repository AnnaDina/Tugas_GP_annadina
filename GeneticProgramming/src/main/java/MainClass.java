/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 *
 * @author Anna Dina
 */
import java.util.LinkedList;
import java.util.List;
import java.io.*;

import com.Genetic.gp.symbolic.SymbolicRegressionEngine;
import com.Genetic.gp.symbolic.SymbolicRegressionIterationListener;
import com.Genetic.gp.symbolic.TabulatedFunctionFitness;
import com.Genetic.gp.symbolic.Target;
import com.GeneticProgramming.gp.symbolic.interpreter.Expression;
import com.GeneticProgramming.gp.symbolic.interpreter.Functions;


public class MainClass {

	public static void main(String[] args) {

		// define training set
            int[] deret=new int[7];
            System.out.println("========================================");
            System.out.println("         Genetic Programming");
            System.out.println("========================================");
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader buf=new BufferedReader(isr);
            for (int i=0;i<=6;i++)
            {
                try{
                    System.out.print("Masukkan deret ke-"+(i+1)+" = ");
                    String s=buf.readLine();
                    deret[i]=Integer.parseInt(s);
                }catch(IOException e){}
            }
            System.out.println("=================================================================");
            System.out.println("Deret angka yang dimasukkan :");
            for (int i=0;i<=6;i++)
            {System.out.print("\t"+deret[i]);
            }
            System.out.println();
            System.out.println("=================================================================");
            
		TabulatedFunctionFitness fitness =
				new TabulatedFunctionFitness(
                                                new Target().when("x", 0).targetIs(deret[0]),
						new Target().when("x", 1).targetIs(deret[1]),
						new Target().when("x", 2).targetIs(deret[2]),
						new Target().when("x", 3).targetIs(deret[3]),
						new Target().when("x", 4).targetIs(deret[4]),
						new Target().when("x", 5).targetIs(deret[5]),
						new Target().when("x", 6).targetIs(deret[6]));

		SymbolicRegressionEngine engine =
				new SymbolicRegressionEngine(
						fitness,
						// define variables
						list("x"),
						// define base functions
						list(Functions.ADD, Functions.SUB, Functions.MUL, Functions.VARIABLE, Functions.CONSTANT));

		addListener(engine);

		// max iterations
		engine.evolve(100);
	}

	/**
	 * Track each iteration
	 */
	private static void addListener(SymbolicRegressionEngine engine) {
		engine.addIterationListener(new SymbolicRegressionIterationListener() {
			@Override
			public void update(SymbolicRegressionEngine engine) {

				Expression bestSyntaxTree = engine.getBestSyntaxTree();

				double currFitValue = engine.fitness(bestSyntaxTree);

				// log to console
				System.out.println(String.format("iterasi = %s \t fitness = %s \t function = %s",engine.getIteration(), currFitValue, bestSyntaxTree.print()));

				// halt condition
				if (currFitValue < 1) {
					engine.terminate();
                                        System.out.println("=================================================================");
                                        System.out.println("Hasil Proses Genetic Programming yaitu didapatkan ");
                                        System.out.println(String.format("Nilai fitness  = %s", currFitValue));
                                        System.out.println(String.format("Nilai function = %s", bestSyntaxTree.print()));
                                        System.out.println("=================================================================");

				}
			}
		});
	}

	private static <T> List<T> list(T... items) {
		List<T> list = new LinkedList<T>();
		for (T item : items) {
			list.add(item);
		}
		return list;
	}
}
