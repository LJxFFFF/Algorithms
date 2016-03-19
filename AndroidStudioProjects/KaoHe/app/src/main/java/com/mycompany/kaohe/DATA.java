package com.mycompany.kaohe;

import java.util.List;

/**
 * Created by Ljx on 2015/11/27.
 */
public class DATA {
        public String content;
        public String time;
        public String title;
        public String url;


        public void setContent(String content) {
            this.content = content;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
        return url;
    }

        public String getContent() {
            return content;
        }

        public String getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }
        @Override
        public String toString(){
            return "标题：" + this.title + "\n" + "时间：" +  this.time + "\n" + "网址："+  this.url + "\n" +"正文：" + this.content+ "\n"+ "\n" + "\n";
        }
}
