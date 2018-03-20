package com.multipz.maindemo;


/**
 * Created by Admin on 06-02-2018.
 */

public class StatusModel {

    public StatusModel(String statusId, String descriptions) {
        StatusId = statusId;
        Descriptions = descriptions;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String statusId) {
        StatusId = statusId;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public String getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(String category_Id) {
        Category_Id = category_Id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    String StatusId, Descriptions, Category_Id, Category;

}
