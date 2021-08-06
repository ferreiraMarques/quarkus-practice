package services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import entities.Customer;
import entities.Orders;
import repositories.CustomerRepository;
import repositories.OrderRepository;

@Singleton
public class OrderService {

	OrderRepository orderRepository;

	CustomerRepository customerRepository;

	@Inject
	public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
	}
	
	public List<Orders> getAll(long customerId) {
		return orderRepository.findAll(customerId);
	}
	
	public Orders getById(long orderId) {
		return orderRepository.findOrderById(orderId);
	}

	public void create(Orders order, Long customerId) {
		Customer customer = customerRepository.findCustomerById(customerId);
		orderRepository.createOrder(order, customer);
	}
	
	public void update(Orders order) {
		Orders updateOrder = getById(order.getId());
		orderRepository.updateOrder(updateOrder, order);
	}

	public void delete(Long orderId) {
		Orders order = getById(orderId);
		orderRepository.deleteOrder(order);
	}
	
}
