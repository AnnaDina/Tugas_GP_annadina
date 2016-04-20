/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GeneticProgramming.gp.symbolic.interpreter;
/**
 *
 * @author Anna Dina
 */

import java.util.List;

public interface Function {

	double eval(Expression expression, Context context);

	int argumentsCount();

	boolean isVariable();

	boolean isNumber();

	boolean isCommutative();

	String print(Expression expression);

	List<Double> getCoefficients(Expression expression);

	void setCoefficients(Expression expression, List<Double> coefficients, int startIndex);

	int coefficientsCount();

}
