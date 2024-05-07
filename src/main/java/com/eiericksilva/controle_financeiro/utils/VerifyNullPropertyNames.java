package com.eiericksilva.controle_financeiro.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class VerifyNullPropertyNames {
    private VerifyNullPropertyNames() {
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper sourceBean = new BeanWrapperImpl(source);

        PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor propertyDescriptorsItem : propertyDescriptors) {
            Object srcValue = sourceBean.getPropertyValue(propertyDescriptorsItem.getName());

            if (srcValue == null) {
                emptyNames.add(propertyDescriptorsItem.getName());
            }
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }
}
