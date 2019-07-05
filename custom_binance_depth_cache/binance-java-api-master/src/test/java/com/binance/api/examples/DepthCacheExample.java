package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.binance.api.client.domain.market.TickerPrice;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

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

  private Map<String, NavigableMap<BigDecimal, BigDecimal>> depthCache;

  public DepthCacheExample(String symbol) {
    initializeDepthCache(symbol);
    startDepthEventStreaming(symbol);
  }
  
  private static Jedis pool = new Jedis(redisHost);
  
  
 // private static String stringify(Object object) {
	   // ObjectMapper jackson = new ObjectMapper();
	    //jackson.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	    //jackson.setDefaultPropertyInclusion(JsonInclude.Value.construct(Include.ALWAYS, Include.NON_NULL));
	    //jackson.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	    //jackson.setSerializationInclusion(Include.NON_NULL);
	    //jackson.setSerializationInclusion(Include.NON_NULL);
	//    try {
	      //  return jackson.writeValueAsString(object);
	  //  } catch (Exception ex) {
	        //LOG.log(Level.SEVERE, "Error while creating json: ", ex);
	    //}
	    //return null;
	//}
  
  //private static <T> T objectify(String content, TypeReference valueType) {
	//    try {
	       // ObjectMapper mapper = new ObjectMapper();
	        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	       // mapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
	        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
	        //dateFormat.setTimeZone(Calendar.getInstance().getTimeZone());
	        //mapper.setDateFormat(dateFormat);
	        //return mapper.readValue(content, valueType);
	  //  } catch (Exception e) {
	       // LOG.log(Level.WARNING, "returning null because of error : {0}", e.getMessage());
	    //    return null;
	    //}
	//}

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

    client.onDepthEvent(symbol.toLowerCase(), response -> {
      if (response.getUpdateId() > lastUpdateId) {
        System.out.println(response);
        lastUpdateId = response.getUpdateId();
        updateOrderBook(getAsks(), response.getAsks());
        updateOrderBook(getBids(), response.getBids());
        printDepthCache();
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
    getAsks().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    System.out.println("BIDS:");
    getBids().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    System.out.println("BEST ASK: " + toDepthCacheEntryString(getBestAsk()));
    System.out.println("BEST BID: " + toDepthCacheEntryString(getBestBid()));
  }

  /**
   * Pretty prints an order book entry in the format "price / quantity".
   */
  private static String toDepthCacheEntryString(Map.Entry<BigDecimal, BigDecimal> depthCacheEntry) {
    return depthCacheEntry.getKey().toPlainString() + " / " + depthCacheEntry.getValue();
  }

  public static void main(String[] args) {
	  BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
	  BinanceApiRestClient client = factory.newRestClient();
	  List<TickerPrice> allPrices = client.getAllPrices();
	  for (int i = 0; i < allPrices.size(); i++) {
		  TickerPrice tickPrice = allPrices.get(i);
		  String sym = tickPrice.getSymbol();
		  if(sym.contains("BTC")){
		    new DepthCacheExample(sym);
		  }
		}
	  //for (String symb : allPrices.getSymbol()) {
	      //asks.put(new BigDecimal(ask.getPrice()), new BigDecimal(ask.getQty()));
	    //}
	  
    //new DepthCacheExample("ETHBTC");
  }
}
