package services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import entities.Customer;
import repositories.CustomerRepository;

@Singleton
public class CustomerService {

	CustomerRepository customerRepository;

	@Inject
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findCustomerById(Long id) {
		return customerRepository.findCustomerById(id);
	}

	public void updateCustomer(Customer customer) {
		Customer old = customerRepository.findCustomerById(customer.getId());
		customerRepository.updateCustomer(old, customer);
	}

	public void createCustomer(Customer customer) {
		customerRepository.createCustomer(customer);
	}

	public void deleteCustomer(Long id) {
		Customer customer = customerRepository.findCustomerById(id);
		customerRepository.deleteCustomer(customer);
	}

}
