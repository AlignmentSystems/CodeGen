package com.alignmentsystems.sbe._2020_09_02_01_26_02;


//Author : John Greenan
//Creation Date : 2020_09_02_01_26_02

public class BusinessMessageReject implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private idString businesRejectRefId;
 private businessRejectReasonEnum businessRejectReason;
 private DATA text;

//Implementation required



//Implementation required fields...


//Header fields....




public BusinessMessageReject(idString businesRejectRefId , businessRejectReasonEnum businessRejectReason , DATA text){
	this.BusinesRejectRefId = businesRejectRefId ,
	this.BusinessRejectReason = businessRejectReason ,
	this.Text = text
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
	return "BusinessMessageReject ["	 
	+ "businesRejectRefId =" + businesRejectRefId
	+ ", businessRejectReason =" + businessRejectReason
	+ ", text =" + text;
}



 
 public idString getBusinesRejectRefId() {
     return this.businesRejectRefId;
 }
 
 public void setBusinesRejectRefId(idString businesRejectRefId) {
     this.businesRejectRefId = businesRejectRefId;
 } 
 public businessRejectReasonEnum getBusinessRejectReason() {
     return this.businessRejectReason;
 }
 
 public void setBusinessRejectReason(businessRejectReasonEnum businessRejectReason) {
     this.businessRejectReason = businessRejectReason;
 } 
 public DATA getText() {
     return this.text;
 }
 
 public void setText(DATA text) {
     this.text = text;
 } 
}
