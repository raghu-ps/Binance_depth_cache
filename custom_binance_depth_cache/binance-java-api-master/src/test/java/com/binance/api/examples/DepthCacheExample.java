package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.domain.account.NewOrder;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Collections;
import java.io.*;

import org.json.simple.JSONObject;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
/**
 * Illustrates how to use the depth event stream to create a local cache of bids/asks for a symbol.
 */
public class DepthCacheExample {

  private static final String BIDS  = "BIDS";
  private static final String ASKS  = "ASKS";
  
//address of your redis server
  private static final String redisHost = "localhost";
  private static final Integer redisPort = 6379;

  private long lastUpdateId;
  
  private long timeAfter;
  
  private long time;
  
  private Date afterAddingTenSeconds;

  private Map<String, NavigableMap<BigDecimal, BigDecimal>> depthCache;
  
  private Map<BigDecimal, BigDecimal> askMap = new HashMap<BigDecimal,BigDecimal>();
  
  private Map<BigDecimal, BigDecimal> bidMap = new HashMap<BigDecimal,BigDecimal>();

  public DepthCacheExample(String symbol) {
	Calendar cal = Calendar.getInstance();
	long time= cal.getTimeInMillis();
	Date afterAddingTenSeconds=new Date(time + (1 * 10000));
	long timeAfter = afterAddingTenSeconds.getTime();
    initializeDepthCache(symbol);
    startDepthEventStreaming(symbol);
	}
  
  private static Jedis pool = new Jedis(redisHost);


  /**
   * Initializes the depth cache by using the REST API.
   */
  private void initializeDepthCache(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiRestClient client = factory.newRestClient();
    OrderBook orderBook = client.getOrderBook(symbol.toUpperCase(), 10);

    this.depthCache = new HashMap<>();
    this.lastUpdateId = orderBook.getLastUpdateId();

    NavigableMap<BigDecimal, BigDecimal> asks = new TreeMap<>(Comparator.reverseOrder());
    for (OrderBookEntry ask : orderBook.getAsks()) {
      asks.put(new BigDecimal(ask.getPrice()), new BigDecimal(ask.getQty()));
    }
    depthCache.put(ASKS, asks);
    
    String askString = asks.toString();
    pool.set(ASKS, askString);

    NavigableMap<BigDecimal, BigDecimal> bids = new TreeMap<>(Comparator.reverseOrder());
    for (OrderBookEntry bid : orderBook.getBids()) {
      bids.put(new BigDecimal(bid.getPrice()), new BigDecimal(bid.getQty()));
    }
    depthCache.put(BIDS, bids);
    String bidString = bids.toString();
    pool.set(BIDS,bidString);
  }

  /**
   * Begins streaming of depth events.
   */
  private void startDepthEventStreaming(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();
    
    
  BinanceApiClientFactory factoryvar = BinanceApiClientFactory.newInstance();
  BinanceApiRestClient clientvar = factory.newRestClient();
  Calendar cals = Calendar.getInstance();
  time= cals.getTimeInMillis();
  afterAddingTenSeconds=new Date(time + (1 * 10000));
  timeAfter = afterAddingTenSeconds.getTime();
// ii = 0;
    client.onDepthEvent(symbol.toLowerCase(), response -> {
      if (response.getUpdateId() > lastUpdateId) {
        System.out.println(response);
        lastUpdateId = response.getUpdateId();
        updateOrderBook(getAsks(), response.getAsks());
        updateOrderBook(getBids(), response.getBids());
        printDepthCache();
        Calendar cal = Calendar.getInstance();
        long timenow= cal.getTimeInMillis();
        System.out.println("Time Now: "+timenow);
        System.out.println("Time fututre: "+timeAfter);
    	if(timenow >= timeAfter) {
        System.out.println("INSIDE 10SEC TIME WINDOW: ");
		 TickerStatistics tickerStatisticsForUSD = clientvar.get24HrPriceStatistics("BTCUSDT");
		 String percentChange = tickerStatisticsForUSD.getPriceChangePercent();
		 double percentChangeInUSD = Double.parseDouble(percentChange);
		 TickerStatistics tickerStatisticsForETH = clientvar.get24HrPriceStatistics("ETHBTC");
		 String percentChangeETH = tickerStatisticsForETH.getPriceChangePercent();
		 double percentChangeInETHInteger = Double.parseDouble(percentChangeETH);
		 Map<BigDecimal, BigDecimal> reverseSortedMapOfBids = new TreeMap<BigDecimal, BigDecimal>(Collections.reverseOrder());
		 reverseSortedMapOfBids.putAll(bidMap);
		 Map.Entry<BigDecimal, BigDecimal> entry = reverseSortedMapOfBids.entrySet().iterator().next();
		 BigDecimal keyOfBids= entry.getKey();
		 Double doubleValueOfBids = keyOfBids.doubleValue();
		 Double nintySevenPercentOfBids = 0.97 * keyOfBids.doubleValue();
		 BigDecimal bidsAtNintySevenPercent = BigDecimal.valueOf(nintySevenPercentOfBids);
		 Double totalQuantOfBids = 0.0;
		 
		 BigDecimal bestPriceAtBid = entry.getValue();
		 Double dBestPriceAtBid = bestPriceAtBid.doubleValue();
		 
		 for (Map.Entry<BigDecimal, BigDecimal> entryInLoop : reverseSortedMapOfBids.entrySet()) {
			    BigDecimal key = entryInLoop.getKey();
			    Double keyOfBidsInLoop = key.doubleValue();
			    BigDecimal value = entryInLoop.getValue();
			    Double valueOfBidsInLoop = value.doubleValue();
			    if ((keyOfBidsInLoop <= doubleValueOfBids) || (keyOfBidsInLoop >= nintySevenPercentOfBids)) {
			    	totalQuantOfBids=totalQuantOfBids + valueOfBidsInLoop;	
			    }    
			}
		 
		 
		 Map<BigDecimal, BigDecimal> sortedMapOfAsks = new TreeMap<BigDecimal, BigDecimal>(askMap);
		 Map.Entry<BigDecimal, BigDecimal> entryOfAsks = sortedMapOfAsks.entrySet().iterator().next();
		 BigDecimal keyOfAsks= entryOfAsks.getKey();
		 Double doubleValueOfAsks = keyOfAsks.doubleValue();
		 
		 Double hundredAndThreeOfAsks = 1.03 * keyOfAsks.doubleValue();
		 BigDecimal bidsAtHundredAndThree = BigDecimal.valueOf(hundredAndThreeOfAsks);
		 Double totalQuantOfAsks = 0.0;
		 
		 BigDecimal priceOfAsks = entryOfAsks.getValue();
		 Double dPriceOfAsks = priceOfAsks.doubleValue();
		 
		 for (Map.Entry<BigDecimal, BigDecimal> entryInLoop : sortedMapOfAsks.entrySet()) {
			    BigDecimal key = entryInLoop.getKey();
			    Double keyOfBidsInLoop = key.doubleValue();
			    BigDecimal value = entryInLoop.getValue();
			    Double valueOfBidsInLoop = value.doubleValue();
			    if ((keyOfBidsInLoop <= doubleValueOfAsks) || (keyOfBidsInLoop >= hundredAndThreeOfAsks)) {
			    	totalQuantOfAsks=totalQuantOfAsks + valueOfBidsInLoop;	
			    }    
			}
		 
		 if ((totalQuantOfBids >= 3.0) && (percentChangeInUSD <= 3) && (percentChangeInETHInteger <= 3)) {
			 System.out.println("CONDITION # 1 SATISFIED ");
			 if(totalQuantOfBids >= (4.0 * totalQuantOfAsks)) {
				 System.out.println("CONDITION # 2 SATISFIED ");
				 System.out.println("############################################ ");
				 System.out.println("TOTAL QUANTITY in BIDS: "+ totalQuantOfBids);
				 System.out.println("TOTAL QUANTITY in ASKS: "+ totalQuantOfAsks);
				 System.out.println("BEST PRICE IN BIDS: "+ dBestPriceAtBid);
				 System.out.println("BEST PRICE IN ASKS: "+ dPriceOfAsks);
				 System.out.println("############################################ ");
				 // WITH THE ACCOUNT DETAILS PLACE THE BUY ORDER AND THEN SELL IT AT HIGHER PRICE
				 System.out.println("INSIDE THE PLACING ORDER STRATEGY: ");
				 JSONObject obj = new JSONObject();
				 obj.put("TOTAL QUANTITY in BIDS",totalQuantOfBids);
				 obj.put("TOTAL QUANTITY in ASKS",totalQuantOfAsks);
				 obj.put("BEST PRICE IN BIDS", dBestPriceAtBid);
				 obj.put("BEST PRICE IN ASKS", dPriceOfAsks);
				 StringWriter out = new StringWriter();
				 try {
			     obj.writeJSONString(out);
			     String jsonText = out.toString();
				 pool.set("BEST TRADE"+timenow, jsonText);
				 }
				 catch(IOException ex) {
					 System.out.println (ex.toString());
					 
				 }
			     
				 
				 
			 }
			 
		 }
		 time = cal.getTimeInMillis();
	    afterAddingTenSeconds=new Date(time + (1 * 10000));
	     timeAfter = afterAddingTenSeconds.getTime();
	     askMap.clear();
	     bidMap.clear();
    	}
    	
      }
    });
  }

  /**
   * Updates an order book (bids or asks) with a delta received from the server.
   *
   * Whenever the qty specified is ZERO, it means the price should was removed from the order book.
   */
  private void updateOrderBook(NavigableMap<BigDecimal, BigDecimal> lastOrderBookEntries, List<OrderBookEntry> orderBookDeltas) {
    for (OrderBookEntry orderBookDelta : orderBookDeltas) {
      BigDecimal price = new BigDecimal(orderBookDelta.getPrice());
      BigDecimal qty = new BigDecimal(orderBookDelta.getQty());
      if (qty.compareTo(BigDecimal.ZERO) == 0) {
        // qty=0 means remove this level
        lastOrderBookEntries.remove(price);
      } else {
        lastOrderBookEntries.put(price, qty);
      }
    }
  }

  public NavigableMap<BigDecimal, BigDecimal> getAsks() {
	  
	  //NavigableMap<BigDecimal, BigDecimal> asksOutput = objectify(pool.get(ASKS), new TypeReference<NavigableMap<BigDecimal, BigDecimal>>(){});
    return depthCache.get(ASKS);
	//  return asksOutput;
  }

  public NavigableMap<BigDecimal, BigDecimal> getBids() {
    //return depthCache.get(BIDS);
	  //NavigableMap<BigDecimal, BigDecimal> bidsOutput = objectify(pool.get(BIDS), new TypeReference<NavigableMap<BigDecimal, BigDecimal>>(){});
	    return depthCache.get(BIDS);
		//  return bidsOutput;
  }

  /**
   * @return the best ask in the order book
   */
  private Map.Entry<BigDecimal, BigDecimal> getBestAsk() {
    return getAsks().lastEntry();
  }

  /**
   * @return the best bid in the order book
   */
  private Map.Entry<BigDecimal, BigDecimal> getBestBid() {
    return getBids().firstEntry();
  }

  /**
   * @return a depth cache, containing two keys (ASKs and BIDs), and for each, an ordered list of book entries.
   */
  public Map<String, NavigableMap<BigDecimal, BigDecimal>> getDepthCache() {
    return depthCache;
  }

  /**
   * Prints the cached order book / depth of a symbol as well as the best ask and bid price in the book.
   */
  private void printDepthCache() {
    System.out.println(depthCache);
    System.out.println("ASKS:");
    //getAsks().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    System.out.println("BIDS:");
    //getBids().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    setFinalAsks();
    setFinalBids();
    System.out.println("BEST ASK: " + toDepthCacheEntryString(getBestAsk()));
    System.out.println("BEST BID: " + toDepthCacheEntryString(getBestBid()));
  }
  
  private void setFinalAsks() {
	  Map.Entry<BigDecimal, BigDecimal> depthCacheEntry = getBestAsk();
	  askMap.put(depthCacheEntry.getKey(), depthCacheEntry.getValue());
	  
  }
  
  private void setFinalBids() {
	  Map.Entry<BigDecimal, BigDecimal> depthCacheEntry = getBestBid();
	  bidMap.put(depthCacheEntry.getKey(), depthCacheEntry.getValue());
	  
  }

  /**
   * Pretty prints an order book entry in the format "price / quantity".
   */
  private static String toDepthCacheEntryString(Map.Entry<BigDecimal, BigDecimal> depthCacheEntry) {
    return depthCacheEntry.getKey().toPlainString() + " / " + depthCacheEntry.getValue();
  }

  public static void main(String[] args) {
	  //BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
	 // BinanceApiRestClient client = factory.newRestClient();
	  //List<TickerPrice> allPrices = client.getAllPrices();
	  //for (int i = 0; i < allPrices.size(); i++) {
		//  TickerPrice tickPrice = allPrices.get(i);
		 // String sym = tickPrice.getSymbol();
		  //if(sym.contains("BTC")) {
		  new DepthCacheExample("ETHBTC");
		  //}
		//}
	  //for (String symb : allPrices.getSymbol()) {
	      //asks.put(new BigDecimal(ask.getPrice()), new BigDecimal(ask.getQty()));
	    //}
	  
    //new DepthCacheExample("ETHBTC");
  }
}
