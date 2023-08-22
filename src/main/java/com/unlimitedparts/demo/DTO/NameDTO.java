package com.unlimitedparts.demo.DTO;

public class NameDTO {

    private String brandAndName;
    private Long size;

    public NameDTO(){

    }

    public String getBrandAndName() {
        return brandAndName;
    }

    public void setBrandAndName(String brandAndName) {
        this.brandAndName = brandAndName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
