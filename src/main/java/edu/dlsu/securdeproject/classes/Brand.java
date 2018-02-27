import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brand {
	private long brandId;
	private String brandName;

	public Brand() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long productId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}