package com.loftblog.loftmoney.internet;

import java.util.List;

public class GetItemsResponse {

    private String status;
    private List<ItemRemote> data;

    public String getStatus() {
        return status;
    }

    public List<ItemRemote> getData() {
        return data;
    }
}
