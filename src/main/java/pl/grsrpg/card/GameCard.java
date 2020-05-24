package pl.grsrpg.card;

import lombok.Getter;

@Getter
public abstract class GameCard implements Card {
    private String name;
    private String description;
}
