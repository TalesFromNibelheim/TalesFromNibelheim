package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.GameCardEnemy;
import pl.grsrpg.card.GameCardFriend;
import pl.grsrpg.card.GameCardItem;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.Field;
import pl.grsrpg.manager.fight.FightManager;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class GamePlayer extends Enemy implements Player {
    protected int equipmentCapacity;
    protected List<Card> cards = new LinkedList<>();
    protected int currentMapLevel = 1;
    protected int currentField;
    protected int gold;

    protected int additionalMaxHealth;
    protected float armor;

    protected int additionalStrength;
    protected int additionalAgility;
    protected int additionalMagicPoints;

    protected FightManager fightManager;

    public GamePlayer(String name, int maxHealth, int strength, int agility, int magicPoints, int equipmentCapacity, int currentField) {
        super(name, maxHealth, strength, agility, magicPoints);
        this.equipmentCapacity = equipmentCapacity;
        this.currentField = 0;
        this.gold = 0;
    }

    @Override
    public boolean addCard(Card card) {
        if(cards.size() < equipmentCapacity){
            cards.add(card);
            return true;
        }
        return false;
    }

    @Override
    public Card removeCard(String name) {
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).getName().equals(name))
                return cards.remove(i);
        }
        return null;
    }

    @Override
    public void recalculateAttributes() {

    }

    @Override
    public void move(Field field) {

    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public boolean removeGold(int amount) {
        if(this.gold >= amount){
            this.gold -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void fight(Entity entity) {
        if(entity instanceof Boss)
            fightManager.fight((Boss) entity);
        else
            fightManager.fight((Enemy) entity);
    }

    @JsonIgnore
    @Override
    public String getCardsInfo() {
        int i = 2;
        return "Cards: \n 1. "+cards.stream()
                .map(card -> "Name: "+card.getName()+",\n  Description: "+card.getDescription())
                .collect(Collectors.joining("\n "+(i++)+". ", "", ""));
    }

    @Override
    public void addGold(int value) {
        this.gold += value;
    }

    @Override
    public void addAdditionalMaxHealth(int maxHealth) {
        this.additionalMaxHealth += maxHealth;
    }

    @Override
    public void addAdditionalStrength(int strength) {
        this.additionalStrength += strength;
    }

    @Override
    public void addAdditionalAgility(int agility) {
        this.additionalAgility += agility;
    }

    @Override
    public void addAdditionalMagicPoints(int magicPoints) {
        this.additionalMagicPoints += magicPoints;
    }

    @Override
    public boolean dodge() {
        return false;
    }

    @JsonIgnore
    public FightManager getFightManager() {
        return fightManager;
    }
}
