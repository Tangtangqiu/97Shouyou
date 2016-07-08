package com.alibaba.tangtang.a97shouyou.module.money.bean;

import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/30.
 */
public class Search_Game{

    /**
     * total : 8
     * pagesize : 10
     * page : 1
     * page_total : 1
     */

    private PageBean page;
    /**
     * info : [{"id":"103","name":"花千骨","score":"6.0","icon":"http://i3.72g.com/upload/201509/201509241111055081.png","size":"183.88MB","dl_back_jifen":"100","limit_number":"0","dl_num":"2418","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=3002551","ios_dl":"1010053124","count_dl":"12159","all_prize":100,"game_statue":0},{"id":"44","name":"明珠三国","score":"6.0","icon":"http://i3.72g.com/upload/201506/201506111614299880.png","size":"7.69MB","dl_back_jifen":"100","limit_number":"0","dl_num":"2032","android_dl":"http://down.72g.com/new/mzsg.apk","ios_dl":"623188804","count_dl":"7666","all_prize":100,"game_statue":0},{"id":"41","name":"黑夜传说之狼人归来","score":"6.0","icon":"http://i3.72g.com/upload/201506/201506101714171978.png","size":"196.38MB","dl_back_jifen":"100","limit_number":"0","dl_num":"911","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=2939432","ios_dl":"971953583","count_dl":"5552","all_prize":100,"game_statue":0},{"id":"15","name":"攻城掠地","score":"6.0","icon":"http://i3.72g.com/upload/201505/201505191553209168.png","size":"72.45MB","dl_back_jifen":"100","limit_number":"0","dl_num":"2810","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=1033015","ios_dl":"726652663","count_dl":"12615","all_prize":100,"game_statue":0},{"id":"10","name":"大主宰","score":"7","icon":"http://i3.72g.com/upload/201505/201505111427303666.jpg","size":"139MB","dl_back_jifen":"100","limit_number":"0","dl_num":"1145","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=2367898","ios_dl":"927850587","count_dl":"6601","all_prize":100,"game_statue":0},{"id":"9","name":"大唐双龙传","score":"7","icon":"http://i3.72g.com/upload/201505/201505111414368517.jpg","size":"194MB","dl_back_jifen":"100","limit_number":"0","dl_num":"782","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=2626641","ios_dl":"998923326","count_dl":"7276","all_prize":100,"game_statue":0},{"id":"4","name":"太极熊猫","score":"8.5","icon":"http://i3.72g.com/upload/201504/201504101715152690.jpg","size":"167M","dl_back_jifen":"100","limit_number":"0","dl_num":"1468","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=2195291","ios_dl":"895691221","count_dl":"5624","all_prize":100,"game_statue":0},{"id":"2","name":"风暴纪元","score":"6.0","icon":"http://i3.72g.com/upload/201504/201504101700234059.jpg","size":"123M","dl_back_jifen":"100","limit_number":"0","dl_num":"1230","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=2307779","ios_dl":"909288339","count_dl":"1920","all_prize":100,"game_statue":0}]
     * page : {"total":8,"pagesize":10,"page":1,"page_total":1}
     * state : success
     */

    private String state;
    /**
     * id : 103
     * name : 花千骨
     * score : 6.0
     * icon : http://i3.72g.com/upload/201509/201509241111055081.png
     * size : 183.88MB
     * dl_back_jifen : 100
     * limit_number : 0
     * dl_num : 2418
     * android_dl : http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=3002551
     * ios_dl : 1010053124
     * count_dl : 12159
     * all_prize : 100
     * game_statue : 0
     */

    private List<InfoBean> info;

    public PageBean getPage(){
        return page;
    }

    public void setPage(PageBean page){
        this.page = page;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public List<InfoBean> getInfo(){
        return info;
    }

    public void setInfo(List<InfoBean> info){
        this.info = info;
    }

    public static class PageBean{
        private int total;
        private int pagesize;
        private int page;
        private int page_total;

        public int getTotal(){
            return total;
        }

        public void setTotal(int total){
            this.total = total;
        }

        public int getPagesize(){
            return pagesize;
        }

        public void setPagesize(int pagesize){
            this.pagesize = pagesize;
        }

        public int getPage(){
            return page;
        }

        public void setPage(int page){
            this.page = page;
        }

        public int getPage_total(){
            return page_total;
        }

        public void setPage_total(int page_total){
            this.page_total = page_total;
        }
    }
}
