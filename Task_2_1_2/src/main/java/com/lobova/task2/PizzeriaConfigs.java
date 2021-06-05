package com.lobova.task2;

import com.google.gson.annotations.SerializedName;

/**
 * Fields to know how many bakers and delivery men to be hired.
 */
class PizzeriaConfigs {
    @SerializedName("numBakers")
    public int numBakers;

    @SerializedName("numDeliveryMen")
    public int numDeliveryMen;
}
