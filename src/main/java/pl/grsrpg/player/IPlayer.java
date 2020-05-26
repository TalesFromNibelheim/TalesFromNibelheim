package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.card.ICard;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.IField;
import pl.grsrpg.utils.Attribute;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayerWarrior.class, name = "warrior"),
        @JsonSubTypes.Type(value = PlayerScout.class, name = "scout"),
        @JsonSubTypes.Type(value = PlayerMage.class, name = "mage")
})
public interface IPlayer extends Entity {
    void recalculateAttributes();

    void move(int mapLevel, int filedNumber, IField IField);

    String getInfo();

    String getCardsInfo();

    boolean addCard(ICard ICard);

    ICard removeCard(String name);

    int getEquipmentCapacity();

    int getGold();

    void setGold(int gold);

    boolean removeGold(int amount);

    int getCurrentField();

    boolean fight(Entity entity);

    int getAdditionalMaxHealth();

    int getAdditionalStrength();

    int getAdditionalAgility();

    int getAdditionalMagicPoints();

    int getCurrentMapLevel();

    void addGold(int value);

    void addAdditionalMaxHealth(int maxHealth);

    void addAdditionalStrength(int strength);

    void addAdditionalAgility(int agility);

    void addAdditionalMagicPoints(int magicPoints);

    int getStartAttribute(Attribute attribute);
}
