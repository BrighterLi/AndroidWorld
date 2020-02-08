package com.xiaoming.widgetaddressselector;

import java.util.List;

public class AddressBean {
    public String provinceName;
    public String provinceId;
    public List<City> citys;


    public static class City {
        public String cityName;
        public String cityId;
        public List<Area> areas;

        public static class Area {
            public String areaName;
            public String id;
        }
    }
}
