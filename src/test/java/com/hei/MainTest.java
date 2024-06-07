package com.hei;

import com.hei.model.Affirmation;
import org.junit.jupiter.api.Test;

import static com.hei.model.Conjonction.*;
import static com.hei.model.ValeurDeVerite.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void exercice1 (){
        var textAffirmation1 = "Lou est pauvre";
        var conjonction = ET;
        var textAffirmation2 = "Lou est généreux";

        var textAVerifier = String.format("%s %s %s", textAffirmation1, conjonction.toString().toLowerCase(), textAffirmation2);

        var affirmation1 = new Affirmation(textAffirmation1, FAUX);
        var affirmation2 = new Affirmation(textAffirmation2, JENESAISPAS);

        var result = affirmation1.combiner(conjonction, affirmation2);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice2 (){
        var textAffirmation1 = "Lou est beau";
        var conjonction = DONC;
        var textAffirmation2 = "Lou est pauvre";

        var textAVerifier = String.format("%s %s %s", textAffirmation1, conjonction.toString().toLowerCase(), textAffirmation2);

        var affirmation1 = new Affirmation(textAffirmation1, VRAIE);
        var affirmation2 = new Affirmation(textAffirmation2, FAUX);

        var result = affirmation1.combiner(conjonction, affirmation2);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice3 (){
        var textAffirmation1 = "Lou est pauvre";
        var conjonction = DONC;
        var textAffirmation2 = "Lou est généreux";

        var textAVerifier = String.format("%s %s %s", textAffirmation1, conjonction.toString().toLowerCase(), textAffirmation2);

        var affirmation1 = new Affirmation(textAffirmation1, FAUX);
        var affirmation2 = new Affirmation(textAffirmation2, JENESAISPAS);

        var result = affirmation1.combiner(conjonction, affirmation2);

        assertEquals(textAVerifier, result.getText());
        assertEquals(VRAIE, result.getValeurVerite());
    }

    @Test
    void exercice4 (){
        var textAffirmation1 = "Lou est beau";
        var conjonction1 = OU;
        var textAffirmation2 = "Lou est généreux";
        var conjonction2 = DONC;
        var textAffirmation3 = "Lou est pauvre";

        var textAVerifier = String.format(
                "%s %s %s %s %s",
                textAffirmation1, conjonction1.toString().toLowerCase(), textAffirmation2, conjonction2.toString().toLowerCase(), textAffirmation3
        );

        var affirmation1 = new Affirmation(textAffirmation1, VRAIE);
        var affirmation2 = new Affirmation(textAffirmation2, JENESAISPAS);
        var affirmation3 = new Affirmation(textAffirmation3, FAUX);

        var result = affirmation1.combiner(conjonction1, affirmation2).combiner(conjonction2, affirmation3);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }
}