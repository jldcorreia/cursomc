package br.com.fortiweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortiweb.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
