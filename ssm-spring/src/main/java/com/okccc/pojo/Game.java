package com.okccc.pojo;

/**
 * @Author: okccc
 * @Date: 2023/10/2 17:09:52
 * @Desc:
 */
public class Game {

    private Integer id;

    private String name;

    public Game() {
    }

    public Game(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
