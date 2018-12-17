package test.jdbc;

public class Person {
	String id;
	String name;
	String details;

	public Person(String id, String name, String details) {
		this.id = id;
		this.name = name;
		this.details = details;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", details="
				+ details + "]";
	}

}
