package ru.relex.hotelteam.dto.category;

import java.math.BigDecimal;

public class CategoryDto {

  private int id;
  private String name;
  private BigDecimal priceMultiplier;

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

  public BigDecimal getPriceMultiplier() {
    return priceMultiplier;
  }

  public void setPriceMultiplier(BigDecimal priceMultiplier) {
    this.priceMultiplier = priceMultiplier;
  }
}
