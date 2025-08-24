package com.example.impati.model.client;

import java.util.List;

public interface ShopClient {

    String getFranchise(String shopNumber);

    List<String> getCategories(String shopNumber);
}
