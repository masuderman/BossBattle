/*
This class is the aggro table for the characters demonstrated in Battle.java

Project by:
	Manuel Sudermann
*/

public class AggroTable
{
	private Fighter[] f;

	public AggroTable(Fighter[] f)
	{
		this.f = f;
	}

	public int getHighestAggroIndex()
	{
		if(f.length == 0)
		{
			System.out.println("Error: Aggro table has no entries.");
			return 0;
		}
		else if(f.length == 1)
			return 0;
		else
		{
			int highestAggroIndex = 0;
			for(int i = 1; i < f.length; i++)
			{
				if(f[highestAggroIndex].getAggro() < f[i].getAggro())
					highestAggroIndex = i;
			}
			return highestAggroIndex;
		}
	}
}
