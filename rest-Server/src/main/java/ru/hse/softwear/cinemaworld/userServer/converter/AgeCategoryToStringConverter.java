package ru.hse.softwear.cinemaworld.userServer.converter;

import org.springframework.core.convert.converter.Converter;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;

public class AgeCategoryToStringConverter implements Converter<AgeCategories, String> {
    @Override
    public String convert(AgeCategories source) {
        return source.getValue();
    }
}
