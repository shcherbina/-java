package by.grsu.kshcherbina.library.db.model;

public class Library {
	private Integer id;
	private Integer telephone;
	private String address;
	private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Library [id=" + id + ", telephone=" + telephone + ", address=" + address + ", email=" + email + "]";
	}
	
}
