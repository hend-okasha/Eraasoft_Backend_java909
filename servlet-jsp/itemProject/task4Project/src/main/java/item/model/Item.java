package item.model;

public class Item {
	private Long id;
	private String name;
	private Double price;
	private Integer totalNumber;
	
	public Item() {
		
	}
	
	public Item(String name , double price, int totalNumber) {
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
	}
	
	public Item(Long id, String name, double price, int totalNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
    }
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

}
