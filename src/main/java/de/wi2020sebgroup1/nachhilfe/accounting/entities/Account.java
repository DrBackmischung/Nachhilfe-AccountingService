package de.wi2020sebgroup1.nachhilfe.accounting.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String number;
	private String type;
	private List<Double> debit;
	private List<Double> credit;
	private boolean isActive;

	private ArrayList<Character> active = new ArrayList<>();
	private ArrayList<Character> passive = new ArrayList<>();
	
	public Account() {
		
	}

	public Account(String number) {
		super();
		this.id = UUID.randomUUID().toString();
		this.number = number;
		this.debit = new ArrayList<Double>();
		this.credit = new ArrayList<Double>();
		this.type = getType(number);
		this.isActive = getActivePassive(number);
		this.active.add('0');
		this.active.add('1');
		this.active.add('2');
		this.active.add('6');
		this.active.add('7');
		this.active.add('9');
		this.passive.add('3');
		this.passive.add('4');
		this.passive.add('5');
		this.passive.add('8');
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Double> getDebit() {
		return debit;
	}

	public void setDebit(List<Double> debit) {
		this.debit = debit;
	}

	public List<Double> getCredit() {
		return credit;
	}

	public void setCredit(List<Double> credit) {
		this.credit = credit;
	}

	public String getType() {
		return type;
	}

	public String getType(String number) {
		switch (number.charAt(0)) {
		case '0', '1': return "Anlagevermögen";
		case '2': return "Umlaufvermögen";
		case '3': return "Eigenkapital/Rückstellung";
		case '4': return "Verbindlichkeiten";
		case '5': return "Erträge";
		case '6', '7': return "Aufwendungen";
		case '8': return "Ergebnisrechnung";
		case '9': return "KLR";
		default:
			throw new IllegalArgumentException("Falsche Kontonummer: " + number);
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean getActivePassive(String number) {
		if(this.active.contains(number.charAt(0))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", type=" + type + ", debit=" + debit + ", credit=" + credit
				+ ", isActive=" + isActive + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(credit, debit, id, isActive, number, type);
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
				&& Objects.equals(id, other.id) && isActive == other.isActive && Objects.equals(number, other.number)
				&& Objects.equals(type, other.type);
	}

}
