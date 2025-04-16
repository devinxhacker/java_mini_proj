package main.api;

import java.util.Date;

public class ApiSchema {

	// transactions
    public static class TransactionApiResponse {
        
    	boolean success;
        TransactionData[] data;
    }

    public static class TransactionData {
        
    	int id, itemId, quantity;
        String itemName, categoryName, type;
        Date date;
    }

    // warehouse
    public static class WarehouseApiResponse {
        
    	boolean success;
        CategoryData[] data;
    }

    public static class CategoryData {
    	
        int id, maxCapacity, currentCapacity, availableSpace, utilizationPercentage;
        String name;
        ItemData[] items;
    }

    public static class ItemData {
        
    	int id, maxQuantity, currentQuantity, availableSpace;
        String name;
    }
    
    // send/receive
    public static class ItemsApiResponse {
    
    	boolean success;
    	ItemsData[] data;
    }
    
    public static class ItemsData {
    	
    	int id, categoryId, maxQuantity, currentQuantity, availableSpace;
    	String name, categoryName;
    }

    public static class SendReceivePayload {
    	
        int itemId, quantity;
    }

    public static class PostResponse {
        
        boolean success;
        String message;
    }

} 