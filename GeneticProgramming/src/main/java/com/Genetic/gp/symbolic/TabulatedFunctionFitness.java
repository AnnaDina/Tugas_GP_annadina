/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Genetic.gp.symbolic;
/**
 *
 * @author Anna Dina
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.GeneticProgramming.gp.symbolic.interpreter.Context;
import com.GeneticProgramming.gp.symbolic.interpreter.Expression;

public class TabulatedFunctionFitness implements ExpressionFitness {

	private List<Target> targets = new LinkedList<Target>();

	public TabulatedFunctionFitness(Target... targets) {
		for (Target target : targets) {
			this.targets.add(target);
		}
	}

	public TabulatedFunctionFitness(List<Target> targets) {
		this.targets.addAll(targets);
	}

	@Override
	public double fitness(Expression expression, Context context) {
		double diff = 0;

		for (Target target : this.targets) {
			for (Entry<String, Double> e : target.getContextState().entrySet()) {
				String variableName = e.getKey();
				Double variableValue = e.getValue();
				context.setVariable(variableName, variableValue);
			}
			double targetValue = target.getTargetValue();
			double calculatedValue = expression.eval(context);
			diff += this.sqr(targetValue - calculatedValue);
		}

		return diff;
	}

	private double sqr(double x) {
		return x * x;
	}

}
