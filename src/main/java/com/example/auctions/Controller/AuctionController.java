package com.example.auctions.Controller;

import com.example.auctions.DTO.AuctionDTO;
import com.example.auctions.Service.AuctionService;
import com.example.auctions.Service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/auctions")
@CrossOrigin(origins = "http://localhost:3000")
public class AuctionController {

    private static final String IMAGES_DIRECTORY = "src/main/resources/static/images/auctions";

    private final AuctionService auctionService;
    private final ImageService imageService;

    // Create a REST API GET endpoint that will return an Auction from the database
    @GetMapping("/{id}")
    public ResponseEntity<AuctionDTO> getAuctionById(@PathVariable final Long id) {
        AuctionDTO auctionDTO = auctionService.getAuctionById(id);
        return new ResponseEntity<>(auctionDTO, HttpStatus.OK);
    }

    // Create a REST API GET endpoint that will return all Auctions from the database
    @GetMapping
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        List<AuctionDTO> auctionDTOS = auctionService.getAllAuctions();
        return new ResponseEntity<>(auctionDTOS, HttpStatus.OK);
    }

    @GetMapping("/owner/{username}")
    public ResponseEntity<List<AuctionDTO>> getAuctionsByUsername(@PathVariable String username) {
        List<AuctionDTO> auctionDTOS = auctionService.getAuctionsByUserUsername(username);
        return new ResponseEntity<>(auctionDTOS, HttpStatus.OK);
    }

    // Create a REST API POST endpoint that will add a new Auction to the database
    @PostMapping
    public ResponseEntity<AuctionDTO> createAuction(@RequestBody final AuctionDTO auctionDTO) {
        AuctionDTO savedAuction = auctionService.createAuction(auctionDTO);

        return new ResponseEntity<>(savedAuction, HttpStatus.CREATED);
    }

    // Create a REST API PUT endpoint that will update an Auction in the database
    // TODO: implement the update of the images
    @PutMapping("/{id}")
    public ResponseEntity<AuctionDTO> updateAuctionById(@PathVariable final Long id,
                                                        @RequestBody final AuctionDTO auctionDTO){
        AuctionDTO updatedAuction = auctionService.updateAuctionById(id, auctionDTO);
        return new ResponseEntity<>(updatedAuction, HttpStatus.OK);
    }

    // Create a REST API DELETE endpoint that will delete an Auction from the database
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuctionById(@PathVariable final Long id) {
        auctionService.deleteAuctionById(id);
        return new ResponseEntity<>("Auction deleted successfully!", HttpStatus.OK);
    }

}
