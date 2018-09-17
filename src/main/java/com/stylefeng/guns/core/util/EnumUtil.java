package com.stylefeng.guns.core.util;

import com.stylefeng.guns.common.constant.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;

    }

}
