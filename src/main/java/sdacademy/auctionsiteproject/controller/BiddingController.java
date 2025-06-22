package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sdacademy.auctionsiteproject.dto.BiddingRequestDTO;
import sdacademy.auctionsiteproject.entity.Bidding;
import sdacademy.auctionsiteproject.service.BiddingService;

@RestController
@RequestMapping("/currentBids")
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @PostMapping("/{auction_id}")
    public ResponseEntity<Bidding> createBidding(@RequestBody BiddingRequestDTO biddingRequestDTO, @PathVariable Long auction_id)
    {
        Bidding newBidding = biddingService.createBidding(biddingRequestDTO.getBidding(), biddingRequestDTO.getUserName(), auction_id);
        return new ResponseEntity<>(newBidding, HttpStatus.CREATED);
    }

    @PostMapping("/buy/{auction_id}")
    public ResponseEntity<Bidding> buyNow(@PathVariable Long auction_id, @RequestBody BiddingRequestDTO biddingRequestDTO) {
        Bidding bidding = biddingService.buyNow(biddingRequestDTO.getUserName(), auction_id);
        return new ResponseEntity<>(bidding, HttpStatus.CREATED);
    }

    @GetMapping("/completed/{categoryName}")
    public ResponseEntity<?> getCompletedByCategory(@PathVariable String categoryName) {
        return new ResponseEntity<>(biddingService.getCompletedBiddingsByCategory(categoryName), HttpStatus.OK);
    }
}