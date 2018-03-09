package com.aboutblank.popular_movies;

public class UseCaseExecutor {
    private static UseCaseExecutor instance;

    public static UseCaseExecutor getInstance() {
        if(instance == null) {
            instance = new UseCaseExecutor();
        }

        return instance;
    }

    public <T extends UseCase.RequestValue, R extends UseCase.ResponseValue> void execute(
            final UseCase<T, R> useCase,
            T requestValue,
            UseCase.CallBack<R> callback) {
        useCase.setRequestValue(requestValue);
        useCase.setCallBack(callback);

        useCase.run();
    }
}
