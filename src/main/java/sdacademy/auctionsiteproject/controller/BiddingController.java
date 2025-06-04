package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdacademy.auctionsiteproject.dto.BiddingRequestDTO;
import sdacademy.auctionsiteproject.entity.Bidding;
import sdacademy.auctionsiteproject.service.BiddingService;

@RestController
@RequestMapping("/currentBits")
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @PostMapping("{auction_id}")
    public ResponseEntity<Bidding> createBidding(@RequestBody BiddingRequestDTO biddingRequestDTO, @PathVariable Long auction_id)
    {
        Bidding newBidding = biddingService.createBidding(biddingRequestDTO.getBidding(), biddingRequestDTO.getUserName(), auction_id);
        return new ResponseEntity<>(newBidding, HttpStatus.CREATED);
    }
}
