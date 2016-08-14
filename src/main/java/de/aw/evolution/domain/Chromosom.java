package de.aw.evolution.domain;

import java.util.HashMap;

/**
 * Chromosomen sind Makromolekülkomplexe, die Gene und somit auch Erbinformationen enthalten.
 * Sie bestehen aus DNA, die mit vielen Proteinen verpackt ist.
 * Diese Mischung aus DNA und Proteinen wird auch als Chromatin bezeichnet.
 *
 * Für das Modell reicht es im Moment ein Chromosom als eine Menge von Genen abzubilden,
 * die an jeweils an einem bestimmten GenLocus vorliegen.
 *
 * @author armin.weisser
 */
public class Chromosom extends HashMap<GeneLocus, Gene> {
}
