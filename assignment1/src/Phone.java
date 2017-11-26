
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Phone implements java.io.Serializable {
	
	String id;
	String retailer;
    String image;
    String name;
    String company;
    String condition;
    String color;
	String description;
    float price;
	int qty;
	String rebate;
	String rebateName;
	String sale;
	Float original;
	
	//HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
  
    public Phone(){
        //accessories = new HashMap<String,Accessory>();
    }

	void setId(String id) {
		this.id = id;
	}

	public String getId() {
			return id;
		}

	void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getRetailer() {
			return retailer;
		}

	void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
			return image;
		}

	void setName(String name) {
		this.name = name;
	}

	public String getName() {
			return name;
		}

	void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
			return company;
		}

	void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
			return condition;
		}

	void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
			return color;
		}

	void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
			return description;
		}

	void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
			return price;
		}
		
		void setQty(int qty) {
		this.qty = qty;
	}

	public int getQty() {
			return qty;
		}
	
	void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getRebate() {
			return rebate;
		}
	void setRebateName(String rebateName) {
		this.rebateName = rebateName;
	}

	public String getRebateName() {
			return rebateName;
		}
		
	void setOriginal(float original) {
		this.original = original;
	}

	public float getOriginal() {
			return original;
		}
		
	void setSale(String sale) {
		this.sale = sale;
	}

	public String getSale() {
			return sale;
		}
	/*
	void setAccessories(HashMap<String,Accessory> accessories) {
			this.accessories = accessories;
		}

	public HashMap<String,Accessory> getAccessories() {
			return accessories;
		}
	*/





}
