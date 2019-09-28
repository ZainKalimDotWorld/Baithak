package com.example.sample;

public class Item {

    private int ID;
    private String Category;
    private String Product;
    private String Description;
    private String Image;
    private float Price;



//    public Item(String name, int drawable) {
//        this.drawable = drawable;
//        this.name = name;
//    }

    public int getID() {
        return ID;
    }
    public void setID (int ID)
    {
        this.ID = ID;
    }

    public String getCategory() {
        return Category;
    }
    public void setCategory (String Category)
    {
        this.Category = Category;
    }


    public String getProduct() {
        return Product;
    }
    public void setProduct (String Product)
    {
        this.Product = Product;
    }


    public String getDescription() {
        return Description;
    }
    public void setDescription (String Description)
    {
        this.Description = Description;
    }


    public String getImage() {
        return Image;
    }
    public void setImage (String Image)
    {
        this.Image = Image;
    }


    public float getPrice() {
        return Price;
    }
    public void setPrice (float Price)
    {
        this.Price = Price;
    }
}
