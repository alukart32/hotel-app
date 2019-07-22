package ru.relex.hotelteam.service.dto.CTFwithPrice;

import java.math.BigDecimal;

public class CategoryDto {

  private int id;
  private String name;
  private BigDecimal price_multiplier;

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

  public BigDecimal getPrice_multiplier() {
    return price_multiplier;
  }

  public void setPrice_multiplier(BigDecimal price_multiplier) {
    this.price_multiplier = price_multiplier;
  }
}
