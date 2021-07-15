package com.glarimy.directory.service;

import com.glarimy.directory.DTO.ProductDTO;
import com.glarimy.directory.data.ProductRepository;
import com.glarimy.directory.domain.Product;
import com.glarimy.directory.domain.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ModelMapper mapper;
  private final ProductRepository repo;

  public ProductService(ModelMapper mapper, ProductRepository repo) {
    this.mapper = mapper;
    this.repo = repo;
  }

  public ProductDTO addProduct(ProductDTO productDTO) {

    Product product = mapper.map(productDTO, Product.class);
    Product entity = repo.save(product);

    return mapper.map(entity, ProductDTO.class);
  }

  public ProductDTO find(int id) {
    Product product = repo.findById(id).orElseThrow(ProductNotFoundException::new);
    return mapper.map(product, ProductDTO.class);
  }

  public List<ProductDTO> search(String name) {
    List<Product> products;
    if (name.isEmpty()) products = repo.findAll();
    else products = repo.findByNameIgnoreCaseContaining(name);

    List<ProductDTO> productDTOList = new ArrayList<>();
    for (Product product : products) {
      productDTOList.add(mapper.map(product, ProductDTO.class));
    }
    return productDTOList;
  }
}
