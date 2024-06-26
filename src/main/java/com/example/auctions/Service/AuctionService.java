package com.example.auctions.Service;

import com.example.auctions.DTO.AuctionDTO;

import java.util.List;

public interface AuctionService {
    AuctionDTO createAuction(AuctionDTO auctionDTO);

    AuctionDTO getAuctionById(Long id);

    AuctionDTO getAuctionByUserId(Long userId);

    List<AuctionDTO> getAuctionsByUserUsername(String userUsername);

    List<AuctionDTO> getAllAuctions();

    AuctionDTO updateAuctionById(Long id, AuctionDTO auctionDTO);

    void deleteAuctionById(Long id);

}
