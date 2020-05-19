package pl.grsrpg.action;

import pl.grsrpg.player.EntityPlayable;

public interface Action {

    boolean execute(EntityPlayable player);

}
