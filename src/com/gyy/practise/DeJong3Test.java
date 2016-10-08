/**
 * 
 */
package com.gyy.practise;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

/**
 * @author Gloria
 *
 */
public class DeJong3Test {
    public static void main (String args[]) throws Exception{
        //构造configuration对象
        Configuration conf = new DefaultConfiguration();
        //是否保留最佳的个体
        conf.setPreservFittestIndividual(true);
        
        //确定自己的适应度函数
        FitnessFunction mFitFunction = new DeJong3Function();
        conf.setFitnessFunction(mFitFunction);
        
        //构建基因
        Gene[] mGene = new Gene[5];
        mGene[0] = new DoubleGene(conf, -5.12, 5.12);
        mGene[1] = new DoubleGene(conf, -5.12, 5.12);
        mGene[2] = new DoubleGene(conf, -5.12, 5.12);
        mGene[3] = new DoubleGene(conf, -5.12, 5.12);
        mGene[4] = new DoubleGene(conf, -5.12, 5.12);
        
        //构建染色体
        IChromosome mChromosome = new Chromosome(conf, mGene);
        conf.setSampleChromosome(mChromosome);
        
        //定义种群的大小，也就是染色体的个数
        conf.setPopulationSize(20);
        
        //初始化种群
        //构建基因型
        Genotype mPopulation;
        mPopulation = Genotype.randomInitialGenotype(conf);
        
        //开始进化，并计算运行时间
        long startTime = System.currentTimeMillis();
        for(int i = 0;i<100;i++){
            mPopulation.evolve();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("the total evolve time: "+(endTime-startTime));
        
        //打印输出最终的最佳的染色体
        IChromosome bestSolutionSoFar = mPopulation.getFittestChromosome();
        System.out.println("The best solution has a fitness value of "
                + bestSolutionSoFar.getFitnessValue());
        
        //打印输出每个基因的表现型
        Double x1Val = (Double) bestSolutionSoFar.getGene(0).getAllele();
        Double x2Val = (Double) bestSolutionSoFar.getGene(1).getAllele();
        Double x3Val = (Double) bestSolutionSoFar.getGene(2).getAllele();
        Double x4Val = (Double) bestSolutionSoFar.getGene(1).getAllele();
        Double x5Val = (Double) bestSolutionSoFar.getGene(2).getAllele();
        
        System.out.println("x1 = " + x1Val.doubleValue());
        System.out.println("x2 = " + x2Val.doubleValue());
        System.out.println("x3 = " + x3Val.doubleValue());
        System.out.println("x4 = " + x4Val.doubleValue());
        System.out.println("x5 = " + x5Val.doubleValue());       
    }
}
