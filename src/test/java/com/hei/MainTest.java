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

        var estPauvre = new Affirmation(textAffirmation1, FAUX);
        var estGenereux = new Affirmation(textAffirmation2, JENESAISPAS);

        var result = estPauvre.combiner(conjonction, estGenereux);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice2 (){
        var textAffirmation1 = "Lou est beau";
        var conjonction = DONC;
        var textAffirmation2 = "Lou est pauvre";

        var textAVerifier = String.format("%s %s %s", textAffirmation1, conjonction.toString().toLowerCase(), textAffirmation2);

        var estBeau = new Affirmation(textAffirmation1, VRAIE);
        var estPauvre = new Affirmation(textAffirmation2, FAUX);

        var result = estBeau.combiner(conjonction, estPauvre);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice3 (){
        var textAffirmation1 = "Lou est pauvre";
        var conjonction = DONC;
        var textAffirmation2 = "Lou est généreux";

        var textAVerifier = String.format("%s %s %s", textAffirmation1, conjonction.toString().toLowerCase(), textAffirmation2);

        var estPauvre = new Affirmation(textAffirmation1, FAUX);
        var estGenereux = new Affirmation(textAffirmation2, JENESAISPAS);

        var result = estPauvre.combiner(conjonction, estGenereux);

        assertEquals(textAVerifier, result.getText());
        assertEquals(VRAIE, result.getValeurVerite());
    }

    @Test
    void exercice4 (){
        var textAffirmation1 = "Lou est beau";
        var conjonctino_ou = OU;
        var textAffirmation2 = "Lou est généreux";
        var conjonction_donc = DONC;
        var textAffirmation3 = "Lou est pauvre";

        var textAVerifier = String.format(
                "%s %s %s %s %s",
                textAffirmation1, conjonctino_ou.toString().toLowerCase(), textAffirmation2, conjonction_donc.toString().toLowerCase(), textAffirmation3
        );

        var estBeau = new Affirmation(textAffirmation1, VRAIE);
        var estGenereux = new Affirmation(textAffirmation2, JENESAISPAS);
        var estPauvre = new Affirmation(textAffirmation3, FAUX);

        var result = Affirmation.combiner(estBeau, conjonctino_ou, estGenereux).combiner(conjonction_donc, estPauvre);

        assertEquals(textAVerifier, result.getText());
        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice4_plus_simple (){
        var estBeau = new Affirmation("Lou est beau", VRAIE);
        var estGenereux = new Affirmation("Lou est généreux", JENESAISPAS);
        var estPauvre = new Affirmation("Lou est pauvre", FAUX);

        var result = Affirmation.combiner(estBeau, OU, estGenereux).combiner(DONC, estPauvre);

        assertEquals(FAUX, result.getValeurVerite());
    }

    @Test
    void exercice5_plus_simple (){
        var estBeau = new Affirmation("Lou est beau", VRAIE);
        var estGenereux = new Affirmation("Lou est généreux", JENESAISPAS);
        var estPauvre = new Affirmation("Lou est pauvre", FAUX);

        var premierement = Affirmation.combiner(estBeau, OU, estGenereux).combiner(DONC, estPauvre);
        var deuxiemement = Affirmation.combiner(estPauvre, OU, estGenereux);

        var result = Affirmation.combiner(premierement, ET, deuxiemement);

        assertEquals(FAUX, result.getValeurVerite());
    }
}