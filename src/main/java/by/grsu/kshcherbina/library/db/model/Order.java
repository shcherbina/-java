package by.grsu.kshcherbina.library.db.model;
import java.sql. Timestamp;
public class Order {
	private Integer id;
	private Timestamp takenOn;
	private Integer bookId;
	private Integer userAccount;
	private Timestamp returnOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTakenOn() {
		return takenOn;
	}
	public void setTakenOn(Timestamp takenOn) {
		this.takenOn = takenOn;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(Integer userAccount) {
		this.userAccount = userAccount;
	}
	public Timestamp getReturnOn() {
		return returnOn;
	}
	public void setReturnOn(Timestamp returnOn) {
		this.returnOn = returnOn;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", takenOn=" + takenOn + ", bookId=" + bookId + ", userAccount=" + userAccount
				+ ", returnOn=" + returnOn + "]";
	}

	
	
}
