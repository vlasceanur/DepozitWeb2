package pojos;

public class Warehouse {
	private int id;
	private String name;
	private String location;
	private float cash;
	private float volume;
	private int manager_id;
	
	
	
	public Warehouse(int id, String name, String location, float cash,
			float volume, int manager_id) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cash = cash;
		this.volume = volume;
		this.manager_id = manager_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getCash() {
		return cash;
	}
	public void setCash(float cash) {
		this.cash = cash;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	
	@Override
	public String toString() {
		String ret = id + "\n";
		ret += name + "\n";
		ret += location + "\n";
		ret += cash + "\n";
		ret += volume + "\n";
		ret += manager_id + "\n";
		
		return ret;
	}
	
	

}
