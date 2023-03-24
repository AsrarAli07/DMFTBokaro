
package com.bokaro.utils;

/**
 * Created by Asrar Ali on 21/07/2022.
 */

public abstract class ListObject {
        public static final int TYPE_DATE = 0;
        public static final int TYPE_MESSAGE = 1;

        abstract public int getType(int userId);
    }