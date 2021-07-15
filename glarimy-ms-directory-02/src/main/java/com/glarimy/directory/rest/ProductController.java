package com.glarimy.directory.rest;

import com.glarimy.directory.DTO.ProductDTO;
import com.glarimy.directory.domain.ProductNotFoundException;
import com.glarimy.directory.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Validated
@CrossOrigin
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("/product")
  public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
    ProductDTO entity = productService.addProduct(product);
    return new ResponseEntity<>(entity, HttpStatus.CREATED);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductDTO> find(@PathVariable("id") int id) {
    ProductDTO product = productService.find(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping("/product")
  public ResponseEntity<List<ProductDTO>> search(
      @RequestParam(value = "name", defaultValue = "") String name) {
    List<ProductDTO> products = productService.search(name);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Error> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> errors = result.getFieldErrors();
    Error error = new Error(400, "Invalid Product");
    for (FieldError fe : errors) {
      error.addError(fe.getField(), fe.getDefaultMessage());
    }
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
    return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
  }
}
