package pl.grsrpg.card;

public class GameCardItem extends GameCard {
    private float health;
    private float strength;
    private float agility;
    private float magicPoints;
    private int gold;
    private float itemValue;

    //TODO CONSTRUCTOR

    public float getHealth() {
        return health;
    }

    public float getStrength() {
        return strength;
    }

    public float getAgility() {
        return agility;
    }

    public float getMagicPoints() {
        return magicPoints;
    }

    public int getGold() {
        return gold;
    }

    public float getItemValue() {
        return itemValue;
    }
}
