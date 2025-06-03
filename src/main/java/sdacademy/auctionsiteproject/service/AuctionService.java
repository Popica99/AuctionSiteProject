package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.entity.Category;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.repository.AuctionRepository;

import javax.print.attribute.UnmodifiableSetException;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    public AuctionRepository auctionRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public CategoryService categoryService;

    public Auction createAuction(Auction auction, String userName, String categoryName)
    {
        Auction newAuction = new Auction();

        newAuction.setName(auction.getName());
        newAuction.setDescription(auction.getDescription());
        newAuction.setPromoted(auction.getPromoted());
        newAuction.setBitNowPrice(auction.getBitNowPrice());
        newAuction.setBuyNowPrice(auction.getBuyNowPrice());
        newAuction.setStartBiddingDate(auction.getStartBiddingDate());
        newAuction.setEndBiddingDate(auction.getEndBiddingDate());

        newAuction.setUsers(userService.getUserByAccountName(userName));
        newAuction.setCategory(categoryService.getCategoryByName(categoryName));

        return auctionRepository.save(newAuction);
    }

    public List<Auction> getAllAuctions()
    {
        return auctionRepository.findAll();
    }

    public List<Auction> getAuctionsByName(String auctionName)
    {
        return auctionRepository.findAllByName(auctionName);
    }

    public Optional<Auction> updateAuction (Long id, Auction updatedAuction)
    {
        return auctionRepository.findById(id)
                .map(auction ->
                {
                    auction.setName(updatedAuction.getName());
                    auction.setDescription(updatedAuction.getDescription());
                    auction.setPromoted(updatedAuction.getPromoted());
                    auction.setBitNowPrice(updatedAuction.getBitNowPrice());
                    auction.setBuyNowPrice(updatedAuction.getBuyNowPrice());
                    auction.setStartBiddingDate(updatedAuction.getStartBiddingDate());
                    auction.setEndBiddingDate(updatedAuction.getEndBiddingDate());
                    auction.setNumbersOfViews(updatedAuction.getNumbersOfViews());

                    auction.setCategory(updatedAuction.getCategory());

                    return auctionRepository.save(auction);
                });
    }

    public Auction getAuctionById(Long id)
    {
        Optional<Auction> optionalAuction = auctionRepository.findById(id);
        if (optionalAuction.isPresent())
        {
            return optionalAuction.get();
        }
        else throw new RuntimeException("Auction with id " + id + " does not exists!");
    }

    public String deleteAuctionById(Long id)
    {
        auctionRepository.delete(getAuctionById(id));
        return "Auction was deleted successfully!";
    }
}
