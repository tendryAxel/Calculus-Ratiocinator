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

    public Affirmation combiner(Conjonction conjonction, Affirmation autre_affirmation){
        ValeurDeVerite nouvelleValeurDeVerite = ValeurDeVerite.JENESAISPAS;;
        if (this.valeurVerite != ValeurDeVerite.JENESAISPAS) {
            switch (conjonction){
                case ET, OU, DONC -> throw new NotImplemented();
                case null, default -> throw new ValeurDeVeriteNonDefinie();
            }
        }

        return new Affirmation(
                String.format("%s %s %s", this.text, conjonction.toString().toLowerCase(), autre_affirmation.text),
                nouvelleValeurDeVerite
        );
    }
}
