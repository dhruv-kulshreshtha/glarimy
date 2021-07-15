package com.glarimy.directory.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ProductDTO {

  private int id;

  @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$")
  @NotEmpty
  private String name;

  private long price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long phone) {
    this.price = phone;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
  }
}
