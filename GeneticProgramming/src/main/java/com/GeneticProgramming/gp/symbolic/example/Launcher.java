/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GeneticProgramming.gp.symbolic.example;
/**
 *
 * @author Anna Dina
 */

import java.util.LinkedList;
import java.util.List;

import com.Genetic.gp.symbolic.SymbolicRegressionEngine;
import com.Genetic.gp.symbolic.SymbolicRegressionIterationListener;
import com.GeneticProgramming.gp.symbolic.interpreter.Expression;
import com.GeneticProgramming.gp.symbolic.interpreter.Functions;

public class Launcher {

	public static void main(String[] args) {
		SymbolicRegressionEngine sr = new SymbolicRegressionEngine(new TestExpressionFitness2(), list("x", "y"), list(Functions.values()));

		sr.addIterationListener(new SymbolicRegressionIterationListener() {
			private double prevFitValue = -1;

			@Override
			public void update(SymbolicRegressionEngine engine) {
				Expression bestSyntaxTree = engine.getBestSyntaxTree();
				double currFitValue = engine.fitness(bestSyntaxTree);
				if (Double.compare(currFitValue, this.prevFitValue) != 0) {
					System.out.println("Func = " + bestSyntaxTree.print());
				}
				System.out.println(String.format("%s \t %s", engine.getIteration(), currFitValue));
				this.prevFitValue = currFitValue;
				if (currFitValue < 10) {
					engine.terminate();
				}
			}
		});

		sr.evolve(200);
		System.out.println(sr.getBestSyntaxTree().print());
	}

	private static <T> List<T> list(T... items) {
		List<T> list = new LinkedList<T>();
		for (T item : items) {
			list.add(item);
		}
		return list;
	}
}
