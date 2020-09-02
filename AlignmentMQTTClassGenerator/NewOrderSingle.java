package com.alignmentsystems.sbe._2020_09_02_01_26_02;


//Author : John Greenan
//Creation Date : 2020_09_02_01_26_02

public class NewOrderSingle implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private idString clOrdId;
 private idString account;
 private idString symbol;
 private sideEnum side;
 private timestampEncoding transactTime;
 private qtyEncoding orderQty;
 private ordTypeEnum ordType;
 private optionalDecimalEncoding price;
 private optionalDecimalEncoding stopPx;

//Implementation required



//Implementation required fields...


//Header fields....




public NewOrderSingle(idString clOrdId , idString account , idString symbol , sideEnum side , timestampEncoding transactTime , qtyEncoding orderQty , ordTypeEnum ordType , optionalDecimalEncoding price , optionalDecimalEncoding stopPx){
	this.ClOrdId = clOrdId ,
	this.Account = account ,
	this.Symbol = symbol ,
	this.Side = side ,
	this.TransactTime = transactTime ,
	this.OrderQty = orderQty ,
	this.OrdType = ordType ,
	this.Price = price ,
	this.StopPx = stopPx
}


@Override
public boolean putByteBuffer(ByteBuffer payload) throws NotImplementedException {

		Boolean returnValue = Boolean.FALSE;
		int initialPosition = 0;

		try {
			initialPosition = payload.position();
			
			//implementation goes here!

			}
		} catch (Exception e) {
			log.error(e.toString(),e);
			returnValue = Boolean.FALSE;
		}
		return returnValue;
}


@Override
public ByteBuffer getByteBuffer()  throws NotImplementedException {
		try {
			final ByteOrder boSBE = ByteOrder.BIG_ENDIAN;
			byte[] signThis = null;

			//implementation goes here!

			return ByteBuffer.wrap(signThis); 
		} catch (Exception e) {
			log.error(e.toString(),e);
			return null;
		}
}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
public String toString() {
	return "NewOrderSingle ["	 
	+ "clOrdId =" + clOrdId
	+ ", account =" + account
	+ ", symbol =" + symbol
	+ ", side =" + side
	+ ", transactTime =" + transactTime
	+ ", orderQty =" + orderQty
	+ ", ordType =" + ordType
	+ ", price =" + price
	+ ", stopPx =" + stopPx;
}



 
 public idString getClOrdId() {
     return this.clOrdId;
 }
 
 public void setClOrdId(idString clOrdId) {
     this.clOrdId = clOrdId;
 } 
 public idString getAccount() {
     return this.account;
 }
 
 public void setAccount(idString account) {
     this.account = account;
 } 
 public idString getSymbol() {
     return this.symbol;
 }
 
 public void setSymbol(idString symbol) {
     this.symbol = symbol;
 } 
 public sideEnum getSide() {
     return this.side;
 }
 
 public void setSide(sideEnum side) {
     this.side = side;
 } 
 public timestampEncoding getTransactTime() {
     return this.transactTime;
 }
 
 public void setTransactTime(timestampEncoding transactTime) {
     this.transactTime = transactTime;
 } 
 public qtyEncoding getOrderQty() {
     return this.orderQty;
 }
 
 public void setOrderQty(qtyEncoding orderQty) {
     this.orderQty = orderQty;
 } 
 public ordTypeEnum getOrdType() {
     return this.ordType;
 }
 
 public void setOrdType(ordTypeEnum ordType) {
     this.ordType = ordType;
 } 
 public optionalDecimalEncoding getPrice() {
     return this.price;
 }
 
 public void setPrice(optionalDecimalEncoding price) {
     this.price = price;
 } 
 public optionalDecimalEncoding getStopPx() {
     return this.stopPx;
 }
 
 public void setStopPx(optionalDecimalEncoding stopPx) {
     this.stopPx = stopPx;
 } 
}
