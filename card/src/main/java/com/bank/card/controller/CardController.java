package com.bank.card.controller;

import com.bank.card.business.CardBusiness;
import com.bank.card.dto.CardDTO;
import com.bank.card.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Card REST API",
        description = "CRUD operation for Card"
)
@RestController
@RequestMapping(path = "/card/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardController {

    @Autowired
    private CardBusiness cardBusiness;

    @Operation(
            description = "Create card using mobile number",
            summary = "Create card"

    )
    //apiresponse also have content annotation
    @ApiResponse(
            responseCode = "201",
            description = "Successfully Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                         String mobileNumber){
        cardBusiness.createCard(mobileNumber);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.CREATED, "Successfully Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            description = "Fetch card using customer mobile number",
            summary = "Fetch card Details"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping("/get-user-by-mobile")
    public ResponseEntity<CardDTO> getCardByMobileNumber(@RequestParam
                                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                     String mobileNumber){
        CardDTO cardDTO = cardBusiness.getCustomerData(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardDTO);
    }

    @Operation(
            description = "update Account using customer DTO",
            summary = "Update Account"

    )
    @ApiResponse(
            responseCode = "202",
            description = "Successfully Updated"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDTO){
        boolean isUpdated = cardBusiness.updateCard(cardDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.ACCEPTED, "Updated: "+isUpdated);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(responseDTO);
    }

    @Operation(
            description = "Delete Account using customer mobile number",
            summary = "Delete Account"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam
                                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                    String mobileNumber){
        boolean isDeleted = cardBusiness.deleteCard(mobileNumber);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Deleted: "+isDeleted);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDTO);
    }

}
