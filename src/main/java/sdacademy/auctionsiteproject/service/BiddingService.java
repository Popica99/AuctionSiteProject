package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.dto.BiddingRequestDTO;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.entity.Bidding;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.exceptions.AuctionNotFoundException;
import sdacademy.auctionsiteproject.exceptions.PriceTooLowException;
import sdacademy.auctionsiteproject.exceptions.UserNotFoundException;
import sdacademy.auctionsiteproject.repository.BiddingRepository;

import java.text.ParseException;

@Service
public class BiddingService {

    @Autowired
    public BiddingRepository biddingRepository;

    @Autowired
    public AuctionService auctionService;

    @Autowired
    public UserService userService;

    public Bidding createBidding(Bidding bidding, String userName, Long auctionId)
    {
        Bidding newBidding = new Bidding();

        Auction auction = auctionService.getAuctionById(auctionId);
        User user = userService.getUserByAccountName(userName);
        if (auction != null)
        {
            if (bidding.getCurrentPrice() > auction.getBitNowPrice())
            {
                newBidding.setCurrentPrice(bidding.getCurrentPrice());
                newBidding.setEndDate(auction.getEndBiddingDate());
                newBidding.setAuction(auction);
                newBidding.setUser(user);

                Bidding savedBidding = biddingRepository.save(newBidding);

                auction.setBitNowPrice(bidding.getCurrentPrice());
                auctionService.saveAuction(auction);

                return savedBidding;
            }
            else throw new PriceTooLowException("New price is lower or equal then the current price!");
        }
        else  throw new AuctionNotFoundException("Auction not found!");
    }

    public Bidding buyNow(String userName, Long auctionId) {
        User user = userService.getUserByAccountName(userName);
        Auction auction = auctionService.getAuctionById(auctionId);
        Bidding bidding = new Bidding();
        bidding.setCurrentPrice(auction.getBitNowPrice());
        bidding.setUser(user);
        bidding.setAuction(auction);
        return biddingRepository.save(bidding);
    }

}