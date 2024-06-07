package com.hei.model;

import org.junit.jupiter.api.Test;

import static com.hei.model.Conjonction.*;
import static com.hei.model.ValeurDeVerite.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AffirmationTest {

    @Test
    void peu_combiner_deux_je_ne_sais_pas() {
        var jeNeSaisPas1 = new Affirmation("l'eau est chaude", JENESAISPAS);
        var jeNeSaisPas2 = new Affirmation("l'eau est froide", JENESAISPAS);

        var combinaison = jeNeSaisPas1.combiner(ET, jeNeSaisPas2);

        assertEquals("l'eau est chaude et l'eau est froide", combinaison.getText());
        assertEquals(JENESAISPAS, combinaison.getValeurVerite());
    }

    @Test
    void peu_combiner_deux_avec_la_conjonction_OU() {
        var verite = new Affirmation("le soleil est chaud", VRAIE);
        var faux = new Affirmation("le soleil tourne autour de la terre", FAUX);

        var combinaison = verite.combiner(OU, faux);

        assertEquals("le soleil est chaud ou le soleil tourne autour de la terre", combinaison.getText());
        assertEquals(VRAIE, combinaison.getValeurVerite());
    }

    @Test
    void peu_combiner_deux_avec_la_conjonction_ET_de_vrai_et_vrai() {
        var verite = new Affirmation("le soleil est chaud", VRAIE);
        var autreVerite = new Affirmation("la terre tourne autour du soleil", VRAIE);

        var combinaison = verite.combiner(ET, autreVerite);

        assertEquals("le soleil est chaud et la terre tourne autour du soleil", combinaison.getText());
        assertEquals(VRAIE, combinaison.getValeurVerite());
    }

    @Test
    void peu_combiner_deux_avec_la_conjonction_ET_de_vrai_et_faux() {
        var verite = new Affirmation("le soleil est chaud", VRAIE);
        var faux = new Affirmation("le soleil tourne autour de la terre", FAUX);

        var combinaison = verite.combiner(ET, faux);

        assertEquals("le soleil est chaud et le soleil tourne autour de la terre", combinaison.getText());
        assertEquals(FAUX, combinaison.getValeurVerite());
    }

    @Test
    void peu_combiner_deux_avec_la_conjonction_DONC_de_vrai_donc_vrai() {
        var verite = new Affirmation("le soleil est chaud", VRAIE);
        var autreVerite = new Affirmation("la terre tourne autour du soleil", VRAIE);

        var combinaison = verite.combiner(DONC, autreVerite);

        assertEquals("le soleil est chaud donc la terre tourne autour du soleil", combinaison.getText());
        assertEquals(VRAIE, combinaison.getValeurVerite());
    }

    @Test
    void peu_combiner_deux_avec_la_conjonction_DONC_de_vrai_donc_faux() {
        var verite = new Affirmation("le soleil est chaud", VRAIE);
        var faux = new Affirmation("le soleil tourne autour de la terre", FAUX);

        var combinaison = verite.combiner(DONC, faux);

        assertEquals("le soleil est chaud donc le soleil tourne autour de la terre", combinaison.getText());
        assertEquals(FAUX, combinaison.getValeurVerite());
    }
}