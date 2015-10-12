package me.zsr.talkcheapshowcode;

import java.util.List;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 9/25/15
 */
public class DemoCatogery {
    public String id;
    public String name;
    public List<Demo> demos;

    public class Demo {
        public String id;
        public String name;
        public String thumbnail;
        public String link;
    }

    @Override
    public String toString() {
        return "DemoCatogery{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", demos=" + demos +
                '}';
    }
}
