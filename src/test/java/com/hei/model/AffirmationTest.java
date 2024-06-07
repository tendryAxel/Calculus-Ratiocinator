package com.hei.model;

import org.junit.jupiter.api.Test;

import static com.hei.model.Conjonction.ET;
import static com.hei.model.ValeurDeVerite.JENESAISPAS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AffirmationTest {

    @Test
    void peu_combiner_deux_je_ne_sais_pas() {
        var jeNeSaisPas1 = new Affirmation("l'eau est chaude", JENESAISPAS);
        var jeNeSaisPas2 = new Affirmation("l'eau est froide", JENESAISPAS);

        assertDoesNotThrow(()->{
            var combinaison = jeNeSaisPas1.combiner(ET, jeNeSaisPas2);

            assertEquals("l'eau est chaude et l'eau est froide", combinaison.getText());
            assertEquals(JENESAISPAS, combinaison.getValeurVerite());
        });

    }
}