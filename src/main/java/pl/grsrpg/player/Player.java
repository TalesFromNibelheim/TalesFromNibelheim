package pl.grsrpg.player;

import pl.grsrpg.entity.EntityFightable;

public abstract class Player implements EntityPlayable, EntityFightable {
    private final String name;
    private float health;
    private float damage;
    private float resistance;

    public Player(String name, float health, float damage, float resistance) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.resistance = resistance;
    }
}
