package phonebook;

public class Address {
	@Override
	public String toString() {
		return "Address [street=" + street + ", City=" + City + ", State=" + State + ", zipCode=" + zipCode + "]";
	}

	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	private String City;
	private String State;
	private String zipCode;
}
