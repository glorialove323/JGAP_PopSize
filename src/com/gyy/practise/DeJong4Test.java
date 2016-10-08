/**
 * 
 */
package com.gyy.practise;

import java.util.ArrayList;
import java.util.List;

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
public class DeJong4Test {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String args[]) throws Exception {
        // 构造configuration对象
        Configuration conf = new DefaultConfiguration();
        // 是否保留最佳的个体
        conf.setPreservFittestIndividual(true);

        // 确定自己的适应度函数
        FitnessFunction mFitFunction = new DeJong4Function();
        conf.setFitnessFunction(mFitFunction);

        // 构建基因
        Gene[] mGene = new Gene[30];
        for(int i =0;i<30;i++){
            mGene[i] = new DoubleGene(conf, -1.28, 1.28);
        }

        // 构建染色体
        IChromosome mChromosome = new Chromosome(conf, mGene);
        conf.setSampleChromosome(mChromosome);

        // 定义种群的大小，也就是染色体的个数
        conf.setPopulationSize(20);

        // 初始化种群
        // 构建基因型
        Genotype mPopulation;
        mPopulation = Genotype.randomInitialGenotype(conf);

        // 开始进化，并计算运行时间
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            mPopulation.evolve();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("the total evolve time: " + (endTime - startTime));

        // 打印输出最终的最佳的染色体
        IChromosome bestSolutionSoFar = mPopulation.getFittestChromosome();
        System.out.println("The best solution has a fitness value of " + bestSolutionSoFar.getFitnessValue());

        // 打印输出每个基因的表现型
        List xList = new ArrayList();
        for(int i=0;i<30;i++){
            Double xVal = (Double) bestSolutionSoFar.getGene(i).getAllele();
            xList.add(xVal);
        }

        for(int j =0;j<mChromosome.size();j++){
            System.out.println("x1= "+xList.get(j));
        }
    }
}
