# Binance_depth_cache
The Depth cache example in this project uses Redis cache.


Make sure Redis cache is running on your machine on port 6379


Build and Run the example/DepthCacheExample.java file


pom.xml has been updated with redis dependencies.

##Output:


Time Now: 1562933500020

Time fututre: 1562933499426

INSIDE TIME STRATEGY:

INSIDE FIRST STRATEGY:

############################################

TOTAL QUANTITY in BIDS: 16.116

TOTAL QUANTITY in ASKS: 0.289

BEST PRICE IN BIDS: 0.073

BEST PRICE IN ASKS0.289

############################################

INSIDE THE PLACING ORDER STRATEGY: 

Time Now: 1562934048739

Time fututre: 1562934049787

DepthEvent[eventType=depthUpdate,eventTime=1562934049727,symbol=ETHBTC,updateId=681540132,bids=

[OrderBookEntry[price=0.02354400,qty=0.08400000], OrderBookEntry[price=0.02353800,qty=0.00000000], 

OrderBookEntry[price=0.02353700,qty=0.67700000], OrderBookEntry[price=0.02353400,qty=10.24600000], 

OrderBookEntry[price=0.02352300,qty=12.78000000], OrderBookEntry[price=0.02351100,qty=24.19100000], 

OrderBookEntry[price=0.02350000,qty=49.85200000], OrderBookEntry[price=0.02349400,qty=43.30600000], 

OrderBookEntry[price=0.02349300,qty=0.70800000], OrderBookEntry[price=0.02349000,qty=42.46400000], 

OrderBookEntry[price=0.02348700,qty=16.10300000], OrderBookEntry[price=0.02347800,qty=7.40200000], 

OrderBookEntry[price=0.02324700,qty=0.08100000]],asks=[OrderBookEntry[price=0.02355200,qty=1.76000000], 

OrderBookEntry[price=0.02355300,qty=0.21000000], OrderBookEntry[price=0.02355400,qty=0.00000000], 

OrderBookEntry[price=0.02355500,qty=0.00000000], OrderBookEntry[price=0.02355600,qty=5.68400000], 

OrderBookEntry[price=0.02355700,qty=20.00000000], OrderBookEntry[price=0.02356300,qty=5.00000000], 

OrderBookEntry[price=0.02357000,qty=38.00000000], OrderBookEntry[price=0.02357500,qty=0.00000000], 

OrderBookEntry[price=0.02358600,qty=3.65100000], OrderBookEntry[price=0.02361400,qty=79.46300000], 

OrderBookEntry[price=0.02362700,qty=3.51200000], OrderBookEntry[price=0.02372000,qty=0.00000000], 

OrderBookEntry[price=0.02425900,qty=10.17400000]]]


{ASKS={0.05000000=608.39800000, 0.03526200=0.07800000, 0.03453100=0.20000000, 0.03452500=0.13100000, 0.03381300=0.04500000, 

0.03317200=0.06900000, 0.03316800=0.16300000, 0.03313400=0.16600000, 0.03299300=0.07100000, 0.03293000=0.54200000, 

0.03268900=0.04700000, 0.03258300=0.14600000, 0.03207300=0.11000000, 0.03202900=2.14300000, 0.03191100=0.34000000, 

0.03176000=0.64100000, 0.03158300=0.05200000, 0.03134100=0.11300000, 0.03067700=0.08700000, 0.03026900=0.04900000, 

0.03011300=0.28900000, 0.02981500=0.08800000, 0.02955800=0.50300000, 0.02886700=1.05400000, 0.02860600=0.04100000, 

0.02854900=0.12100000, 0.02812600=0.31400000, 0.02806600=0.18300000, 0.02755300=0.05300000, 0.02730500=0.09600000, 

0.02712900=0.49300000, 0.02703300=0.05600000, 0.02678200=0.16600000, 0.02675100=0.14000000, 0.02659300=0.26400000, 

0.02653000=0.68200000, 0.02652700=0.04300000, 0.02646500=0.00600000, 0.02645400=0.09400000, 0.02643500=0.00500000, 

0.02642800=0.07900000, 0.02583600=0.05600000, 0.02500000=348.79300000, 0.02487800=0.13100000, 0.02481800=0.04500000, 

0.02476400=7.37400000, 0.02474600=0.05000000, 0.02466300=0.11300000, 0.02461200=0.00800000, 0.02460100=3.01300000, 

0.02439500=0.12700000, 0.02429100=9.91700000, 0.02428900=0.00900000, 0.02428600=49.59500000, 0.02428500=29.77500000, 

0.02428100=0.07600000, 0.02427700=0.00500000, 0.02427100=0.03600000, 0.02425900=10.17400000, 0.02422800=0.43000000, 

0.02422600=0.00500000, 0.02422500=19.07400000, 0.02422400=19.24600000, 0.02422300=0.61500000, 0.02422100=0.18000000, 

0.02421800=0.03200000, 0.02421600=6.61200000, 0.02421400=1.02000000, 0.02421100=0.01500000, 0.02420500=38.06000000, 


0.02420300=19.18400000, 0.02416600=0.34300000, 0.02415600=0.06100000, 0.02415100=0.00700000, 0.02411100=0.08700000, 

0.02410600=6.18200000, 0.02409400=1.89000000, 0.02403900=0.31900000, 0.02403600=0.63100000, 0.02403000=0.00500000, 

0.02394200=0.00500000, 0.02393200=0.66700000, 0.02393100=6.10000000, 0.02391700=1.10600000, 0.02391600=0.28500000, 

0.02389900=0.04400000, 0.02385800=0.24200000, 0.02384900=5.45900000, 0.02384600=2.27400000, 0.02384300=0.10700000, 

0.02384000=0.15100000, 0.02383800=3.00000000, 0.02383500=0.01200000, 0.02383200=0.06000000, 0.02383000=0.21100000, 

0.02382600=0.05300000, 0.02380500=0.30200000, 0.02380400=12.27300000, 0.02379900=124.58400000, 0.02379300=0.07100000, 

0.02377000=0.08500000, 0.02376200=0.84200000, 0.02375400=0.65100000, 0.02375000=2.14500000, 0.02374500=0.32900000, 

0.02374300=104.81500000, 0.02371400=0.30000000, 0.02371300=0.40000000, 0.02371000=10.99700000, 0.02370100=12.41100000, 

0.02370000=4.00000000, 0.02369900=0.20000000, 0.02369800=0.67300000, 0.02369400=0.01200000, 0.02368400=1.58700000, 

0.02368000=4.51400000, 0.02367600=10.46000000, 0.02367100=0.20900000, 0.02366500=345.48000000, 0.02366300=0.20000000, 

0.02366200=8.07400000, 0.02366000=4.61900000, 0.02365600=17.00000000, 0.02365500=7.94400000, 0.02365200=0.05600000, 

0.02365000=0.20000000, 0.02364500=0.10500000, 0.02364300=0.04000000, 0.02364200=50.52400000, 0.02364000=4.00000000, 

0.02363900=2.00000000, 0.02363800=3.63000000, 0.02363700=30.00000000, 0.02363600=2.00000000, 0.02363400=0.21200000, 

0.02363000=0.04400000, 0.02362800=31.00000000, 0.02362700=3.51200000, 0.02362500=0.15000000, 0.02362200=7.29400000, 

0.02362100=11.69300000, 0.02362000=1.98000000, 0.02361400=79.46300000, 0.02361100=1.69800000, 0.02360900=3.51500000, 

0.02360700=3.64800000, 0.02360600=3.27000000, 0.02360300=1.30000000, 0.02360100=41.97800000, 0.02359900=2.12100000, 

0.02359800=28.31600000, 0.02359600=0.13000000, 0.02358600=3.65100000, 0.02358500=4.00000000, 0.02358400=3.00000000, 

0.02357900=2.54700000, 0.02357700=14.63800000, 0.02357600=21.22600000, 0.02357200=9.33500000, 0.02357000=38.00000000, 

0.02356900=6.45100000, 0.02356800=9.15200000, 0.02356700=10.00000000, 0.02356600=9.34500000, 0.02356400=8.00000000, 

0.02356300=5.00000000, 0.02356200=8.00000000, 0.02356100=7.02200000, 0.02356000=7.77700000, 0.02355800=0.44900000, 

0.02355700=20.00000000, 0.02355600=5.68400000, 0.02355300=0.21000000, 0.02355200=1.76000000}, BIDS={0.02360700=4.30800000, 

0.02354400=0.08400000, 0.02354200=0.08400000, 0.02354000=0.02800000, 0.02353700=0.67700000, 0.02353600=25.61000000, 

0.02353400=10.24600000, 0.02353300=12.76400000, 0.02353200=5.02900000, 0.02353100=5.08200000, 0.02353000=0.86400000, 

0.02352900=0.19500000, 0.02352800=0.21000000, 0.02352700=1.09300000, 0.02352500=0.40600000, 0.02352400=0.00600000, 

0.02352300=12.78000000, 0.02352200=14.54100000, 0.02352100=7.42800000, 0.02352000=5.66700000, 0.02351900=0.02400000, 

0.02351800=0.26200000, 0.02351700=0.05000000, 0.02351400=0.00500000, 0.02351300=38.20100000, 0.02351100=24.19100000, 

0.02351000=11.81800000, 0.02350800=0.00500000, 0.02350700=1.42200000, 0.02350600=14.71800000, 0.02350500=4.69100000, 

0.02350200=127.66000000, 0.02350100=0.27500000, 0.02350000=49.85200000, 0.02349900=7.12200000, 0.02349800=0.30000000, 

0.02349600=0.06200000, 0.02349500=0.00700000, 0.02349400=43.30600000, 0.02349300=0.70800000, 0.02349200=30.00600000, 

0.02349100=7.26600000, 0.02349000=42.46400000, 0.02348700=16.10300000, 0.02348600=20.00000000, 0.02348400=0.00500000, 

0.02348200=4.58500000, 0.02348000=9.20000000, 0.02347800=7.40200000, 0.02347600=0.67800000, 0.02347400=16.80000000, 

0.02347200=45.77400000, 0.02347100=32.00600000, 0.02347000=20.53900000, 0.02346900=0.05800000, 0.02346800=0.02100000, 

0.02346700=7.18600000, 0.02346400=0.39600000, 0.02346300=0.00500000, 0.02346000=4.07000000, 0.02345600=32.79800000, 

0.02345500=0.20800000, 0.02345400=0.21600000, 0.02345000=8.39200000, 0.02344900=46.54200000, 0.02344700=0.59700000, 

0.02344200=0.04000000, 0.02344000=25.06700000, 0.02343600=60.34500000, 0.02343400=0.01000000, 0.02343300=0.11200000, 

0.02343100=2.52400000, 0.02343000=4.12200000, 0.02342900=0.63300000, 0.02342800=0.02400000, 0.02342700=0.04300000, 

0.02342600=114.97700000, 0.02342500=0.33000000, 0.02342400=0.01300000, 0.02342300=1.96700000, 0.02342200=1.96000000, 


0.02341800=0.66400000, 0.02341300=0.05500000, 0.02341200=0.18500000, 0.02341100=0.00500000, 0.02340900=0.00700000, 

0.02340800=1.16200000, 0.02340200=83.66800000, 0.02340100=30.91200000, 0.02339400=0.05800000, 0.02339100=0.94300000, 

0.02338700=0.05600000, 0.02338500=1.00100000, 0.02338100=1.38300000, 0.02337700=15.97200000, 0.02336600=0.19000000, 

0.02336500=0.81000000, 0.02336400=0.20000000, 0.02336300=0.39800000, 0.02336200=0.15300000, 0.02336100=5.65500000, 

0.02336000=0.02600000, 0.02335900=0.16500000, 0.02335800=0.21900000, 0.02335700=0.87800000, 0.02335600=0.14900000, 

0.02335500=0.15300000, 0.02335400=1.01800000, 0.02335300=0.59600000, 0.02335200=7.81600000, 0.02335100=8.79800000, 

0.02335000=7.06300000, 0.02334900=0.51200000, 0.02334800=0.34600000, 0.02334700=0.01700000, 0.02334400=0.13400000, 

0.02334300=0.10800000, 0.02334200=0.93800000, 0.02333900=0.00500000, 0.02333700=0.29500000, 0.02333600=0.63200000, 

0.02333500=3.80800000, 0.02333400=0.39900000, 0.02333300=0.41500000, 0.02333200=2.59700000, 0.02333100=0.01300000, 

0.02333000=0.15200000, 0.02332900=0.14700000, 0.02332800=0.14700000, 0.02332700=1.04400000, 0.02332500=0.06100000, 

0.02332300=0.62300000, 0.02332200=0.01800000, 0.02332100=212.18700000, 0.02332000=0.00500000, 0.02331900=0.14500000, 

0.02331500=0.10700000, 0.02331300=0.74300000, 0.02331200=1.65900000, 0.02330900=5.15300000, 0.02330700=0.36500000, 

0.02330400=5.87100000, 0.02330300=0.79000000, 0.02330200=0.15300000, 0.02329900=0.14800000, 0.02329500=43.25700000, 

0.02329300=0.25500000, 0.02328500=1.93200000, 0.02328400=2.83200000, 0.02328200=1.40900000, 0.02328100=0.15000000, 

0.02327900=0.28000000, 0.02327700=0.05000000, 0.02326800=2.13200000, 0.02326700=0.40800000, 0.02326100=0.00800000, 

0.02326000=1.12800000, 0.02325900=0.08500000, 0.02325800=0.00500000, 0.02325700=1.48000000, 0.02325500=0.08400000, 

0.02325100=18.20600000, 0.02324700=0.08100000, 0.02324600=0.04700000, 0.02324200=0.92900000, 0.02322900=14.85500000, 

0.02322700=18.46800000, 0.02322100=0.05000000, 0.02321800=0.14700000, 0.02321700=0.51300000, 0.02321600=0.00500000, 

0.02321400=0.32800000, 0.02320600=0.00500000, 0.02319900=0.04400000, 0.02319700=0.14700000, 0.02319300=0.46000000, 

0.02319200=2.39800000, 0.02319100=0.39900000, 0.02319000=0.63100000, 0.02318900=0.05500000, 0.02318700=0.14600000, 

0.02318500=0.31900000, 0.02317300=0.58000000, 0.02315700=0.15600000, 0.02315200=9.45400000, 0.02313400=0.58700000, 

0.02312600=0.03100000, 0.02312500=0.00700000, 0.02312000=0.00800000, 0.02311700=5.24200000, 0.02310800=0.18100000, 

0.02310700=0.14600000, 0.02310400=0.14800000, 0.02310000=59.98000000, 0.02309900=0.22200000, 0.02309600=0.18600000, 

0.02309400=0.03600000, 0.02307800=0.09500000, 0.02307000=34.90100000, 0.02306200=0.51000000, 0.02304100=3.12900000, 

0.02304000=0.00600000, 0.02303400=8.33800000, 0.02303300=0.02100000, 0.02303100=0.50000000, 0.02300800=0.11700000, 

0.02298600=0.00500000, 0.02298500=21.77800000, 0.02298300=0.10000000, 0.02293000=3.39200000, 0.02289100=0.02800000, 

0.02278900=0.00500000, 0.02277300=4.16400000, 0.02275300=0.00800000, 0.02274900=0.00500000, 0.02274800=0.18900000, 

0.02271800=0.87800000, 0.02270000=1.27800000, 0.02264400=0.02600000, 0.02264000=0.06700000, 0.02258900=0.00900000, 

0.02246000=0.02600000, 0.02245800=51.03500000, 0.02244900=20.72200000, 0.02244300=20.11800000, 0.02242900=10.01700000, 

0.02241800=2.19600000, 0.02205300=0.08000000, 0.02173100=0.05000000, 0.02000100=429.22600000}}

ASKS:

BIDS:

BEST ASK: 0.02355200 / 1.76000000

BEST BID: 0.02360700 / 4.30800000

Time Now: 1562934049760

Time fututre: 1562934049787

## WITH THE ACCOUNT DETAILS AND CURRENCY THE ETH CAN BE BOUGHT AND SOLD WITH THIS STRATEGY


