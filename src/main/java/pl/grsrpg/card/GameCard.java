package pl.grsrpg.card;

public abstract class GameCard implements Card {
    private String name;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
