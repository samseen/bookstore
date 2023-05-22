package com.samseen.book_store_application.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;

/**
 * Data transfer object for sales
 */

public class SalesDto {

    //book id
    @ApiModelProperty(value = "Id of the book to be sold")
    private long bookId;

    //book name
    @ApiModelProperty(value = "Number of copies of the book to be sold.")
    @Min(value = 0, message = "Total sold should be a positive value")
    private int quantity;
}
