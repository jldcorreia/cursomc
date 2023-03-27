package br.com.fortiweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortiweb.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
