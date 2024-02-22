package com.wellsfargo.counselor.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDto {

    private long portfolio_id;
    private Date creationDate;
}
