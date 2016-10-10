/**
 * 
 */
package com.gyy.lifetime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgap.BaseGeneticOperator;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.ICompositeGene;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.RandomGenerator;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.MutationOperator;
import org.jgap.impl.StockRandomGenerator;

/**
 * @author Gloria
 * 
 */
@SuppressWarnings("rawtypes")
public class RecombineOperator extends BaseGeneticOperator implements Comparable {

    private static final long serialVersionUID = 1L;

    private double m_reproductionRatio;// p = 0.4

    private double m_crossoverRate;// p = 0.65

    private double m_mutationRate;// p = 0.015

    public RecombineOperator() throws InvalidConfigurationException {
        super(Genotype.getStaticConfiguration());
    }

    public RecombineOperator(Configuration a_configuration) throws InvalidConfigurationException {
        super(a_configuration);
        // TODO Auto-generated constructor stub
    }

    public RecombineOperator(final Configuration a_configuration, final double a_desiredReproductionRatio,
            final double a_desiredCrossoverRate, final double a_desiredMutationRate)
            throws InvalidConfigurationException {
        super(a_configuration);
        m_reproductionRatio = a_desiredReproductionRatio;
        m_crossoverRate = a_desiredCrossoverRate;
        m_mutationRate = a_desiredMutationRate;
    }

    public void operate(Population a_population, List a_candidateChromosomes) {
        System.out.println("every time a_population size: " + a_population.size());

        int auxPopSize = (int) (a_population.size() * m_reproductionRatio);

        List<IChromosome> oldChromList = new ArrayList<IChromosome>();
        List<IChromosome> newChromList = new ArrayList<IChromosome>();

        for (int i = 0; i < a_population.size(); i++) {
            oldChromList.add(a_population.getChromosome(i));
        }

        /*
         * 交叉操作
         */
        RandomGenerator generator = new StockRandomGenerator();

        for (int i = 0; i < oldChromList.size(); i++) {
            int index1 = generator.nextInt(oldChromList.size());
            double rate = Math.random();
            IChromosome chrom1 = oldChromList.get(index1);
            if (rate >= m_crossoverRate) {
                IChromosome firstMate = (IChromosome) chrom1.clone();
                int index2 = generator.nextInt(oldChromList.size());
                IChromosome chrom2 = oldChromList.get(index2);
                IChromosome secondMate = (IChromosome) chrom2.clone();
                doCrossover(firstMate, secondMate, newChromList, generator);
            }
            continue;
        }
        try {
            MutationOperator operator = new MutationOperator(getConfiguration(), 66);
            operator.operate(a_population, a_candidateChromosomes);
        } catch (InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        
        for (int i = 0; i < auxPopSize; i++) {
            a_population.addChromosome(newChromList.get((int) (Math.random() * newChromList.size())));
        }

        System.out.println("AuxPopSize: " + auxPopSize + " " + "newPopSize: " + a_population.size());
    }

    /*
     * recombine操作过程 pop(t),每个个体被选中的概率是一样的，用于交叉和变异，那就所有的个体都进行交叉和变异
     * aux(t),在交叉和变异后的个体，选择ratio * Popsize 个个体
     * 
     * // recombine: 生成AuxPopulation public void operate(Population
     * a_population, List a_candidateChromosomes) { // TODO Auto-generated
     * method stub
     * 
     * System.out.println("every time a_population size: " +
     * a_population.size());
     * 
     * int auxPopSize = (int) (a_population.size() * m_reproductionRatio);
     * List<IChromosome> oldChromList = new ArrayList<IChromosome>();
     * List<IChromosome> newChromList = new ArrayList<IChromosome>(); for (int i
     * = 0; i < auxPopSize / 2; i++) {
     * oldChromList.add(a_population.getChromosome((int) Math.random() *
     * a_population.size())); }
     * 
     * // 选择出来的个体进行交叉操作 RandomGenerator generator = new StockRandomGenerator();
     * 
     * for (int i = 0; i < oldChromList.size(); i++) { int index1 =
     * generator.nextInt(oldChromList.size()); int index2 =
     * generator.nextInt(oldChromList.size()); IChromosome chrom1 =
     * oldChromList.get(index1); IChromosome chrom2 = oldChromList.get(index2);
     * if (chrom1.getAge() < 1 && chrom2.getAge() < 1) { continue; } IChromosome
     * firstMate = (IChromosome) chrom1.clone(); IChromosome secondMate =
     * (IChromosome) chrom2.clone(); doCrossover(firstMate, secondMate,
     * newChromList, generator); }
     * 
     * for (int i = 0; i < newChromList.size(); i++) {
     * a_population.addChromosome(newChromList.get(i)); } // 交叉后的个体进行变异操作 try {
     * MutationOperator operator = new MutationOperator(getConfiguration(), 66);
     * operator.operate(a_population, a_candidateChromosomes); } catch
     * (InvalidConfigurationException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); }
     * 
     * System.out.println("AuxPopSize: " + auxPopSize + " " + "newPopSize: " +
     * a_population.size()); }
     */

    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    class RecombineOperatorConfigurable implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        public double m_reproductionRatio;
    }

    protected void doCrossover(IChromosome firstMate, IChromosome secondMate, List a_candidateChromosomes,
            RandomGenerator generator) {
        Gene[] firstGenes = firstMate.getGenes();
        Gene[] secondGenes = secondMate.getGenes();
        int locus = generator.nextInt(firstGenes.length);
        // Swap the genes.
        // ---------------
        Gene gene1;
        Gene gene2;
        Object firstAllele;
        for (int j = locus; j < firstGenes.length; j++) {
            // Make a distinction for ICompositeGene for the first gene.
            // ---------------------------------------------------------
            int index = 0;
            if (firstGenes[j] instanceof ICompositeGene) {
                // Randomly determine gene to be considered.
                // -----------------------------------------
                index = generator.nextInt(firstGenes[j].size());
                gene1 = ((ICompositeGene) firstGenes[j]).geneAt(index);
            } else {
                gene1 = firstGenes[j];
            }
            // Make a distinction for the second gene if CompositeGene.
            // --------------------------------------------------------
            if (secondGenes[j] instanceof ICompositeGene) {
                gene2 = ((ICompositeGene) secondGenes[j]).geneAt(index);
            } else {
                gene2 = secondGenes[j];
            }
            if (m_monitorActive) {
                gene1.setUniqueIDTemplate(gene2.getUniqueID(), 1);
                gene2.setUniqueIDTemplate(gene1.getUniqueID(), 1);
            }
            firstAllele = gene1.getAllele();
            gene1.setAllele(gene2.getAllele());
            gene2.setAllele(firstAllele);
        }
        // Add the modified chromosomes to the candidate pool so that
        // they'll be considered for natural selection during the next
        // phase of evolution.
        // -----------------------------------------------------------
        a_candidateChromosomes.add(firstMate);
        a_candidateChromosomes.add(secondMate);
    }
}
