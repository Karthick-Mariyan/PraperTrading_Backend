package com.stera.papertrade.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.model.TradedAsset;
import com.stera.papertrade.serviceinterface.BalanceInterface;
import com.stera.papertrade.serviceinterface.TradeAssetInterface;
import com.stera.papertrade.serviceinterface.TradeProfileInterface;

@Service
public class TradeAssetService implements TradeAssetInterface{
     private static final Logger LOG = LogManager.getLogger(TradeAssetService.class);
    @Autowired
    private TradeProfileInterface tradingProfileService;

    @Autowired
    private BalanceInterface balanceService;

    @Override
    public ResponseEntity<String> buyAsset(TradedAsset asset, Integer profileId) {
        Optional<TradeProfile> tradeProfile =  tradingProfileService.getTradeProfile(profileId);
        if(tradeProfile.isPresent()){
            if(tradeProfile.get().getTradedAssets() == null){
                //Balance Update for Buy 
                if(balanceService.checkForEnoughBalance(tradeProfile.get(), asset.getPurchasePrice() * asset.getPurhcaseQuantity())){
                    tradeProfile = balanceService.affectBalance(tradeProfile.get(), asset.getPurchasePrice() * asset.getPurhcaseQuantity(), asset.isAction());
                }else{
                    return new ResponseEntity<String>("Trade Profile Not Enough Balance!", HttpStatus.NOT_ACCEPTABLE);
                }
                ArrayList<TradedAsset> listofTradedAssets= new ArrayList<TradedAsset>();
                listofTradedAssets.add(asset);
                tradeProfile.get().setTradedAssets(listofTradedAssets);
                tradingProfileService.modifyTradeProfile(tradeProfile.get());
                LOG.debug("TradeAssetService : TradePrifle After Balance Update : " + tradeProfile.get().toString());
                return new ResponseEntity<String>("Asset Bought!" + asset.getStockName(), HttpStatus.OK);
            }else{
                //Balance Update for Buy 
                if(balanceService.checkForEnoughBalance(tradeProfile.get(), asset.getPurchasePrice() * asset.getPurhcaseQuantity())){
                    tradeProfile = balanceService.affectBalance(tradeProfile.get(), asset.getPurchasePrice() * asset.getPurhcaseQuantity(), asset.isAction());
                    LOG.debug("TradeAssetService : TradePrifle Balance Update : CurentBalance : " +  tradeProfile.get().getCurrentBalance());
                }else{
                    return new ResponseEntity<String>("Trade Profile Not Enough Balance!", HttpStatus.NOT_ACCEPTABLE);
                }
                tradeProfile.get().getTradedAssets().add(asset);
                LOG.debug("TradeAssetService : TradePrifle After Balance Update : " + tradeProfile.get().toString());
                tradingProfileService.modifyTradeProfile(tradeProfile.get());
                return new ResponseEntity<String>("Asset Bought!" + asset.getStockName(), HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<String>("Trade Profile Not Present!", HttpStatus.NOT_FOUND);
        }
       
    }

    @Override
    public ResponseEntity<String> sellAsset(TradedAsset asset, Integer profileId) {
        Optional<TradeProfile> tradeProfile =  tradingProfileService.getTradeProfile(profileId);
        if(tradeProfile.isPresent()){
            if(tradeProfile.get().getTradedAssets() == null){
                return new ResponseEntity<String>("No Assets to Sell! : " + asset.getStockName(), HttpStatus.NOT_FOUND);
            }else{
                tradeProfile = balanceService.affectBalance(tradeProfile.get(), asset.getPurchasePrice() * asset.getPurhcaseQuantity(), asset.isAction());
                Long totalAssetsinThisStock =  tradeProfile.get().getTradedAssets().stream().
                filter(value -> value.getStockName().equals(asset.getStockName())).
                filter(value -> value.isAction()==true ).
                mapToLong(value -> value.getPurhcaseQuantity()).
                sum();
                Long totalAssetsSoldinThisStock = tradeProfile.get().getTradedAssets().stream().
                filter(value -> value.getStockName().equals(asset.getStockName())).
                filter(value -> value.isAction()==false ).
                mapToLong(value -> value.getPurhcaseQuantity()).
                sum();
                
                if(totalAssetsinThisStock >= totalAssetsSoldinThisStock+asset.getPurhcaseQuantity()){
                    tradeProfile.get().getTradedAssets().add(asset);
                    tradingProfileService.modifyTradeProfile(tradeProfile.get());
                    return new ResponseEntity<String>("Assets Sold! : " + asset.getStockName() +" : "+ asset.getPurhcaseQuantity(), HttpStatus.OK);
                }else{
                    return new ResponseEntity<String>("Not Enough Assets! : " + asset.getStockName() +" : Requested : "+ asset.getPurhcaseQuantity() + " :  Available :" + (totalAssetsinThisStock - totalAssetsSoldinThisStock), HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }else{
            return new ResponseEntity<String>("Trade Profile Not Present!", HttpStatus.NOT_FOUND);
        }
        
    }
    
}
