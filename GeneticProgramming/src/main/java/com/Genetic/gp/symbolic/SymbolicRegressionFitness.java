/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Genetic.gp.symbolic;
/**
 *
 * @author Anna Dina
 */

import com.lagodiuk.ga.Fitness;
import com.GeneticProgramming.gp.symbolic.interpreter.Context;
import com.GeneticProgramming.gp.symbolic.interpreter.Expression;

class SymbolicRegressionFitness implements Fitness<GpChromosome, Double> {

	private ExpressionFitness expressionFitness;

	public SymbolicRegressionFitness(ExpressionFitness expressionFitness) {
		this.expressionFitness = expressionFitness;
	}

	@Override
	public Double calculate(GpChromosome chromosome) {
		Expression expression = chromosome.getSyntaxTree();
		Context context = chromosome.getContext();
		return this.expressionFitness.fitness(expression, context);
	}

}
