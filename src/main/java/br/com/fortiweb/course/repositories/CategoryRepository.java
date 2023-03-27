package br.com.fortiweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortiweb.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
