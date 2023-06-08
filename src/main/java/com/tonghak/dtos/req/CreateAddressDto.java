package com.tonghak.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDto {

    private String name;

    private String slug;

    private String note;

    private String tag;

    private Boolean status;

    private Long lat;

    private Long lng;

}
