package pl.grsrpg.player;

import lombok.Getter;
import pl.grsrpg.card.Card;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.Field;

import java.util.LinkedList;
import java.util.List;

@Getter
public abstract class GamePlayer extends Enemy implements Player {
    protected int equipmentCapacity;
    protected List<Card> cards = new LinkedList<>();
    protected Field currentField;
    protected int gold;

    protected float additionalMaxHealth;

    protected float additionalStrength;
    protected float additionalAgility;
    protected float additionalMagicPoints;

    public GamePlayer(String name, float maxHealth, float strength, float agility, float magicPoints, int equipmentCapacity, Field currentField) {
        super(name, maxHealth, strength, agility, magicPoints);
        this.equipmentCapacity = equipmentCapacity;
        this.currentField = currentField;
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
/*        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i))
        }*/
        return null;
    }

    @Override
    public void recalculateAttributes() {

    }

    @Override
    public void move(Field field) {

    }

    @Override
    public int getEquipmentCapacity() {
        return equipmentCapacity;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public int getGold() {
        return gold;
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
    public Field getCurrentField() {
        return currentField;
    }

    @Override
    public void fight(Entity entity) {

    }
}
