package app.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@Column(name = "itemNo")
	private int id;
	@Column
	private String name;
	@Column
	private int stock;
	@Column
	private int inventoryCode;

	public Item(int id, String name, int stock, int inventoryCode) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.inventoryCode = inventoryCode;
	}

	public Item() {

	}

	public int getItemNo() {
		return id;
	}

	public void setItemNo(int itemNo) {
		this.id = itemNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(int inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	@Override
	public String toString() {
		return "Item " + id + ": name=" + name + ", stock=" + stock + ", inventoryCode=" + inventoryCode;
	}

}
