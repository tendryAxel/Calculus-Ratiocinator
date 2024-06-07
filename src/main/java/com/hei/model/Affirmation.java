package com.hei.model;

import com.hei.erreur.ValeurDeVeriteNonDefinie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Affirmation {
    private final String text;
    private final ValeurDeVerite valeurVerite;

    static ValeurDeVerite combine(ValeurDeVerite valeur1, ValeurDeVerite valeur2, Conjonction conjonction) {
        ValeurDeVerite nouvelleValeurDeVerite = ValeurDeVerite.JENESAISPAS;;
        if (valeur1 != ValeurDeVerite.JENESAISPAS) {
            switch (conjonction){
                case OU -> {
                    if (valeur1 == ValeurDeVerite.VRAIE || valeur2 == ValeurDeVerite.VRAIE) {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    }
                }
                case ET -> {
                    if (valeur1 == ValeurDeVerite.VRAIE && valeur2 == ValeurDeVerite.VRAIE) {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    }
                }
                case DONC -> {
                    if (valeur1 == ValeurDeVerite.VRAIE && valeur2 == ValeurDeVerite.FAUX) {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    }
                }
                case null, default -> throw new ValeurDeVeriteNonDefinie();
            }
        }

        return nouvelleValeurDeVerite;
    }

    static Affirmation combine(Affirmation aff1, Affirmation aff2, Conjonction conjonction) {
        return new Affirmation(
                String.format("%s %s %s", aff1.text, conjonction.toString().toLowerCase(), aff2.text),
                combine(aff1.valeurVerite, aff2.valeurVerite, conjonction)
        );
    }

    public Affirmation combiner(Conjonction conjonction, Affirmation autreAffirmation){
        return combine(this, autreAffirmation, conjonction);
    }
}
