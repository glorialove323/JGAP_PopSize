/**
 * Rosenbrock's valley is a classic optimization problem, 
 * also known as Banana function. 
 * The global optimum is inside a long, narrow, parabolic shaped flat valley. 
 * To find the valley is trivial, 
 * however convergence to the global optimum is difficult and hence this problem 
 * has been repeatedly used in assess the performance of optimization algorithms.
 * function definition
 * f2(x)=sum(100·(x(i+1)-x(i)^2)^2+(1-x(i))^2), i=1:n-1;
 * -2.048<=x(i)<=2.048.
 * global minimum
 * f(x)=0; x(i)=1, i=1:n.
 */
package com.gyy.practise;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 * @author Gloria
 *
 */
public class DeJong2Function extends FitnessFunction{

    private static final long serialVersionUID = 3558754470728964047L;

    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub
        
        int numOfGens = a_chromosome.size();
        if(numOfGens !=2){
           throw new IllegalArgumentException("Chromosome for "
                    + "MaximizingFunction must have " + "exactly 2 genes.");
        }
        Double x1Val = (Double) a_chromosome.getGene(0).getAllele();
        Double x2Val = (Double) a_chromosome.getGene(1).getAllele();   
        Double x1 = 100 * (x1Val * x1Val - x2Val) * (x1Val * x1Val - x2Val);
        Double x2 = (1 - x1Val) * (1 - x1Val);
        return -(x1+x2);
    }

}
