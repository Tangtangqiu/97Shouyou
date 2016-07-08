package com.alibaba.tangtang.a97shouyou.module.money.bean;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

@Table(name = "tab_gameInfo")
public class InfoBean{
        @Id
        private int index;
        private String id;
        @Unique
        private String name;
        private String score;
        private String icon;
        private String size;
        private String dl_back_jifen;
        private String limit_number;
        private String dl_num;
        private String android_dl;
        private String ios_dl;
        private String count_dl;
        private int all_prize;
        private int game_statue;

        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id = id;
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getScore(){
            return score;
        }

        public void setScore(String score){
            this.score = score;
        }

        public String getIcon(){
            return icon;
        }

        public void setIcon(String icon){
            this.icon = icon;
        }

        public String getSize(){
            return size;
        }

        public void setSize(String size){
            this.size = size;
        }

        public String getDl_back_jifen(){
            return dl_back_jifen;
        }

        public void setDl_back_jifen(String dl_back_jifen){
            this.dl_back_jifen = dl_back_jifen;
        }

        public String getLimit_number(){
            return limit_number;
        }

        public void setLimit_number(String limit_number){
            this.limit_number = limit_number;
        }

        public String getDl_num(){
            return dl_num;
        }

        public void setDl_num(String dl_num){
            this.dl_num = dl_num;
        }

        public String getAndroid_dl(){
            return android_dl;
        }

        public void setAndroid_dl(String android_dl){
            this.android_dl = android_dl;
        }

        public String getIos_dl(){
            return ios_dl;
        }

        public void setIos_dl(String ios_dl){
            this.ios_dl = ios_dl;
        }

        public String getCount_dl(){
            return count_dl;
        }

        public void setCount_dl(String count_dl){
            this.count_dl = count_dl;
        }

        public int getAll_prize(){
            return all_prize;
        }

        public void setAll_prize(int all_prize){
            this.all_prize = all_prize;
        }

        public int getGame_statue(){
            return game_statue;
        }

        public void setGame_statue(int game_statue){
            this.game_statue = game_statue;
        }

    @Override
    public String toString(){
        return "InfoBean{" +
                "index=" + index +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", icon='" + icon + '\'' +
                ", size='" + size + '\'' +
                ", dl_back_jifen='" + dl_back_jifen + '\'' +
                ", limit_number='" + limit_number + '\'' +
                ", dl_num='" + dl_num + '\'' +
                ", android_dl='" + android_dl + '\'' +
                ", ios_dl='" + ios_dl + '\'' +
                ", count_dl='" + count_dl + '\'' +
                ", all_prize=" + all_prize +
                ", game_statue=" + game_statue +
                '}';
    }
}