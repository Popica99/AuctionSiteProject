package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import sdacademy.auctionsiteproject.dto.AuctionRequestDTO;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.exceptions.AuctionNotFoundException;
import sdacademy.auctionsiteproject.exceptions.CategoryNotFoundException;
import sdacademy.auctionsiteproject.service.AuctionService;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/{userName}")
    public ResponseEntity<Auction> createAuction(@PathVariable String userName, @RequestBody AuctionRequestDTO auctionRequestDTO)
    {
        Auction newAuction = auctionService.createAuction(auctionRequestDTO.getAuction(), userName, auctionRequestDTO.getCategoryName());
        return new ResponseEntity<>(newAuction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@PathVariable Long id, @RequestBody AuctionRequestDTO updatedAuctionDTO)
    {
        Auction auctionResult = auctionService.updateAuction(id, updatedAuctionDTO);
        return ResponseEntity.ok(auctionResult);
    }

    @GetMapping
    public ResponseEntity<List<Auction>> getAllAuctions()
    {
        return new ResponseEntity<>(auctionService.getAllAuctions(), HttpStatus.OK);
    }

    @GetMapping("/{auctionName}")
    public ResponseEntity<List<Auction>> getAuctionByName(@PathVariable String auctionName)
    {
        try {
            return new ResponseEntity<>(auctionService.getAuctionsByName(auctionName), HttpStatus.OK);
        } catch (AuctionNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuctionById(@PathVariable Long id)
    {
        try {
            String message = auctionService.deleteAuctionById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (AuctionNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //---------------- Functions added for Frontend ----------------

    @GetMapping("/search")
    public ResponseEntity<List<Auction>> searchAuctionsByName(@RequestParam("name") String name) {
        List<Auction> results = auctionService.getAuctionsByName(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
