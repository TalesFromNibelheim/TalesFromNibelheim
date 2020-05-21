package pl.grsrpg.action;

import pl.grsrpg.player.Player;

public class GameActionCoach extends GameAction {

    protected float price;
    protected float maxHealth;
    protected float strength;
    protected float agility;
    protected float magicPoints;

    @Override
    public boolean execute (Player player){
        return true;
    }

    void textMenu(){ // co wyswietla coach

    }

}
