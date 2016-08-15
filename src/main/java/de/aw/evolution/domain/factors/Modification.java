package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Environment;
import de.aw.evolution.domain.model.Organism;
import de.aw.evolution.domain.model.Phenotype;

/**
 * Eine Modifikation ist eine durch Umweltfaktoren hervorgerufene Veränderung des Phänotyps, des Erscheinungsbildes eines Lebewesens.
 * Dabei werden die Gene nicht verändert, das bedeutet, dass eine Modifikation – anders als eine Veränderung durch Mutation – nicht vererbbar ist,
 * eine epigenetische Weitergabe dieser Veränderung ist aber nicht auszuschließen.
 *
 * @author armin.weisser
 */
public interface Modification {

    Phenotype apply(Environment environment, Organism organism);

}
