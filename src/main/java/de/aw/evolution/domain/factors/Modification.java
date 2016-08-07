package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Environment;
import de.aw.evolution.domain.Genom;
import de.aw.evolution.domain.Phenotype;

/**
 * Eine Modifikation ist eine durch Umweltfaktoren hervorgerufene Veränderung des Phänotyps, des Erscheinungsbildes eines Lebewesens.
 * Dabei werden die Gene nicht verändert, das bedeutet, dass eine Modifikation – anders als eine Veränderung durch Mutation – nicht vererbbar ist,
 * eine epigenetische Weitergabe dieser Veränderung ist aber nicht auszuschließen.
 *
 * @author armin.weisser
 */
public interface Modification {

    void apply(Environment environment, Phenotype phenotype, Genom genom);

}
