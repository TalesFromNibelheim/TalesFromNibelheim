package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;

@NoArgsConstructor
public class WarriorFightManager implements FightManager {
    private PlayerWarrior player;
    private boolean stun;

    public WarriorFightManager(PlayerWarrior player) {
        this.player = player;
    }

    int tour = DiceRoll.rollPrivate(1, 2);
    int numberOfTour = 0;
    int numberOfAdditionalArmor = 0;
    int time = 0;

    private void chooseAttack() {
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack.");
        System.out.println(Logger.CYAN + "2." + Logger.RESET + " Knockdown.");
        System.out.println(Logger.CYAN + "3." + Logger.RESET + " Cleave.");
        System.out.println(Logger.CYAN + "4." + Logger.RESET + "Blessing of the shield.");
    }

    private float basicAttack() {
        float dmg = (0.2F * player.getStrength()) * DiceRoll.rollPrivate(1, 4);
        printDmg(dmg);
        return dmg;
    }

    private float enemyAttack(Enemy enemy) {
        float dmg = (0.4F * enemy.getBaseStrength()) * DiceRoll.rollPrivate(1, 4);
        printDmg(dmg);
        return dmg;
    }

    private void printDmg(float dmg) {
        System.out.println("Enemy lost " + dmg + " hp.");
    }


    private void bless() {
        numberOfTour = DiceRoll.rollPrivate(2, 4);
        numberOfAdditionalArmor = DiceRoll.rollPrivate(5, 30);
        System.out.println(Logger.YELLOW + "God supports you. You gain " + numberOfAdditionalArmor + " additional armor for " + numberOfTour + " tours." + Logger.RESET);
    }

    private float damageReduce() {
        float absorb = DiceRoll.rollPrivate(1, 4) * (player.getArmor() + numberOfAdditionalArmor);
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." + Logger.RESET);
        return absorb;
    }

    private void playerTour(Entity enemy) {
        if (numberOfTour >= 0) numberOfTour--;
        System.out.println("It's your turn " + Logger.BLUE + player.getName() + "." + Logger.RESET);
        System.out.println("Your current health: " + Logger.RED + this.player.getHealth() + "." + Logger.RESET);
        System.out.println("Choose your attack: ");
        chooseAttack();
        int temp;
        do temp = IOUtils.getScanner().nextInt();
        while (temp != 1 && temp != 2 && temp != 3 && temp != 4);
        switch (temp) {
            case 1:
                enemy.takeDamage(basicAttack());
                break;
            case 2:
                enemy.takeDamage(player.knockdown());
                stun = true;
                break;
            case 3:
                enemy.takeDamage(player.cleave(enemy));
                break;
            case 4:
                player.blessingOfTheShield();
                bless();
        }
        if (numberOfTour < 0) {
            numberOfTour = 0;
            numberOfAdditionalArmor = 0;
        }
    }

    private void enemyTour(Enemy enemy) {
        if (!stun) {
            System.out.println(Logger.BLUE + "Opponent's turn." + Logger.RESET);
            System.out.println("Enemy current health: " + Logger.RED + enemy.getHealth() + "." + Logger.RESET);
            float enemyDamage = enemyAttack(enemy);
            float absorb = damageReduce();
            if (enemyDamage - absorb <= 0) enemyDamage = 0;
            else enemyDamage -= absorb;
            this.player.takeDamage(enemyDamage);
            System.out.println("Enemy dealt you: " + Logger.RED + enemyDamage + " damage." + Logger.RESET);
        } else
            stun = false;
    }

    private void bossTour(Boss enemy) {

    }

    @Override
    public boolean fight(Entity enemy) {
        stun = false;
        while (enemy.getHealth() > 0 && this.player.getHealth() > 0) {
            switch (tour) {
                case 1: // tura wojownika
                    playerTour(enemy);
                    tour = 2;
                    break;
                case 2: // tura wroga
                    if (enemy instanceof Boss) {
                        bossTour((Boss) enemy);
                    } else {
                        enemyTour((Enemy) enemy);
                    }
                    tour = 1;
            }
        }
        if (this.player.getHealth() > 0) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost.");
        }
        this.player.restore();
        return true;
    }

}
