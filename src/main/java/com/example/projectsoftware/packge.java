package com.example.projectsoftware;

public class packge {
    private int packageId;
    private String packageName;
    private String description;
    private double price;
    private int maxGuests;
    private String[] includes;


    // Constructor
    public packge(int packageId, String packageName, String description, double price, int maxGuests, String[] includes) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.description = description;
        this.price = price;
        this.maxGuests = maxGuests;
        this.includes = includes;

    }

    // Getter methods
    public int getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public String[] getIncludes() {
        return includes;
    }


}
