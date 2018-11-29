package shoppingcart;

/**
 * @author Ashwin Varma
 *
 */
public class Categories {

	int id;
	String name;
	float discPerc;

	/**
	 * 
	 */
	public Categories() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param discPerc
	 */
	public Categories(int id, String name, float discPerc) {
		super();
		this.id = id;
		this.name = name;
		this.discPerc = discPerc;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
		return "Category [id=" + id + ", name=" + name + ", discountPercent="
				+ discountPercent + "]";
	}*/
}

