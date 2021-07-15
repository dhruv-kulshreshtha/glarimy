package com.glarimy.directory.data;

import com.glarimy.directory.domain.Employee;
import com.glarimy.directory.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByNameIgnoreCaseContaining(String token);
}
