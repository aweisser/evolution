package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGenom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class CoupleTest {

    @Test
    public void aCoupleContainsTwoGenoms() {
        Genom mother = aGenom();
        Genom father = aGenom();
        Couple couple = new Couple(mother, father);
        assertThat(couple.getMother(), is(equalTo(mother)));
        assertThat(couple.getFather(), is(equalTo(father)));
    }

}
