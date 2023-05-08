package com.example.packyourbag.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {

    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

//    public List<Items> getBasicData(){
//        category = "Basic Needs";
//        List<Items> basicItem = new ArrayList<>();
//
//        basicItem.add(new Items("Visa",category,false));
//        basicItem.add(new Items("Passport",category,false));
//        basicItem.add(new Items("Adharcard",category,false));
//        basicItem.add(new Items("Wallet",category,false));
//        basicItem.add(new Items("Driving License",category,false));
//        basicItem.add(new Items("Currency",category,false));
//        basicItem.add(new Items("House Key",category,false));
//        basicItem.add(new Items("Book",category,false));
//        basicItem.add(new Items("Travel Pillow",category,false));
//        basicItem.add(new Items("Eye Patch",category,false));
//        basicItem.add(new Items("Umbrella",category,false));
//        basicItem.add(new Items("Note book",category,false));
//
//        return basicItem;
//    }


    public List<Items> getBasicData() {
        String[] data = {"Visa", "Passport", "Adharcard", "Wallet", "Driving", "Currency", "House Key", "Book", "Travel Pillow",
                "Eye Patch", "Umbrella", "Note book"};
        return prepareItemsList(MyConstants.BASIC_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Tooth-Brush", "Tooth-paste", "Floss", "Mouthwash", "Shaving Cream", "Razor Blade", "Hair Clip", "Moisturizer", "Contact Lens",
                "Makeup Materials", "Makeup Remover"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"Stocking", "Undeware", "Pajamas", "T-Shirts", "Event Dress", "Shirt", "Vest", "Jacket", "Skirt",
                "Jeans", "Suit"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Snapsuit", "Tooth-paste", "Outfit", "Baby Socks", "Cream", "Baby Cotton", "Baby Shampoo", "Diaper", "Serum",
                "Syrup", "Toys"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getHealthData() {
        String[] data = {"Aspirin", "Lens Solution", "Outfit", "Plaster", "Cream", "Cotton", "Pain Reliever", "Spary", "Serum",
                "Syrup", "Vitamins"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE, data);
    }

    public List<Items> getTechnologyData() {
        String[] data = {"Laptop", "Charger", "PenDrive", "HeadPhone", "ipad"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

    public List<Items> getFoodData() {
        String[] data = {"Sandwich", "Juice", "Tea Bag", "Coffee", "IceCream", "BabyFood", "Water", "ColdDrinx", "Milk",
                "Beans", "Chips"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getBeachSuppliesData() {
        String[] data = {"Sea Glasses", "Sea bed", "Suntan Cream", "Cap", "Cream", "Umbrella", "Baby Shampoo", "Swim Fins", "Clock",
                "Water Proof Cover", "Sand Toys"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getCarSuppliesData() {
        String[] data = {"Pump", "Key", "Cover", "Bag", "Cream", "Window Shades", "Car Jack", "Charger", "Sun Shades"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_FOOD_CAMEL_CASE, data);
    }

    public List<Items> getNeedsData() {
        String[] data = {"Bag", "BackPack", "Kit", "Socks", "Cream", "Lock", "Numbers", "Diaper", "Serum"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE, data);
    }

    public List<Items> prepareItemsList(String category, String[] data) {
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();   //when ever dataList already content data then first clear

        for (int i = 0; i < list.size(); i++) {
            dataList.add(new Items(list.get(i), category, false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData() {
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();
        listOfAllItems.add(getPersonalCareData());
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getBabyNeedsData());
        listOfAllItems.add(getHealthData());
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSuppliesData());
        listOfAllItems.add(getNeedsData());

        return listOfAllItems;
    }

    public void persistAllData() {
        List<List<Items>> listOfAllItems = getAllData();
        for (List<Items> list : listOfAllItems) {
            for (Items items : list) {
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data Added");
    }

    public void persistDataByCategory(String category, boolean onlyDelete) {
        try {
            List<Items> list = deleteAndGetListByCategory(category, onlyDelete);
            if (!onlyDelete) {
                //reset to default

                for (Items item : list) {
                    database.mainDao().saveItem(item);
                }
                Toast.makeText(context, category + " Reset Successfully.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, category + " Reset Successfully.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Items> deleteAndGetListByCategory(String category, Boolean onlyDelete) {
        if (onlyDelete) {
            database.mainDao().deleteAllBycategoryAndAddedBy(category, MyConstants.SYSTEM_SMALL);  //delete all data add by system by default
        } else {
            database.mainDao().deleteAllByCategory(category);  //It is delete those data that is enter by user
        }
        switch (category) {
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();

            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();

            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();

            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();

            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();

            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();

            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();

            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();

            case MyConstants.CAR_SUPPLIES_FOOD_CAMEL_CASE:
                return getCarSuppliesData();

            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();
        }
    }
}
