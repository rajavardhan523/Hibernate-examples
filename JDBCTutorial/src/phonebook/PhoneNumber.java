package phonebook;

public class PhoneNumber {
	@Override
	public String toString() {
		return "PhoneNumber [phoneType=" + phoneType + ", phoneNumber=" + phoneNumber + "]";
	}

	private String phoneType;
	private String phoneNumber;

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
