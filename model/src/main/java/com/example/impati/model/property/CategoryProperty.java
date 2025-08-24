package com.example.impati.model.property;

import java.util.List;

public record CategoryProperty(
        List<String> shopCategories
) implements Property {

}
