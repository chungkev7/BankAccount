
public class User {

	private int id;
	private String name;
	private String securityCode;
	private double checkingsAccount;
	private double savingsAccount;

	public User() {
		super();
	}

	public User(String name, String securityCode, double checkingsAccount, double savingsAccount) {
		super();
		this.name = name;
		this.securityCode = securityCode;
		this.checkingsAccount = checkingsAccount;
		this.savingsAccount = savingsAccount;
	}

	public User(int id, String name, String securityCode, double checkingsAccount, double savingsAccount) {
		super();
		this.id = id;
		this.name = name;
		this.securityCode = securityCode;
		this.checkingsAccount = checkingsAccount;
		this.savingsAccount = savingsAccount;
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

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public double getCheckingsAccount() {
		return checkingsAccount;
	}

	public void setCheckingsAccount(double checkingsAccount) {
		this.checkingsAccount = checkingsAccount;
	}

	public double getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(double savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + securityCode + "," + checkingsAccount + "," + savingsAccount;
	}

}
