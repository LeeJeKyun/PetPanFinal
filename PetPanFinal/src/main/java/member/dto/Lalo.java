package member.dto;

public class Lalo {

	private int latitude;
	private int longitude;
	
	public Lalo() {	}

	public Lalo(int latitude, int longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Lalo [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	
	
}
