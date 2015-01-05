package pojos;

public class Item {
	
	private int id;
	private String name;
	private float volume;
	private float price;
	
	public Item()
	{
		this(-1, "", 0.0f, 0.0f);
	}
	
	public Item(String name, float volume, float price)
	{
		this(-1, name, volume, price);
	}
	
	public Item(int id, String name, float volume, float price)
	{
		this.name = name;
		this.volume = volume;
		this.price = price;
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret += getId() + "\n";
		ret += getName() +"\n";
		ret += getVolume() + "\n";
		ret += getPrice() +"\n";
		
		return ret;
	}

}
