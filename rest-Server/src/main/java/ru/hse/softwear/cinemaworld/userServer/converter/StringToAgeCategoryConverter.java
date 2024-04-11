package ru.hse.softwear.cinemaworld.userServer.converter;

import org.springframework.core.convert.converter.Converter;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;

public class StringToAgeCategoryConverter implements Converter<String, AgeCategories> {
    @Override
    public AgeCategories convert(String source) {
        for(AgeCategories category : AgeCategories.values()) {
            if(category.getValue().equals(source)) {
                return category;
            }
        }

        throw new IllegalArgumentException("Unknown enum value: " + source);
    }
}
