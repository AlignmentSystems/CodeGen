package com.alignmentsystems.sbe._2020_09_01_08_39_34;


//Author : John Greenan
//Creation Date : 2020_09_01_08_39_34

public class ExecutionReport implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private String orderID;
 private String execID;
 private execTypeEnum execType;
 private ordStatusEnum ordStatus;
 private String symbol;
 private MONTH_YEAR maturityMonthYear;
 private sideEnum side;
 private qtyEncoding leavesQty;
 private qtyEncoding cumQty;
 private date tradeDate;
 private optionalDecimalEncoding fillPx;
 private qtyEncoding fillQty;

//Implementation required



//Implementation required fields...


//Header fields....




public ExecutionReport(String orderID , String execID , execTypeEnum execType , ordStatusEnum ordStatus , String symbol , MONTH_YEAR maturityMonthYear , sideEnum side , qtyEncoding leavesQty , qtyEncoding cumQty , date tradeDate , optionalDecimalEncoding fillPx , qtyEncoding fillQty){
	this.OrderID = orderID ,
	this.ExecID = execID ,
	this.ExecType = execType ,
	this.OrdStatus = ordStatus ,
	this.Symbol = symbol ,
	this.MaturityMonthYear = maturityMonthYear ,
	this.Side = side ,
	this.LeavesQty = leavesQty ,
	this.CumQty = cumQty ,
	this.TradeDate = tradeDate ,
	this.FillPx = fillPx ,
	this.FillQty = fillQty
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
	return "ExecutionReport ["	 
	+ "orderID =" + orderID
	+ ", execID =" + execID
	+ ", execType =" + execType
	+ ", ordStatus =" + ordStatus
	+ ", symbol =" + symbol
	+ ", maturityMonthYear =" + maturityMonthYear
	+ ", side =" + side
	+ ", leavesQty =" + leavesQty
	+ ", cumQty =" + cumQty
	+ ", tradeDate =" + tradeDate
	+ ", fillPx =" + fillPx
	+ ", fillQty =" + fillQty;
}



 
 public String getOrderID() {
     return this.orderID;
 }
 
 public void setOrderID(String orderID) {
     this.orderID = orderID;
 } 
 public String getExecID() {
     return this.execID;
 }
 
 public void setExecID(String execID) {
     this.execID = execID;
 } 
 public execTypeEnum getExecType() {
     return this.execType;
 }
 
 public void setExecType(execTypeEnum execType) {
     this.execType = execType;
 } 
 public ordStatusEnum getOrdStatus() {
     return this.ordStatus;
 }
 
 public void setOrdStatus(ordStatusEnum ordStatus) {
     this.ordStatus = ordStatus;
 } 
 public String getSymbol() {
     return this.symbol;
 }
 
 public void setSymbol(String symbol) {
     this.symbol = symbol;
 } 
 public MONTH_YEAR getMaturityMonthYear() {
     return this.maturityMonthYear;
 }
 
 public void setMaturityMonthYear(MONTH_YEAR maturityMonthYear) {
     this.maturityMonthYear = maturityMonthYear;
 } 
 public sideEnum getSide() {
     return this.side;
 }
 
 public void setSide(sideEnum side) {
     this.side = side;
 } 
 public qtyEncoding getLeavesQty() {
     return this.leavesQty;
 }
 
 public void setLeavesQty(qtyEncoding leavesQty) {
     this.leavesQty = leavesQty;
 } 
 public qtyEncoding getCumQty() {
     return this.cumQty;
 }
 
 public void setCumQty(qtyEncoding cumQty) {
     this.cumQty = cumQty;
 } 
 public date getTradeDate() {
     return this.tradeDate;
 }
 
 public void setTradeDate(date tradeDate) {
     this.tradeDate = tradeDate;
 } 
 public optionalDecimalEncoding getFillPx() {
     return this.fillPx;
 }
 
 public void setFillPx(optionalDecimalEncoding fillPx) {
     this.fillPx = fillPx;
 } 
 public qtyEncoding getFillQty() {
     return this.fillQty;
 }
 
 public void setFillQty(qtyEncoding fillQty) {
     this.fillQty = fillQty;
 } 
}
