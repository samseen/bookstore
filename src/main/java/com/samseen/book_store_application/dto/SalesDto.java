package com.samseen.book_store_application.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for sales
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {

    //book id
    @ApiModelProperty(value = "Id of the book to be sold")
    private long bookId;

    //book name
    @ApiModelProperty(value = "Number of copies of the book to be sold.")
    @Min(value = 0, message = "Total sold should be a positive value")
    private int quantity;
}
