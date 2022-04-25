package com.onuryalcin.dto;


import lombok.Data;

import java.util.List;

@Data
public class PersonDto {

    private Long id;

    private String name;

    private String lastName;

    private List<String> personAddress;
}
