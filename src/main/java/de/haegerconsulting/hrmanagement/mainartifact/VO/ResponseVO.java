package de.haegerconsulting.hrmanagement.mainartifact.VO;


public class ResponseVO<T> {
    private T results;

    public ResponseVO(T results) {
        this.results = results;
    }

    public T getResults() {
        return this.results;
    }
}