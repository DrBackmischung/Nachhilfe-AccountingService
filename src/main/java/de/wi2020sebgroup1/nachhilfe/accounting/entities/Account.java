package de.wi2020sebgroup1.nachhilfe.accounting.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.OneToMany;

@RedisHash("Account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	@OneToMany(mappedBy="accountId")
	private ArrayList<Double> debit = new ArrayList<Double>();
	@OneToMany(mappedBy="accountId")
	private ArrayList<Double> credit = new ArrayList<Double>();
	
	public Account() {
		
	}

	public Account(String number) {
		super();
		this.id = number;
	}
	
	public void credit(double c) {
		this.credit.add(c);
	}
	
	public void debit(double d) {
		this.debit.add(d);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Double> getDebit() {
		return debit;
	}

	public void setDebit(ArrayList<Double> debit) {
		this.debit = debit;
	}

	public ArrayList<Double> getCredit() {
		return credit;
	}

	public void setCredit(ArrayList<Double> credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", debit=" + debit + ", credit=" + credit + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(credit, debit, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(credit, other.credit) && Objects.equals(debit, other.debit)
				&& Objects.equals(id, other.id);
	}
	
	
	
}
