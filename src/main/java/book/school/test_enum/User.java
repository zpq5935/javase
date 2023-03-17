package book.school.test_enum;

public class User {
	private String name;
	private Gender gender;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}

	public static void main(String[] args) {
		User user = new User();
		user.setAge(15);
		Gender gender = Gender.FeMail;
		System.out.println(gender.toString().equals("FeMail"));
		user.setGender(gender);
		System.out.println(user);
		System.out.println(user.getGender().toString().equals("FeMail"));
	}
}
