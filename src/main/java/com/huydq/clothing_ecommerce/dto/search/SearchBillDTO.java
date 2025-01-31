package com.huydq.clothing_ecommerce.dto.search;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchBillDTO {
    private Date dateStart;
    private Date dateEnd;
    private Integer status;
    private String phoneNumber;
    private String staff;
    private Integer billType;
    private Integer size;
    private Integer page;
    private String strDateStart;
    private String strDateEnd;

}
