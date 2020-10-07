/*
This class is designed to carry all of the statistics and attributes of a character demonstrated in Battle.java

Project by:
	Manuel Sudermann
*/

import java.util.*;

public class Fighter
{
	public double startingHP;
	public double dps, hp, crit;
	public int aggro, cd, fresh;

	public char kind;

	public Fighter(char kind)
	{
		this.kind = kind;
	}

	public void setStartingHP(double h) { startingHP = h; }
	public double getStartingHP() { return startingHP; }

	public void setKind(char k) { kind = k; }
	public char getKind() { return kind; }

	public void setDPS(double d) { dps = d; }
	public double getDPS() { return dps; }

	public void setHP(double h) { hp = h; }
	public double getHP() { return hp; }

	public void setCrit(double c) { crit = c; }
	public double getCrit() { return crit; }

	public void setAggro(int a) { aggro = a; }
	public int getAggro() { return aggro; }

	public double getHitDamage()
	{
		Random rand = new Random();
		double r = rand.nextInt(101) / 100.0;
		int k = 2; //set crit damage to 2x normal damage
		double p = 0.20; //20% variance
		double damage = dps + ((2*p)/100)*(r - 0.5)*dps;
		int critCalc = rand.nextInt(101);
		if(critCalc <= crit)
			return damage * k;
		else
			return damage;
	}
	public double hit(double dmg)
	{
		double newHP = getHP()-dmg;
		setHP(newHP);
		return newHP;
	}

	public void heal(double h)
	{
		this.hp += h;
	}

	public double getSpecial()
	{
		switch(kind)
		{
			case 'm':	return getHitDamage()*2;
			case 'w':	return getHitDamage()*2;
			case 'h':	return 100; //heal for 60 hp
			case 't': 	return getHitDamage()*2;
			case 'b':	return getHitDamage()*1.5;
			default:	return 0;
		}
	}

	public int getCooldown()
	{
		return cd;
	}
	public void setCooldown(int f)
	{
		fresh = f;
	}
	public void resetCooldown()
	{
		cd = fresh;
	}
	public void decCooldown()
	{
		cd--;
	}

}