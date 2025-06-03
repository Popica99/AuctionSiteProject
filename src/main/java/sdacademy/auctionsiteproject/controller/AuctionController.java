package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import sdacademy.auctionsiteproject.dto.AuctionRequestDTO;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.service.AuctionService;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    @PostMapping
    public ResponseEntity<Auction> createAuction(@RequestBody AuctionRequestDTO auctionRequestDTO)
    {
        Auction newAuction = auctionService.createAuction(auctionRequestDTO.getAuction(), auctionRequestDTO.getUserName(), auctionRequestDTO.getCategoryName());
        return new ResponseEntity<>(newAuction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuction(@PathVariable Long id, @RequestBody Auction updatedAuction)
    {
        if (auctionService.updateAuction(id, updatedAuction).isPresent())
            return ResponseEntity.ok("Auction " + updatedAuction.getAuction_id() + " updated successfully!");
        else
            return ResponseEntity.status(404).body("Action with id " + id + " not found!");
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
        } catch (RuntimeException e)
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
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
