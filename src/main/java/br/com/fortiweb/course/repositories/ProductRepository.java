package br.com.fortiweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortiweb.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
