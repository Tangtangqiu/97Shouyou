package com.alibaba.tangtang.a97shouyou.module.home.bean;

import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/7/1.
 */
public class Home_Banner{

    /**
     * info : [{"notice_id":"25753","content":"玩家【q39**】兑换了骏网10元游戏充值卡。"},{"notice_id":"25760","content":"第53011期欢乐竞猜已经揭晓啦！大奖花落谁家，赶紧去看看吧！"}]
     * page : null
     * state : success
     */

    private Object page;
    private String state;
    /**
     * notice_id : 25753
     * content : 玩家【q39**】兑换了骏网10元游戏充值卡。
     */

    private List<InfoBean> info;

    public Object getPage(){
        return page;
    }

    public void setPage(Object page){
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

    public static class InfoBean{
        private String notice_id;
        private String content;

        public String getNotice_id(){
            return notice_id;
        }

        public void setNotice_id(String notice_id){
            this.notice_id = notice_id;
        }

        public String getContent(){
            return content;
        }

        public void setContent(String content){
            this.content = content;
        }
    }
}
