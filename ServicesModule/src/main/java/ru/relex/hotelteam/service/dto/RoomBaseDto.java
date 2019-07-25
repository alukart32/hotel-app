package ru.relex.hotelteam.service.dto;

import java.math.BigDecimal;
import java.util.List;
import ru.relex.hotelteam.service.dto.CTFwithoutPrice.CategoryDtoWithNoPricemult;
import ru.relex.hotelteam.service.dto.CTFwithoutPrice.FacilityDtoWithNoPrice;
import ru.relex.hotelteam.service.dto.CTFwithoutPrice.TypeDtoWithNoPrice;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public class RoomBaseDto {

  private int number;
  private int floor;

  private TypeDtoWithNoPrice type;

  private CategoryDtoWithNoPricemult category;

  private BigDecimal dailyPrice;

  private List<FacilityDtoWithNoPrice> facilities;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public List<FacilityDtoWithNoPrice> getFacilities() {
    return facilities;
  }

  public void setFacilities(List<FacilityDtoWithNoPrice> facilities) {
    this.facilities = facilities;
  }

  public TypeDtoWithNoPrice getType() {
    return type;
  }

  public void setType(TypeDtoWithNoPrice type) {
    this.type = type;
  }

  public CategoryDtoWithNoPricemult getCategory() {
    return category;
  }

  public void setCategory(CategoryDtoWithNoPricemult category) {
    this.category = category;
  }

  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigDecimal dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

}
