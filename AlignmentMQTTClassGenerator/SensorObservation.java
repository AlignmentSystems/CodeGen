package com.alignmentsystems.sbe._2020_09_02_03_57_15;


//Author : John Greenan
//Creation Date : 2020_09_02_03_57_15

public class SensorObservation implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private Long uUIDmsb;
 private Long uUIDlsb;
 private Long x;
 private Long y;
 private Long z;

//Implementation required



//Implementation required fields...


//Header fields....




public SensorObservation(Long uUIDmsb , Long uUIDlsb , Long x , Long y , Long z){
	this.UUIDmsb = uUIDmsb ,
	this.UUIDlsb = uUIDlsb ,
	this.x = x ,
	this.y = y ,
	this.z = z
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
	return "SensorObservation ["	 
	+ "uUIDmsb =" + uUIDmsb
	+ ", uUIDlsb =" + uUIDlsb
	+ ", x =" + x
	+ ", y =" + y
	+ ", z =" + z;
}



 
 public Long getUUIDmsb() {
     return this.uUIDmsb;
 }
 
 public void setUUIDmsb(Long uUIDmsb) {
     this.uUIDmsb = uUIDmsb;
 } 
 public Long getUUIDlsb() {
     return this.uUIDlsb;
 }
 
 public void setUUIDlsb(Long uUIDlsb) {
     this.uUIDlsb = uUIDlsb;
 } 
 public Long getX() {
     return this.x;
 }
 
 public void setX(Long x) {
     this.x = x;
 } 
 public Long getY() {
     return this.y;
 }
 
 public void setY(Long y) {
     this.y = y;
 } 
 public Long getZ() {
     return this.z;
 }
 
 public void setZ(Long z) {
     this.z = z;
 } 
}
