package com.aboutblank.popular_movies;

public abstract class UseCase<T extends UseCase.RequestValue, R extends UseCase.ResponseValue> {
    private T requestValue;
    private CallBack<R> callBack;

    public T getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(T requestValue) {
        this.requestValue = requestValue;
    }

    public CallBack<R> getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack<R> callBack) {
        this.callBack = callBack;
    }

    public abstract void execute(T requestValue);

    public void run() {
        execute(requestValue);
    }

    public interface RequestValue {

    }

    public interface ResponseValue {
    }

    public interface CallBack<R> {
        void onSuccess(R response);
        void onError(String error);
    }
}
