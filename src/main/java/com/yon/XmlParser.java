package com.yon;

import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static class XmlNode {
        public XmlNode(String value) {
            this.value = value;
            this.isLeaf = true;
        }

        public XmlNode(String name, List<XmlNode> children) {
            this.name = name;
            this.children = children;
            this.isLeaf = false;
        }

        private String name;
        private String value;
        private boolean isLeaf;
        private List<XmlNode> children;

        @Override
        public String toString() {
            if (isLeaf) {
                return value;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("<").append(name).append(">");
            for (XmlNode child : children) {
                builder.append(child.toString());
            }
            builder.append("</").append(name).append(">");
            return builder.toString();
        }
    }

    public XmlNode parse(String src) {
        if (!src.startsWith("<")) {
            return new XmlNode(src);
        }
        String name = src.substring(1, src.indexOf('>'));
        String childStr = src.substring(name.length() + 2, src.length() - name.length() - 3);
        List<XmlNode> childs = new ArrayList<>();

        if (!childStr.startsWith("<")) {
            childs.add(parse(childStr));
        } else {
            while (!childStr.isEmpty()) {
                String firstNodeName = childStr.substring(1, src.indexOf('>'));
                int splitIndex = childStr.indexOf("/" + firstNodeName) + firstNodeName.length() + 2;
                childs.add(parse(childStr.substring(0, splitIndex)));
                childStr = childStr.substring(splitIndex);
            }
        }

        return new XmlNode(name, childs);
    }

}
