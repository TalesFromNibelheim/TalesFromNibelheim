package pl.grsrpg.manager.fight;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ScoutFightManager.class, name = "scout"),
        @JsonSubTypes.Type(value = WarriorFightManager.class, name = "warrior"),
        @JsonSubTypes.Type(value = MageFightManager.class, name = "mage")
})
public interface FightManager {
    boolean fight(Entity enemy);
}
