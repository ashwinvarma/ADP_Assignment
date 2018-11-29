package shoppingcart;

/**
 * @author Ashwin Varma
 *
 */
public class FlatDiscountSlabs {

	int rangeMin;
	int rangeMax;
	float discPerc;
	/**
	 * 
	 */
	public FlatDiscountSlabs() {
		super();
	}
	/**
	 * @param rangeMin
	 * @param rangeMax
	 * @param discPerc
	 */
	public FlatDiscountSlabs(int rangeMin, int rangeMax, float discPerc) {
		super();
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
		this.discPerc = discPerc;
	}
	/**
	 * @return the rangeMin
	 */
	public int getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public int getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}
	/**
	 * @return the discPerc
	 */
	public float getDiscPerc() {
		return discPerc;
	}
	/**
	 * @param discPerc the discPerc to set
	 */
	public void setDiscPerc(float discPerc) {
		this.discPerc = discPerc;
	}

	/*@Override
	public String toString() {
		return "Slab [rangeMin=" + rangeMin + ", rangeMax=" + rangeMax
				+ ", discountPercent=" + discountPercent + "]";
	}*/
}

