
public abstract class Animal {
	private double mass;
	public Animal(double mass){
		this.mass = mass;
	}
	public void eat(double mass)
	{
		this.mass += mass;
	}
	public void exercise(double calories)
	{
		this.mass -= calories/7000;
	}
	public double getmass()
	{
		return(mass);
	}
}
