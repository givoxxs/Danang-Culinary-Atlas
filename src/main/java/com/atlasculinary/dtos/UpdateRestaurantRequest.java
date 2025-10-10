package com.atlasculinary.dtos;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class UpdateRestaurantRequest {

    private String name;

    private String address;

    private String[] images;

    private Integer wardId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Map<String, String> openingHours;

}