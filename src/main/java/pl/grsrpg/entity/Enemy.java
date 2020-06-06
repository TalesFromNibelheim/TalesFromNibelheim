package pl.grsrpg.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Enemy implements Entity, Cloneable {
    @JsonProperty
    protected String name;
    @JsonProperty
    protected float health;
    @JsonProperty
    protected int baseMaxHealth;

    @JsonProperty
    protected int baseStrength;
    @JsonProperty
    protected int baseAgility;
    @JsonProperty
    protected int baseMagicPoints;

    public Enemy(String name, int baseMaxHealth, int baseStrength, int baseAgility, int baseMagicPoints) {
        this.name = name;
        this.health = baseMaxHealth;
        this.baseMaxHealth = baseMaxHealth;
        this.baseStrength = baseStrength;
        this.baseAgility = baseAgility;
        this.baseMagicPoints = baseMagicPoints;
    }

    @Override
    public void takeDamage(float damage) {
        this.health -= (int) damage;
    }

    @Override
    public Enemy clone() {
        try{
            return (Enemy)super.clone();
        } catch (CloneNotSupportedException exception) {
            //ignore
        }
        return null;
    }
}
