package com.alignmentsystems.sbe._2020_09_01_08_39_34;


//Author : John Greenan
//Creation Date : 2020_09_01_08_39_34

public class BusinessMessageReject implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private String businesRejectRefId;
 private businessRejectReasonEnum businessRejectReason;
 private String text;

//Implementation required



//Implementation required fields...


//Header fields....




public BusinessMessageReject(String businesRejectRefId , businessRejectReasonEnum businessRejectReason , String text){
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



 
 public String getBusinesRejectRefId() {
     return this.businesRejectRefId;
 }
 
 public void setBusinesRejectRefId(String businesRejectRefId) {
     this.businesRejectRefId = businesRejectRefId;
 } 
 public businessRejectReasonEnum getBusinessRejectReason() {
     return this.businessRejectReason;
 }
 
 public void setBusinessRejectReason(businessRejectReasonEnum businessRejectReason) {
     this.businessRejectReason = businessRejectReason;
 } 
 public String getText() {
     return this.text;
 }
 
 public void setText(String text) {
     this.text = text;
 } 
}
