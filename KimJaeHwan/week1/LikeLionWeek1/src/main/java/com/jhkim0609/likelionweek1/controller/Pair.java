package com.jhkim0609.likelionweek1.controller;

public class Pair<A, B> {
    private A first;
    private B second;
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
    public A getFirst() {
        return first;
    }
    public B getSecond() {
        return second;
    }
    public void of(A first, B second) {
        this.first = first;
        this.second = second;
    }
    public void setFirst(A first) {
        this.first = first;
    }
    public void setSecond(B second) {
        this.second = second;
    }
}
