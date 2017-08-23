package org.cz.online.entity;

public class Teamer {
    private String name;
    private int doing;
    private int done;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoing() {
        return doing;
    }

    public void setDoing(int doing) {
        this.doing = doing;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public synchronized Teamer addDoing(){
        this.doing+=1;
        return this;
    }

    public synchronized Teamer addDone(){
        if(this.doing==0){
            throw new RuntimeException("没有正在进行的工作");
        }
        this.doing-=1;
        this.done+=1;
        return this;
    }
}
