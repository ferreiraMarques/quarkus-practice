package repositories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import entities.Customer;

@Singleton
public class CustomerRepository {

	@Inject
	EntityManager entityManager;

	public List<Customer> findAll() {
		return entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
	}

	public Customer findCustomerById(Long id) {
		Customer customer = entityManager.find(Customer.class, id);

		if (customer == null) {
			throw new WebApplicationException("Customer with id of " + id + " does not exist", 404);
		}

		return customer;
	}

	@Transactional
	public void updateCustomer(Customer oldCustomer, Customer newCustomer) {
		oldCustomer.setName(newCustomer.getName());
		oldCustomer.setSurname(newCustomer.getSurname());
	}

	@Transactional
	public void createCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@Transactional
	public void deleteCustomer(Customer customer) {
		entityManager.remove(customer);
	}
}
