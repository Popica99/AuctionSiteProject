package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.dto.BiddingCompletedDTO;
import sdacademy.auctionsiteproject.dto.BiddingRequestDTO;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.entity.Bidding;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.exceptions.AuctionNotFoundException;
import sdacademy.auctionsiteproject.exceptions.PriceTooLowException;
import sdacademy.auctionsiteproject.exceptions.UserNotFoundException;
import sdacademy.auctionsiteproject.repository.BiddingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiddingService {

    @Autowired
    public BiddingRepository biddingRepository;

    @Autowired
    public AuctionService auctionService;

    @Autowired
    public UserService userService;

    public Bidding createBidding(Bidding bidding, String userName, Long auctionId) {
        Bidding newBidding = new Bidding();

        Auction auction = auctionService.getAuctionById(auctionId);
        User user = userService.getUserByAccountName(userName);
        if (auction != null) {
            if (bidding.getCurrentPrice() > auction.getBitNowPrice()) {
                newBidding.setCurrentPrice(bidding.getCurrentPrice());
                newBidding.setEndDate(auction.getEndBiddingDate());
                newBidding.setAuction(auction);
                newBidding.setUser(user);

                Bidding savedBidding = biddingRepository.save(newBidding);

                auction.setBitNowPrice(bidding.getCurrentPrice());
                auctionService.saveAuction(auction);

                return savedBidding;
            } else throw new PriceTooLowException("New price is lower or equal than the current price!");
        } else throw new AuctionNotFoundException("Auction not found!");
    }

    public Bidding buyNow(String userName, Long auctionId) {
        User user = userService.getUserByAccountName(userName);
        Auction auction = auctionService.getAuctionById(auctionId);

        if (auction == null) {
            throw new AuctionNotFoundException("Auction not found");
        }

        if (auction.getUsers().getAccountName().equals(userName)) {
            throw new RuntimeException("You can't buy your own auction.");
        }

        if (auction.isCompleted()) {
            throw new RuntimeException("Auction already completed.");
        }

        Bidding bidding = new Bidding();
        bidding.setCurrentPrice(auction.getBuyNowPrice());
        bidding.setUser(user);
        bidding.setAuction(auction);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        bidding.setEndDate(sdf.format(new Date()));

        auction.setIsCompleted(true);
        auctionService.saveAuction(auction);

        return biddingRepository.save(bidding);
    }

    public List<BiddingCompletedDTO> getCompletedBiddingsByCategory(String categoryName) {
        List<Bidding> allBiddings = biddingRepository.findAll();

        return allBiddings.stream()
                .filter(bid -> bid.getAuction().isCompleted() && bid.getAuction().getCategory().getName().equalsIgnoreCase(categoryName))
                .map(bid -> {
                    BiddingCompletedDTO dto = new BiddingCompletedDTO();
                    dto.setName(bid.getAuction().getName());
                    dto.setDescription(bid.getAuction().getDescription());
                    dto.setPromoted(bid.getAuction().getPromoted());
                    dto.setStart(bid.getAuction().getStartBiddingDate());
                    dto.setEnd(bid.getEndDate());
                    dto.setBoughtWithPrice(bid.getCurrentPrice());
                    dto.setBuyer(bid.getUser().getAccountName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}