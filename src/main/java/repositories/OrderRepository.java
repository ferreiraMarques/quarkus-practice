package repositories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import entities.Customer;
import entities.Orders;

@Singleton
public class OrderRepository {

	@Inject
	EntityManager entityManager;

	public List<Orders> findAll(Long customerId) {
		return (List<Orders>) entityManager.createNamedQuery("Orders.findAll", Orders.class)
				.setParameter("customerId", customerId).getResultList();
	}

	public Orders findOrderById(Long id) {
		Orders order = entityManager.find(Orders.class, id);

		if (order == null) {
			throw new WebApplicationException("Order with id of " + id + " does not exist", 404);
		}
		return order;
	}
	
	@Transactional
	public void updateOrder(Orders orderUpdate, Orders newOrder) {
		orderUpdate.setItem(newOrder.getItem());
		orderUpdate.setPrice(newOrder.getPrice());
	}
	
	@Transactional
	public void createOrder(Orders order, Customer customer) {
		order.setCustomer(customer);
		entityManager.persist(order);
		entityManager.flush();
	}
	
	@Transactional
	public void deleteOrder(Orders order) {
		entityManager.remove(order);
	}
	
}
