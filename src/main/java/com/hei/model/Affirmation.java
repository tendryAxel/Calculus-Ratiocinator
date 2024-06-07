package com.hei.model;

import com.hei.erreur.NotImplemented;
import com.hei.erreur.ValeurDeVeriteNonDefinie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Affirmation {
    private final String text;
    private final ValeurDeVerite valeurVerite;

    public Affirmation combiner(Conjonction conjonction, Affirmation autreAffirmation){
        ValeurDeVerite nouvelleValeurDeVerite = ValeurDeVerite.JENESAISPAS;;
        if (this.valeurVerite != ValeurDeVerite.JENESAISPAS) {
            switch (conjonction){
                case OU -> {
                    if (this.valeurVerite == ValeurDeVerite.VRAIE || autreAffirmation.valeurVerite == ValeurDeVerite.VRAIE) {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    }
                }
                case ET -> {
                    if (this.valeurVerite == ValeurDeVerite.VRAIE && autreAffirmation.valeurVerite == ValeurDeVerite.VRAIE) {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    }
                }
                case DONC -> {
                    if (this.valeurVerite == ValeurDeVerite.VRAIE && autreAffirmation.valeurVerite == ValeurDeVerite.FAUX) {
                        nouvelleValeurDeVerite = ValeurDeVerite.FAUX;
                    } else {
                        nouvelleValeurDeVerite = ValeurDeVerite.VRAIE;
                    }
                }
                case null, default -> throw new ValeurDeVeriteNonDefinie();
            }
        }

        return new Affirmation(
                String.format("%s %s %s", this.text, conjonction.toString().toLowerCase(), autreAffirmation.text),
                nouvelleValeurDeVerite
        );
    }
}
