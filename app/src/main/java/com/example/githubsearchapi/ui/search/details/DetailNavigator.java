package com.example.githubsearchapi.ui.search.details;

public interface DetailNavigator {

    void handleError(Throwable throwable);

    void showMessage(Integer resourceId);

    void openWebView();
}

