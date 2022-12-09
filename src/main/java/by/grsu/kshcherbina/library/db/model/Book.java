package by.grsu.kshcherbina.library.db.model;

public class Book {
	private Integer id;
	private String name;
	private String author;
	private Integer page;
	private Integer libraryId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}
	@Override
	public String toString() {
		return "book [id=" + id + ", name=" + name + ", author=" + author + ", page=" + page + ", libraryId="
				+ libraryId + "]";
	}

}
