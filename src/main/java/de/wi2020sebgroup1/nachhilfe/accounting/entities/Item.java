package de.wi2020sebgroup1.nachhilfe.accounting.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private double amount;
	
	public Item() {
		
	}
	
	public Item(double amount) {
		super();
		this.id = UUID.randomUUID().toString();
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", amount=" + amount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && Objects.equals(id, other.id);
	}

}
