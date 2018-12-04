package com.example.user.mathquiz;

public class Highscore {

    private long id;
    private String name;
    private int score;

    public Highscore(){}

    public Highscore(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
