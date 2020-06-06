package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.IOUtils;

@NoArgsConstructor
public class MageFightManager implements FightManager {
    private PlayerMage player;
    private boolean stun = false;

    public MageFightManager(PlayerMage player) {
        this.player = player;
    }

    private void chooseAttack() {
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack.");
        System.out.println(" Need Magic Points: "+Logger.YELLOW + "0"+Logger.RESET);
        if(player.getFightMagicPoints() >= 5){
            System.out.println(Logger.CYAN + "2." + Logger.RESET + " Thunder.");
            System.out.println(" Need Magic Points: "+Logger.YELLOW + "5"+Logger.RESET);
            System.out.println(Logger.CYAN + "3." + Logger.RESET + " Fire Ball.");
            System.out.println(" Need Magic Points: "+Logger.YELLOW + "5"+Logger.RESET);
            System.out.println(Logger.CYAN + "4." + Logger.RESET + " Ice Chain.");
            System.out.println(" Need Magic Points: "+Logger.YELLOW + "5"+Logger.RESET);
        }
    }

    private float damageReduce() {
        float absorb = DiceRoll.rollPrivate(1, 4) * (player.getArmor());
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." + Logger.RESET);
        return absorb;
    }

    private void printDmg(float dmg) {
        System.out.println("Enemy lost " + dmg + " hp.");
    }

    private float basicAttack() {
        float dmg = (0.2F * player.getStrength()) * DiceRoll.rollPrivate(1, 4);
        printDmg(dmg);
        return dmg;
    }

    private float enemyAttack(Enemy enemy) {
        float dmg = (0.4F * enemy.getBaseStrength()) * DiceRoll.rollPrivate(1, 4);
        return dmg;
    }

    private void playerTour(Entity enemy) {
        System.out.println("It's your turn " + Logger.BLUE + player.getName() + "." + Logger.RESET);
        System.out.println("Your current health: " + Logger.RED + this.player.getHealth() + "." + Logger.RESET);
        System.out.println("Your current magic points: " + Logger.RED + this.player.getFightMagicPoints() + "." + Logger.RESET);
        System.out.println("Choose your attack: ");
        chooseAttack();
        int temp;
        int max = (player.getFightMagicPoints() >= 5 ? 4 : 1);
        do temp = IOUtils.getScanner().nextInt();
        while (temp < 1 || temp > max);
        switch (temp) {
            case 1:
                enemy.takeDamage(basicAttack());
                break;
            case 2:
                enemy.takeDamage(player.thunder());
                stun = true;
                break;
            case 3:
                enemy.takeDamage(player.fireBall());
                break;
            case 4:
                enemy.takeDamage(player.iceChain());
                break;
        }
    }

    private void enemyTour(Enemy enemy) {
        if (!stun) {
            System.out.println(Logger.BLUE + "Opponent's turn." + Logger.RESET);
            System.out.println(enemy.getName() + "  current health: " + Logger.RED + enemy.getHealth() + "." + Logger.RESET);
            float enemyDamage = enemyAttack(enemy);
            float absorb = damageReduce();
            if (enemyDamage - absorb <= 0) enemyDamage = 0;
            else enemyDamage -= absorb;
            this.player.takeDamage(enemyDamage);
            System.out.println(enemy.getName() + "  dealt you: " + Logger.RED + enemyDamage + " damage." + Logger.RESET);
        } else
            stun = false;
    }

    public void bossAttack(int roll, float dmg, Boss enemy) {
        if (roll == 1) {
            this.player.takeDamage(dmg);
            System.out.println(enemy.getName() + " dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        } else if (roll == 2) {
            dmg = enemy.headHunter(enemy.rage(dmg));
            this.player.takeDamage(dmg);
            System.out.println(enemy.getName() + " dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        } else if (roll == 3) {
            dmg = enemy.finalShot(enemy.rage(dmg));
            this.player.takeDamage(dmg);
            System.out.println(enemy.getName() + " dealt you: " + Logger.RED + dmg + " damage." + Logger.RESET);
        }
    }

    private void bossTour(Boss enemy) {
        if (!stun) {
            System.out.println(Logger.BLUE + "Opponent's turn." + Logger.RESET);
            System.out.println(enemy.getName() + "  current health: " + Logger.RED + enemy.getHealth() + "." + Logger.RESET);
            float enemyDamage = enemyAttack(enemy);
            float absorb = damageReduce();
            if (enemyDamage - absorb <= 0) enemyDamage = 0;
            else enemyDamage -= absorb;
            bossAttack(DiceRoll.rollPrivate(1, 4), enemyDamage, enemy);
        } else
            System.out.println(enemy.getName() + " can't move!!! Now is your chance.");
        stun = false;
    }

    @Override
    public boolean fight(Entity enemy) {
        stun = false;
        int tour = DiceRoll.rollPrivate(1, 2);
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
            this.player.restore();
            return true;
        }
        System.out.println("You lost.");
        this.player.restore();
        return false;
    }

}
