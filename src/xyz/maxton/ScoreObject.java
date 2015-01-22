package xyz.maxton;

public class ScoreObject {
    public String name;
    public int timestamp;
    public long score;
    @Override
    public String toString(){
        return name+" "+timestamp+" "+score;
    }
}
