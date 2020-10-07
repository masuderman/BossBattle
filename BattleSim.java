/*
This class is designed to demonstrate a simple boss battle with randomized results.
A separate Battle object could/should be created for a cleaner Simulation class

Project by:
	Manuel Sudermann
*/

import java.util.*;
public class BattleSim
{
	public static void main(String[] args) throws InterruptedException
	{
	/* Create characters here */
		Fighter mage = new Fighter('m'); //creating mage
		mage.setHP(100.0);
		mage.setDPS(45.0);
		mage.setCrit(15.0);
		mage.setAggro(1);
		mage.setCooldown(15);

		Fighter warrior = new Fighter('w'); //creating warrior
		warrior.setHP(350.0);
		warrior.setDPS(30.0);
		warrior.setCrit(40.0);
		warrior.setAggro(3);
		warrior.setCooldown(12);

		Fighter tank = new Fighter('t'); //creating tank
		tank.setHP(850.0);
		tank.setDPS(10.0);
		tank.setCrit(5.0);
		tank.setAggro(4);
		tank.setCooldown(10);

		Fighter healer = new Fighter('h'); //creating healer
		healer.setHP(250.0);
		healer.setDPS(10.0);
		healer.setCrit(5.0);
		healer.setAggro(2);
		healer.setCooldown(3);

		Fighter boss = new Fighter('b'); //creating boss
		boss.setHP(4000.0);
		boss.setDPS(50.0);
		boss.setCrit(20.0);
		boss.setAggro(1);
		boss.setCooldown(8);

		Fighter[] fighters = {tank, healer, warrior, mage};
		AggroTable at = new AggroTable(fighters);

		double damage = 0.0;
		double heal = 0.0;

	/* Combat system */
		//make sure that there is at least one character still alive for the loop to be running
		while(boss.getHP() > 0.0 && (tank.getHP() > 0.0 || warrior.getHP() > 0.0 || mage.getHP() > 0.0))
		{
			//Thread.sleep(1000); //put 2 second delay for each time interval

		/* For tank hit */
			if(tank.getHP() > 0.0 && boss.getHP() > 0.0)
			{
				if(tank.getCooldown() == 0)
				{
					damage = tank.getSpecial();
					boss.hit(damage);
					tank.resetCooldown();
					if(boss.getHP() <= 0.0)
						boss.setHP(0.0);
					System.out.printf("Tank hits boss with a special attack for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						boss.setHP(0.0);
						System.out.println();
						System.out.println("The boss has been defeated!");
						break;
					}
				}
				else
				{
					damage = tank.getHitDamage();
					boss.hit(damage);
					if(tank.getCooldown() > 0)
						tank.decCooldown();
					if(boss.getHP() < 0.0)
						boss.setHP(0.0);
					System.out.printf("Tank hits boss for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						System.out.println();
						System.out.println("The boss has been defeated!");
						System.out.println();
						break;
					}
				}
			}
		/* tank hit ends */
		/* For boss hit */
			if(boss.getHP() < (boss.getStartingHP()/2.0) && boss.getCooldown() == 0)
			{
				//damage done to each character by boss' special ability differs
				if(tank.getHP() > 0.0)
				{
					damage = boss.getSpecial();
					tank.hit(damage);
					if(tank.getHP() <= 0.0)
					{
						tank.setHP(0.0);
						System.out.printf("Boss hits tank with a special attack for %.2f damage! The tank has been killed!", damage);
					}
					else
						System.out.printf("Boss hits tank with a special attack for %.2f damage! Tank's HP is: %.2f", damage, tank.getHP());
					System.out.println();
				}
				if(healer.getHP() > 0.0)
				{
					damage = boss.getSpecial();
					healer.hit(damage);
					if(healer.getHP() <= 0.0)
					{
						healer.setHP(0.0);
						System.out.printf("Boss hits healer with a special attack for %.2f damage! The healer has been killed!", damage);
					}
					else
						System.out.printf("Boss hits healer with a special attack for %.2f damage! Healer's HP is: %.2f", damage, healer.getHP());
					System.out.println();
				}
				if(warrior.getHP() > 0.0)
				{
					damage = boss.getSpecial();
					warrior.hit(damage);
					if(warrior.getHP() <= 0.0)
					{
						warrior.setHP(0.0);
						System.out.printf("Boss hits warrior with a special attack for %.2f damage! The warrior has been killed!", damage);
					}
					else
						System.out.printf("Boss hits warrior with a special attack for %.2f damage! Warrior's HP is: %.2f", damage, warrior.getHP());
					System.out.println();
				}
				if(mage.getHP() > 0.0)
				{
					damage = boss.getSpecial();
					mage.hit(damage);
					if(mage.getHP() <= 0.0)
					{
						mage.setHP(0.0);
						System.out.printf("Boss hits mage with a special attack for %.2f damage! The mage has been killed!", damage);
					}
					else
						System.out.printf("Boss hits mage with a special attack for %.2f damage! Mage's HP is: %.2f", damage, mage.getHP());
					System.out.println();
				}
				boss.resetCooldown();
			}
			else
			{
				at = new AggroTable(fighters);
				int index = at.getHighestAggroIndex();
				if(index == 0)
				{
					if(tank.getHP() > 0.0)
					{
						damage = boss.getHitDamage();
						tank.hit(damage);
						if(tank.getHP() <= 0.0)
						{
							tank.setHP(0.0);
							System.out.printf("Boss hits tank for %.2f damage! The tank has been killed!", damage);
						}
						else
							System.out.printf("Boss hits tank for %.2f damage! Tank's HP is: %.2f", damage, tank.getHP());
						System.out.println();
					}
					else
						index++;
				}
				if(index == 1)
				{
					if(healer.getHP() > 0.0)
					{
						damage = boss.getHitDamage();
						healer.hit(damage);
						if(healer.getHP() <= 0.0)
						{
							healer.setHP(0.0);
							System.out.printf("Boss hits healer for %.2f damage! The healer has been killed!", damage);
						}
						else
							System.out.printf("Boss hits healer for %.2f damage! Healer's HP is: %.2f", damage, healer.getHP());
						System.out.println();
					}
					else
						index++;
				}
				if(index == 2)
				{
					if(warrior.getHP() > 0.0)
					{
						damage = boss.getHitDamage();
						warrior.hit(damage);
						if(warrior.getHP() <= 0.0)
						{
							warrior.setHP(0.0);
							System.out.printf("Boss hits warrior for %.2f damage! The warrior has been killed!", damage);
						}
						else
							System.out.printf("Boss hits warrior for %.2f damage! Warrior's HP is: %.2f", damage, warrior.getHP());
						System.out.println();
					}
					else
						index++;
				}
				if(index == 3)
				{
					if(mage.getHP() > 0.0)
					{
						damage = boss.getHitDamage();
						mage.hit(damage);
						if(mage.getHP() <= 0.0)
						{
							mage.setHP(0.0);
							System.out.printf("Boss hits mage for %.2f damage! The mage has been killed!", damage);
						}
						else
							System.out.printf("Boss hits mage for %.2f damage! Mage's HP is: %.2f", damage, mage.getHP());
						System.out.println();
					}
					else
						index++;
				}
				if(boss.getCooldown() > 0)
					boss.decCooldown();
			}
		/* boss hit ends */
		/* For healer hit */
			if(healer.getHP() > 0.0 && boss.getHP() > 0.0)
			{
				if(healer.getCooldown() == 0)
				{
					heal = healer.getSpecial();
					Fighter[] all = {tank, healer, warrior, mage};
					int in = findHealIndex(all);

					switch(in)
					{
						case 0: if(tank.getHP() > 0.0)
									tank.heal(heal);
								System.out.printf("Healer heals tank for %.2f hp! Tank HP is: %.2f", heal, tank.getHP());
								break;
						case 1: if(healer.getHP() > 0.0)
									healer.heal(heal);
								System.out.printf("Healer heals themself for %.2f hp! Healer HP is: %.2f", heal, healer.getHP());
								break;
						case 2:	if(warrior.getHP() > 0.0)
									warrior.heal(heal);
								System.out.printf("Healer heals warrior for %.2f hp! Warrior HP is: %.2f", heal, warrior.getHP());
								break;
						case 3:	if(mage.getHP() > 0.0)
									mage.heal(heal);
								System.out.printf("Healer heals mage for %.2f hp! Mage HP is: %.2f", heal, mage.getHP());
								break;
					}
					healer.resetCooldown();
					System.out.println();
				}
				else
				{
					damage = healer.getHitDamage();
					boss.hit(damage);
					if(healer.getCooldown() > 0)
						healer.decCooldown();
					if(boss.getHP() < 0.0)
						boss.setHP(0.0);
					System.out.printf("Healer hits boss for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						System.out.println();
						System.out.println("The boss has been defeated!");
						System.out.println();
						break;
					}
				}
			}
		/* healer hit ends */
		/* For warrior hit */
			if(warrior.getHP() > 0.0 && boss.getHP() > 0.0)
			{
				if(warrior.getCooldown() == 0)
				{
					damage = warrior.getSpecial();
					boss.hit(damage);
					warrior.resetCooldown();
					if(boss.getHP() <= 0.0)
						boss.setHP(0.0);
					System.out.printf("Warrior hits boss with a special attack for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						boss.setHP(0.0);
						System.out.println();
						System.out.println("The boss has been defeated!");
						break;
					}
				}
				else
				{
					damage = warrior.getHitDamage();
					boss.hit(damage);
					if(warrior.getCooldown() > 0)
						warrior.decCooldown();
					if(boss.getHP() < 0.0)
						boss.setHP(0.0);
					System.out.printf("Warrior hits boss for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						System.out.println();
						System.out.println("The boss has been defeated!");
						System.out.println();
						break;
					}
				}
			}
		/* warrior hit ends */
		/* For mage hit */
			if(mage.getHP() > 0.0 && boss.getHP() > 0.0)
			{
				if(mage.getCooldown() == 0)
				{
					damage = mage.getSpecial();
					boss.hit(damage);
					mage.resetCooldown();
					if(boss.getHP() <= 0.0)
						boss.setHP(0.0);
					System.out.printf("Mage hits boss with a special attack for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						boss.setHP(0.0);
						System.out.println();
						System.out.println("The boss has been defeated!");
						break;
					}
				}
				else
				{
					damage = mage.getHitDamage();
					boss.hit(damage);
					if(mage.getCooldown() > 0)
						mage.decCooldown();
					if(boss.getHP() < 0.0)
						boss.setHP(0.0);
					System.out.printf("Mage hits boss for %.2f damage! Boss' HP is: %.2f", damage, boss.getHP());
					System.out.println();
					if(boss.getHP() <= 0.0)
					{
						System.out.println();
						System.out.println("The boss has been defeated!");
						System.out.println();
						break;
					}
				}
			}
		/* mage hit ends */
			System.out.println();
			if(boss.getHP() > 0.0 && tank.getHP() <= 0.0 && healer.getHP() <= 0.0 && warrior.getHP() <= 0.0 && mage.getHP() <= 0.0)
			{
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("The boss has defeated all of the characters!");
			}
			System.out.println();
		}
	}
	public static int findHealIndex(Fighter[] fighters)
	{
		double p = 0;
		int lowestHP = 0;

		for(int i = 1; i < fighters.length; i++)
		{
			if((fighters[lowestHP].getHP() / fighters[lowestHP].getStartingHP()) < (fighters[i].getHP() / fighters[i].getStartingHP()))
				lowestHP = i;
		}
		return lowestHP;
	}
}