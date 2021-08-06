package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.id", 
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
public class Customer {

	@Id
	@SequenceGenerator(name = "customerSequence", sequenceName = "customerId_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "customerSequence")
	private Long id;

	@Column(length = 40)
	private String name;

	@Column(length = 40)
	private String surname;

	@OneToMany(mappedBy = "customer")
	private List<Orders> orders;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}
