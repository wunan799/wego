/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MpNewsMsg implements Serializable {
    private String touser;
    private String toparty;
    private String totag;
    private String msgtype = "mpnews";
    private int agentid;
    private Mpnews mpnews = new Mpnews();
    private int safe = 0;

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTouser() {
        return this.touser;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getToparty() {
        return this.toparty;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getTotag() {
        return this.totag;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgtype() {
        return this.msgtype;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public int getAgentid() {
        return this.agentid;
    }

    public void setMpnews(Mpnews mpnews) {
        this.mpnews = mpnews;
    }

    public Mpnews getMpnews() {
        return this.mpnews;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public int getSafe() {
        return this.safe;
    }

    public static class Mpnews implements Serializable {
        private List<Articles> articles = new ArrayList<>();

        public void setArticles(List<Articles> articles) {
            this.articles = articles;
        }

        public List<Articles> getArticles() {
            return this.articles;
        }

        public void addArticles(Articles articles) {
            this.articles.add(articles);
        }
    }

    public static class Articles implements Serializable {
        private String title;
        private String thumb_media_id;
        private String author;
        private String content_source_url;
        private String content;
        private String digest;
        private String show_cover_pic;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
        }

        public String getThumb_media_id() {
            return this.thumb_media_id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setContent_source_url(String content_source_url) {
            this.content_source_url = content_source_url;
        }

        public String getContent_source_url() {
            return this.content_source_url;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDigest() {
            return this.digest;
        }

        public void setShow_cover_pic(String show_cover_pic) {
            this.show_cover_pic = show_cover_pic;
        }

        public String getShow_cover_pic() {
            return this.show_cover_pic;
        }
    }
}
