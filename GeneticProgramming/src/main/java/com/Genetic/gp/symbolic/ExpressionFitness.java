/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Genetic.gp.symbolic;
/**
 *
 * @author Anna Dina
 */

import com.GeneticProgramming.gp.symbolic.interpreter.Context;
import com.GeneticProgramming.gp.symbolic.interpreter.Expression;

public interface ExpressionFitness {

	double fitness(Expression expression, Context context);

}
