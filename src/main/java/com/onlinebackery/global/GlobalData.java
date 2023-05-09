package com.onlinebackery.global;

import com.onlinebackery.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<>();

    }
}
