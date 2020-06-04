package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;


@NoArgsConstructor
public class ScoutFightManager implements FightManager {
    private PlayerScout player;
    int tour = DiceRoll.rollPrivate(1, 2);
    boolean dodge = false;

    public ScoutFightManager(PlayerScout player) {
        this.player = player;
    }

    private void printDmg(float dmg) {
        System.out.println("Enemy lost " + dmg + " hp.");
    }

    private void chooseAttack() {
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack.");
        System.out.println(Logger.CYAN + "2." + Logger.RESET + " Critical shoot.");
        System.out.println(Logger.CYAN + "3." + Logger.RESET + " Series of arrows.");
        System.out.println(Logger.CYAN + "4." + Logger.RESET + " Repetable arrow.");
    }

    private float enemyAttack(Enemy enemy) {
        float dmg = (0.4F * enemy.getBaseStrength()) * DiceRoll.rollPrivate(1, 4);
        printDmg(dmg);
        return dmg;
    }

    private float damageReduce() {
        float absorb = DiceRoll.rollPrivate(1, 4) * (player.getArmor()) * 0.5F;
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." + Logger.RESET);
        return absorb;
    }

    private void playerTour(Entity enemy) {
        System.out.println("It's your turn " + Logger.BLUE + player.getName() + "." + Logger.RESET);
        System.out.println("Your current health: " + Logger.RED + this.player.getHealth() + "." + Logger.RESET);
        System.out.println("Choose your attack: ");
        chooseAttack();
        int temp;
        float dmg;
        do temp = IOUtils.getScanner().nextInt();
        while (temp != 1 && temp != 2 && temp != 3 && temp != 4);
        switch (temp) {
            case 1:
                dmg = player.basicAttack();
                enemy.takeDamage(dmg);
                printDmg(dmg);
                break;
            case 2:
                dmg = player.criticalArrow();
                enemy.takeDamage(dmg);
                printDmg(dmg);
                break;
            case 3:
                dmg = player.seriesOfArrows();
                enemy.takeDamage(dmg);
                printDmg(dmg);
                break;
            case 4:
                dmg = player.repeatableArrow();
                enemy.takeDamage(dmg);
                printDmg(dmg);
        }
        tour = 2;
    }

    public void bossAttack(int roll, float dmg, Boss enemy) {
        if (roll == 1){
            this.player.takeDamage(dmg);
            System.out.println("Boss dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        }
        else if (roll == 2) {
            dmg = enemy.headHunter(enemy.rage(dmg));
            this.player.takeDamage(dmg);
            System.out.println("Boss dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        } else if (roll == 3) {
            dmg = enemy.finalShot(enemy.rage(dmg));
            this.player.takeDamage(dmg);
            System.out.println("Boss dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        }
    }

    private void bossTour(Boss enemy) {
            System.out.println(Logger.BLUE + "Opponent's turn." + Logger.RESET);
            System.out.println("Enemy current health: " + Logger.RED + enemy.getHealth() + "." + Logger.RESET);
            float enemyDamage = enemyAttack(enemy);
            float absorb = damageReduce();
            if (enemyDamage - absorb <= 0) enemyDamage = 0;
            else enemyDamage -= absorb;
            bossAttack(DiceRoll.rollPrivate(1,4), enemyDamage , enemy);
    }

    private void enemyTour(Enemy enemy) {
        float check = DiceRoll.rollPrivate(1, 100);
        if (0.5F * (check + player.getAgility()) > 50) dodge = true;
        if (!dodge) {
            System.out.println(Logger.BLUE + "Opponent's turn." + Logger.RESET);
            System.out.println("Enemy current health: " + Logger.RED + enemy.getHealth() + "." + Logger.RESET);
            float enemyDamage = enemyAttack(enemy);
            float absorb = damageReduce();
            if (enemyDamage - absorb <= 0) enemyDamage = 0;
            else enemyDamage -= absorb;
            this.player.takeDamage(enemyDamage);
            System.out.println("Enemy dealt you: " + Logger.RED + enemyDamage + " damage." + Logger.RESET);
        } else System.out.println(Logger.YELLOW + "Enemy missed! You dodged his attack!" + Logger.RESET);
    }


    @Override
    public boolean fight(Entity enemy) {
        while (enemy.getHealth() > 0 && this.player.getHealth() > 0) {
            switch (tour) {
                case 1: // tura zwiadowcy
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
                    break;
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
