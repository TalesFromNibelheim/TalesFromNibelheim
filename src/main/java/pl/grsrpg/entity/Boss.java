package pl.grsrpg.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import pl.grsrpg.utils.DiceRoll;

@NoArgsConstructor
public class Boss extends Enemy {
    @JsonProperty
    private float multiplier = 1F;

    public Boss(String name, int baseMaxHealth, int baseStrength, int baseAgility, int baseMagicPoints) {
        super(name, baseMaxHealth, baseStrength, baseAgility, baseMagicPoints);
    }

    public float headHunter(float damage) {
        if (DiceRoll.rollPrivate(1, 6) >= 4) {
            System.out.println("My body desire your soul!!!");
            return damage * 1.4F;
        } else return damage * 1.1F;
    }

    public float rage(float damage) {
        if (getHealth() < getBaseMaxHealth() * 0.25) {
            System.out.println("Try to stop me now!!!!!!");
            return damage * multiplier;
        } else return damage;
    }

    public float finalShot(float damage) {
        return damage * multiplier * multiplier;
    }

    public void setMultiplier(float multiplier){
        this.multiplier = multiplier;
    }
}